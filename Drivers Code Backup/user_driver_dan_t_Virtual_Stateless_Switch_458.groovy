metadata {
    definition (name: "Virtual Stateless Switch", namespace: "dan.t", author: "Daniel Terryn") {
        capability "Switch"
    }   
}

def on() {
    sendEvent(name: "switch", value: "on", isStateChange: true)
}

def off() {
    sendEvent(name: "switch", value: "off", isStateChange: true)
}

def installed() {
}