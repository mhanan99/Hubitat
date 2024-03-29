metadata {
    definition (name: "Virtual Stateless Dimmer", namespace: "dan.t", author: "Daniel Terryn") {
        capability "Switch"
        capability "Switch Level"
    }   
}

def on() {
    sendEvent(name: "switch", value: "on", isStateChange: true)
}

def off() {
    sendEvent(name: "switch", value: "off", isStateChange: true)
}


def setLevel(value, duration = 1) {
    sendEvent(name: "level", value: value, isStateChange: true)
    if (value == 0)
        off()
    else if (device.currentValue("switch") == "off")
        on()
}

def installed() {
}