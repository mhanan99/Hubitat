/**
 *  Z-Wave Garage Door Opener
 *
 *  Copyright 2014 SmartThings
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
metadata {
	definition (name: "My Z-Wave Garage Door Opener", namespace: "smartthings", author: "SmartThings") {
		capability "Actuator"
		capability "Door Control"
		capability "Contact Sensor"
		capability "Refresh"
		capability "Sensor"
        capability "Polling"
  		capability "Switch" 
		capability "Momentary"
		capability "Relay Switch"


		fingerprint deviceId: "0x4007", inClusters: "0x98"
		fingerprint deviceId: "0x4006", inClusters: "0x98"
	}

	simulator {
		status "closed": "command: 9881, payload: 00 66 03 00"
		status "opening": "command: 9881, payload: 00 66 03 FE"
		status "open": "command: 9881, payload: 00 66 03 FF"
		status "closing": "command: 9881, payload: 00 66 03 FC"
		status "unknown": "command: 9881, payload: 00 66 03 FD"

		reply "988100660100": "command: 9881, payload: 00 66 03 FC"
		reply "9881006601FF": "command: 9881, payload: 00 66 03 FE"
	}

	tiles {
		standardTile("toggle", "device.door", width: 2, height: 2) {
			state("unknown", label:'${name}', action:"refresh.refresh", icon:"st.doors.garage.garage-open", backgroundColor:"#ffa81e")
			state("closed", label:'${name}', action:"door control.open", icon:"st.doors.garage.garage-closed", backgroundColor:"#79b821", nextState:"opening")
			state("open", label:'${name}', action:"door control.close", icon:"st.doors.garage.garage-open", backgroundColor:"#ffa81e", nextState:"closing")
			state("opening", label:'${name}', icon:"st.doors.garage.garage-opening", backgroundColor:"#ffe71e")
			state("closing", label:'${name}', icon:"st.doors.garage.garage-closing", backgroundColor:"#ffe71e")
			
		}
		standardTile("open", "device.door", inactiveLabel: false, decoration: "flat") {
			state "default", label:'open', action:"door control.open", icon:"st.doors.garage.garage-opening"
		}
		standardTile("close", "device.door", inactiveLabel: false, decoration: "flat") {
			state "default", label:'close', action:"door control.close", icon:"st.doors.garage.garage-closing"
		}
		standardTile("refresh", "device.door", inactiveLabel: false, decoration: "flat") {
			state "default", label:'', action:"refresh.refresh", icon:"st.secondary.refresh"
		}
		main "toggle"
		details(["toggle", "open", "close", "refresh"])
	}
}


import hubitat.zwave.commands.barrieroperatorv1.*

def parse(String description) {
	def result = null
	if (description.startsWith("Err")) {
		if (state.sec) {
			result = createEvent(descriptionText:description, displayed:false)
		} else {
			result = createEvent(
				descriptionText: "This device failed to complete the network security key exchange. If you are unable to control it via SmartThings, you must remove it from your network and add it again.",
				eventType: "ALERT",
				name: "secureInclusion",
				value: "failed",
				displayed: true,
			)
		}
	} else {
		def cmd = zwave.parse(description, [ 0x98: 1, 0x72: 2 ])
		if (cmd) {
			result = zwaveEvent(cmd)
		}
	}
	log.debug "\"$description\" parsed to ${result.inspect()}"
	result
}

def zwaveEvent(hubitat.zwave.commands.securityv1.SecurityMessageEncapsulation cmd) {
	def encapsulatedCommand = cmd.encapsulatedCommand([0x71: 3, 0x80: 1, 0x85: 2, 0x63: 1, 0x98: 1])
	log.debug "encapsulated: $encapsulatedCommand"
	if (encapsulatedCommand) {
		zwaveEvent(encapsulatedCommand)
	}
}

def zwaveEvent(hubitat.zwave.commands.securityv1.NetworkKeyVerify cmd) {
	createEvent(name:"secureInclusion", value:"success", descriptionText:"Secure inclusion was successful")
}

def zwaveEvent(hubitat.zwave.commands.securityv1.SecurityCommandsSupportedReport cmd) {
	state.sec = cmd.commandClassSupport.collect { String.format("%02X ", it) }.join()
	if (cmd.commandClassControl) {
		state.secCon = cmd.commandClassControl.collect { String.format("%02X ", it) }.join()
	}
	log.debug "Security command classes: $state.sec"
	createEvent(name:"secureInclusion", value:"success", descriptionText:"$device.displayText is securely included")
}

def zwaveEvent(hubitat.zwave.commands.barrieroperatorv1.BarrierOperatorReport cmd) {
	def result = []
	def map = [ name: "door" ]
    def switchMap = [ name: "switch" ]
    
	switch (cmd.barrierState) {
		case BarrierOperatorReport.BARRIER_STATE_CLOSED:
			map.value = "closed"
			result << createEvent(name: "contact", value: "closed", displayed: false)
            result << createEvent(name: "switch", value: "off", displayed: false)
			break
		case BarrierOperatorReport.BARRIER_STATE_UNKNOWN_POSITION_MOVING_TO_CLOSE:
			map.value = "closing"
			break
		case BarrierOperatorReport.BARRIER_STATE_UNKNOWN_POSITION_STOPPED:
			map.descriptionText = "$device.displayName door state is unknown"
			map.value = "unknown"
			break
		case BarrierOperatorReport.BARRIER_STATE_UNKNOWN_POSITION_MOVING_TO_OPEN:
			map.value = "opening"
			result << createEvent(name: "contact", value: "open", displayed: false)
			break
		case BarrierOperatorReport.BARRIER_STATE_OPEN:
			map.value = "open"
			result << createEvent(name: "contact", value: "open", displayed: false)
            result << createEvent(name: "switch", value: "on", displayed: false)
			break
	}
	result + createEvent(map)
}

def zwaveEvent(hubitat.zwave.commands.notificationv3.NotificationReport cmd) {
	def result = []
	def map = [:]
	if (cmd.notificationType == 6) {
		map.displayed = true
		switch(cmd.event) {
			case 0x40:
				if (cmd.eventParameter[0]) {
					map.descriptionText = "$device.displayName performing initialization process"
				} else {
					map.descriptionText = "$device.displayName initialization process complete"
				}
				break
			case 0x41:
				map.descriptionText = "$device.displayName door operation force has been exceeded"
				break
			case 0x42:
				map.descriptionText = "$device.displayName motor has exceeded operational time limit"
				break
			case 0x43:
				map.descriptionText = "$device.displayName has exceeded physical mechanical limits"
				break
			case 0x44:
				map.descriptionText = "$device.displayName unable to perform requested operation (UL requirement)"
				break
			case 0x45:
				map.descriptionText = "$device.displayName remote operation disabled (UL requirement)"
				break
			case 0x46:
				map.descriptionText = "$device.displayName failed to perform operation due to device malfunction"
				break
			case 0x47:
				if (cmd.eventParameter[0]) {
					map.descriptionText = "$device.displayName vacation mode enabled"
				} else {
					map.descriptionText = "$device.displayName vacation mode disabled"
				}
				break
			case 0x48:
				if (cmd.eventParameter[0]) {
					map.descriptionText = "$device.displayName safety beam obstructed"
				} else {
					map.descriptionText = "$device.displayName safety beam obstruction cleared"
				}
				break
			case 0x49:
				if (cmd.eventParameter[0]) {
					map.descriptionText = "$device.displayName door sensor ${cmd.eventParameter[0]} not detected"
				} else {
					map.descriptionText = "$device.displayName door sensor not detected"
				}
				break
			case 0x4A:
				if (cmd.eventParameter[0]) {
					map.descriptionText = "$device.displayName door sensor ${cmd.eventParameter[0]} has a low battery"
				} else {
					map.descriptionText = "$device.displayName door sensor has a low battery"
				}
				result << createEvent(name: "battery", value: 1, unit: "%", descriptionText: map.descriptionText)
				break
			case 0x4B:
				map.descriptionText = "$device.displayName detected a short in wall station wires"
				break
			case 0x4C:
				map.descriptionText = "$device.displayName is associated with non-Z-Wave remote control"
				break
			default:
				map.descriptionText = "$device.displayName: access control alarm $cmd.event"
				map.displayed = false
				break
		}
	} else if (cmd.notificationType == 7) {
		switch (cmd.event) {
			case 1:
			case 2:
				map.descriptionText = "$device.displayName detected intrusion"
				break
			case 3:
				map.descriptionText = "$device.displayName tampering detected: product cover removed"
				break
			case 4:
				map.descriptionText = "$device.displayName tampering detected: incorrect code"
				break
			case 7:
			case 8:
				map.descriptionText = "$device.displayName detected motion"
				break
			default:
				map.descriptionText = "$device.displayName: security alarm $cmd.event"
				map.displayed = false
		}
	} else if (cmd.notificationType){
		map.descriptionText = "$device.displayName: alarm type $cmd.notificationType event $cmd.event"
	} else {
		map.descriptionText = "$device.displayName: alarm $cmd.v1AlarmType is ${cmd.v1AlarmLevel == 255 ? 'active' : cmd.v1AlarmLevel ?: 'inactive'}"
	}
	result ? [createEvent(map), *result] : createEvent(map)
}

def zwaveEvent(hubitat.zwave.commands.batteryv1.BatteryReport cmd) {
	def map = [ name: "battery", unit: "%" ]
	if (cmd.batteryLevel == 0xFF) {
		map.value = 1
		map.descriptionText = "$device.displayName has a low battery"
	} else {
		map.value = cmd.batteryLevel
	}
	state.lastbatt = new Date().time
	createEvent(map)
}

def zwaveEvent(hubitat.zwave.commands.manufacturerspecificv2.ManufacturerSpecificReport cmd) {
	def result = []

	def msr = String.format("%04X-%04X-%04X", cmd.manufacturerId, cmd.productTypeId, cmd.productId)
	log.debug "msr: $msr"
	updateDataValue("MSR", msr)

	result << createEvent(descriptionText: "$device.displayName MSR: $msr", isStateChange: false)
	result
}

def zwaveEvent(hubitat.zwave.commands.versionv1.VersionReport cmd) {
	def fw = "${cmd.applicationVersion}.${cmd.applicationSubVersion}"
	updateDataValue("fw", fw)
	def text = "$device.displayName: firmware version: $fw, Z-Wave version: ${cmd.zWaveProtocolVersion}.${cmd.zWaveProtocolSubVersion}"
	createEvent(descriptionText: text, isStateChange: false)
}

def zwaveEvent(hubitat.zwave.commands.applicationstatusv1.ApplicationBusy cmd) {
	def msg = cmd.status == 0 ? "try again later" :
	          cmd.status == 1 ? "try again in $cmd.waitTime seconds" :
	          cmd.status == 2 ? "request queued" : "sorry"
	createEvent(displayed: true, descriptionText: "$device.displayName is busy, $msg")
}

def zwaveEvent(hubitat.zwave.commands.applicationstatusv1.ApplicationRejectedRequest cmd) {
	createEvent(displayed: true, descriptionText: "$device.displayName rejected the last request")
}

def zwaveEvent(hubitat.zwave.Command cmd) {
	createEvent(displayed: false, descriptionText: "$device.displayName: $cmd")
}

def open() {
	secure(zwave.barrierOperatorV1.barrierOperatorSet(requestedBarrierState: BarrierOperatorSet.REQUESTED_BARRIER_STATE_OPEN))
}

def close() {
	secure(zwave.barrierOperatorV1.barrierOperatorSet(requestedBarrierState: BarrierOperatorSet.REQUESTED_BARRIER_STATE_CLOSE))
}


def refresh() {
	secure(zwave.barrierOperatorV1.barrierOperatorGet())
}

def poll() {
	secure(zwave.barrierOperatorV1.barrierOperatorGet())
}


def on() {
	log.debug "on() was called and ignored"
}

def off() {
	log.debug "off() was called and ignored"
}

def push() {
    
    // get the current "door" attribute value
    //
    // For some reason, I can't use "device.doorState" or just "doorState".  Not sure why not.
    
    def lastValue = device.latestValue("door");
    
    // if its open, then close the door
    if (lastValue == "open") {
        return close()
        
    // if its closed, then open the door
    } else if (lastValue == "closed") {
        return open()
        
    } else {
    	log.debug "push() called when door state is $lastValue - there's nothing push() can do"
    }
    
}



private secure(hubitat.zwave.Command cmd) {
	zwave.securityV1.securityMessageEncapsulation().encapsulate(cmd).format()
}

private secureSequence(commands, delay=200) {
	delayBetween(commands.collect{ secure(it) }, delay)
}