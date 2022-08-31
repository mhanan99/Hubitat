/*
 *  webCoRE - Community's own Rule Engine - Web Edition for HE
 *
 *  Copyright 2016 Adrian Caramaliu <ady624("at" sign goes here)gmail.com>
 *
 *  webCoRE (MAIN APP)
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Last update August 22, 2022 for Hubitat
 */

//file:noinspection unused
//file:noinspection GroovyUnusedAssignment
//file:noinspection GroovySillyAssignment
//file:noinspection GrDeprecatedAPIUsage
//file:noinspection GroovyPointlessBoolean
//file:noinspection SpellCheckingInspection
//file:noinspection GrMethodMayBeStatic

@Field static final String sVER='v0.3.114.20220203'
@Field static final String sHVER='v0.3.114.20220822_HE'

static String version(){ return sVER }
static String HEversion(){ return sHVER }

/*** webCoRE DEFINITION	***/

@Field static final String sWC='webCoRE'
@Field static final String sWCD='webcore.co'


private static String handle(){ return sWC }
private static String domain(){ return sWCD }

@Field static final String sPISTN=' Piston'
@Field static final String sWEAT=' Weather'
@Field static final String sSTOR=' Storage'
@Field static final String sFUELS=' Fuel Stream'
@Field static final String sPRES=' Presence Sensor'
private static String handlePistn(){ return sWC+sPISTN }
private static String handleWeat(){ return sWC+sWEAT }
private static String handleStor(){ return sWC+sSTOR }
private static String handleFuelS(){ return sWC+sFUELS }
private static String handlePres(){ return sWC+sPRES }

definition(
	name: handle(),
	namespace: "ady624",
	author: "Adrian Caramaliu",
	description: "Tap to install ${handle()} ${sVER}",
	category: "Convenience",
	singleInstance: false,
	documentationLink:'https://wiki.webcore.co',
	/* icons courtesy of @chauger - thank you */
	iconUrl: "https://raw.githubusercontent.com/ady624/webCoRE/master/resources/icons/app-CoRE.png",
	iconX2Url: "https://raw.githubusercontent.com/ady624/webCoRE/master/resources/icons/app-CoRE@2x.png",
	iconX3Url: "https://raw.githubusercontent.com/ady624/webCoRE/master/resources/icons/app-CoRE@3x.png",
	importUrl: "https://raw.githubusercontent.com/imnotbob/webCoRE/hubitat-patches/smartapps/ady624/webcore.src/webcore.groovy"
)

import java.text.SimpleDateFormat
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.transform.CompileStatic
import groovy.transform.Field
import java.security.MessageDigest
import java.util.concurrent.Semaphore

preferences{
	//UI pages
	page((sNM): "pageMain")
	page((sNM): "pageDisclaimer")
	page((sNM): "pageEngineBlock")
	page((sNM): "pageInitializeDashboard")
	page((sNM): "pageFinishInstall")
	page((sNM): "pageSelectDevices")
	page((sNM): "pageFuelStreams")
	page((sNM): "pageSettings")
	page((sNM): "pageGraphs")
	page((sNM): "pageChangePassword")
	page((sNM): "pageClearTokens")
	page((sNM): "pageRebuildCache")
	page((sNM): "pageResetEndpoint")
	page((sNM): "pageCleanups")
	page((sNM): "pageLogCleanups")
	page((sNM): "pageUberCleanups")
	page((sNM): "pageDumpR")
	page((sNM): "pageDumpPR")
	page((sNM): "pageRemove")
	page((sNM): "graphDuplicationPage")
	page((sNM):sPDPC)
}

@CompileStatic
private static Boolean eric(){ return false }
@CompileStatic
private static Boolean graphsOn(){ return true }

//#include ady624.webCoRElib1

/******************************************************************************/
/*** webCoRE CONSTANTS														***/
/******************************************************************************/

@Field static final String sNULL=(String)null
@Field static final String sBLK=''
@Field static final String sSPC=' '
@Field static final String sCOLON=':'
@Field static final String sDIV='/'
@Field static final String sBOOL='bool'
@Field static final String sAPPJAVA="application/javascript;charset=utf-8"
@Field static final String sDATA='data'
@Field static final String sSTS='status'
@Field static final String sERR='error'
@Field static final String sSUCC="ST_SUCCESS"
@Field static final String sERRID="ERR_INVALID_ID"
@Field static final String sERRTOK="ERR_INVALID_TOKEN"
@Field static final String sERROR="ST_ERROR"
@Field static final String sERRCHUNK="ERR_INVALID_CHUNK"
@Field static final String sERRUNK="ERR_UNKNOWN"
@Field static final String sTXT='text'
@Field static final String sAPPJSON='application/json'
@Field static final String sTIT='title'
@Field static final String sDESC='description'
@Field static final String sREQ='required'
@Field static final String sNM='name'
@Field static final String sVAL='value'
@Field static final String sVARIABLE='variable'
@Field static final String sDISARMD='disarmed'
@Field static final String sCANCEL='cancel'
@Field static final String sALLDISARM='allDisarmed'
@Field static final String sCANRULEA='cancelRuleAlerts'
@Field static final String sRGB='rgb'
@Field static final String sT='t'
@Field static final String sH='h'
@Field static final String sI='i'
@Field static final String sC='c'
@Field static final String sS='s'
@Field static final String sL='l'
@Field static final String sM='m'
@Field static final String sN='n'
@Field static final String sA='a'
@Field static final String sD='d'
@Field static final String sP='p'
@Field static final String sG='g'
@Field static final String sR='r'
@Field static final String sV='v'
@Field static final String sO='o'


/******************************************************************************/
/*** CONFIGURATION PAGES													***/
/******************************************************************************/

/******************************************************************************/
/*** COMMON PAGES															***/
/******************************************************************************/
def pageMain(){
	//webCoRE Dashboard initialization
	Boolean success=initializeWebCoREEndpoint()
	if(!(Boolean)state.installed){
		return dynamicPage((sNM): "pageMain", (sTIT): sBLK, install: false, uninstall: false, nextPage: "pageInitializeDashboard"){
			section(){
				paragraph "Welcome to "+handle()
				paragraph "You will be guided through a few installation steps that should only take a minute."
			}
			if(success){
				if(!state.oAuthRequired){
					section('Note'){
						paragraph "If you have previously installed webCoRE and are trying to open it, please go back to Apps in the HE console access webCoRE.\r\n\r\nIf you are trying to install another instance of webCoRE then please continue with the steps.", (sREQ): true
					}
				}
				if(mTZ()){
					section(){
						paragraph "It looks like you are ready to go, please tap Next"
					}
				}else{
					section(){
						paragraph "Your location is not correctly setup."
					}
					pageSectionTimeZoneInstructions()
				}
			}else{
				section(){
					paragraph "We'll start by configuring. You need to setup OAuth in the HE console for the webCoRE App."
				}
				pageSectionInstructions()
				section (){
					paragraph "Once you have finished the steps above, tap Next", (sREQ): true
				}
			}
		}
	}
	//webCoRE main page
	dynamicPage((sNM): "pageMain", (sTIT): sBLK, install: true, uninstall: false){
		if(!(Boolean)settings.agreement){
			pageSectionDisclaimer()
		}else{
			section("Engine block"){
				href "pageEngineBlock", (sTIT): imgTitle("app-CoRE.png", inputTitleStr("Cast iron")), (sDESC): sVER+" HE: "+ sHVER, (sREQ): false, state: "complete"
			}

		}

		section("Dashboard"){
			String mPng="dashboard.png"
			if(!(String)state.endpoint){
				href "pageInitializeDashboard", (sTIT): imgTitle(mPng, inputTitleStr("Dashboard")), (sDESC): "Tap to initialize", (sREQ): false, state: "complete"
			}else{
				//trace "*** DO NOT SHARE THIS LINK WITH ANYONE *** Dashboard URL: ${getDashboardInitUrl()}"
				href sBLK, (sTIT): imgTitle(mPng, inputTitleStr("Dashboard")), style: "external", url: getDashboardInitUrl(), (sDESC): "Tap to open", (sREQ): false
				href sBLK, (sTIT): imgTitle("browser-reg.png", inputTitleStr("Register a browser")), style: "external", url: getDashboardInitUrl(true), (sDESC): "Tap to open", (sREQ): false
			}
		}

		section((sTIT):"Settings"){
			href "pageSettings", (sTIT): imgTitle("settings.png", inputTitleStr("Settings")), (sREQ): false, state: "complete"
		}

		if(graphsOn()){
			section((sTIT):"Graphs"){
				href "pageGraphs", (sTIT): imgTitle("settings.png", inputTitleStr("Graphs")), (sREQ): false, state: "complete"
			}
			clearDuplicationItems()
		}
	}
}

private pageSectionDisclaimer(){
	section('Disclaimer'){
		paragraph "Please read the following information carefully", (sREQ): true
		paragraph "webCoRE is a web-enabled product, which means data travels across the internet. webCoRE is using TLS for encryption of data and NEVER provides real object IDs to any system outside the WebCoRE server. The IDs are hashed into a string of letters and numbers that cannot be 'decoded' back to their original value. These hashed IDs are stored by your browser and can be cleaned up by using the Logout action in the dashboard."
		paragraph "Access to a webCoRE App is done through the browser using a security password provided during the installation of webCoRE. The browser never stores this password and it is only used during the initial registration and authentication of your browser. A security token is generated for each browser and is used for any subsequent communication. This token expires at a preset life length, or when the password is changed, or when the tokens are manually revoked from the webCoRE App's Settings menu."
	}
	section('Server-side features'){
		paragraph "Some features require that a webcore.co server processes your data. Such features include emails (sending emails out, or triggering pistons with emails), inter-location communication for superglobal variables, fuel streams, backup bins."
		paragraph "At no time does the server receive any real IDs of HE objects, the instance security password, nor the instance security token that your browser uses to communicate with the App. The server is therefore unable to access any information that only an authenticated browser can."
	}
	section('Information collected by the server'){
		paragraph "The webcore.co server(s) collect ANONYMIZED hashes of 1) your unique account identifier, 2) your locations, and 3) installed webCoRE instances. It also collects an encrypted version of your app instances' endpoints that allow the server to trigger pistons on emails (if you use that feature), proxy IFTTT requests to your pistons, or provide inter-location communication between your webCoRE instances, as well as data points provided by you when using the Fuel Stream feature. It also allows for automatic browser registration when you use another browser, by providing that browser basic information about your existing instances. You will still need to enter the password to access each of those instances, the server does not have the password, nor the security tokens."
	}
	section('Information NOT collected by the server'){
		paragraph "The webcore.co server(s) do NOT intentionally collect any real object IDs from HE, any names, phone numbers, email addresses, physical location information, addresses, or any other personally identifiable information."
	}
	section('Fuel Streams'){
		paragraph "The information you provide while using the Fuel Stream feature is not encrypted and is not filtered in any way. Please avoid providing personally identifiable information in either the canister name, the fuel stream name, or the data point."
	}
	section('Local webCoRE servers'){
		paragraph "Advanced users may enable a local webcore www server. Less data sharing with external webCoRE servers is done if this is configured/enabled. Some features may not be available if you choose to do this."
	}
	section('Agreement'){
		paragraph "Certain advanced features may not work if you do not agree to the webcore.co servers collecting the anonymized information described above."
		input "agreement", sBOOL, (sTIT): "Allow webcore.co to collect basic, anonymized, non-personally identifiable information", defaultValue: true
	}
}

private pageDisclaimer(){
	dynamicPage((sNM): "pageDisclaimer"){
		pageSectionDisclaimer()
	}
}

private pageSectionInstructions(){
	state.oAuthRequired=true
	section (){
		paragraph "Please follow these steps:", (sREQ): true
		paragraph "1. Go to your HE console and log in", (sREQ): true
		paragraph "2. Click on 'Apps Code' and locate the 'webCoRE' App in the list", (sREQ): true
		paragraph "3. Click the App name", (sREQ): true
		paragraph "4. Click on 'OAuth'", (sREQ): true
		paragraph "5. Click the 'Enable OAuth in App' button", (sREQ): true
		paragraph "6. Click the 'Update' button", (sREQ): true
	}
}

private pageSectionTimeZoneInstructions(){
	section (){
		paragraph "Please follow these steps to setup your location timezone:", (sREQ): true
		paragraph "1. Using the HE console, abort this installation and go to 'Settings' section", (sREQ): true
		paragraph "2. Click on 'Location and Modes'", (sREQ): true
		paragraph "3. Edit your postal code, and time zone, then Click on the map to edit your location", (sREQ): true
		paragraph "4. Find your location on the map and place the pin there, adjusting the desired radius", (sREQ): true
		paragraph "5. Tap the Update button", (sREQ): true
		paragraph "6. Try installing webCoRE again", (sREQ): true
	}
}

private pageInitializeDashboard(){
	//webCoRE Dashboard initialization
	Boolean success=initializeWebCoREEndpoint()
	Boolean hasTZ=mTZ()!=null
	dynamicPage((sNM): "pageInitializeDashboard", nextPage: success && hasTZ ? "pageSelectDevices" : sNULL){
		if(!(Boolean)state.installed){
			if(success){
				if(hasTZ){
					section(){
						paragraph "Great, ready to go."
					}
					section(){
						paragraph "Now, please choose a name for this webCoRE instance"
						label( (sNM): "name", (sTIT): "Name", state: (name ? "complete" : sNULL), defaultValue: app.name, (sREQ): false)
					}

					pageSectionDisclaimer()

					section(){
						paragraph "${(Boolean)state.installed ? "Tap Done to continue." : "Next, choose a security password for your webCoRE dashboard. You will need to enter this password when accessing your dashboard for the first time, and possibly from time to time, depending on your settings."}", (sREQ): false
					}
				}else{
					section(){
						paragraph "Your location is not correctly setup."
					}
					pageSectionTimeZoneInstructions()
					section(){
						paragraph "Once you have finished the steps above, go back and try again", (sREQ): true
					}
					return
				}
			}else{
				section(){
					paragraph "Sorry, it looks like OAuth is not properly enabled."
				}
				pageSectionInstructions()
				section(){
					paragraph "Once you have finished the steps above, go back and try again", (sREQ): true
				}
				return
			}
		}
		pageSectionPIN()
		pageSectionAcctId(true)
	}
}

private pageEngineBlock(){
	dynamicPage((sNM): "pageEngineBlock", (sTIT): sBLK){
		section(){
			paragraph "Under construction. This will help you upgrade your engine block to get access to extra features such as email triggers, fuel streams, and more."
		}
		if(eric()){
			section('Debug'){
				href sPDPC,(sTIT):'Dump base result Cache',description:sBLK
			}
		}
	}
}

private pageSelectDevices(){
	dynamicPage((sNM): "pageSelectDevices", nextPage: "pageFinishInstall"){
		section(){
			paragraph ((Boolean)state.installed ? "Select the devices you want webCoRE to have access to." : "Great, now let's select some devices.")
			paragraph "A DEVICE ONLY NEEDS TO BE SELECTED ONCE, THE CATEGORIES BELOW ARE TO MAKE THEM EASIER TO FIND."
			paragraph "It is a good idea to only select the devices you plan on using with webCoRE pistons. Pistons will only have access to the devices you selected."
		}
		if(!(Boolean)state.installed){
			section ('Note'){
				paragraph "Remember, you can always come back to webCoRE and add or remove devices as needed.", (sREQ): true
			}
			section(){
				paragraph "So go ahead, select a few devices, then tap Next"
			}
		}

		section (sectionTitleStr('Select devices by type')){
			paragraph "Most devices should fall into one of these categories"
			input "dev:actuator", "capability.actuator", multiple: true, (sTIT): "Actuators", (sREQ): false
			input "dev:sensor", "capability.sensor", multiple: true, (sTIT): "Sensors", (sREQ): false
			input "dev:all", "capability.*", multiple: true, (sTIT): "Devices", (sREQ): false
		}

		section (sectionTitleStr('Select devices by capability')){
			paragraph "If you cannot find a device by type, you may try looking for it by category below"
			def d=null
			for (capability in capabilities().findAll{ (!((String)it.value.d in [null, 'actuators', 'sensors'])) }.sort{ (String)it.value.d }){
				if(capability.value.d!=d) input "dev:${capability.key}", "capability.${capability.key}", multiple: true, (sTIT): "Which ${capability.value.d}", (sREQ): false
				d=capability.value.d
			}
		}
	}
}

private pageFinishInstall(){
	Boolean inst=(Boolean)state.installed
	if(!inst) initTokens()
	refreshDevices()
	dynamicPage((sNM): "pageFinishInstall", /* nextPage: (inst ? "pageSettings" : sBLK),*/ install: true){
		if(!inst){
			section(){
				paragraph "Excellent! You are now ready to use webCoRE"
			}
			section("Note"){
				paragraph "After you tap Done, go to 'Apps', and open the '"+appName()+"' App to access the dashboard.", (sREQ): true
				paragraph "You can also access the dashboard on any another device by entering ${domain()} in the address bar of your browser.", (sREQ): true
			}
			section(){
				paragraph "Now tap Done and enjoy webCoRE!"
			}
		}else{
			section(){
				paragraph "Devices updated"
			}
		}
	}
}

def pageSettings(){
	//clear devices cache
	dynamicPage((sNM): "pageSettings", install: false, uninstall: false){
		section("General"){
			label ((sNM): "name", (sTIT): "Name", state: (name ? "complete" : sNULL), defaultValue: app.name, (sREQ): false)
		}

/*
		def storageApp=getStorageApp()
		if(storageApp!=null){
			section("Storage Application"){
				app([(sTIT): isHubitat() ? 'Do not click - App Launchs automatically' : 'Available Devices', multiple: false, install: true, uninstall: false], 'storage', 'ady624', handleStor())
			}
		}else{*/
			section("Available devices"){
				href "pageSelectDevices", (sTIT): "Available devices", (sDESC): "Tap to select which devices are available to pistons", state: "complete"
			}
		//}

		section(sectionTitleStr("pushMessage Device")){
			input "pushDevice", "capability.notification", (sTIT): "Notification device for pushMessage (HE mobile App or pushOver)", multiple: true, (sREQ): false, submitOnChange: true
		}

		section(sectionTitleStr('Enable \$weather via external provider')){
			String apiXU='apiXU'
			String DarkSky='DarkSky'
			String OpnW='OpenWeatherMap'
			input "weatherType", sENUM, (sTIT): "Weather Type to enable?", defaultValue: sBLK, submitOnChange: true, (sREQ): false, options:[apiXU, DarkSky, OpnW, sBLK]
			String defaultLoc=sNULL
			String defaultLoc1=sNULL
			String mreq=settings.weatherType ? (String)settings.weatherType : sNULL
			String zipDesc=sNULL
			String zipDesc1=sNULL
			if(mreq){
				input "apixuKey", sTXT, (sTIT): mreq+" key?", (sREQ): true
				switch(mreq){
				case apiXU:
					defaultLoc=(String)location.zipCode
					zipDesc="Override zip code (${defaultLoc}), or set city name or latitude,longitude?".toString()
					break
				case DarkSky:
					defaultLoc="${location.latitude},${location.longitude}".toString()
					zipDesc="Override latitude,longitude (Default: ${defaultLoc})?".toString()
					break
				case OpnW:
					defaultLoc="${location.latitude}".toString()
					defaultLoc1="${location.longitude}".toString()
					zipDesc="Override latitude (Default: ${defaultLoc})?".toString()
					zipDesc1="Override longitude (Default: ${defaultLoc1})?".toString()
					break
				default:
					break
				}
				input "zipCode", sTXT, (sTIT): zipDesc, defaultValue: defaultLoc, (sREQ): false
				if(mreq==OpnW) input "zipCode1", sTXT, (sTIT): zipDesc1, defaultValue: defaultLoc1, (sREQ): false
			}
		}

		section(sectionTitleStr("Fuel Streams")){
			input "localFuelStreams", sBOOL, (sTIT): "Use local fuel streams?", defaultValue: (settings.localFuelStreams!=null) ? (Boolean)settings.localFuelStreams : true , submitOnChange: true
			if((Boolean)settings.localFuelStreams){
				href "pageFuelStreams", (sTIT): "Fuel Streams", (sDESC): "Tap to manage fuel streams", state: "complete"
			}
		}

/*		section("Integrations"){
			href "pageIntegrations", (sTIT): "Integrations with other services", (sDESC): "Tap to configure your integrations"
		}*/

		section(sectionTitleStr("Security")){
			href "pageChangePassword", (sTIT): "Security", (sDESC): "Tap to change your dashboard security settings", state: "complete"
		}

		section(sectionTitleStr("Custom Endpoints - Advanced")){
			paragraph "Custom Endpoints allows use of a local webserver for webCoRE IDE pages and local hub API endpoint address. webCoRE servers are still used for instance registration, non-local backup / restore / import, send email, NFL, store media, and optionally fuel streams"
			input "customEndpoints", sBOOL, submitOnChange: true, (sTIT): "Use custom endpoints?", default: false, (sREQ): true
			if((Boolean)customEndpoints){
				Boolean req=false
				if((Boolean)customEndPoints && (Boolean)localHubUrl) req=true
				input "customWebcoreInstanceUrl", sSTR, (sTIT): "Custom webCoRE webserver (local webserver url different from dashboard.webcore.co)", default: null, (sREQ): req
				if((Boolean)localHubUrl && !customWebcoreInstanceUrl) paragraph "If you use a local hub API url you MUST use a custom webCoRE server url, as dashboard.webcore.co site is restricted to Hubitat and Smartthing's cloud API access only"
				input "localHubUrl", sBOOL, (sTIT): "Use local hub URL for API access?", submitOnChange: true, default: false, (sREQ): false
			}else{
				app.clearSetting('localHubUrl')
				app.clearSetting('customWebcoreInstanceUrl')
			}
			state.endpointCloud=sNULL
			state.endpoint=sNULL
			state.endpointLocal=sNULL
			if((String)state.accessToken) updateEndpoint()
		}

		section(sectionTitleStr("Logging")){
			input "logging", sENUM, (sTIT): "Logging level", options: ["None", "Minimal", "Medium", "Full"], (sDESC): "Enable Logs in platform logs", defaultValue: "None", (sREQ): false
		}

		section((sTIT):"Privacy"){
			href "pageDisclaimer", (sTIT): imgTitle("settings.png", inputTitleStr("Data Collection Notice")), (sREQ): false, state: "complete"
		}

		section((sTIT): "Maintenance"){
			paragraph "Memory usage is at ${mem()}", (sREQ): false
			input "disabled", sBOOL, (sTIT): "Disable all pistons", (sDESC): "Disable all pistons belonging to this instance", defaultValue: false, (sREQ): false
			input "logPistonExecutions", sBOOL, (sTIT): "Log piston executions as Location events?", (sDESC): "Tap to change logging pistons as hub location events", defaultValue: false, (sREQ): false
			input "enableDashNotifications", sBOOL, (sTIT): "Enable Dashboard Notifications for device state changes?", (sDESC): "Tap to change enable dashboard notifications of device state changes (more overhead)", defaultValue: false, (sREQ): false
			href "pageRebuildCache", (sTIT): "Clean up and rebuild IDE data cache", (sDESC): "Tap to change your clean up and rebuild your data cache", state: "complete"
		}

		section((sTIT): "Recovery"){
			paragraph "webCoRE can run a recovery procedure every so often. This augments the built-in automatic recovery procedures that allows webCoRE to rely on all healthy pistons to keep the failed ones running."
			input "recovery", sENUM, (sTIT): "Run recovery", options: ["Never", "Every 5 minutes", "Every 10 minutes", "Every 15 minutes", "Every 30 minutes", "Every 1 hour", "Every 3 hours"], (sDESC): "Allows recovery procedures to run every so often", defaultValue: "Every 30 minutes", (sREQ): true
		}

		if((Boolean)getLogging().debug || eric()){
			String a='Tap to clear'
			String b='complete'
			section("Piston Log Cleanups"){
				href "pageLogCleanups", (sTIT): "Clear all piston logs, trace, stats, optimization caches, reset all piston logs, stats settings to default", (sDESC): a, state: b
			}
			section("Piston Caches Cleanup"){
				href "pageCleanups", (sTIT): "Clear all piston optimization caches", (sDESC): a, state: b
			}
			section("Piston Uber Cleanups"){
				href "pageUberCleanups", (sTIT): "Danger: Clear all piston variables, piston caches, and logs", (sDESC): a, state: b
			}
			section("Dump dashload cache"){
				href "pageDumpR",(sTIT):'Dump dashload Cache',description:sBLK
			}
			section("Dump global variables in use "){
				href "pageDumpPR",(sTIT):'Dump global variables in use',description:sBLK
			}
		}

		section("Uninstall"){
			href "pageRemove", (sTIT): "Uninstall webCoRE", (sDESC): "Tap to uninstall ${handle()}"
		}
	}
}

private pageGraphs(){
	dynamicPage((sNM): "pageGraphs", uninstall: false, install: false){
		section(){
			List graphApps = getGraphApps()
			app([(sTIT): 'List of streams and graphs', multiple: true, install: true, uninstall: false], 'fuelStreams', 'ady624', handleFuelS())
			if(graphApps?.size()) {
				input "graphDuplicateSelect", sENUM, title: "Duplicate Existing Graph", description: 'Tap to select...', options: graphApps.collectEntries { [(it?.id):it?.getLabel()] }, required: false, multiple: false, submitOnChange: true
				if(settings.graphDuplicateSelect) {
					href "graphDuplicationPage", title: "Create Duplicate Graph?", description: 'Tap to proceed...'
				}
			}
		}
	}
}

def graphDuplicationPage() {
	return dynamicPage(name: "graphDuplicationPage", nextPage: "pageGraphs", uninstall: false, install: false) {
		section() {
			if((Boolean)state.graphDuplicated) {
				paragraph "Graph already duplicated..." + "Return to graph page and select it"
			} else {
				def grf = getGraphApps()?.find { it?.id?.toString() == settings.graphDuplicateSelect?.toString() }
				if(grf) {
					Map grfData = grf.getSettingsAndStateMap() ?: [:]
					String grfId = (String)grf.getId().toString()
					if(grfData.settings && grfData.state) {
						String myId=app.getId()
						if(!childDupMapFLD[myId]) childDupMapFLD[myId] = [:]
						if(!childDupMapFLD[myId].graphs) childDupMapFLD[myId].graphs = [:]
						childDupMapFLD[myId].graphs[grfId] = grfData
						log.debug "Dup Data: ${childDupMapFLD[myId].graphs[grfId]}"
					}
					Map app_name= (Map)grfData.settings['app_name']
					String nm="${grfData.label}"+' (Dup)' //app_name.value+' (Dup)'
					app_name.value= nm
					grfData.settings['app_name']= app_name
					grfData.settings["duplicateFlag"] = [type: sBOOL, value: true]
					// grfData?.settings["actionPause"] = [type: sBOOL, value: true]
					grfData.settings["duplicateSrcId"] = [type: "text", value: grfId]
					def a=addChildApp("ady624", handleFuelS(), nm, [settings: grfData.settings])
					paragraph "Graph Duplicated..." + "<br>Return to Graph Page and look for the App with '(Dup)' in the name..."
					state.graphDuplicated = true
				} else { paragraph "Graph not Found" }
			}
		}
	}
}

@Field volatile static Map<String, Map> childDupMapFLD        = [:]

public Map getChildDupeData(String type, String childId) {
	String myId=app.getId()
	return (childDupMapFLD[myId] && childDupMapFLD[myId][type] && childDupMapFLD[myId][type][childId]) ? (Map)childDupMapFLD[myId][type][childId] : [:]
}

public void clearDuplicationItems() {
	state.graphDuplicated = false
	if(settings.graphDuplicateSelect) app.removeSetting("graphDuplicateSelect")
	state.remove('graphDuplicated')
}

public void childAppDuplicationFinished(String type, String childId) {
	log.trace "childAppDuplicationFinished($type, $childId)"
//    Map data = [:]
	String myId=app.getId()
	if(childDupMapFLD[myId] && childDupMapFLD[myId][type] && childDupMapFLD[myId][type][childId]) {
		childDupMapFLD[myId][type].remove(childId)
	}
	clearDuplicationItems()
}



List getGraphApps() {
	return ((List)getAllChildApps())?.findAll {
		String t= it?.gtSetting('graphType')
		t && it?.name == handleFuelS() && !(t in ['longtermstorage'])
	}
}


private pageFuelStreams(){
	dynamicPage((sNM): "pageFuelStreams", uninstall: false, install: false){
		section(){
			app([(sTIT): isHubitat() ? 'Do not click - List of streams below that launches automatically' : 'Fuel Streams', multiple: true, install: true, uninstall: false], 'fuelStreams', 'ady624', handleFuelS())
		}
	}
}

private pageSectionAcctId(Boolean ins=false){
	section((sTIT): "Set Account/Location Identifier"){
		paragraph "If you have multiple webCoRE instances (ie you have (or may have) multiple hubs running webCoRE), for proper IDE operations all of the hubs should be linked together with a common account identifier."
		if(!ins)paragraph "NOTE changing these settings will require all piston references to be corrected (calling other webCoRE pistons, URL access, and access apps such as HomeBridge or Echo Speaks.)"
		input "setACCT", sBOOL, (sTIT): "Set custom account identifier?", (sDESC): "Tap to change", defaultValue: ins, submitOnChange: true, (sREQ): false
		if((Boolean)settings.setACCT){
			paragraph "An email address is usually a good choice (is not used/shared)"
			input "acctID", sTXT, (sTIT): 'Account identifier (email is usually good)', (sREQ): true
			if(settings.acctID){
				app.updateSetting("properSID", [type: sBOOL, (sVAL): true])
				paragraph "All hubs in same location should have a common location identifier. This could be Boston, Vacation, or Home1, etc..."
				input "locID", sTXT, (sTIT): 'Location identifier - no imbedded spaces', (sREQ): true
			}
		} else{
			app.removeSetting("acctID")
			app.removeSetting("locID")
			input "properSID", sBOOL, (sTIT): "Use New SID for location?", (sDESC): "Tap to change", defaultValue: true, (sREQ): false
		}
		String wName=sAppId()
		acctlocFLD[wName]=null
		locFLD[wName]=sNULL
		clearHashMap(wName)
	}
}

private pageChangePassword(){
	dynamicPage((sNM): "pageChangePassword", uninstall: false, install: false){
		section(){
			paragraph "Choose a security password for your dashboard. You will need to enter this password when accessing your dashboard for the first time and possibly from time to time.", (sREQ): false
		}
		pageSectionPIN()
		pageSectionAcctId(false)
		section(){
			href "pageClearTokens", (sTIT): "Clear all Browser Security Tokens", (sDESC): "Tap to clear all security tokens in use by browsers", state: "complete"
		}
		if(settings.PIN){
			section(){
				paragraph "webCoRE uses an access token to allow communication with webCoRE via REST calls. You may choose to reset this token.", (sREQ): false
				paragraph "NOTE resetting the access token will invalidate any remote access to pistons (the URLs they are using), and this will have to be re-enabled / setup once the new access token has been created.", (sREQ): true
				paragraph "If your dashboard fails to load and no log messages appear in Hubitat console 'Logs' when you refresh the dashboard, resetting the access token may restore access to webCoRE.", (sREQ): false
				href "pageResetEndpoint", (sTIT): "Reset access token", (sDESC): "WARNING: URLs for triggering pistons or accessing piston URLs will need to be updated", state: "complete"
			}
		}
	}
}

private pageSectionPIN(){
	section(){
		input "PIN", "password", (sTIT): "Choose a security password for your dashboard", (sREQ): true
		input "expiry", sENUM, options: ["Every hour", "Every day", "Every week", "Every month (recommended)", "Every three months", "Never (not recommended)"], defaultValue: "Every month (recommended)", (sTIT): "Choose how often the dashboard login expires", (sREQ): true
	}
}

private pageClearTokens(){
	initTokens()
	dynamicPage((sNM): "pageClearTokens", install: false, uninstall: false ){
		section(){
			paragraph "Browser Tokens have been Cleared. You will have to re-login to the webCoRE dashboards."
		}
	}
}

def pageRebuildCache(){
	cleanUp()
	dynamicPage((sNM): "pageRebuildCache", install: false, uninstall: false){
		section(){
			paragraph "Success! Data cache has been cleaned up and rebuilt."
		}
	}
}

def pageResetEndpoint(){
	revokeAccessToken()
	String wName=sAppId()
	lastRecoveredFLD[wName]=0L
	lastRegFLD[wName]=0L
	lastRegTryFLD[wName]=0L
	Boolean success=initializeWebCoREEndpoint()
	clearParentPistonCache("reset endpoint")
	updated()
	dynamicPage((sNM): "pageResetEndpoint", install: false, uninstall: false){
		section(){
			paragraph "Success: $success Please sign out and back in to the webCoRE dashboard."
			paragraph "If you use external URLs to trigger pistons, these URLs must be updated. See the piston detail page for an updated external URL; all pistons will use the same new token."
		}
	}
}

def pageCleanups(){
	String t= 'cleanup old super'
	Boolean didw= getTheLock(t)

	Map<String,Map> vars=(Map<String,Map>)atomicState.vars
	vars=vars ?: [:]
	Boolean fnd=false
	if(vars){ // clear out obsolete superglobals
		List<String> b=vars.collect{ (String)it.key }
		for (String c in b){
			if(c.startsWith('@@')){
				a=vars.remove(c) // @@
				fnd=true
			}
		}
		if(fnd)atomicState.vars=vars
		b=null
	}

	releaseTheLock(t)
	if(fnd){
		clearGlobalPistonCache(t)
		clearBaseResult(t)
	}

	clearChldCaches(true)
	return dynamicPage((sNM):'pageCleanups', install: false, uninstall:false){
		section('Clear'){
			paragraph 'Optimization caches have been cleared.'
		}
	}
}

def pageLogCleanups(){
	clearChldCaches(false,true)
	return dynamicPage((sNM):'pageLogCleanups', install: false, uninstall:false){
		section('Clear'){
			paragraph 'Logs been cleared.'
		}
	}
}

def pageUberCleanups(){
	clearChldCaches(false,false, true)
	return dynamicPage((sNM):'pageUberCleanups', install: false, uninstall:false){
		section('Uber Clear'){
			paragraph 'Everything has been cleared.'
		}
	}
}

def pageDumpR(){
	Map<String,Object> t0 =api_get_base_result(true)
	String message=getMapDescStr(t0)
	return dynamicPage((sNM):"pageDumpR",(sTIT):sBLK,uninstall:false){
		section('Dashboard Data Cache dump'){
			paragraph message
		}
	}
}

def pageDumpPR(){
	String n=handlePistn()
	List t0
	t0=wgetChildApps().findAll{ (String)it.name==n }
	def t1=t0[0]
	Map<String,List> t2
	if(t1!=null) t2= t1.gtGlobalVarsInUse()
	else t2=[:]
	Map<String,Object> newMap
	newMap=[:]
	Map<String,Map> glbs=listAvailableVariables1()
	String nf= ' (VARIABLE NOT FOUND)'
	t2.each {
		String k
		k= it.key
		List<String> l= (List<String>)it.value
		List<String> newLst
		newLst=[]
		l.each{ String pid ->
			def pist= t0.find { tid -> tid.id.toString() == pid }
			if(pist){
				String nm= normalizeLabel(pist)
				newLst << nm
			}
		}
		if(!glbs.containsKey(k)) k+= nf
		else k+= " (${(String)glbs[k].t})"
		newMap[k]= []+newLst
	}
	newMap = newMap.sort { (String)it.key }

	String message=getMapDescStr(newMap)
	return dynamicPage((sNM):"pageDumpPR",(sTIT):sBLK,uninstall:false){
		section('Global variable in use dump'){
			paragraph message
		}
	}
}

def pageRemove(){
	dynamicPage((sNM): "pageRemove", (sTIT): sBLK, install: false, uninstall: true){
		section('CAUTION'){
			paragraph "You are about to completely remove webCoRE and all of its pistons.", (sREQ): true
			paragraph "This action is irreversible.", (sREQ): true
			paragraph "It is suggested save a hub backup prior to this delete", (sREQ): true
			paragraph "If you are sure you want to do this, please tap on the Remove button below.", (sREQ): true
		}
	}
}

void revokeAccessToken(){
	state.accessToken=null
	state.endpointCloud=sNULL
	state.endpoint=sNULL
	state.endpointLocal=sNULL
	resetFuelStreamList()
	initTokens()
}


/******************************************************************************/
/***																		***/
/*** INITIALIZATION ROUTINES												***/
/***																		***/
/******************************************************************************/

void installed(){
	state.installed=true
	initialize()
}

void updated(){
	info "Updated ran webCoRE "+sVER+" HE: "+sHVER
	unsubscribe()
	unschedule()
	initialize()

	Boolean chg=false
	Boolean frcResub=false
	Boolean verchg=false

	if((Boolean)atomicState.disabled!=(Boolean)settings.disabled){
		atomicState.disabled=(Boolean)settings.disabled==true
		chg=true
	}
	if((Boolean)atomicState.lPE!=(Boolean)settings.logPistonExecutions){
		atomicState.lPE=(Boolean)settings.logPistonExecutions==true
		chg=true
	}
	if(atomicState.doResub){
		chg=true
		frcResub=true
		verchg=true
	}
	if((String)atomicState.cV!=sVER || (String)atomicState.hV!=sHVER){
		debug "Detected version change ${state.cV} ${sVER} ${state.hV} ${sHVER}"
		atomicState.cV=sVER
		atomicState.hV=sHVER
		frcResub=true
		chg=true
		verchg=true
	}
	if((Boolean)atomicState.lFS!=(Boolean)settings.localFuelStreams){
		atomicState.lFS=(Boolean)settings.localFuelStreams==true
		chg=true
	}
	if(chg){
		if(verchg){
			runIn(150, afterRun) // try to deal with people updating this file first vs. last with HPM
			log.info "webCoRE scheduled install/upgrade completion in 150 seconds"
			return
		}else{
			clearParentPistonCache("parent updated", frcResub, chg)
			cleanUp()
			resetFuelStreamList()
		}
	}
	clearBaseResult('updated')
}

void afterRun(){
	atomicState.doResub=false
	state.remove('doResub')
	clearParentPistonCache("parent updated", true, true)
	cleanUp()
	resetFuelStreamList()
	clearBaseResult('updated after')
	log.info "webCoRE upgrade completed"
}

Map getChildPstate(){ gtPdata() }
Map gtPdata(){
	LinkedHashMap msettings=(LinkedHashMap)atomicState.settings
	if((String)state.accessToken) updateEndpoint()
	List a1=[ hashId(((Long)location.id).toString()+sML), hashId(hubUID.toString()+location.name.toString()+sML)]
	String lsid=locationSid()
	return [
		sCv: sVER,
		sHv: sHVER,
		stsettings: msettings,
		lifx: state.lifx ?: [:],
		powerSource: state.powerSource ?: 'mains',
		region: ((String)state.endpointCloud).contains('graph-eu') ? 'eu' : 'us',
		instanceId: getInstanceSid(),
		accountId: accountSid(),
		newAcctSid: acctANDloc(),
		locationId: lsid,
		oldLocations: a1,
		allLocations: [lsid]+a1,
		enabled: (Boolean)atomicState.disabled!=true,
		logPExec: (Boolean)atomicState.lPE==true,
		incidents: getIncidents(),
		useLocalFuelStreams: (Boolean)atomicState.lFS==true
	]
}

private void clearGlobalPistonCache(String meth=null){
	String n=handlePistn()
	List t0=wgetChildApps().findAll{ (String)it.name==n }
	def t1=t0[0]
	if(t1!=null) t1.clearGlobalCache(meth) // will cause a child to read global Vars
	t0=null
}

private void clearParentPistonCache(String meth=sNULL, Boolean frcResub=false, Boolean callAll=false){
	String wName=sAppId()
	clearHashMap(wName)
	acctlocFLD[wName]=null
	locFLD[wName]=sNULL
	pStateFLD[wName]=(Map)[:]
	pStateFLD=pStateFLD
	mb()
	String n=handlePistn()
	List t0=wgetChildApps().findAll{ (String)it.name==n }
	if(t0){
		def t1=t0[0]
		if(t1!=null) t1.clearParentCache(meth) // will cause one child to read gtPdata
		if(frcResub){
			t0.sort().each{ chld -> // this runs updated on all child pistons
				chld.updated()
			}
		}else if(callAll){
			clearChldCaches(true)
		}
	}
	t0=null
}

@Field volatile static Map<String,Map<String, Long>> cldClearFLD=[:]

void clearChldCaches(Boolean all=false, Boolean clrLogs=false, Boolean uber=false){
// clear child caches if has not run in 61 mins
	String wName=sAppId()
	String n=handlePistn()
	if(all||clrLogs||uber){
		pStateFLD[wName]=(Map)[:]
		mb()
	}
	Long t1=wnow()
	List t0=wgetChildApps().findAll{ (String)it.name==n }
	if(t0){
		if(!cldClearFLD[wName]){ cldClearFLD[wName]=(Map)[:]; cldClearFLD=cldClearFLD }
		if(clrLogs|uber){
			t0.sort().each{ chld ->
				Map a= !uber ? chld.clearLogsQ() : chld.clearAllQ()
				String schld=chld.id.toString()
				cldClearFLD[wName][schld]=t1
			}
		}else{
			Boolean updateCache=true
			//Long recTime=3660000L // 61 min in ms (regular piston cache cleanup)
			Long recTime=86460000L // 24hrs + 1 min in ms (regular piston cache cleanup)
			if(all) recTime=1000L // aggressive cache cleanup
			Long threshold=t1 - recTime
			t0.sort().each{ chld ->
				String myId=hashPID(chld.id)
				if(pStateFLD[wName]==null){ pStateFLD[wName]= (Map)[:]; pStateFLD=pStateFLD }
				Map meta=(Map)pStateFLD[wName][myId]
				if(meta==null){
					meta=(Map)chld.curPState()
					pStateFLD[wName][myId]=meta
					pStateFLD=pStateFLD
				}
				String schld=chld.id.toString()
				Long t2=cldClearFLD[wName][schld]
				Long t3=(Long)meta?.t
				Boolean t4=(Boolean)meta?.heCached
				if(t2==null){
					t2=threshold-3600000L
					cldClearFLD[wName][schld]=t2
				}
				else if( all || ( meta!=null && t4 && (Boolean)meta.a && t3!=null && t3>t2 && t3<threshold)){
					cldClearFLD[wName][schld]=t1
					Map a=chld.clearCache()
				}
			}
		}
	}
	t0=null
}

private void initialize(){
	Boolean chg=false
	Boolean initT=false
	Boolean reSub=(Boolean)atomicState.forceResub1
	if((Boolean)reSub==null){
		atomicState.forceResub1=true
		atomicState.properSID=null
		warn "resetting SID"
	}
	Boolean prpSid=(Boolean)atomicState.properSID
	if(prpSid==null || prpSid!=(Boolean)settings.properSID){
		Boolean t0=settings.properSID!=null ? (Boolean)settings.properSID : true
		atomicState.properSID=t0
		state.properSID=t0
		if(settings.properSID==null) app.updateSetting("properSID", [type: sBOOL, (sVAL): true])
		initT=true
		chg=true
	}
	String wName=sAppId()
	acctlocFLD[wName]=null
	locFLD[wName]=sNULL
	String a=accountSid()
	String l=locationSid()
	String a1=(String)atomicState.aSID
	String l1=(String)atomicState.lSID
	if(a1!=a || l1!=l){
		if(a1!=null || l1!=null){
			debug "Detected account SID change ${a1} -> ${a} ${l1} -> ${l}"
			initT=true
		}
		chg=true
		if(!acctANDloc()){
			app.removeSetting("acctID")
			app.removeSetting("locID")
		}
		atomicState.aSID=a
		atomicState.lSID=l
	}
	if(chg) atomicState.doResub=true
	if(initT)initTokens()

	subscribeAll()
	Map t0=(Map)atomicState.vars
	if(t0==null)atomicState.vars=[:]

	verFLD[wName]=sVER
	HverFLD[wName]=sHVER

	refreshDevices()

	if((String)state.accessToken) updateEndpoint()
	registerInstance()

	checkWeather()

	lastRecoveredFLD[wName]=0L
	String recoveryMethod=(settings.recovery ?: 'Every 30 minutes').replace('Every ', 'Every').replace(' minute', 'Minute').replace(' hour', 'Hour')
	if(recoveryMethod!='Never'){
		try{
			"run$recoveryMethod"(recoveryHandler)
		}catch(ignored){}
	}
	schedule('22 4/15 * * * ?', 'clearChldCaches') // regular child cache cleanup
}

private void checkWeather(){
	if(settings.weatherType || state.storAppOn){
		Boolean t0=settings.weatherType && settings.apixuKey
		def storageApp=getStorageApp(t0)
		if(storageApp!=null){
			state.storAppOn=true
			String weatherTyp= settings.weatherType ? (String)settings.weatherType : sNULL
			storageApp.settingsToState("weatherType", weatherTyp)
			storageApp.settingsToState("apixuKey", settings.apixuKey)
			storageApp.settingsToState("zipCode", settings.zipCode)
			if(weatherTyp=='OpenWeatherMap') storageApp.settingsToState("zipCode1", (String)settings.zipCode1)
			if(t0){
				storageApp.startWeather()
			}else{
				storageApp.stopWeather()
				//delete it ??
			}
		}else state.storAppOn=false
	}
}

Map getWCendpoints(){
	Map t0=[:]
	String ep
	String epl
	ep=apiServerUrl("$hubUID/apps/${app.id}".toString())
	epl=localApiServerUrl("${app.id}".toString())

	if(isCustomEndpoint()) ep=epl
	if(ep.endsWith(sDIV))ep=ep.substring(iZ,ep.length()-i1)
	if(epl.endsWith(sDIV))epl=epl.substring(iZ,epl.length()-i1)

	t0.ep=ep
	t0.epl=epl
	t0.at=state.accessToken
	return t0
}

private void updateEndpoint(){
	String accessToken=(String)state.accessToken
	String newEP
	String newEPLocal
	newEP=apiServerUrl("$hubUID/apps/${app.id}/?access_token=${accessToken}".toString())
	newEPLocal=localApiServerUrl("${app.id}/?access_token=${accessToken}".toString())
	state.endpointCloud=newEP
	if(isCustomEndpoint()) newEP=newEPLocal
	if(newEP!=(String)state.endpoint){
		String wName=sAppId()
		state.endpoint=newEP
		state.endpointLocal=newEPLocal
		lastRegFLD[wName]=0L
		lastRegTryFLD[wName]=0L
		registerInstance()
	}
}

private Boolean initializeWebCoREEndpoint(Boolean disableRetry=false){
	if(!(String)state.endpoint || !(String)state.endpointCloud){
		String accessToken=(String)state.accessToken
		if(!accessToken){
			try{
				accessToken=createAccessToken() // this fills in state.accessToken
			}catch(e){
				error "An error has occurred during endpoint initialization: ", null, e
				state.endpointCloud=sNULL
				state.endpoint=sNULL
				state.endpointLocal=sNULL
			}
		}
		if(accessToken){
			updateEndpoint()
		}else if(!disableRetry){
			enableOauth()
			return initializeWebCoREEndpoint(true)
		}else error "Could not get access token"
	}
	return (String)state.endpoint!=sNULL
}

private void enableOauth(){
	Map params=[
		uri: "http://localhost:8080/app/edit/update?_action_update=Update&oauthEnabled=true&id=${app.appTypeId}".toString(),
		headers: ['Content-Type':'text/html;charset=utf-8']
	]
	try{
		httpPost(params){ resp ->
			//LogTrace("response (sDATA): ${resp.data}")
		}
	}catch(e){
		error "enableOauth something went wrong: ", null, e
	}
}

private getHub(){
	return ((List)location.getHubs()).find{ (String)it.getType()=='PHYSICAL' }
}

private void subscribeAll(){
	subscribe(location, handle()+".poll", webCoREHandler)
//	subscribe(location, '@@'+handle(), webCoREHandler)
	subscribe(location, "systemStart", startHandler)
	subscribe(location, "mode", modeHandler)
//below unused
//	subscribe(location, "HubUpdated", hubUpdatedHandler, [filterEvents: false])
//	subscribe(location, "summary", summaryHandler, [filterEvents: false])
	subscribe(location, "hsmStatus", hsmHandler, [filterEvents: false])
	subscribe(location, "hsmAlert", hsmAlertHandler, [filterEvents: false])
	setPowerSource(getHub()?.isBatteryInUse() ? 'battery' : 'mains')
}

/******************************************************************************/
/***																		***/
/*** DASHBOARD MAPPINGS														***/
/***																		***/
/******************************************************************************/

mappings{
	//path("/dashboard"){action: [GET: "api_dashboard"]}
	path("/intf/dashboard/load"){action: [GET: "api_intf_dashboard_load"]}
	path("/intf/dashboard/devices"){action: [GET: "api_intf_dashboard_devices"]}
	path("/intf/dashboard/refresh"){action: [GET: "api_intf_dashboard_refresh"]}
	path("/intf/dashboard/piston/new"){action: [GET: "api_intf_dashboard_piston_new"]}
	path("/intf/dashboard/piston/create"){action: [GET: "api_intf_dashboard_piston_create"]}
	path("/intf/dashboard/piston/backup"){action: [GET: "api_intf_dashboard_piston_backup"]}
	path("/intf/dashboard/piston/get"){action: [GET: "api_intf_dashboard_piston_get"]}
	path("/intf/dashboard/piston/getDb"){action: [GET: "api_intf_dashboard_piston_getDb"]}
	path("/intf/dashboard/piston/set"){action: [GET: "api_intf_dashboard_piston_set"]}
	path("/intf/dashboard/piston/set.start"){action: [GET: "api_intf_dashboard_piston_set_start"]}
	path("/intf/dashboard/piston/set.chunk"){action: [GET: "api_intf_dashboard_piston_set_chunk"]}
	path("/intf/dashboard/piston/set.end"){action: [GET: "api_intf_dashboard_piston_set_end"]}
	path("/intf/dashboard/piston/pause"){action: [GET: "api_intf_dashboard_piston_pause"]}
	path("/intf/dashboard/piston/resume"){action: [GET: "api_intf_dashboard_piston_resume"]}
	path("/intf/dashboard/piston/set.bin"){action: [GET: "api_intf_dashboard_piston_set_bin"]}
	path("/intf/dashboard/piston/tile"){action: [GET: "api_intf_dashboard_piston_tile"]}
	path("/intf/dashboard/piston/set.category"){action: [GET: "api_intf_dashboard_piston_set_category"]}
	path("/intf/dashboard/piston/logging"){action: [GET: "api_intf_dashboard_piston_logging"]}
	path("/intf/dashboard/piston/clear.logs"){action: [GET: "api_intf_dashboard_piston_clear_logs"]}
	path("/intf/dashboard/piston/delete"){action: [GET: "api_intf_dashboard_piston_delete"]}
	path("/intf/dashboard/piston/evaluate"){action: [GET: "api_intf_dashboard_piston_evaluate"]}
	path("/intf/dashboard/piston/test"){action: [GET: "api_intf_dashboard_piston_test"]}
	path("/intf/dashboard/piston/activity"){action: [GET: "api_intf_dashboard_piston_activity"]}
	path("/intf/dashboard/presence/create"){action: [GET: "api_intf_dashboard_presence_create"]}
	path("/intf/dashboard/variable/set"){action: [GET: "api_intf_variable_set"]}
	path("/intf/dashboard/settings/set"){action: [GET: "api_intf_settings_set"]}
	path("/intf/fuelstreams/list"){action: [GET: "api_intf_fuelstreams_list"]}
	path("/intf/fuelstreams/get"){action: [GET: "api_intf_fuelstreams_get"]}
	path("/intf/location/entered"){action: [GET: "api_intf_location_entered"]}
	path("/intf/location/exited"){action: [GET: "api_intf_location_exited"]}
	path("/intf/location/updated"){action: [GET: "api_intf_location_updated"]}
	path("/ifttt/:eventName"){action: [GET: "api_ifttt", POST: "api_ifttt"]}
	path("/email/:pistonId"){action: [POST: "api_email"]}
	path("/execute/:pistonIdOrName"){action: [GET: "api_execute", POST: "api_execute"]}
	path("/global/:varName"){action: [GET: "api_global"]}
	path("/tap"){action: [POST: "api_tap"]}
	path("/tap/:tapId"){action: [GET: "api_tap"]}
}

private Map api_get_error_result(String error,String m=sNULL){
	debug "Dashboard: error: ${error} m:$m"
	return [
		(sNM): (String)location.name + ' \\ ' + appName(),
		(sERR): error,
		now: wnow()
	]
}

private Map getHubitatVersion(){
	return ((List)location.getHubs()).collectEntries{ [(it.id.toString()): it.getFirmwareVersionString()] }
}

private static String normalizeLabel(pisN){
	String label=(String)pisN.label
	String regex=' <span style.*$'
	String t0=label.replaceAll(regex, sBLK)
	return t0!=sNULL ? t0 : label
}

@Field static Semaphore theSerialLockFLD=new Semaphore(1)
@Field volatile static Long lockTimeFLD
private void wpauseExecution(Long t){ pauseExecution(t) }

@CompileStatic
Boolean getTheLock(String meth=sNULL){
	Long waitT=1600L
	Boolean wait=false
	Semaphore sema=theSerialLockFLD
	while(!((Boolean)sema.tryAcquire())){
		// did not get the lock
		Long t=lockTimeFLD
		if(t==null){
			t=wnow()
			lockTimeFLD=t
		}
		//if(eric())log.warn "waiting for ${qname} lock access $meth"
		wpauseExecution(waitT)
		wait=true
		if((wnow()-t) > 30000L){
			releaseTheLock('getLock')
			warn "overriding lock $meth"
		}
	}
	lockTimeFLD=wnow()
	return wait
}

@CompileStatic
static void releaseTheLock(String meth=sNULL){
	lockTimeFLD=null
	Semaphore sema=theSerialLockFLD
	sema.release()
}

@Field static final String sCB='clearB'
@CompileStatic
private void clearBaseResult(String meth=sNULL,String wNi=sNULL){
	String wName= wNi ?: sAppId()
	Boolean didw=getTheLock(sCB)
	Map a=null
	base_resultFLD[wName]=a
	base_resultFLD=base_resultFLD
	lastActivityFLD=sNULL
	lastActivityTOKFLD=sNULL
	tlastActivityFLD=0L
	releaseTheLock(sCB)
	//if(eric())debug "clearBaseResult "+meth
}

@Field volatile static Map<String,Map<String,Object>> base_resultFLD= [:]
@Field volatile static Map<String,Integer> cntbase_resultFLD= [:]


private List<Map> presult(String wName){
	String n=handlePistn()
	return wgetChildApps().findAll{ (String)it.name==n }.sort{ (String)it.label }.collect{
		String myId=hashPID(it.id)
		if(pStateFLD[wName]==null){ pStateFLD[wName]= (Map)[:]; pStateFLD=pStateFLD }
		/*Map meta=[
				(sA):isAct(t0),
				(sC):t0[sCTGRY],
				(sT):(Long)t0[sLEXEC],
				(sN):(Long)t0[sNSCH],
				(sZ):(String)t0.pistonZ,
				(sS):st,
				heCached:(Boolean)t0.Cached ?: false
		] */
		Map meta=(Map)pStateFLD[wName][myId]
		if(meta==null){
			meta=(Map)it.curPState()
			meta= meta?:[:]
			pStateFLD[wName][myId]=meta
			pStateFLD=pStateFLD
		}
		[ id: myId, (sNM): normalizeLabel(it), meta: [:]+meta ]
	}
}

private Map<String,Object> api_get_base_result(Boolean updateCache=false){
	String t='baseR'
	String wName=sAppId()

	Boolean didw=getTheLock(t)

	Long lnow=wnow()
	if(base_resultFLD[wName]!=null){
		cntbase_resultFLD[wName]=cntbase_resultFLD[wName]+i1
		if(cntbase_resultFLD[wName]>200){
			base_resultFLD[wName]=null
		}else{
			Map<String,Object> result=[:]+base_resultFLD[wName]
			((Map)result.instance).pistons= presult(wName)
			base_resultFLD[wName]=[:]+result
			base_resultFLD=base_resultFLD
			releaseTheLock(t)
			result.now=lnow
			return result
		}
	}

	cntbase_resultFLD[wName]=0
	//log.warn "filling in"

	TimeZone tz=mTZ()
	String currentDeviceVersion=(String)gtSt('deviceVersion')
	Long incidentThreshold=Math.round(lnow - 604800000.0D)
	def a=gtSt('hsmAlerts')
	List<Map> alerts= a ? (List<Map>)a : []

	String instanceId=getInstanceSid()
	String locationId=locationSid()

	String myN= appName()
	Map<String,Object> result=[
		(sNM): (String)location.name+ ' \\ ' +myN,
		instance: [
			account: [id: accountSid()],
			pistons:  presult(wName),
			id: instanceId,
			locationId: locationId,
			(sNM): myN,
			uri: (String)gtSt('endpoint'),
			deviceVersion: currentDeviceVersion,
			coreVersion: sVER,
			heVersion: sHVER,
			enabled: !gtSetting('disabled'),
			settings: gtSt('settings') ?: [:],
			lifx: gtSt('lifx') ?: [:],
			virtualDevices: virtualDevices(updateCache),
			globalVars: listAvailableVariables1(),
			fuelStreamUrls: getFuelStreamUrls(instanceId),
		],
		location: [
			//hubs: location.getHubs().findAll{ !((String)it.name).contains(':') }.collect{ [id: it.id /*hashId(it.id)*/, (sNM): (String)it.name, firmware: isHubitat() ? getHubitatVersion()[it.id] : it.getFirmwareVersionString(), physical: it.getType().toString().contains('PHYSICAL'), powerSource: it.isBatteryInUse() ? 'battery' : 'mains' ]},
			hubs: ((List)location.getHubs()).collect{ [id: it.id /*hashId(it.id)*/, (sNM): (String)location.name, firmware: isHubitat() ? (String)((Map)getHubitatVersion())[(String)it.id.toString()] : (String)it.getFirmwareVersionString(), physical: it.getType().toString().contains('PHYSICAL'), powerSource: it.isBatteryInUse() ? 'battery' : 'mains' ]},
			incidents: alerts.collect{it}.findAll{ (Long)it.date >= incidentThreshold },
			//incidents: isHubitat() ? [] : location.activeIncidents.collect{[date: it.date.time, (sTIT): it.getTitle(), message: it.getMessage(), args: it.getMessageArgs(), sourceType: it.getSourceType()]}.findAll{ it.date >= incidentThreshold },
			id: locationId,
			mode: hashId(location.getCurrentMode().id),
			modes: location.getModes().collect{ [id: hashId(it.id), (sNM): (String)it.name ]},
			shm: transformHsmStatus((String)location.hsmStatus),
			(sNM): (String)location.name,
			temperatureScale: (String)location.temperatureScale,
			timeZone: tz ? [
				id: tz.ID,
				(sNM): tz.displayName,
				offset: tz.rawOffset
			] : null,
			zipCode: (String)location.zipCode,
		],
	]
	base_resultFLD[wName]=[:]+result
	base_resultFLD=base_resultFLD
	releaseTheLock(t)
	result.now=lnow
	return result
}

private Map<String,Map> getFuelStreamUrls(String iid){
	if(!useLocalFuelStreams()){
	//if((Boolean)state.installed && (Boolean)settings.agreement){
		String region=((String)state.endpointCloud).contains('graph-eu') ? 'eu' : 'us'
		String baseUrl='https://api-' + region + '-' + iid[32] + '.webcore.co:9287/fuelStreams'
		Map headers=[ 'Auth-Token' : iid ]

		return [
			list: [(sL): false, (sM): 'POST', (sH): headers, u: baseUrl + '/list', (sD): [(sI): iid]],
			get : [(sL): false, (sM): 'POST', (sH): headers, u: baseUrl + '/get' , (sD): [(sI): iid ], (sP): 'f']
		]
	}

	//if((Boolean)state.installed && (Boolean)settings.agreement){
	String baseUrl=isCustomEndpoint() && useLocalFuelStreams() ? customApiServerUrl(sDIV) : apiServerUrl("$hubUID/apps/${app.id}/".toString())

	String params=baseUrl.contains((String)state.accessToken) ? sBLK : "access_token=${state.accessToken}".toString()

	return [
		list: [(sL): true, u: baseUrl + "intf/fuelstreams/list?${params}".toString() ],
		get : [(sL): true, u: baseUrl + "intf/fuelstreams/get?id={fuelStreamId}${params ? "&" + params : sBLK}".toString(), (sP): 'fuelStreamId' ]
	]
}

Boolean useLocalFuelStreams(){
	return (Boolean)settings.localFuelStreams!=null ? (Boolean)settings.localFuelStreams : true
}

@SuppressWarnings('GroovyFallthrough')
@CompileStatic
private static String transformHsmStatus(String status){
	if(status==sNULL) return "unconfigured"
	switch(status){
		case sDISARMD:
		case sALLDISARM:
			return sOFF
			break
		case "armedHome":
		case "armedNight":
			return "stay"
			break
		case "armedAway":
			return "away"
			break
		default:
			return "Unknown"
	}
}

private TimeZone mTZ() { return (TimeZone)location.timeZone }

private api_intf_dashboard_load(){
	Map result
//	debug "Dashboard: load ${params}"
	recoveryHandler()
	String s='dashLoad'
	String tok=(String)params.token
	if(verifySecurityToken(tok)){
		result=api_get_base_result(true)

		if((String)params.dashboard=="1"){
			startDashboard()
		}else{
			if((String)state.dashboard!=sINACT) stopDashboard()
		}
	}else{
		if((String)params.pin!=sNULL){
			if(settings.PIN && md5('pin:'+(String)settings.PIN)==(String)params.pin){
				result=api_get_base_result(true)
				result.instance.token=createSecurityToken()
			}else{
				error "Dashboard: Authentication failed due to an invalid PIN"
			}
		}
		if(result==null) result=api_get_error_result(sERRTOK,s)
	}

	if(result)result.remove('now')
	String jsonData= JsonOutput.toJson(result)
	String rl=generateMD5_A(jsonData)
	Long t=wnow()
	if(tlastActivityFLD < (t-11000L) || rl!=lastActivityFLD || tok!=lastActivityTOKFLD){
		//log.warn "rl: $rl lastAct: $lastActivityFLD"
		lastActivityFLD=rl
		lastActivityTOKFLD=tok
	}else result=[:]
	tlastActivityFLD=t

	if((Boolean)getLogging().debug) checkResultSize(result, false, s)

	//for accuracy, use the time as close as possible to the render
	result.now=wnow()
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private api_intf_dashboard_devices(){
	Map result
	String s='dashbaord_devices '
	if(verifySecurityToken((String)params.token)){
		String soffset= "${params.offset}".toString()
		Integer offset= soffset.isInteger() ? soffset.toInteger() : 0
		if(eric())debug s+soffset
		result=listAvailableDevices(false, false, offset) +
				[ deviceVersion: (String)atomicState.deviceVersion ]
	}else{ result=api_get_error_result(sERRTOK,s) }
	//for accuracy, use the time as close as possible to the render
	result.now=wnow()
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private api_intf_dashboard_refresh(){
	debug "Dashboard: Request received to refresh instance"
	startDashboard()
	Map result
	if(verifySecurityToken((String)params.token)){
		result=getDashboardData()
	}else{
		if(result==null) result=api_get_error_result(sERRTOK)
	}
	//for accuracy, use the time as close as possible to the render
	result.now=wnow()
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private Map getDashboardData(){
//	def start=wnow()
	Map result
	def storageApp //= getStorageApp()
	if(storageApp!=null){
		result=storageApp.getDashboardData()
	}else{
		result=((Map<String,Object>)settings).findAll{ ((String)it.key).startsWith("dev:") }.collect{ it.value }.flatten().collectEntries{ dev -> [(hashId(dev.id)): dev]}.collectEntries{ id, dev ->
			[ (id): dev.getSupportedAttributes().collect{ (String)it.name }.unique().collectEntries{
				def value
				try { value=dev.currentValue(it) }catch(ignored){ value=null }
				return [ (it) : value]
			}]
		}
	}
	return result
}

private api_intf_dashboard_piston_new(){
	Map result
	debug "Dashboard: Request received to generate a new piston name"
	String s='piston_new'
	if(verifySecurityToken((String)params.token)){
		result=[(sSTS): sSUCC, (sNM): generatePistonName()]
	}else{ result=api_get_error_result(sERRTOK,s) }
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private api_intf_dashboard_piston_create(){
	Map result
	debug "Dashboard: Request received to create a new piston"
	if(verifySecurityToken((String)params.token)){
		String pname=(String)params.name!=sNULL ? (String)params.name : generatePistonName()
		String n=handlePistn()
		List apps=wgetChildApps().findAll{ (String)it.name==n }
		Boolean found=false
		for(mapp in apps){
			String tN= (String)mapp.label ?: (String)mapp.name
			if(tN==pname){
				found=true
				break
			}
		}
		apps=null
		if(!found){
			try{
				def piston=addChildApp("ady624", handlePistn(), pname)
				debug "created piston $piston.id  params $params"
				if((String)params.author!=sNULL || (String)params.bin!=sNULL){
					piston.config([bin: (String)params.bin, author: (String)params.author, initialVersion: sVER])
				}
				debug "Created Piston "+pname
				result=[(sSTS): sSUCC, id: hashPID(piston.id)]
			}catch(ignored){
				error "Please install the webCoRE Piston app"
				result=[(sSTS): sERROR, (sERR): sERRUNK]
			}
		}else{
			error "create piston: Name in use "+pname
			result=[(sSTS): sERROR, (sERR): sERRUNK]
		}
	}else{ result=api_get_error_result(sERRTOK,'piston_create') }
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private findPiston(String id, String nm=sNULL){
	def piston=null
	if(id!=sNULL || nm!=sNULL){
		String n=handlePistn()
		List t0=wgetChildApps().findAll{ (String)it.name==n }
		if(id!=sNULL){
			piston=t0.find{ hashPID(it.id)==id }
			if (!piston)piston=t0.find{ hashId(it.id)==id }
		}
		if(nm!=sNULL && !piston) piston=t0.find{ (String)it.label==nm }
		t0=null
	}
	return piston
}

private api_intf_dashboard_piston_getDb() {
	Map result=[:]
	if(verifySecurityToken((String)params.token)){
		String serverDbVersion=sHVER
		debug "Dashboard: getDb sending new db current: ${serverDbVersion} in server"
		Map theDb=[
				capabilities: capabilities().sort{ (String)it.value.d },
				commands: [
						physical: commands().sort{ (String)it.value.d!=sNULL ? (String)it.value.d : (String)it.value.n },
						virtual: virtualCommands().sort{ (String)it.value.d!=sNULL ? (String)it.value.d : (String)it.value.n }
				],
				attributes: attributesFLD.sort{ (String)it.key },
				comparisons: comparisonsFLD,
				functions: functionsFLD,
				colors: [
						//standard: colorUtil?.ALL ?: getColors()
						standard: getColors()
				],
		]
		result.dbVersion=serverDbVersion
		result.db=theDb
	}else{ result=api_get_error_result(sERRTOK,'getDb') }
	String wName=sAppId()
	clearBaseResult('get Db',wName)
	result.now=wnow()
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private api_intf_dashboard_piston_get(){
	Map result=[:]
	Boolean requireDb
	String wName=sAppId()
	clearBaseResult('get Piston',wName)
	String s='piston_get'
	if(verifySecurityToken((String)params.token)){
		String pistonId=(String)params.id
		def piston=findPiston(pistonId)
		if(piston!=null){
			debug "Dashboard: Request received to get piston ${pistonId} ${(String)piston.label}"

			String serverDbVersion=sHVER
			String clientDbVersion=(String)params.db
			requireDb=serverDbVersion!=clientDbVersion
			Map t0=(Map)piston.get()
			result.data=t0!=null ? t0 : [:]
			if(requireDb){
				debug "Dashboard: get piston ${params?.id} needs new db current: ${serverDbVersion} in server ${clientDbVersion}"
				/*Map theDb=[
					capabilities: capabilities().sort{ (String)it.value.d },
					commands: [
						physical: commands().sort{ (String)it.value.d!=sNULL ? (String)it.value.d : (String)it.value.n },
						virtual: virtualCommands().sort{ (String)it.value.d!=sNULL ? (String)it.value.d : (String)it.value.n }
					],
					attributes: attributesFLD.sort{ (String)it.key },
					comparisons: comparisonsFLD,
					functions: functionsFLD,
					colors: [
						//standard: colorUtil?.ALL ?: getColors()
						standard: getColors()
					],
				]*/
				result.dbVersion=serverDbVersion
				//result.db=theDb
			}
			if((Boolean)getLogging().debug) checkResultSize(result, requireDb, "get piston")
		}else{
			result=api_get_error_result(sERRID,s)
			warn "Dashboard: get piston bad ID : ${params?.id}"
		}
	}else{
		result=api_get_error_result(sERRTOK,s)
		warn "Dashboard: get piston bad token: ${params}"
	}

	//for accuracy, use the time as close as possible to the render
	result.now=wnow()

	//def jsonData=JsonOutput.toJson(result)
	//log.debug "Trimmed resonse length: ${jsonData.getBytes("UTF-8").length}"
	//render contentType: sAPPJAVA, data: "${params.callback}(${jsonData})"
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private void checkResultSize(Map result, Boolean requireDb=false, String caller=sNULL){
	if(!isCustomEndpoint() || !(Boolean)localHubUrl){
		String jsonData= JsonOutput.toJson(result)
		//data saver for Hubitat ~100K limit
		Integer responseLength=jsonData.getBytes("UTF-8").length
		Integer resl= (Integer)(responseLength / 1024)
		//debug "Check size found ${resl}KB response requireDb: (${requireDb}) caller: ${caller}"
		if(resl > 95){ //these are loaded anyway right after loading the piston
			warn "Trimming ${resl}KB response to smaller size (${requireDb}) caller: ${caller}"

			if((Map)result.data){
				result.data.logs=[]
				result.data.trace=[:]
				result.data.localVars=[:]
				result.data.state=[:]
				result.data.schedules=[]
			}

			Integer svLength=responseLength
			jsonData= JsonOutput.toJson(result)
			responseLength=jsonData.getBytes("UTF-8").length
			resl= (Integer)(responseLength / 1024)
			debug "First Trimmed response length: ${resl}KB"
			if(responseLength==svLength || resl > 105){
				warn "First TRIMMING may be un-successful, trying further trimming ${resl}KB"

				if((Map)result.data){
					result.data.systemVars=[:]
					result.data.stats.timing=[]
				}

				svLength=responseLength
				jsonData= JsonOutput.toJson(result)
				responseLength=jsonData.getBytes("UTF-8").length
				resl= (Integer)(responseLength / 1024)
				debug "Second Trimmed response length: ${resl}KB"
				if(responseLength==svLength || resl > 105){
					warn "Final TRIMMING may be un-successful, you should load a smaller piston then reload this piston ${resl}KB"
				}else warn "Final TRIMMING successful, you should load a small piston again to complete IDE update ${resl}KB"
			}else warn "First TRIMMING successful ${resl}KB"
		}
		//log.debug "Trimmed response length: ${jsonData.getBytes("UTF-8").length}"
	}
}

private api_intf_dashboard_piston_backup(){
	Map result=[
		pistons: [],
		now:0L
	]
	debug "Dashboard: Request received to backup pistons ${params?.ids}"
	if(verifySecurityToken((String)params.token)){
		List pistonIds=((String)params.ids ?: sBLK).tokenize(',')
		String myN= appName()
		for(String pistonId in pistonIds){
			def piston=findPiston(pistonId)
			if(piston){
				Map pd=(Map)piston.get(true)
				if(pd){
					pd.instance=[id: getInstanceSid(), (sNM): myN]
					Boolean a=result.pistons.push(pd)
					if(!isCustomEndpoint() || !(Boolean)localHubUrl){
						String jsonData= JsonOutput.toJson(result)
						Integer responseLength=jsonData.getBytes("UTF-8").length
						if(responseLength > 110 * 1024){
							warn "Backup too big ${ (Integer)(responseLength/1024) }KB response"
						}
					}
				}
			}
		}
	}else{ result=api_get_error_result(sERRTOK,'piston_backup') }
	//for accuracy, use the time as close as possible to the render
	result.now=wnow()
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private String decodeEmoji(String value){
	if(!value) return sBLK
	return value.replaceAll(/(\:%[0-9A-F]{2}%[0-9A-F]{2}%[0-9A-F]{2}%[0-9A-F]{2}\:)/, { m -> URLDecoder.decode(m[0].substring(1, 13), 'UTF-8') })
}

private Map api_intf_dashboard_piston_set_save(String id, String data, Map<String,String>chunks){
	def piston=findPiston(id)
	String myS="Dashboard: Request received to set_save"
	if(piston){
		debug myS
	/*
		def s=decodeEmoji(new String(data.decodeBase64(), "UTF-8"))
		int cs=512
		for (int a=0; a <= Math.floor(s.size() / cs); a++){
			int x=a * cs + cs - 1
		if(x >= s.size()) x=s.size() - 1
			log.trace s.substring(a * cs, x)
		}
	*/
		LinkedHashMap p=(LinkedHashMap) new JsonSlurper().parseText(decodeEmoji(new String(data.decodeBase64(), "UTF-8")))
		Map result=(Map)piston.setup(p, chunks)
		broadcastPistonList()
		return result
	}
	debug myS+" $id $chunks NOT FOUND"
	return null
}

//set is used for small pistons, for large data, using set.start, set.chunk, and set.end
private api_intf_dashboard_piston_set(){
	Map result
	debug "Dashboard: Request received to set a piston"
	if(verifySecurityToken((String)params.token)){
		String data=(String)params?.data
		//save the piston
		Map saved=api_intf_dashboard_piston_set_save((String)params?.id, data, ['chunk:0' : data])
		if(saved){
			if(saved.rtData){
				updateRunTimeData((Map)saved.rtData)
				saved.rtData=null
			}
			result=[(sSTS): sSUCC] + saved
		}else{ result=[(sSTS): sERROR, (sERR): sERRUNK] }
	}else{ result=api_get_error_result(sERRTOK) }
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

@Field volatile static LinkedHashMap<String, LinkedHashMap> pPistonChunksFLD= [:]

private api_intf_dashboard_piston_set_start(){
	Map result
	debug "Dashboard: Request received to set a piston (chunked start)"
	if(verifySecurityToken((String)params.token)){
		String chunkstr="${params?.chunks}".toString()
		Integer chunks=chunkstr.isInteger() ? chunkstr.toInteger() : 0
		String wName=sAppId()
		if((chunks > 0) && (chunks < 100)){
			clearHashMap(wName)
			//atomicState.chunks=[id: params?.id, count: chunks]
			pPistonChunksFLD[wName]=[id: params?.id, count: chunks]
			pPistonChunksFLD=pPistonChunksFLD
			mb()
			result=[(sSTS): "ST_READY"]
		}else{ result=[(sSTS): sERROR, (sERR): "ERR_INVALID_CHUNK_COUNT"] }
	}else{ result=api_get_error_result(sERRTOK) }
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private api_intf_dashboard_piston_set_chunk(){
	Map result
	String wName=sAppId()
	String mchunk="${params?.chunk}".toString()
	Integer chunk=mchunk.isInteger() ? mchunk.toInteger() : -i1
	//debug "Dashboard: Request received to set a piston chunk (#${1 + chunk}/${atomicState.chunks?.count})"
	debug "Dashboard: Request received to set a piston chunk (#${1 + chunk}/${pPistonChunksFLD[wName]?.count})"
	if(verifySecurityToken((String)params.token)){
		String data=(String)params?.data
		//def chunks=atomicState.chunks
		mb()
		LinkedHashMap<String,Object>chunks=pPistonChunksFLD[wName]
		if(chunks && (Integer)chunks.count && (chunk >= 0) && (chunk < (Integer)chunks.count)){
			chunks["chunk:$chunk".toString()]=data
			//atomicState.chunks=chunks
			pPistonChunksFLD[wName]=chunks
			mb()
			result=[(sSTS): "ST_READY"]
		}else{ result=[(sSTS): sERROR, (sERR): sERRCHUNK] }
	}else{ result=api_get_error_result(sERRTOK) }
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private api_intf_dashboard_piston_set_end(){
	Map result
	String wName=sAppId()
	debug "Dashboard: Request received to set a piston (chunked end)"
	if(verifySecurityToken((String)params.token)){
		//def chunks=atomicState.chunks
		mb()
		LinkedHashMap<String,Object> chunks=pPistonChunksFLD[wName]
		if(chunks && (Integer)chunks.count){
			Boolean ok=true
			String data=sBLK
			Integer i=0
			Integer count=(Integer)chunks.count
			while(i<count){
				String s=chunks["chunk:$i".toString()]
				if(s){
					data += s
				}else{
					data=sBLK
					ok=false
					break
				}
				i++
			}
			//atomicState.chunks=null
			//state.remove("chunks")
			pPistonChunksFLD[wName]=null
			mb()
			if(ok){
				//save the piston
				Map saved=api_intf_dashboard_piston_set_save(
						(String)chunks.id,
						data,
						((Map<String,String>)chunks).findAll{ it -> it.key.startsWith('chunk:') }
				)
				if(saved){
					if(saved.rtData){
						updateRunTimeData((Map)saved.rtData)
						saved.rtData=null
					}
					result=[(sSTS): sSUCC] + saved
				}else{ result=[(sSTS): sERROR, (sERR): sERRUNK] }
			}else{ result=[(sSTS): sERROR, (sERR): sERRCHUNK] }
		}else{ result=[(sSTS): sERROR, (sERR): sERRCHUNK] }
	}else{ result=api_get_error_result(sERRTOK) }
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private api_intf_dashboard_piston_pause(){
	Map result
	if(verifySecurityToken((String)params.token)){
		def piston=findPiston((String)params.id)
		if(piston){
			Map rtData=(Map)piston.pausePiston()
			updateRunTimeData(rtData)
			result=[(sSTS): sSUCC, active: false]
		}else result=api_get_error_result(sERRID)
	}else result=api_get_error_result(sERRTOK)
	debug "Dashboard: Request received to pause a piston"
	clearBaseResult('pause piston')
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private api_intf_dashboard_piston_resume(){
	Map result
	if(verifySecurityToken((String)params.token)){
		def piston=findPiston((String)params.id)
		if(piston){
			Map rtData=(Map)piston.resume(null,true)
			result=(Map)rtData.result
			updateRunTimeData(rtData)
			result.status=sSUCC
		}else result=api_get_error_result(sERRID)
	}else result=api_get_error_result(sERRTOK)
	debug "Dashboard: Request received to resume a piston"
	clearBaseResult('resume piston')
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private api_intf_dashboard_piston_test(){
	Map result
	if(verifySecurityToken((String)params.token)){
		def piston=findPiston((String)params.id)
		if(piston!=null){
			result=(Map)piston.test()
			result.status=sSUCC
		}else result=api_get_error_result(sERRID)
	}else result=api_get_error_result(sERRTOK)
	debug "Dashboard: Request received to test a piston"
	clearBaseResult('test piston')
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private api_intf_dashboard_presence_create(){
	Map result
	if(verifySecurityToken((String)params.token)){
		String dni=(String)params.dni
		def sensor=(dni ? getChildDevices().find{ (String)it.getDeviceNetworkId()==dni } : null) ?: addChildDevice("ady624", handlePres(), dni ?: hashId("${wnow()}"), null, [label: params.name])
		if(sensor){
			sensor.label=(String)params.name
			result=[
				(sSTS): sSUCC,
				deviceId: hashId(sensor.id)
			]
			refreshDevices()
		}else result=api_get_error_result("ERR_COULD_NOT_CREATE_DEVICE")
	}else result=api_get_error_result(sERRTOK)
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private api_intf_dashboard_piston_tile(){
	Map result
	debug "Dashboard: Clicked a piston tile"
	if(verifySecurityToken((String)params.token)){
		def piston=findPiston((String)params.id)
		if(piston){
			result=(Map)piston.clickTile(params.tile)
			result.status=sSUCC
		}else result=api_get_error_result(sERRID)
	}else result=api_get_error_result(sERRTOK)
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private api_intf_dashboard_piston_set_bin(){
	Map result
	debug "Dashboard: Request received to set piston bin"
	if(verifySecurityToken((String)params.token)){
		def piston=findPiston((String)params.id)
		if(piston){
			result=(Map)piston.setBin((String)params.bin)
			result.status=sSUCC
		}else{ result=api_get_error_result(sERRID) }
	}else{ result=api_get_error_result(sERRTOK) }
	clearBaseResult('set bin')
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private api_intf_dashboard_piston_set_category(){
	Map result
	String wName=sAppId()
	debug "Dashboard: Request received to set piston category"
	if(verifySecurityToken((String)params.token)){
		def piston=findPiston((String)params.id)
		if(piston){
			result=(Map)piston.setCategory(params.category)
			String myId=(String)params.id
			if(pStateFLD[wName]==null){ pStateFLD[wName]= (Map)[:]; pStateFLD=pStateFLD }
			Map st=(Map)pStateFLD[wName][myId]
			if(st==null) st=(Map)piston.curPState() //st=atomicState[myId]
			if(st){
				st.c=params.category
				pStateFLD[wName][myId]=st
				pStateFLD=pStateFLD
				//atomicState[myId]=st
			}
			result.status=sSUCC
		}else{ result=api_get_error_result(sERRID) }
	}else{ result=api_get_error_result(sERRTOK) }
	clearBaseResult('set category')
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private api_intf_dashboard_piston_logging(){
	Map result
	debug "Dashboard: Request received to set piston logging level"
	if(verifySecurityToken((String)params.token)){
		def piston=findPiston((String)params.id)
		if(piston){
			result=(Map)piston.setLoggingLevel((String)params.level)
			result.status=sSUCC
		}else{ result=api_get_error_result(sERRID) }
	}else{ result=api_get_error_result(sERRTOK) }
	clearBaseResult('change logging')
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private api_intf_dashboard_piston_clear_logs(){
	Map result
	debug "Dashboard: Request received to clear piston logs"
	if(verifySecurityToken((String)params.token)){
		def piston=findPiston((String)params.id)
		if(piston){
			result=(Map)piston.clearLogs()
			result.status=sSUCC
		}else{ result=api_get_error_result(sERRID) }
	}else{ result=api_get_error_result(sERRTOK) }
	clearBaseResult('clear logs')
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private api_intf_dashboard_piston_delete(){
	Map result
	String wName=sAppId()
	debug "Dashboard: Request received to delete a piston"
	if(verifySecurityToken((String)params.token)){
		String id=(String)params.id
		def piston=findPiston(id)
		if(piston){
			if(pStateFLD[wName]==null){ pStateFLD[wName]= (Map)[:]; pStateFLD=pStateFLD }
			pStateFLD[wName][id]=null
			pStateFLD=pStateFLD
			String schld=piston.id.toString()
			if(!cldClearFLD[wName]){ cldClearFLD[wName]=(Map)[:]; cldClearFLD=cldClearFLD }
			cldClearFLD[wName].remove(schld)
			result=(Map)piston.deletePiston()
			app.deleteChildApp(piston.id)
//			p_executionFLD[wName][id]=null
//			p_executionFLD=p_executionFLD
			clearHashMap(wName)
			mb()
			clearBaseResult('delete Piston',wName)
			result=[(sSTS): sSUCC]
			//cleanUp()
			//clearParentPistonCache("piston deleted")
			runIn(10, broadcastPistonList)
		}else{ result=api_get_error_result(sERRID) }
	}else{ result=api_get_error_result(sERRTOK) }
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private api_intf_location_entered(){
	String deviceId=(String)params.device
	String dni=(String)params.dni
	def device=getChildDevices().find{ ((String)it.getDeviceNetworkId()==dni) || (hashId(it.id)==deviceId) }
	if(device && params.place) device.processEvent([(sNM): 'entered', place: params.place, places: state.settings.places])
}

private api_intf_location_exited(){
	String deviceId=(String)params.device
	String dni=(String)params.dni
	def device=getChildDevices().find{ ((String)it.getDeviceNetworkId()==dni) || (hashId(it.id)==deviceId) }
	if(device && params.place) device.processEvent([(sNM): 'exited', place: params.place, places: state.settings.places])
}

private api_intf_location_updated(){
	String deviceId=(String)params.device
	String dni=(String)params.dni
	def device=getChildDevices().find{ ((String)it.getDeviceNetworkId()==dni) || (hashId(it.id)==deviceId) }
	Map location=params.location ? (LinkedHashMap) new JsonSlurper().parseText((String)params.location) : [(sERR): "Invalid data"]
	if(device) device.processEvent([(sNM): 'updated', location: location, places: state.settings.places])
}

private api_intf_variable_set(){
	Map result
	String meth="dashboard variable_set "
	debug meth+"Request received to set a variable"
	String meth1=sNULL
	if(verifySecurityToken((String)params.token)){
		String pid=(String)params.id
		String name=(String)params.name
		def value=params.value ? (LinkedHashMap) new JsonSlurper().parseText(new String(((String)params.value).decodeBase64(), "UTF-8")) : null
		trace meth+"pid: $pid name: $name value: $value"
		Map<String,Map> globalVars
		Map<String,Object> localVars
		if(!pid){
			Boolean chgd=false
			String vln=value ? (String)value.n : sNULL
			if( (name && (Boolean)name.startsWith('@@')) || (vln && vln.startsWith('@@')) ){
				String vn=sNULL
				if(name && !value){
					// delete a global
					vn=name.substring(2)
					chgd=deleteGlobalVar(vn)
					meth1=meth+"DELETE HE global $vn "
					if(!chgd){
						warn meth1+"FAILED"
					}
					else trace meth1
					chgd=true
					//result=[(sNM): name, (sVAL): null, type: null]
				}else if(value && value.n){
					vln=((String)value.n).substring(2)
					if(name=='null') name=sNULL
					if(!name || name!=(String)value.n ){
						if(name){
							vn= name.substring(2)
							meth1=meth+"DELETE before update of HE global $vn "
							try{
								chgd= deleteGlobalVar(vn)
							}catch(ignored){
								meth1=meth+"DELETE not allowed HE global $vn "
							}
							if(!chgd) warn meth1+"FAILED"
							else trace meth1
						}
						// add a variable
						def vl=value.v
						if((String)value.t==sTIME){
							Long aa=vl.toLong()
							// the browers is in local zone but internally HE is utc
							Integer mmtvl=mTZ().rawOffset
							if(eric()) debug "att is adjustment is $mmtvl"
							vl=vl - mmtvl
						}
						Map ta=fixHeGType(true, (String)value.t, vl, sNULL)
						for(it in ta){
							String typ=(String)it.key
							vl=it.value
							meth1=meth+"CREATE HE global $vln ${value.t} ${typ} ${vl} "
							try{
								chgd=createGlobalVar(vln, vl, typ)
							}catch(ignored){
								meth1=meth+"CREATE not allowed HE global $vn "
							}
							if(!chgd) warn meth1+"FAILED"
							else trace meth1
						}
					}else{
						//update a variable
						def hg=getGlobalVar(vln)
						if(!hg) warn meth+"GET HE global failed $vln"
						else{
							def vl=value.v
							if(vl){
								if(eric())debug "vl is ${myObj(vl)}"
								if((String)value.t==sTIME){
									Long aa=vl.toLong()
									if(eric())debug "aa is $aa"
									// the browser is in local zone but internally HE is utc
									if(vl instanceof Long){
										Integer mtvl=mTZ().getOffset(wnow())
										Integer mmtvl=mTZ().rawOffset
										if(eric()) debug "btt is adjustment is ${mmtvl} - ${mtvl}"
										vl=vl-mmtvl-mtvl
									}
									if(eric()) debug "found time $vl"
								}
								Map ta=fixHeGType(true, (String)value.t, vl, (String)hg.type)
								String typ
								for(it in ta){
									typ=(String)it.key
									vl=it.value
									chgd=false
									try{ chgd=setGlobalVar(vln, vl) }catch(ignored){}
									meth1=meth+"SET HE global $vln ${vl} "
									if(!chgd) warn meth1+"FAILED mismatch $vln ${hg.type} ${typ} ${value.t} ${vl}"
									else trace meth1
								}
							}else warn meth1+"no value"
						}
					}
				}
			}else{
				def am=atomicState.vars
				globalVars= am? (Map<String,Map>)am : [:]
				if(name && !value){
					//deleting a variable
					globalVars.remove(name)
					chgd=true
					result=[(sNM): name, (sVAL): null, type: null]
				}else if(value && value.n){
					if(!name || name!=vln ){
						//add a new variable
						if(name) globalVars.remove(name)
						name=vln
					}
					//update a variable
					if(name){
						globalVars[name]=[(sT): (String)value.t, (sV): value.v]
						result=[(sNM): name, (sVAL): value.v, type: (String)value.t]
						chgd=true
					}
				}
				if(chgd){
					atomicState.vars=globalVars
					clearGlobalPistonCache("dashboard set")
					clearBaseResult('api_intf_variable_set')
					//noinspection GroovyVariableNotAssigned
					sendVariableEvent(result)
				}else trace meth+"SET webcore global FAILED $name"
			}
			if(chgd){
				// return all globals
				globalVars=(Map)atomicState.vars
				globalVars=globalVars ?: [:]
				Map heV=AddHeGlobals(globalVars, meth)
				globalVars=globalVars+heV
				result=[(sSTS): sSUCC]+[globalVars: globalVars]
			}else result=[(sSTS): sERROR, (sERR): sERRUNK]
		}else{
			def piston=findPiston(pid)
			if(piston){
				localVars=(Map)piston.setLocalVariable(name, value.v)
				//clearBaseResult('api_intf_variable_set')
				result=[(sSTS): sSUCC] + [id: pid, localVars: localVars]
			}else{ result=api_get_error_result(sERRID) }
		}
	}else{ result=api_get_error_result(sERRTOK) }
	clearBaseResult('set var')
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

//@Field static final String sDTIME='datetime'
@Field static final String sTIME='time'
@Field static final String sDATE='date'
//@Field static final String sSTR='string'
//@Field static final String sINT='integer'
//@Field static final String sDEC='decimal'
//@Field static final String sDYN='dynamic'
//@Field static final String sBOOLN='boolean'
@Field static final String sDEV='device'

@Field static final Long lMSDAY=86400000L

private Long getMidnightTime(){
	return (Long)((Date)timeToday('00:00',mTZ())).getTime()
}




private void resetFuelStreamList(){
	state.fuelStreams=[]
/*
	name=handleFuelS()
	fuelStreams=wgetChildApps().findAll{ it.name==name }.collect { it.label }
	state.fuelStreams=fuelStreams
*/
	state.remove("fuelStreams")
}

def findCreateFuel(Map req){
	String n=handleFuelS()
	def result
	result=null

	// LTS can return multple streams
	if(req.c == 'LTS'){
		def lts = getChildAppByLabel("webCoRE Long Term Storage")
		String[] s= ((String)req.n).split('_')
		String sensorId= s[0]
		String attribute= s[1]
		if (lts!=null && (Boolean)lts.isStorage(sensorId, attribute)){
			result= lts
		}

	} else {
		String streamName="${(req.c ?: sBLK)}||${req.n}"
		List l
		l=wgetChildApps().findAll{ (String)it.name== n && ((String)it.label)?.contains(streamName)}
		for (sa in l){
			String sl=(String)sa.label
			Integer ndx=sl.indexOf(' - ' )
			if (ndx >= 0){
				String lbl=sl.substring(ndx + 3)
				if(lbl==streamName){
					result=sa
					break
				}
			}
		}
		l=null

		if(!result){
			def t0= wgetChildApps().findAll{ (String)it.name==n && ((String)it.label)?.contains(' - ') }.collect{ ((String)it.label).split(' - ')[0].toInteger() }.max()
			//def t0=wgetChildApps().findAll{ (String)it.name==n }.collect{ ((String)it.label).split(' - ')[0].toInteger()}.max()
			def id=(t0 ?: 0) + 1
			try{
				result=addChildApp('ady624', n, "$id - $streamName")
				result.createStream([id: id, (sNM): req.n, canister: req.c ?: sBLK])
			}
			catch(ignored){
				error "Please install the webCoRE Fuel Streams app for local Fuel Streams"
				return null
			}
		}
	}
	result
}

List<Map> readFuelStream(Map req){
	def result=findCreateFuel(req)
	if(result) return result.readFuelStream(req)
	return null
}

void writeFuelStream(Map req){
	def result=findCreateFuel(req)
	if(result)result.writeFuelStream(req)
}

void clearFuelStream(Map req){
	def result=findCreateFuel(req)
	if(result)result.clearFuelStream(req)

}

void writeToFuelStream(Map req){
	def result=findCreateFuel(req)
	if(result)result.updateFuelStream(req)
}

List listFuelStreams(Boolean includeLTS=true){
	List result
	result = []
	String n=handleFuelS()
	List chlds = wgetChildApps().findAll{ (String)it.name==n }
	chlds.each { it ->
		List a = (List)it.getFuelStreams(includeLTS)
		if(a) result+= a
	}
	return result
}

private api_intf_fuelstreams_list(){
	debug "Dashboard: Request received to list fuelstreams"
	List result
	result = listFuelStreams()
	/*
	result = []
	//if(verifySecurityToken((String)params.token)){
	String n=handleFuelS()
	List chlds = wgetChildApps().findAll{ (String)it.name==n }
	//result=wgetChildApps().findAll{ (String)it.name==n }*.getFuelStreams()
	chlds.each { it ->
		Map a = it.getFuelStreams()
		if(a) result << a
	}
	*/

	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(["fuelStreams" : result])})"
}

private api_intf_fuelstreams_get(){
	List result
	result=[]
	String id=(String)params.id
	debug "Dashboard: Request received to get fuelstream data $id"

	//if(verifySecurityToken((String)params.token)){
	String n=handleFuelS()
	// TODO if LTS stream form, need to find proper LTS and pass stream id
	def stream
	if(id.isNumber()){
		stream=wgetChildApps().find { (String)it.name==n && ((String)it.label).startsWith("$id -")}
		result=stream.listFuelStreamData(id)
	} else {
		stream = getChildAppByLabel("webCoRE Long Term Storage")
	}
	if(stream)
		result=stream.listFuelStreamData(id)

	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(["points" : result])})"
}


// may not need
Map quantParams(sensorId, String attribute){
	def lts = getChildAppByLabel("webCoRE Long Term Storage")

	if (lts!=null) {
		return (Map)lts.quantParams(sensorId, attribute)
	} else return null
}

Boolean ltsExists(){
	def lts = getChildAppByLabel("webCoRE Long Term Storage")
	return (lts!=null)
}

// child graphs calls this
Boolean ltsAvailable(sensorId, String attribute){
	def lts = getChildAppByLabel("webCoRE Long Term Storage")

	if (lts!=null){
		return (Boolean)lts.isStorage(sensorId, attribute)
	}
	return false
}

Boolean ltsQuant(sensorId, String attribute){
	def lts = getChildAppByLabel("webCoRE Long Term Storage")

	if (lts!=null){
		return (Boolean)lts.isQuant(sensorId, attribute)
	}
	return false
}

Map openWeatherConfig(){
	String weatherTyp= settings.weatherType ? (String)settings.weatherType : sNULL
	if( state.storAppOn && weatherTyp=='OpenWeatherMap') {
		return [latitude: settings.zipCode, longitude: settings.zipCode1, apiKey: settings.apixuKey, pollInterval: '30 Minutes']
	}
	return null
}

Map getWData(){
	def storageApp=getStorageApp(true)
	Map t0
	t0=[:]
	if(storageApp){
		t0=storageApp.getWData()
	}
	return t0
}

String getOpenWeatherData(){
	def childDevice = getChildDevice("OPEN_WEATHER${app.id}")
	if (!childDevice){
		log.debug("Error: No Child Found")
		return sNL
	}
	return childDevice.getWeatherData()
}






private api_intf_settings_set(){
	Map result
	debug "Dashboard: Request received to set settings"
	if(verifySecurityToken((String)params.token)){
		String pset=(String)params.settings
		LinkedHashMap msettings=pset ? (LinkedHashMap) new JsonSlurper().parseText(new String(pset.decodeBase64(), "UTF-8")) : null
		atomicState.settings=msettings

		clearParentPistonCache("dashboard changed settings")
		clearBaseResult('settings change')

		testLifx()
		result=[(sSTS): sSUCC]
	}else{ result=api_get_error_result(sERRTOK) }
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

private api_intf_dashboard_piston_evaluate(){
	Map result
	debug "Dashboard: Request received to evaluate an expression"
	if(verifySecurityToken((String)params.token)){
		def piston=findPiston((String)params.id)
		if(piston){
			LinkedHashMap expression=(LinkedHashMap) new JsonSlurper().parseText(new String(((String)params.expression).decodeBase64(), "UTF-8"))
			Map msg=timer "Evaluating expression"
			result=[(sSTS): sSUCC, (sVAL): piston.proxyEvaluateExpression(null /* getRunTimeData()*/, expression, (String)params.dataType)]
			trace msg
		}else{ result=api_get_error_result(sERRID) }
	}else{ result=api_get_error_result(sERRTOK) }
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

@Field volatile static String lastActivityFLD
@Field volatile static String lastActivityTOKFLD
@Field volatile static Long tlastActivityFLD=0L

private api_intf_dashboard_piston_activity(){
	Map result
	//debug "Dashboard: Activity request received $params"
	String tok=(String)params.token
	if(verifySecurityToken(tok)){
		String pistonId=(String)params.id
		def piston=findPiston(pistonId)
		if(piston!=null){
			Map t0=(Map)piston.activity(params.log)
			String jsonData= JsonOutput.toJson(t0)
			String rl=generateMD5_A(jsonData)
			if(rl!=lastActivityFLD || tok!=lastActivityTOKFLD){
				lastActivityFLD=rl
				lastActivityTOKFLD=tok
				result=[(sSTS):sSUCC, activity: (t0 ?: [:]) + [globalVars: listAvailableVariables1()/*, mode: hashId(location.getCurrentMode().id), shm: location.currentState("alarmSystemStatus")?.value, hubs: location.getHubs().collect{ [id: hashId(it.id), (sNM): it.name, firmware: it.getFirmwareVersionString(), physical: it.getType().toString().contains('PHYSICAL'), powerSource: it.isBatteryInUse() ? 'battery' : 'mains' ]}*/]]
			} else result=[(sSTS):sSUCC, activity: [:]]
			tlastActivityFLD=wnow()
		}else{ result=api_get_error_result(sERRID) }
	}else{ result=api_get_error_result(sERRTOK) }
	if((Boolean)getLogging().debug) checkResultSize(result, false, "piston activity")
	render contentType: sAPPJAVA, data: "${params.callback}(${JsonOutput.toJson(result)})"
}

def api_ifttt(){
	def data=[:]
	//def remoteAddr=isHubitat() ? "UNKNOWN" : request.getHeader("X-FORWARDED-FOR") ?: request.getRemoteAddr()
	def remoteAddr=request.headers.'X-forwarded-for' ?: request.headers.Host
	if(remoteAddr==null)remoteAddr=request.'X-forwarded-for' ?: request.Host
	debug "Request received ifttt call IP $remoteAddr Referer: ${request.headers.Referer}"
//log.debug "params ${params}"
	if(params){
		data.params=[:]
		for(param in params){
			if(!((String)param.key in ['access_token', 'theAccessToken', 'appId', 'action', 'controller'])){
				data[(String)param.key]=param.value
			}
		}
	}
	data=data + (request?.JSON ?: [:])
	data.remoteAddr=remoteAddr
	String eventName=(String)params?.eventName
	if(eventName){
		sendLocationEvent([(sNM): "ifttt.${eventName}", (sVAL): eventName, isStateChange: true, linkText: "IFTTT event", descriptionText: "${handle()} has received an IFTTT event: $eventName", (sDATA): data])
	}
	render contentType: "text/html", data: "<!DOCTYPE html><html lang=\"en\">Received event $eventName.<body></body></html>"
}

def api_email(){
	def data=request?.JSON ?: [:]
	def from=data.from ?: sBLK
	def pistonId=params?.pistonId
	if(pistonId){
		sendLocationEvent([(sNM): "email.${pistonId}", (sVAL): pistonId, isStateChange: true, linkText: "Email event", descriptionText: "${handle()} has received an email from $from", (sDATA): data])
	}
	render contentType: "text/plain", data: 'OK'
}

private api_execute(){
	Map data,result
	result=[:]
	data=[:]
	//def remoteAddr=isHubitat() ? "UNKNOWN" : request.getHeader("X-FORWARDED-FOR") ?: request.getRemoteAddr()
	def remoteAddr
	remoteAddr=request.headers.'X-forwarded-for' ?: request.headers.Host
	if(remoteAddr==null)remoteAddr=request.'X-forwarded-for' ?: request.Host
	if(remoteAddr==null)remoteAddr='just'
	debug "Dashboard or web request received to execute a piston from IP $remoteAddr Referer: ${request.headers.Referer}"
//log.debug "params ${params} request: ${request}"
	if(params){
		for(param in params){
			if(!((String)param.key in ['access_token', 'pistonIdOrName'])){
				data[(String)param.key]=param.value
			}
		}
	}
	data=data+(request?.JSON ?: [:])
	data.remoteAddr=remoteAddr
	data.referer=request.headers.Referer
	String pistonIdOrName=(String)params?.pistonIdOrName
	def piston= findPiston(pistonIdOrName,pistonIdOrName)
	if(piston!=null){
		sendExecuteEvt(hashPID(piston.id),
				remoteAddr,
				"Execute event",
				"External piston execute ${(String)piston.label} request from IP $remoteAddr".toString(),
				data)
		result.result='OK'
	}else{
		result.result='ERROR'
		error "Piston not found for dashboard or web Request to execute a piston from IP $remoteAddr $pistonIdOrName"
	}
	result.timestamp=(new Date()).time
	render contentType: sAPPJSON, data: JsonOutput.toJson(result)
}

void sendExecuteEvt(String pistonId,val,String desc, String desc1,Map data){
	String json=JsonOutput.toJson(data)
	sendLocationEvent((sNM):pistonId,(sVAL):val,isStateChange:true,displayed:false,linkText:desc,descriptionText:desc1,data:json)
}

// get webcore global variables
private api_global(){
	def remoteAddr
	remoteAddr=request.headers.'X-forwarded-for' ?: request.headers.Host
	if(remoteAddr==null)remoteAddr=request.'X-forwarded-for' ?: request.Host
	if(remoteAddr==null)remoteAddr='just'
	debug "web request received to get variable from IP $remoteAddr Referer: ${request.headers.Referer} | $params"
	Map result=[:]
	Boolean err=true
	String varName=(String)params?.varName
	if(varName && (Boolean)varName.startsWith('@') ){
		if((Boolean)varName.startsWith('@@')){
			String vn=varName.substring(2)
			def hg=getGlobalVar(vn)
			if(hg){ // could return these as webcore types....this uses what is in HE
				result.val=hg.value
				result.type=hg.type
				result.name=vn
				result.desc='HE Hub variable'
				err=false
			}
		}else{
			def am=atomicState.vars
			Map<String,Map> vars= am? (Map<String,Map>)am : [:]
			if(vars[varName]){
				result.val=vars[varName].v
				result.type=vars[varName].t
				result.name=varName
				result.desc='webCoRE global variable'
				err=false
			}
		}
		if(!err) result.result='OK'
	}
	if(err){
		result.result='ERROR'
		error "variable not found for web Request to get variable from IP $remoteAddr $varName"
	}
	Integer st= err ? 400 : 200
	result.timestamp=(new Date()).time
	render contentType: sAPPJAVA, data: JsonOutput.toJson(result), status: st
}

@Field volatile static Map<String,Long> lastRecoveredFLD= [:]
@Field static Map<String,String> verFLD= [:]
@Field static Map<String,String> HverFLD= [:]

void resetMemSt(String meth,String wName){
	atomicState.hsmAlerts=[] // reload or restart
	state.hsmAlerts=[]
	verFLD[wName]=sVER
	HverFLD[wName]=sHVER
	mb()
	clearParentPistonCache(meth)
}

@Field static final String sVC='ver check'
@CompileStatic
void verCheck(String wName){
	if(verFLD[wName]==sVER && HverFLD[wName]==sHVER) return
	if(verFLD[wName]==sNULL || HverFLD[wName]==sNULL){
		if((String)gtSt('cV')==sVER && (String)gtSt('hV')==sHVER){
			resetMemSt(sVC,wName)
			clearBaseResult(sVC,wName)
		}
	}
	if(verFLD[wName]!=sVER || HverFLD[wName]!=sHVER){
		info "webCoRE software Updated to "+sVER+" HE: "+sHVER
		resetMemSt(sVC,wName)
		updated()
	}
}

void recoveryHandler(){
	String wName=sAppId()
	verCheck(wName)

	Long t=wnow()
	Long lastRecovered=lastRecoveredFLD[wName]
	lastRecovered=lastRecovered ?: 0L
	Long recTime=900000L // 15 min in ms
	if(lastRecovered!=0L && (t-lastRecovered) < recTime) return
	lastRecoveredFLD[wName]=t
	Integer delay=Math.round(200.0D * Math.random()).toInteger() // seconds
	runIn(delay, finishRecovery)
}

@CompileStatic
void finishRecovery(){
	registerInstance(false)
	Long recTime=300000L  // 5 min in ms
	String n=handlePistn()
	Long lnow=wnow()
	Long threshold= lnow-recTime
	String wName=sAppId()

	List fPs
	fPs=presult(wName).findAll{
		//[ id: myId, (sNM): normalizeLabel(it), meta: [:]+meta ]
		Map meta=(Map)it.meta
		meta!=null && (Boolean)meta.a && meta.n && (Long)meta.n < threshold
	}
	Integer i
	i= fPs.size()
	if(i){
		i=0
		Long delay=Math.round(2000.0D * Math.random()) // 2 sec
		for (piston in fPs){
			String myId=(String)piston.id
			Map meta=(Map)pStateFLD[wName][myId]
			if((Long)meta.n < threshold){
				if(i!=0) wpauseExecution(delay)
				i++
				sendExecuteEvt((String)piston.id,'recovery',"Recovery event","Recovery event for piston $piston.name",null)
				warn "Piston $piston.name was sent a recovery signal because it was ${lnow - (Long)meta.n}ms late"
			}
		}
	}
	fPs=null
}

/******************************************************************************/
/*** PRIVATE METHODS								***/
/******************************************************************************/

private void cleanUp(){
    try{
		//List pistons=wgetChildApps().collect{ hashPID(it.id) }
		for (item in ((Map<String,Object>)state).findAll{ (it.key.startsWith('sph') || it.key.contains('-') ) }){
			state.remove(item.key)
		}

		List data=['version','versionHE','chunks','hash','virtualDevices','updateDevices',
				   'semaphore','pong','modules','globalVars','devices','migratedStorage','lastRecovered','lastReg','lastRegTry']
		for(String foo in data)state.remove(foo)

		String n=handlePistn()
		String myId
		List t0=wgetChildApps().findAll{ (String)it.name==n }
		for(it in t0){
			myId=hashId(it.id)
			state.remove(myId)
			myId=hashPID(it.id)
			state.remove(myId)
		}
		t0=null
		Map a=api_get_base_result(true)
	}catch(ignored){}
}

private getStorageApp(Boolean install=false){
	String n=handleStor()
	def storageApp=wgetChildApps().find{ (String)it.name==n }

	String n1=handleWeat()
	def weatDev=getChildDevices().find{ (String)it.name==n1 }

	if(storageApp!=null){

/*
// Hubitat does not use storage app for settings for performance reasons;  Someone could have created it elsewhere in UI
		if(storageApp.getStorageSettings()!=null){ //migrate settings off of storage app
			storageApp.getStorageSettings().findAll { it.key.startsWith('dev:') }.each {
				app.updateSetting(it.key, [type: 'capability', (sVAL): it.value.collect { it.id }])
			}
		}
		app.deleteChildApp(storageApp.id)
		return null
*/
	}

	String myN= appName()
	String label=myN+sSTOR
	String label1=myN+sWEAT
	if(storageApp!=null){
		if(label!=storageApp.label){
			storageApp.updateLabel(label)
		}
		if(storageApp!=null && weatDev!=null) return storageApp
	}

	if(install){
		if(storageApp==null){
			try{
				storageApp=addChildApp("ady624", n, label)
			}catch(ignored){
				error "Please install the webCoRE Storage App for \$weather to work"
				return null
			}
		}
		if(weatDev==null){
			try{
				weatDev=addChildDevice("ady624", n1, hashId("${wnow()}"), null, [label: label1])
			}catch(ignored){
//				error "Please install the webCoRE Weather Device for \$weather notification to work"
//				return null
			}
		}
	}
/*
	try{
		storageApp.initData(settings.collect{ it.key.startsWith('dev:') ? it : null }, settings.contacts)
		for (item in settings.collect{ it.key.startsWith('dev:') ? it : null }){
			if(item && item.key){
				//app.updateSetting(item.key, [type: sTXT, (sVAL): null])
				app.clearSetting("${item.key}".toString())
			}
		}
		//app.updateSetting('contacts', [type: sTXT, (sVAL): null])
		app.clearSetting('contacts')
	}catch(all){
	}
*/

	return storageApp
}

def getWeatDev(){
	String n=handleWeat()
	def weatDev=getChildDevices().find{ (String)it.name==n }
	return weatDev
}

private getDashboardApp(Boolean install=false){
	if(!settings.enableDashNotifications) return null
	String name=handle()+' Dashboard'
	String myN= appName()
	String label=myN+' (dashboard)'
	def dashboardApp=wgetChildApps().find{ (String)it.name==name }
	if(dashboardApp!=null){
		if(!settings.enableDashNotifications){
			app.deleteChildApp(dashboardApp.id)
			return null
		}
		if(label!=dashboardApp.label){
			dashboardApp.updateLabel(label)
		}
		return dashboardApp
	}
	try{
		dashboardApp=addChildApp("ady624", name, myN)
	}catch(ignored){
		return null
	}
	return dashboardApp
}

private String customApiServerUrl(String path){
	path= path ?: sBLK
	if(!path.startsWith(sDIV)){
		path=sDIV + path
	}
	if( !(Boolean)settings.localHubUrl){
		return apiServerUrl("$hubUID/apps/${app.id}".toString()) + path
	}
	return localApiServerUrl(sAppId()) + path
}

private Boolean isCustomEndpoint(){
	(Boolean)settings.customEndpoints && (Boolean)settings.localHubUrl
}

String getDashboardUrl(){
	if(!(String)state.endpoint) return sNULL

	String aa= settings.customWebcoreInstanceUrl
	if((Boolean)customEndpoints && aa){
		if(aa.endsWith(sDIV)) return aa
		else return aa + sDIV
	}else{
	//if((Boolean)state.installed && (Boolean)settings.agreement){
		return "https://dashboard.${domain()}/".toString()
	}
}

private String getDashboardInitUrl(Boolean reg=false){
	if(eric()) debug "getDashboardInitUrl: reg: $reg"
	String url=reg ? getDashboardRegistrationUrl() : getDashboardUrl()
	if(!url) return sNULL
	String t0=url + (reg ? "register/" : "init/")
	String regkey
	if(isCustomEndpoint()){
		if(eric())debug "getDashboardInitUrl: isCustomEndpoint"
		//regkey=customApiServerUrl('/')
		//regkey=customApiServerUrl('/?access_token=' + state.accessToken)
		regkey=customApiServerUrl('/?access_token=' + (String)state.accessToken).bytes.encodeBase64()
		if(eric())debug "getDashboardInitUrl: t0 $t0"
		if(eric())debug "getDashboardInitUrl: regkey $regkey"
		t0= t0+regkey
	}else{
		//if((Boolean)state.installed && (Boolean)settings.agreement){
		if(eric())debug "getDashboardInitUrl: NOT isCustomEndpoint"
		regkey= apiServerUrl(sBLK)

//		log.debug "t0 $t0"
/*		String a =(
			regkey.replace('http://',sBLK).replace('https://', sBLK).replace('.api.smartthings.com', sBLK).replace(':443', sBLK).replace('/', sBLK) +
			(hubUID.toString() + sAppId()).replace("-", sBLK) + '/?access_token=' + (String)state.accessToken ) */
//		log.debug "regkey $a"
		t0=t0+( regkey.replace('http://',sBLK).replace('https://', sBLK).replace('.api.smartthings.com', sBLK).replace(':443', sBLK).replace(sDIV, sBLK) +
			(hubUID.toString() + sAppId()).replace("-", sBLK) + '/?access_token=' + (String)state.accessToken ).bytes.encodeBase64()
	}
	if(eric())debug "getDashboardInitUrl result: $t0"
	return t0
}

private String getDashboardRegistrationUrl(){
	if((String)state.accessToken) updateEndpoint()
	if(!(String)state.endpoint) return sNULL
	//if((Boolean)state.installed && (Boolean)settings.agreement){
	return "https://api.${domain()}/dashboard/".toString()
}

Map listAvailableDevices(Boolean raw=false, Boolean updateCache=false, Integer offset=0){
	Long time=wnow()
	def storageApp //=getStorageApp()
	Map result=[:]
	if(storageApp){
		result=storageApp.listAvailableDevices(raw, offset)
	}else{
		List myDevices=(List)((Map<String,Object>)settings).findAll{ it.key.startsWith("dev:") }.collect{ it.value }.flatten().sort{ it.getDisplayName() }
		List devices=(List)myDevices.unique{ it.id }
		if(raw){
			result=devices.collectEntries{ dev -> [(hashId(dev.id)): dev]}
		}else{
			Integer deviceCount=devices.size()
			result.devices=[:]
			if(devices){
				devices=devices[offset..-i1]
				Integer dsz=devices.size()
				result.complete=!devices.indexed().find{ Integer idx, dev ->
//				log.debug "Loaded device at ${idx} after ${now() - time}ms. Data size is ${result.toString().size()}"
					result.devices[hashId(dev.id)]=getDevDetails(dev, true)

					Boolean stop=false
					String jsonData=JsonOutput.toJson(result)
					Integer responseLength=jsonData.getBytes("UTF-8").length
					if(responseLength > 50000 || wnow()-time > 4000) stop=true
					if(stop && idx < dsz-i1 ){
						result.nextOffset= offset+idx+i1
						return true
					}
					false
				}
			}else result.complete=true
			debug "Generated list of ${offset}-${offset + ((Map)result.devices).size()-i1} of ${deviceCount} devices in ${wnow() - time}ms. Data size is ${result.toString().size()}"
		}
		myDevices=null
		devices=null
	}
	if(raw || (Boolean)result.complete){
		String n=handlePres()
		List presenceDevices=getChildDevices().findAll{ (String)it.name==n }
		if(presenceDevices && presenceDevices.size()){
			if(raw){
				result << presenceDevices.collectEntries{ dev -> [(hashId(dev.id)): dev]}
			}else{
				result.devices << presenceDevices.collectEntries{ dev -> [(hashId(dev.id)): dev]}.collectEntries{ id, dev ->
					[ (id): getDevDetails(dev) ]
				}
			}
		}
		presenceDevices=null
	}
	return result
}

Map getDevDetails(dev, Boolean addtransform=false){
	Map<String,Map> overrides=commandOverrides()
	String nm=dev.getDisplayName()
	List cmdL=dev.getSupportedCommands()
	cmdL=cmdL.unique{ (String)it.getName() }
	List newCL=[]
	for(cmd in cmdL){
		Map mycmd=[:]
		mycmd.n=cmd.getName()
		mycmd.p=cmd.getArguments()
		newCL.push(mycmd)
		if(addtransform){
			String an=transformCommand(cmd,overrides,nm)
			if(an){
				mycmd.n=an
				newCL.push(mycmd)
			}
		}
	}
	return [
			(sN): nm,
			cn: dev.getCapabilities()*.name,
			(sA): ((List)dev.getSupportedAttributes()).unique{ (String)it.name }.collect{
				//Map x=[
				[
					(sN): (String)it.name,
					(sT): it.getDataType(),
					(sO): it.getValues()
				]
//				try { // removed from UI in 9/2019
//					x.v= dev.currentValue(x.n)
//				} catch(ignored){}
//				x
			},
			/*(sC): dev.getSupportedCommands().unique{ transform ? transformCommand(it, overrides) : it.getName() }.collect{[
					(sN): transform ? transformCommand(it, overrides) : it.getName(),
					(sP): it.getArguments()
			]} */
			(sC): newCL.unique{ (String)it.n }
	]
}

/*
 Not implemented zwave poller control:
 To add devices to the poll list:
 sendLocationEvent((sNM): "startZwavePoll", (sVAL): devList)

 To remove devices from the poll list:
 sendLocationEvent((sNM): "stopZwavePoll", (sVAL): devList)

 Z-Wave Poller only supports Generic Z-Wave Dimmer and Generic Z-Wave Switch. It won't work with other drivers, as there is a handshake with the driver.

 You can determine if Z-Wave Poller is installed with this:
 isAppInstalled("hubitat", "Z-Wave Poller", "SYSTEM")
*/

private String transformCommand(command, Map<String,Map> overrides, String dvn){
	String nm=(String)command.getName()
	def override=overrides.find{ (String)it.value.c==nm }
//	Map override=overrides[(String)command.getName()]
	if(override){
		String mcommand=(String)override.value.r
		def args= command.getArguments()?.toString()
		if(override.value.s.toString()==args){
			if(eric())debug "transformCommand device $dvn  cmd: $nm  -> $mcommand override: $override commandargs: $args"
			return mcommand
		}
	}
	return sNULL
}

private void setPowerSource(String powerSource, Boolean atomic=true){
	if(state.powerSource==powerSource) return
	atomicState.powerSource=powerSource
	sendLocationEvent([(sNM): 'powerSource', (sVAL): powerSource, isStateChange: true, linkText: "webCoRE power source event", descriptionText: handle()+" has detected a new power source: "+powerSource])
}

private Map AddHeGlobals(Map<String,Map> globalVars, String meth){
	Map<String,Map> res=[:]
	def heV=getAllGlobalVars()
	//if(eric()) trace meth+" ALL HE globals $heV"
	String nm
	Map ta
	String typ
	def vl
	heV?.each{
		nm='@@'+(String)it.key
		ta=fixHeGType(false, (String)it.value.type, it.value.value, sNULL)
		for(iit in ta){
			typ=(String)iit.key
			vl=iit.value
		}
		res[nm]=[(sT):typ,(sV): vl]
	}
	return res
}

Map listAvailableVariables(){
	Map myV=(Map)gtAS('vars')
	return listAV(myV, 'list variables')
}

private Map listAvailableVariables1(){
	Map myV=(Map)gtSt('vars')
	return listAV(myV, 'list variables1')
}

private Map listAV(Map my, String meth){
	Map<String,Map> myV
	myV=my ?: [:]
	//'@@'
	Map heV=AddHeGlobals(myV, meth)
	myV=myV+heV
	return (myV ?: [:]).sort{ (String)it.key }
}

Map getGStore(){
	Map myS=(Map)atomicState.store
	return (myS ?: [:]).sort{ (String)it.key }
}

List getPushDev(){
	return (settings.pushDevice ?: [])
}

private void initTokens(){
	debug "Dashboard: Initializing security tokens"
	atomicState.securityTokens=[:]
}

private Boolean verifySecurityToken(String tokenId){
	//trace "verifySecurityToken ${tokenId}"
	LinkedHashMap<String,Long> tokens=state.securityTokens
	if(!tokens || !tokenId) return false
	Long threshold=wnow()
	Boolean modified=false
	//remove all expired tokens
	for (token in tokens.findAll{ (Long)it.value < threshold }){
		tokens.remove((String)token.key)
		modified=true
	}
	if(modified){
		atomicState.securityTokens=tokens
	}
	Long token=(Long)tokens[tokenId]
	Long lnow=wnow()
	if(token && token < lnow){
		if(tokens) error "Dashboard: Authentication failed due to an invalid token"
	}
	return token && token >= lnow
}

private String createSecurityToken(){
	trace "Dashboard: Generating new security token after a successful PIN authentication"
	String token=UUID.randomUUID().toString()
	Map a= atomicState.securityTokens
	LinkedHashMap<String,Long> tokens= (a ?: [:]) as LinkedHashMap<String,Long>
	Long mexpiry=0L
	String eo=((String)settings.expiry).toLowerCase().replace("every ", sBLK).replace("(recommended)", sBLK).replace("(not recommended)", sBLK).trim()
	switch(eo){
		case "hour": mexpiry=3600L; break
		case "day": mexpiry=86400L; break
		case "week": mexpiry=604800L; break
		case "month": mexpiry=2592000L; break
		case "three months": mexpiry=7776000L; break
		case "never": mexpiry=3110400000L; break //never means 100 years, okay?
	}
	tokens[token]=(Long)Math.round(wnow() + (mexpiry * 1000.0D))
	atomicState.securityTokens=tokens
	return token
}

private void ping(){
	sendLocationEvent( [(sNM): handle(), (sVAL): 'ping', isStateChange: true, displayed: false, linkText: "${handle()} ping reply", descriptionText: "${handle()} has received a ping reply and is replying with a pong", (sDATA): [id: getInstanceSid(), (sNM): appName()]] )
}

private void startDashboard(){
	//debug "startDashboard"
	def dashboardApp=getDashboardApp()
	if(!dashboardApp) return //false
	Map t0=listAvailableDevices(true)
	dashboardApp.start(t0.collect{ it.value }, getInstanceSid())
	if((String)state.dashboard!=sACT){
		atomicState.dashboard=sACT
	}
}

private void stopDashboard(){
	//debug "stopDashboard"
	def dashboardApp=getDashboardApp()
	if(!dashboardApp) return //false
	dashboardApp.stop()
	if((String)state.dashboard!=sINACT) atomicState.dashboard=sINACT
}

private String accountSid(){
	Boolean useNew=state.properSID!=null ? (Boolean)state.properSID : true
	String t='-A'
	String accountStr= hubUID.toString() + (useNew ? t : sNULL)
	if(acctANDloc()) accountStr= (String)settings.acctID
	//if(eric()) debug "instance acct: $accountStr"
	return hashId(accountStr)
}

@Field static Map<String,String> locFLD= [:]
@Field static Map<String,Boolean> acctlocFLD= [:]

private Boolean acctANDloc(){
	String wName=sAppId()
	Boolean t=acctlocFLD[wName]
	if(t==null){
		t= ((String)settings.acctID && (String)settings.locID)
		acctlocFLD[wName]=t
	}
	return t
}

@Field static final String sML='-L'

private String locationSid(){
	String wName=sAppId()
	String t=locFLD[wName]
	if(t==sNULL){
		if(acctANDloc()) t= (String)settings.acctID + (String)settings.locID + sML
		else{
			Boolean useNew=state.properSID!=null ? (Boolean)state.properSID : true
			t= (useNew ? hubUID.toString()+location.name.toString() : location.id.toString()) + sML
		}
		//if(eric()) debug "instance location: $t"
		t= hashId(t)
		locFLD[wName]=t
	}
	return t
}

private String getInstanceSid(){
	Boolean useNew=state.properSID!=null ? (Boolean)state.properSID : true
	String hsh=sAppId()
	String t='-I'
	String instStr=useNew ? hubUID.toString()+hsh+t : hsh
	//if(eric()) debug "instance ID: $instStr"
	return hashId(instStr)
}

private void testLifx(){
	String token=state.settings?.lifx_token
	if(!token) return
	testLifx1(true)
	runIn(4, testLifx1)
}

private void testLifx1(Boolean first=false){
	String token=state.settings?.lifx_token
	if(!token) return
	Map requestParams= [
		uri:  "https://api.lifx.com",
		path: "/v1/scenes",
		headers: [ "Authorization": "Bearer ${token}" ],
		requestContentType: sAPPJSON,
		timeout:20

	]
	if(first) asynchttpGet('lifxHandler', requestParams, [request: 'scenes'])
	else{
		requestParams.path= "/v1/lights/all"
		asynchttpGet('lifxHandler', requestParams, [request: 'lights'])
	}
}

@Field volatile static Map<String,Long> lastRegFLD= [:]
@Field volatile static Map<String,Long> lastRegTryFLD= [:]

private void registerInstance(Boolean force=true){
	//if((Boolean)state.installed && (Boolean)settings.agreement && !isCustomEndpoint()){
	String wName=sAppId()
	Long lnow=wnow()
	if((Boolean)state.installed && (Boolean)settings.agreement){
		if(!force){
			Long lastReg=lastRegFLD[wName]
			lastReg=lastReg ?: 0L
			if(lastReg && (lnow - lastReg < 129600000L)) return // 36 hr in ms

			Long lastRegTry=lastRegTryFLD[wName]
			lastRegTry=lastRegTry ?: 0L
			if(lastRegTry!=0 && (lnow - lastRegTry < 1800000L)) return // 30 min in ms
		}
		if((String)state.accessToken) updateEndpoint()
		lastRegTryFLD[wName]=lnow
		String accountId=accountSid()
		String locationId=locationSid()

		String instanceId=getInstanceSid()
		String endpoint=(String)state.endpointCloud
		String region=endpoint.contains('graph-eu') ? 'eu' : 'us'
		String name=handlePistn()
		if(pStateFLD[wName]==null){ pStateFLD[wName]= (Map)[:]; pStateFLD=pStateFLD }
		List pistons=wgetChildApps().findAll{ (String)it.name==name }.collect{
			String myId=hashPID(it.id)
			Map meta=(Map)pStateFLD[wName][myId]
			if(meta==null){
				//meta=atomicState[myId]
				meta=(Map)it.curPState()
				pStateFLD[wName][myId]=meta
				pStateFLD=pStateFLD
			}
			[ id: myId, (sA): meta?.a ]
		}
		List lpa=pistons.findAll{ it.a }.collect{ it.id }
		Integer pa=lpa.size()
		List lpd=pistons.findAll{ !it.a }.collect{ it.id }
		Integer pd=pistons.size() - pa
		pistons=null

		Map params=[
			uri: "https://api-${region}-${instanceId[32]}.webcore.co:9247".toString(),
			path: '/instance/register',
			headers: ['ST' : instanceId],
			body: [
				(sA): accountId,
				(sL): locationId,
				(sI): instanceId,
				e: endpoint,
				(sV): sVER,
				hv: sHVER,
				(sR): region,
				pa: pa,
				lpa: lpa.join(','),
				pd: pd,
				lpd: lpd.join(',')
			],
			timeout:20
		]
		lpa=null
		lpd=null
		if(eric()) debug "registering instance: params: $params"
		params << [contentType: sAPPJSON, requestContentType: sAPPJSON]
		asynchttpPut('myDone', params, [bbb:0])
	}
}

void myDone(resp, data){
	String endpoint=(String)state.endpointCloud
	String region=endpoint.contains('graph-eu') ? 'eu' : 'us'
	String instanceId=getInstanceSid()
	if(eric())debug "register resp: ${resp?.status} using api-${region}-${instanceId[32]}.webcore.co:9247"
	if(resp?.status==200){
		String wName=sAppId()
		lastRegFLD[wName]=wnow()
	}
}

/******************************************************************************/
/***																		***/
/*** PUBLIC METHODS															***/
/***																		***/
/******************************************************************************/
Boolean isInstalled(){
	return (Boolean)state.installed==true
}

String generatePistonName(){
	List apps=wgetChildApps()
	Integer i=i1
	String bname= handlePistn()+' #'
	while (true){
		String name=bname + i.toString()
		Boolean found=false
		for (mapp in apps){
			String tN= (String)mapp.label ?: (String)mapp.name
			if(tN==name){
				found=true
				break
			}
		}
		if(found){
			i++
			continue
		}
		return name
	}
}

void refreshDevices(){
	state.deviceVersion=wnow().toString()
	atomicState.deviceVersion=(String)state.deviceVersion
	clearParentPistonCache("refreshDevices") // force virtual device to update
	clearBaseResult('refreshDevices')
	testLifx()
}

static String getWikiUrl(){
	return "https://wiki.${domain()}/".toString()
}

private String mem(Boolean showBytes=true){
	Integer bytes=state.toString().length()
	return Math.round(100.0D * (bytes/ 100000.0D)) + "%${showBytes ? " ($bytes bytes)" : sBLK}"
}

@Field volatile static Map<String,Map<String,Long>> p_executionFLD=[:]

@CompileStatic
void pCallupdateRunTimeData(Map data){
	if(!data || !data.id) return
	String id=(String)data.id
	String wName=sAppId()
	if(p_executionFLD[wName]==null){ p_executionFLD[wName]=(Map)[:]; p_executionFLD=p_executionFLD }
	Long cnt=p_executionFLD[wName][id]!=null ? (Long)p_executionFLD[wName][id] : 0L
	cnt +=1L
	p_executionFLD[wName][id]=cnt
	p_executionFLD=p_executionFLD
	updateRunTimeData(data,wName,id)
}

@Field volatile static Map<String,Map<String,Map>> pStateFLD=[:]

private gtSetting(String nm){ return settings."${nm}" }
private gtSt(String nm){ return state."${nm}" }
private gtAS(String nm){ return atomicState."${nm}" }
private void assignSt(String nm,v){ state."${nm}"=v }
private void assignAS(String nm,v){ atomicState."${nm}"=v }
Long wnow(){ return (Long)now() }
List wgetChildApps() { return (List)getChildApps() }

@Field static final String sURT='updateRunTimeData'
@CompileStatic
void updateRunTimeData(Map data, String wNi=sNULL, String idi=sNULL){
	if(!data || !data.id) return
	List<Map> variableEvents=[]
	if(data.gvCache!=null){
		Boolean didw=getTheLock(sURT)

		def am=gtAS('vars')
		Map<String,Map> vars= am? (Map<String,Map>)am : [:]
		Boolean mdfd=false
		for(var in (Map<String,Map>)data.gvCache){
			String k=(String)var.key
			if(k!=sNULL && k.startsWith('@') && vars[k] && var.value.v!=vars[k].v ){
				Boolean a=variableEvents.push([(sNM): k, oldValue: vars[k].v, (sVAL): var.value.v, type: var.value.t])
				vars[k].v=var.value.v
				mdfd=true
			}
		}
		if(mdfd)assignAS('vars',vars)
		releaseTheLock(sURT)
	}
	if(data.gvStoreCache!=null){
		Boolean didw=getTheLock(sURT)

		def am=gtAS('store')
		Map<String,Object> store= am? (Map<String,Object>)am : [:]
		Boolean mdfd=false
		for(var in (Map<String,Object>)data.gvStoreCache){
			String k=(String)var.key
			if(var.value==null) store.remove(k)
			else store[k]=var.value
			mdfd=true
		}
		if(mdfd)assignAS('store',store)
		releaseTheLock(sURT)
	}

	String wName= wNi ?: sAppId()
	String id= idi ?: (String)data.id
	if(wName){
		if(pStateFLD[wName]==null){ pStateFLD[wName]= (Map)[:]; pStateFLD=pStateFLD}
		Map st=[:]+(Map)data.state
		st.remove('old') //remove the old state as we don't need it
		Map piston=[
			(sA): (Boolean)data.active,
			(sC): data.category,
			(sT): data.timestamp ?:wnow(), //last run
			(sN): (Long)((Map)data.stats).nextSchedule,
			z: (String)((Map)data.piston).z, //description
			(sS): st,
			heCached:(Boolean)data.Cached
		]
		//log.warn "data: $data piston: $piston old: ${pStateFLD[wName][id]}"
		if(id){
			pStateFLD[wName][id]=piston
			pStateFLD=pStateFLD
		} else error "no id"
		clearBaseResult(sURT,wName)
	} else error "no wName"

	//broadcast variable change events
	for (Map variable in variableEvents){ // this notifies the other webCoRE master instances and children
		sendVariableEvent(variable)
	}
	//broadcast to dashboard
/*	if((String)state.dashboard==sACT){
		def dashboardApp=getDashboardApp()
		if(dashboardApp) dashboardApp.updatePiston(id, piston)
	} */
	verCheck(wName)
}

Boolean pausePiston(String pistonId,String src){
	def piston=findPiston(pistonId)
	if(piston){
		Map rtData=piston.pausePiston()
		updateRunTimeData(rtData)
		return true
	}
	return false
}

Boolean resumePiston(String pistonId,String src){
	def piston=findPiston(pistonId)
	if(piston){
		Map rtData=piston.resume(null,true)
		updateRunTimeData(rtData)
		return true
	}
	return false
}

Boolean executePiston(String pistonId, Map data, String src){
	def piston=findPiston(pistonId)
	if(piston){
		Map a=piston.execute(data, src)
		return true
	}
	return false
}

private String appName(){ return (String)app.label ?: (String)app.name }

private void sendVariableEvent(Map variable, Boolean onlyChildren=false){
	String myId=getInstanceSid()
	String myLabel=appName()
	String varN=(String)variable.name
	if(varN.startsWith('@@')) return // TODO ERS
	Map theEvent=[
		(sVAL): varN, isStateChange: true, displayed: false,
		(sDATA): [id: myId, (sNM): myLabel, event: sVARIABLE, (sVARIABLE): variable]
	]
// This notifies other webCoRE master instances of super change
/*	if( !onlyChildren && varN.startsWith('@@') ){
		String str=handle()+" Super global variable ${varN} changed".toString()
		sendLocationEvent(theEvent + [
			(sNM): ('@@' + handle()),
			linkText: str, descriptionText: str,
		])
	}*/
// this notifies my children
	String str=handle()+" global variable ${varN} changed".toString()
	sendLocationEvent(theEvent + [
		(sNM): (getInstanceSid()) + ".${varN}",
		linkText: str, descriptionText: str,
		])
}

void broadcastPistonList(){
	sendLocationEvent(
		[
			(sNM): handle(),
			(sVAL): 'pistonList',
			isStateChange: true,
			displayed: false,
			(sDATA): [
				id: getInstanceSid(),
				(sNM): appName(),
				pistons: wgetChildApps().findAll{ (String)it.name==handlePistn() }.collect{
					[ id: hashPID(it.id), (sNM): normalizeLabel(it), aname: (it?.label) ]
				}
			]
		])
}

def webCoREHandler(event){
	String eN=(String)event.name
	def eV=event.value
	info "received event ${eN} with value $eV"
// receive notification of super Global change
	if(!event || (!eN.startsWith(handle()) && !eN.endsWith(handle()) )) return
	def data=event.jsonData ?: null
//log.error "GOT EVENT WITH DATA $data"
/*	if(data && data.variable && ((String)data.event==sVARIABLE) && eV && eV.startsWith('@@')){
		if(eV.startsWith('@@')) return // TODO ERS
		Map variable=data.variable
		String vType=(String)variable.type ?: sDYN
		String vN=(String)variable.name
		def vV=variable.value
		if(vN){
			String t='updateGlobal'
			Boolean didw=getTheLock(t)

			Map<String,Map> vars=(Map<String,Map>)atomicState.vars
			vars=vars ?: [:]
			Map oldVar= vars[vN] ?: [(sT):sBLK, (sV):sBLK]
			if(((String)oldVar.t!=vType) || (oldVar.v!=vV)){ // only notify if it is a change for us.
				if(vV==null){
					vars.remove(vN)
				}else{
					vars[vN]=[(sT): vType, (sV): vV]
				}
				atomicState.vars=vars
				releaseTheLock(t)
				clearGlobalPistonCache("variable event")
// notify my child instances
				if(vV!=null) sendVariableEvent([(sNM): vN, (sVAL): vV, type: vType], true)
			}else releaseTheLock(t) // no change
		}else warn "no variable name $data"
		return
	} */
	switch (eV){
		case 'poll':
			Long delay=Math.round(2000.0D * Math.random())
			wpauseExecution(delay)
			broadcastPistonList()
			break
/*		case 'ping':
		if(data && data.id && data.name && (data.id!=getInstanceSid())){
			sendLocationEvent( [(sNM): handle(), (sVAL): 'pong', isStateChange: true, displayed: false, linkText: "${handle()} ping reply", descriptionText: "${handle()} has received a ping reply and is replying with a pong", (sDATA): [id: getInstanceSid(), (sNM): app.label]] )
		}else{
			break
		}
			//fall through to pong
		case 'pong':
		/*if(data && data.id && data.name && (data.id!=getInstanceSid())){
			def pong=atomicState.pong ?: [:]
			pong[data.id]=data.name
			atomicState.pong=pong
		}*/
	}
}

def instanceRegistrationHandler(response, callbackData){
}

def hubUpdatedHandler(evt){
	if(evt.jsonData && (evt.jsonData.hubType=='PHYSICAL') && evt.jsonData.data && evt.jsonData.data.batteryInUse){
		setPowerSource(evt.jsonData.data.batteryInUse ? 'battery' : 'mains')
	}
}

def summaryHandler(evt){
	//log.error "$evt.name >>> ${evt.jsonData}"
}

def newIncidentHandler(evt){
	//log.error "$evt.name >>> ${evt.jsonData}"
}

def hsmHandler(evt){
	state.hsmStatus=evt.value
	def a=getIncidents() // cause trimming
	clearParentPistonCache("hsmHandler")
	clearBaseResult('hsmHandler')
}

def hsmAlertHandler(evt){
//push incidents
	String evV=evt.value.toString()
	String title='HSM Alert: '+ evV + (evV=='rule' ? ',  '+(String)evt.descriptionText : sBLK)
	String src='HSM Alert:'+ evV
	String msg='HSM '+evV+' Alert'

	Map alert=[
		date:evt.date.getTime(),
		(sTIT): title,
		message: msg,
		args: evt.data,
		sourceType: src,
		(sV):evt.value,
		des:evt.descriptionText,
		//d: evt.data
	]
	//incidents: isHubitat() ? [] : location.activeIncidents.collect{[date: it.date.time, (sTIT): it.getTitle(), message: it.getMessage(), args: it.getMessageArgs(), sourceType: it.getSourceType()]}.findAll{ it.date >= incidentThreshold },

// this should search the db from hsmAlert events?
/*
List t1=getLocationEventsSince('hsmAlert', new Date() - 10)
		def t2
		if(t1.size()){
			t2=t1[0] // newest is first
		}
		if(t2 && t2.value){ return stringToTime(t2.value) + 1000 }
*/
	String locStat=(String)location.hsmStatus

	def a=atomicState.hsmAlerts
	List<Map> alerts= a? (List<Map>)a : []
	Boolean aa=alerts.push(alert)
	if(locStat==sALLDISARM || evV in [sCANCEL, sCANRULEA]) alerts=[]
	atomicState.hsmAlerts=alerts

	if(alerts) a=getIncidents() // cause trimming
	clearParentPistonCache("hsmAlerts changed")
	clearBaseResult('hsmAlertHandler')

	info 'HSM Alert: '+title
}

private List<Map> getIncidents(){
	Long incidentThreshold=Math.round(wnow() - 604800000.0D) // 1 week
	String locStat=(String)location.hsmStatus
	def a=atomicState.hsmAlerts
	List<Map> alerts= a? (List<Map>)a : []
	Integer osz=alerts.size()
	if(osz==0) return []
	if(locStat==sALLDISARM){ alerts=[]; state.remove("hsmAlerts") }
	List<Map> newAlerts=alerts.collect{it}.findAll{ (Long)it.date >= incidentThreshold }
	String intrusion='intrusion'
	List<Map> new2Alerts=newAlerts.collect{it}.findAll{ !(locStat==sDISARMD && ((String)it.v).contains(intrusion)) }.sort { (Long)it.date }
	List<Map> new3Alerts=[]
	for(Map myE in new2Alerts){
		if((String)myE.v in [sCANCEL,sCANRULEA]) new3Alerts=[]
		else Boolean aa=new3Alerts.push(myE)
	}
	a=null
	alerts=null
	newAlerts=null
	new2Alerts=null
	Integer nsz=new3Alerts.size()
	if(osz!=nsz) atomicState.hsmAlerts=new3Alerts
	return new3Alerts
}

void modeHandler(evt){
	clearBaseResult('mode handler')
}

void startHandler(evt){
	debug "startHandler called"
	String wName=sAppId()
	lastRecoveredFLD[wName]=0L
	lastRegFLD[wName]=0L
	lastRegTryFLD[wName]=0L
	runIn(20, startWork)
}

void startWork(){
	checkWeather()
	recoveryHandler()
	broadcastPistonList()
}

def lifxHandler(response, Map cbkData){
	if((response.status==200)){
		def data= response.data instanceof List ? response.data : new JsonSlurper().parseText((String)response.data)
		//cbkData= cbkData instanceof Map ? cbkData : (LinkedHashMap) new JsonSlurper().parseText(cbkData)
		Boolean fnd=false
		if(data instanceof List){
			state.lifx= state.lifx ?: [:]
			switch ((String)cbkData.request){
			case 'scenes':
				state.lifx.scenes= data.collectEntries{[(it.uuid): it.name]}
				fnd=true
				break
			case 'lights':
				state.lifx.lights= data.collectEntries{[(it.id): it.label]}
				state.lifx.groups= data.collectEntries{[(it.group.id): it.group.name]}
				state.lifx.locations= data.collectEntries{[(it.location.id): it.location.name]}
				fnd=true
				break
			}
			if(fnd) debug "got lifx data $cbkData.request"
		}
	}
}

/******************************************************************************/
/*** SECURITY METHODS														***/
/******************************************************************************/
@Field volatile static Map<String,Map> theHashMapVFLD=[:]

/*private String temperatureUnit(){
	return "°" + location.temperatureScale
}*/

/******************************************************************************/
/*** DEBUG FUNCTIONS														***/
/******************************************************************************/

private Map<String,Boolean> getLogging(){
	String logging=settings.logging ? (String)settings.logging : sNULL
	return [
		(sERR): true,
		warn: true,
		info: (logging!='None' && logging!=sNULL),
		trace: (logging=='Medium') || (logging=='Full'),
		debug: (logging=='Full')
	]
}

private Map log(message, Integer shift=-2, err=null, String cmd=sNULL){
	Long lnow=wnow()
	if(cmd=="timer"){
		return [(sM): message, (sT): lnow, (sS): shift, e: err]
	}
	String myMsg=sNULL
	def merr=err
	if(message instanceof Map){
		//shift=(Integer)message.s
		merr=message.e
		myMsg=(String)message.m + " (${lnow - (Long)message.t}ms)"
	}else myMsg=message
	String mcmd=cmd ? cmd : 'debug'
	Map<String,Boolean> myLog=getLogging()
	if(mcmd!='error' && mcmd!='warn'){
		if(!myLog.info && mcmd=='info') return [:]
		if(!myLog.trace && mcmd=='trace') return [:]
		if(!myLog.debug && mcmd=='debug') return [:]
	}
	String prefix=sBLK
/*	Boolean debugging=false
	if(debugging){
		//mode is
		// 0 - initialize level, level set to 1
		// 1 - start of routine, level up
		// -1 - end of routine, level down
		// anything else - nothing happens
		Integer maxLevel=4
		Integer level=state.debugLevel ? state.debugLevel : 0
		Integer levelDelta=0
		prefix="║"
		String pad="░"
		switch (shift){
			case 0:
				level=0
				prefix=sBLK
				break
			case 1:
				level += 1
				prefix="╚"
				pad="═"
				break
			case -1:
				levelDelta=-(level > 0 ? 1 : 0)
				pad="═"
				prefix="╔"
			break
		}

		if(level > 0){
			prefix=prefix.padLeft(level, "║").padRight(maxLevel, pad)
		}

		level += levelDelta
		state.debugLevel=level

		prefix += " "
	}*/

	if(merr){
		myMsg += sSPC+merr.toString()
	}
	log."$mcmd" prefix+myMsg
	return [:]
}

private void info(String message, Integer shift=-2, err=null)	{ Map a=log message, shift, err, 'info' }
private void debug(String message, Integer shift=-2, err=null)	{ Map a=log message, shift, err, 'debug' }
private void trace(message, Integer shift=-2, err=null)	{ Map a=log message, shift, err, 'trace' }
private void warn(String message, Integer shift=-2, err=null)	{ Map a=log message, shift, err, 'warn' }
private void error(String message, Integer shift=-2, err=null)	{ Map a=log message, shift, err, 'error' }
private Map timer(String message, Integer shift=-2, err=null)	{ log message, shift, err, 'timer' }

/******************************************************************************/
/*** DATABASE																***/
/******************************************************************************/

@Field static final String sSTR='string'
@Field static final String sINT='integer'
@Field static final String sDEC='decimal'
@Field static final String sENUM='enum'
@Field static final String sDYN='dynamic'
@Field static final String sDUR='duration'
@Field static final String sDURATION='Duration'
@Field static final String sBOOLN='boolean'
@Field static final String sLVL='level'
@Field static final String sON='on'
@Field static final String sOFF='off'
@Field static final String sOPEN='open'
@Field static final String sCLOSE='close'
@Field static final String sCLOSED='closed'
@Field static final String sCLEAR='clear'
@Field static final String sDETECTED='detected'
@Field static final String sDTIME='datetime'
@Field static final String sVOLUME='Volume'
@Field static final String sSWITCH='switch'
@Field static final String sCOLOR='color'
@Field static final String sCCOLOR='Color'
@Field static final String sTOGON='toggle-on'
@Field static final String sTHERM='thermostatMode'
@Field static final String sTHERFM='thermostatFanMode'
@Field static final String sCLOCK='clock'
@Field static final String sONLYIFSWIS='Only if switch is...'
@Field static final String sIFALREADY=' if already {v}'
@Field static final String sATVOL=' at volume {v}'
@Field static final String sNUMFLASH='Number of flashes'
@Field static final String sACT='active'
@Field static final String sINACT='inactive'

	//n=name
	//d=friendly devices name
	//a=default attribute
	//c=accepted commands
	//m=momentary
	//s=number of subdevices
	//i=subdevice index in event data
@Field final Map<String,Map> capabilitiesFLD=[
	accelerationSensor	: [ (sN): "Acceleration Sensor",	(sD): "acceleration sensors",		(sA): "acceleration",								],
	actuator			: [ (sN): "Actuator",				(sD): "actuators",																	],
	airQuality			: [ (sN): "Air Quality Sensor",		(sD): "air quality sensors",		(sA): "airQualityIndex",							],
	alarm				: [ (sN): "Alarm",					(sD): "alarms and sirens",			(sA): "alarm",		(sC): [sOFF, "strobe", "siren", "both"],			],
	audioNotification	: [ (sN): "Audio Notification",		(sD): "audio notification devices",				(sC): ["playText", "playTextAndResume", "playTextAndRestore", "playTrack", "playTrackAndResume", "playTrackAndRestore"],			],
	audioVolume			: [ (sN): "Audio Volume",			(sD): "audio volume devices",		(sA): "volume",		(sC): ["mute", "setVolume", "unmute", "volumeDown", "volumeUp"],			],
	battery				: [ (sN): "Battery",				(sD): "battery powered devices",	(sA): "battery",									],
	beacon				: [ (sN): "Beacon",					(sD): "beacons",					(sA): "presence",									],
	bulb				: [ (sN): "Bulb",					(sD): "bulbs",						(sA): sSWITCH,		(sC): [sOFF, sON],					],
	carbonDioxideMeasurement	: [ (sN): "Carbon Dioxide Measurement",	(sD): "carbon dioxide sensors",		(sA): "carbonDioxide",								],
	carbonMonoxideDetector		: [ (sN): "Carbon Monoxide Detector",	(sD): "carbon monoxide detectors",		(sA): "carbonMonoxide",								],
	changeLevel			: [ (sN): "Change Level",			(sD): "level adjustment devices",					(sC): ["startLevelChange", "stopLevelChange"],		],
	chime				: [ (sN): "Chime",					(sD): "chime devices",				(sA): "status",		(sC): ["playSound", "stop"],				],
	colorControl		: [ (sN): "Color Control",			(sD): "adjustable color lights",	(sA): sCOLOR,		(sC): ["setColor", "setHue", "setSaturation"],		],
	colorMode			: [ (sN): "Color Mode",				(sD): "color mode devices",		(sA): "colorMode",									],
	colorTemperature	: [ (sN): "Color Temperature",		(sD): "adjustable white lights",	(sA): "colorTemperature",	(sC): ["setColorTemperature"],				],
	configuration		: [ (sN): "Configuration",			(sD): "configurable devices",					(sC): ["configure"],					],
	consumable			: [ (sN): "Consumable",				(sD): "consumables",				(sA): "consumableStatus",	(sC): ["setConsumableStatus"],				],
	contactSensor		: [ (sN): "Contact Sensor",			(sD): "contact sensors",			(sA): "contact",									],
	currentMeter		: [ (sN): "Current Meter",			(sD): "current meter sensors",		(sA): "amperage",								],
	doorControl			: [ (sN): "Door Control",			(sD): "automatic doors",			(sA): "door",		(sC): [sCLOSE, sOPEN],					],
	doubleTapableButton	: [ (sN): "Double Tappable Button",	(sD): "double tappable buttons",		(sA): "doubleTapped",	(sM): true,	(sC): ["doubleTap"], /* (sS): "numberOfButtons,numButtons", i: "buttonNumber",*/	],
	energyMeter			: [ (sN): "Energy Meter",			(sD): "energy meters",				(sA): "energy",									],
	estimatedTimeOfArrival	: [ (sN): "Estimated Time of Arrival",	(sD): "moving devices (ETA)",		(sA): "eta",									],
	fanControl			: [ (sN): "Fan Control",			(sD): "fan devices",				(sA): "speed",		(sC): ["setSpeed", "cycleSpeed"],					],
	filterStatus		: [ (sN): "Filter Status",			(sD): "filters",					(sA): "filterStatus",								],
//	flash				: [ (sN): "Flash",					(sD): "flashers",								(sC): ["flash"],					],
	garageDoorControl	: [ (sN): "Garage Door Control",	(sD): "automatic garage doors",	(sA): "door",		(sC): [sCLOSE, sOPEN],					],
	gasDetector			: [ (sN): "Gas Detector",			(sD): "gas detectors",				(sA): "naturalGas",							],
	healthCheck			: [ (sN): "HealthCheck",			(sD): "healthcheck devices",		(sA): "checkInterval",			(sC): ["ping"],		],
	holdableButton		: [ (sN): "Holdable Button",		(sD): "holdable buttons",			(sA): "held",		(sM): true,	(sC): ["hold"], /* (sS): "numberOfButtons,numButtons", i: "buttonNumber",*/		],
	illuminanceMeasurement	: [ (sN): "Illuminance Measurement",	(sD): "illuminance sensors",		(sA): "illuminance",										],
	imageCapture		: [ (sN): "Image Capture",			(sD): "cameras, imaging devices",	(sA): "image",		(sC): ["take"],						],
	indicator			: [ (sN): "Indicator",				(sD): "indicator devices",			(sA): "indicatorStatus",	(sC): ["indicatorNever", "indicatorWhenOn", "indicatorWhenOff"],		],
	levelPreset			: [ (sN): "Level Preset",			(sD): "adjustable levels",			(sA): "levelPreset",	(sC): ["presetLevel"],							],
	light				: [ (sN): "Light",					(sD): "lights",					(sA): sSWITCH,		(sC): [sOFF, sON],							],
	lightEffects		: [ (sN): "Light Effects",			(sD): "light effects",				(sA): "effectName",	(sC): ["setEffect", "setNextEffect", "setPreviousEffect"],			],
	liquidFlowRate		: [ (sN): "Liquid Flow Rate",		(sD): "liquid flow rates",			(sA): "rate",											],
//	locationMode		: [ (sN): "Mode",					(sD): "modes",						(sA): "mode",			],
	lock				: [ (sN): "Lock",					(sD): "electronic locks",			(sA): "lock",		(sC): ["lock", "unlock"],	/*s:"numberOfCodes,numCodes", i: "usedCode",*/	],
	lockCodes			: [ (sN): "Lock Codes",				(sD): "locks with lock codes",		(sA): "codeChanged",	(sC): ["deleteCode", "getCodes", "setCode", "setCodeLength"],		],
//	lockOnly			: [ (sN): "Lock Only",				(sD): "electronic locks (lock only)",	(sA): "lock",		(sC): ["lock"],								],
	mediaController		: [ (sN): "Media Controller",		(sD): "media controllers",			(sA): "currentActivity",	(sC): ["startActivity", "getAllActivities", "getCurrentActivity"],		],
	mediaInputSource	: [ (sN): "Media Input Source",		(sD): "media input sources",			(sA): "mediaInputSource",	(sC): ["setInputSource"],		],
	mediaTransport		: [ (sN): "Media Transport",		(sD): "media transport",			(sA): "transportStatus",	(sC): ["play", "pause", "stop"],		],
//	momentary			: [ (sN): "Momentary",				(sD): "momentary switches",					(sC): ["push"],								],
	momentary			: [ (sN): "Momentary",				(sD): "momentary switches",			(sM): true,	(sC): ["pushMomentary"],					],
	motionSensor		: [ (sN): "Motion Sensor",			(sD): "motion sensors",			(sA): "motion",											],
	musicPlayer			: [ (sN): "Music Player",			(sD): "music players",				(sA): "status",	(sC): ["mute", "nextTrack", "pause", "play", "playTrack", "previousTrack", "restoreTrack", "resumeTrack", "setLevel", "setTrack", "stop", "unmute"],		],
	notification		: [ (sN): "Notification",			(sD): "notification devices",					(sC): ["deviceNotification"],						],
	outlet				: [ (sN): "Outlet",					(sD): "lights",					(sA): sSWITCH,		(sC): [sOFF, sON],							],
	pHMeasurement		: [ (sN): "pH Measurement",			(sD): "pH sensors",				(sA): "pH",											],
	polling				: [ (sN): "Polling",				(sD): "pollable devices",						(sC): ["poll"],								],
	powerMeter			: [ (sN): "Power Meter",			(sD): "power meters",				(sA): "power",											],
	powerSource			: [ (sN): "Power Source",			(sD): "multisource powered devices",	(sA): "powerSource",										],
	presenceSensor		: [ (sN): "Presence Sensor",		(sD): "presence sensors",			(sA): "presence",											],
	pushableButton		: [ (sN): "Pushable Button",		(sD): "pushable buttons",			(sA): "pushed",		(sM): true,	(sC): ["push"], /* (sS): "numberOfButtons,numButtons", i: "buttonNumber",*/		],
	refresh				: [ (sN): "Refresh",				(sD): "refreshable devices",					(sC): ["refresh"],								],
	relativeHumidityMeasurement	: [ (sN): "Relative Humidity Measurement",	(sD): "humidity sensors",			(sA): "humidity",											],
	relaySwitch			: [ (sN): "Relay Switch",			(sD): "relay switches",			(sA): sSWITCH,		(sC): [sOFF, sON],							],
	releasableButton	: [ (sN): "Releasable Button",		(sD): "releasable buttons",		(sA): "released",		(sM): true,	(sC): ["release"], /* (sS): "numberOfButtons,numButtons", i: "buttonNumber",*/			],
//	samsungTV			: [ (sN): "Samsung TV",		(sD): "Samsung TVs",			(sA): "switch",	(sC): ["mute", sOFF, sON, "setPictureMode", "setSoundMode", "setVolume", "showMessage", "unmute", "volumeDown", "volumeUp"],										],
	securityKeypad		: [ (sN): "Security Keypad",		(sD): "security keypads",			(sA): "securityKeypad",	(sC): ["armAway", "armHome", "deleteCode", "disarm", "getCodes", "setCode", "setCodeLength", "setEntryDelay", "setExitDelay"],										],
	sensor				: [ (sN): "Sensor",					(sD): "sensors",					(sA): "sensor",											],
	shockSensor			: [ (sN): "Shock Sensor",			(sD): "shock sensors",				(sA): "shock",											],
	signalStrength		: [ (sN): "Signal Strength",		(sD): "wireless devices",			(sA): "rssi",											],
	sleepSensor			: [ (sN): "Sleep Sensor",			(sD): "sleep sensors",				(sA): "sleeping",											],
	smokeDetector		: [ (sN): "Smoke Detector",			(sD): "smoke detectors",			(sA): "smoke",											],
	soundPressureLevel	: [ (sN): "Sound Pressure Level",	(sD): "sound pressure sensors",	(sA): "soundPressureLevel",									],
	soundSensor			: [ (sN): "Sound Sensor",			(sD): "sound sensors",				(sA): "sound",											],
	speechRecognition	: [ (sN): "Speech Recognition",		(sD): "speech recognition devices",	(sA): "phraseSpoken",				(sM): true,					],
	speechSynthesis		: [ (sN): "Speech Synthesis",		(sD): "speech synthesizers",					(sC): ["speak"],								],
	stepSensor			: [ (sN): "Step Sensor",			(sD): "step counters",				(sA): "steps",											],
	(sSWITCH)			: [ (sN): "Switch",					(sD): "switches",					(sA): sSWITCH,		(sC): [sOFF, sON],							],
	switchLevel			: [ (sN): "Switch Level",			(sD): "dimmers and dimmable lights",	(sA): sLVL,		(sC): ["setLevel"],							],
//	tv					: [ (sN): "TV",		(sD): "TVs",			(sA): "power",	(sC): ["channelDown", "channelUp", "volumeDown", "volumeUp"],										],
	tamperAlert			: [ (sN): "Tamper Alert",			(sD): "tamper sensors",			(sA): "tamper",											],
//	telnet				: [ (sN): "Telnet",					(sD): "telnet devices",		(sA): "networkStatus",		(sC): ["sendMsg"]				],
	temperatureMeasurement		: [ (sN): "Temperature Measurement",	(sD): "temperature sensors",		(sA): "temperature",										],
//	testCapability		: [ (sN): "Test Ability",				(sD): "test devices",		(				],
	thermostat			: [ (sN): "Thermostat",				(sD): "thermostats",			(sA): sTHERM,	(sC): ["auto", "cool", "eco", "emergencyHeat", "fanAuto", "fanCirculate", "fanOn", "heat", sOFF, "setCoolingSetpoint", "setHeatingSetpoint", /* "setSchedule",*/ "setThermostatFanMode", "setThermostatMode"],	],
	thermostatCoolingSetpoint	: [ (sN): "Thermostat Cooling Setpoint",	(sD): "thermostats (cooling)",		(sA): "coolingSetpoint",	(sC): ["setCoolingSetpoint"],						],
	thermostatFanMode	: [ (sN): "Thermostat Fan Mode",	(sD): "fans",					(sA): sTHERFM,	(sC): ["fanAuto", "fanCirculate", "fanOn", "setThermostatFanMode"],	],
	thermostatHeatingSetpoint	: [ (sN): "Thermostat Heating Setpoint",	(sD): "thermostats (heating)",		(sA): "heatingSetpoint",	(sC): ["setHeatingSetpoint"],						],
	thermostatMode		: [ (sN): "Thermostat Mode",		(sD): "thermostat modes",						(sA): sTHERM,	(sC): ["auto", "cool", "eco", "emergencyHeat", "heat", sOFF, "setThermostatMode"],	],
	thermostatOperatingState	: [ (sN): "Thermostat Operating State",		(sD): "thermostat operating states",		(sA): "thermostatOperatingState",									],
//	thermostatSchedule	: [ (sN): "Thermostat Schedule",							(sA): "schedule",									],
	thermostatSetpoint	: [ (sN): "Thermostat Setpoint",	(sD): "thermostat setpoints",					(sA): "thermostatSetpoint",									],
	threeAxis			: [ (sN): "Three Axis Sensor",		(sD): "three axis sensors",		(sA): "orientation",										],
	timedSession		: [ (sN): "Timed Session",			(sD): "timers",				(sA): "sessionStatus",	(sC): [sCANCEL, "pause", "setTimeRemaining", "start", "stop", ],		],
	tone				: [ (sN): "Tone",					(sD): "tone generators",						(sC): ["beep"],								],
	touchSensor			: [ (sN): "Touch Sensor",			(sD): "touch sensors",			(sA): "touch",  /* (sM): true */									],
	ultravioletIndex	: [ (sN): "Ultraviolet Index",		(sD): "ultraviolet sensors",		(sA): "ultravioletIndex",										],
	valve				: [ (sN): "Valve",					(sD): "valves",				(sA): "valve",		(sC): [sCLOSE, sOPEN],							],
//	variable			: [ (sN): "Variable",				(sD): "variables",				(sA): sVARIABLE,		(sC): ["setVariable"],							],
	videoCamera			: [ (sN): "Video Camera",			(sD): "cameras",				(sA): "camera",		(sC): ["flip", "mute", sOFF, sON, "unmute"],				],
	voltageMeasurement	: [ (sN): "Voltage Measurement",	(sD): "voltmeters",			(sA): "voltage",											],
	waterSensor			: [ (sN): "Water Sensor",			(sD): "water and leak sensors",		(sA): "water",											],
	windowBlind			: [ (sN): "Window Blind",			(sD): "automatic window blinds",		(sA): "windowBlind",	(sC): [sCLOSE, sOPEN, "setPosition", "startPositionChange", "stopPositionChange", "setTiltLevel"],					],
	windowShade			: [ (sN): "Window Shade",			(sD): "automatic window shades",		(sA): "windowShade",	(sC): [sCLOSE, sOPEN, "setPosition", "startPositionChange", "stopPositionChange"],					],
]

private Map capabilities(){
	return capabilitiesFLD
}

Map getChildAttributes(){
	Map<String,Map> result=attributesFLD
	Map<String,Map> cleanResult=[:]
	Map defv=[(sN):sA]
	for(it in result){
		Map t0=[:]
		String hasI=it.value.i
		Boolean hasP=it.value.p
		String hasT=it.value.t
		Boolean hasM=it.value.m
		if(hasI) t0=t0 + [(sI):hasI]
		if(hasP!=null) t0=t0 + [(sP):hasP]
		if(hasT) t0=t0 + [(sT):hasT]
		if(hasM!=null) t0=t0 + [(sM):hasM]
		if(t0==[:]) t0=defv
		cleanResult[it.key.toString()]=t0
	}
	return cleanResult
}

@Field final Map<String,Map> attributesFLD=[
	acceleration		: [ (sN): "acceleration",		(sT): sENUM,		(sO): [sACT, sINACT],						],
	activities			: [ (sN): "activities",			(sT): "object",											],
	airQualityIndex		: [ (sN): "air quality index",	(sT): sINT,	(sR): [0, 500],		u: "AQI",				],
	alarm				: [ (sN): "alarm",				(sT): sENUM,		(sO): ["both", sOFF, "siren", "strobe"],	],
	amperage			: [ (sN): "amperage",			(sT): sDEC,	(sR): [0, null],		u: "A",					],
//	axisX				: [ (sN): "X axis",				(sT): sINT,	(sR): [-1024, 1024],	(sS): "threeAxis",			],
//	axisY				: [ (sN): "Y axis",				(sT): sINT,	(sR): [-1024, 1024],	(sS): "threeAxis",			],
//	axisZ				: [ (sN): "Z axis",				(sT): sINT,	(sR): [-1024, 1024],	(sS): "threeAxis",			],
	axisX				: [ (sN): "axis X",				(sT): sDEC,	(sS): "threeAxis" ],
	axisY				: [ (sN): "axis Y",				(sT): sDEC,	(sS): "threeAxis" ],
	axisZ				: [ (sN): "axis Z",				(sT): sDEC,	(sS): "threeAxis" ],
	battery				: [ (sN): "battery",			(sT): sINT,	(sR): [0, 100],		u: "%",							],
	camera				: [ (sN): "camera",				(sT): sENUM,		(sO): [sON, sOFF, "restarting", "unavailable"],				],
	carbonDioxide		: [ (sN): "carbon dioxide",		(sT): sDEC,	(sR): [0, null],									],
	carbonMonoxide		: [ (sN): "carbon monoxide",	(sT): sENUM,		(sO): [sCLEAR, sDETECTED, "tested"],					],
	codeChanged			: [ (sN): "lock code",			(sT): sENUM,		(sO): ["added", "changed", "deleted", "failed"],				],
//	codeLength			: [ (sN): "Lock code length",	(sT): sINT,											],
	(sCOLOR)			: [ (sN): sCOLOR,				(sT): sCOLOR,											],
//	colorName			: [ (sN): "color name",			(sT): sSTR,											],
	colorMode			: [ (sN): "color mode",			(sT): sENUM,		(sO): ["CT", "RGB"],							],
	colorTemperature	: [ (sN): "color temperature",	(sT): sINT,	(sR): [1000, 30000],	u: "°K",						],
	consumableStatus	: [ (sN): "consumable status",	(sT): sENUM,		(sO): ["good", "maintenance_required", "missing", "order", "replace"],	],
	contact				: [ (sN): "contact",			(sT): sENUM,		(sO): [sCLOSED, sOPEN],							],
	coolingSetpoint		: [ (sN): "cooling setpoint",	(sT): sDEC,	(sR): [-127, 127],		u: '°?',						],
	currentActivity		: [ (sN): "current activity",	(sT): sSTR,											],
//	p: is interaction type
	door				: [ (sN): "door",				(sT): sENUM,		(sO): [sCLOSED, "closing", sOPEN, "opening", "unknown"],		(sP): true,	],
	energy				: [ (sN): "energy",				(sT): sDEC,	(sR): [0, null],		u: "kWh",						],
	eta					: [ (sN): "ETA",				(sT): sDTIME,											],
	effectName			: [ (sN): "effect name",		(sT): sSTR,											],
	filterStatus		: [ (sN): "filter status",		(sT): sENUM,		(sO):["normal", "replace"],						],
	frequency			: [ (sN): "frequency",			(sT): sDEC,		u: "Hz",							],
	goal				: [ (sN): "goal",				(sT): sINT,	(sR): [0, null],									],
	heatingSetpoint		: [ (sN): "heating setpoint",	(sT): sDEC,	(sR): [-127, 127],		u: '°?',						],
	hex					: [ (sN): "hexadecimal code",	(sT): "hexcolor",											],
	hue					: [ (sN): "hue",				(sT): sINT,	(sR): [0, 360],		u: "°",							],
	humidity			: [ (sN): "relative humidity",	(sT): sINT,	(sR): [0, 100],		u: "%",							],
	illuminance			: [ (sN): "illuminance",		(sT): sINT,	(sR): [0, null],		u: "lux",						],
	image				: [ (sN): "image",				(sT): "image",											],
	indicatorStatus		: [ (sN): "indicator status",	(sT): sENUM,		(sO): ["never", "when off", "when on"],					],
//	infraredLevel		: [ (sN): "infrared level",		(sT): sINT,	(sR): [0, 100],		u: "%",							],
//	lastCodeName		: [ (sN): "last lock code",		(sT): sSTR,											],
	level				: [ (sN): sLVL,					(sT): sINT,	(sR): [0, 100],		u: "%",							],
	levelPreset			: [ (sN): "preset level",		(sT): sINT,	(sR): [1, 100],		u: "%",							],
	lightEffects		: [ (sN): "light effects",		(sT): "object",											],
// (sS): is subdevices
	lock				: [ (sN): "lock",				(sT): sENUM,		(sO): ["locked", "unknown", "unlocked", "unlocked with timeout"],	(sC): "lock",		(sP):true,		(sS):"numberOfCodes,numCodes", (sI):"usedCode", sd: "user code"		],
	lockCodes			: [ (sN): "lock codes",			(sT): "object",											],
	lqi					: [ (sN): "link quality",		(sT): sINT,	(sR): [0, 255],									],
//	maxCodes			: [ (sN): "Max Lock codes",		(sT): sINT,											],
//	momentary			: [ (sN): "momentary",			(sT): sENUM,		(sO): ["pushed"],								],
	motion				: [ (sN): "motion",				(sT): sENUM,		(sO): [sACT, sINACT],						],
	mute				: [ (sN): "mute",				(sT): sENUM,		(sO): ["muted", "unmuted"],						],
	naturalGas			: [ (sN): "natural gas",		(sT): sENUM,		(sO): [sCLEAR, sDETECTED, "tested"],					],
	orientation			: [ (sN): "orientation",		(sT): sENUM,		(sO): ["rear side up", "down side up", "left side up", "front side up", "up side up", "right side up"],	],
	pH					: [ (sN): "pH level",			(sT): sDEC,	(sR): [0, 14],									],
	phraseSpoken		: [ (sN): "phrase",				(sT): sSTR,											],
	position			: [ (sN): "position",			(sT): sINT,	(sR): [0, 100],		u: "%",							],
	power				: [ (sN): "power",				(sT): sDEC,		u: "W",									],
	powerSource			: [ (sN): "power source",		(sT): sENUM,		(sO): ["battery", "dc", "mains", "unknown"],				],
	presence			: [ (sN): "presence",			(sT): sENUM,		(sO): ["not present", "present"],						],
	rate				: [ (sN): "liquid flow rate",	(sT): sDEC,											],
//	RGB					: [ (sN): "rgb",				(sT): sSTR,											],
	rssi				: [ (sN): "signal strength",	(sT): sINT,	(sR): [0, 100],		u: "%",							],
	saturation			: [ (sN): "saturation",			(sT): sINT,	(sR): [0, 100],		u: "%",							],
//	schedule			: [ (sN): "schedule",			(sT): "object",											],
	securityKeypad		: [ (sN): "security keypad",	(sT): sENUM,		(sO): [sDISARMD, "armed home", "armed away", "unknown"],			],
	sessionStatus		: [ (sN): "session status",		(sT): sENUM,		(sO): ["canceled", "paused", "running", "stopped"],			],
	shock				: [ (sN): "shock",				(sT): sENUM,		(sO): [sCLEAR, sDETECTED],						],
	sleeping			: [ (sN): "sleeping",			(sT): sENUM,		(sO): ["not sleeping", "sleeping"],					],
	smoke				: [ (sN): "smoke",				(sT): sENUM,		(sO): [sCLEAR, sDETECTED, "tested"],					],
	sound				: [ (sN): "sound",				(sT): sENUM,		(sO): [sDETECTED, "not detected"],					],
	soundEffects		: [ (sN): "sound effects",		(sT): "object",											],
	soundName			: [ (sN): "sound name",			(sT): sSTR,											],
	soundPressureLevel	: [ (sN): "sound pressure level",		(sT): sINT,	(sR): [0, null],		u: "dB",						],
	speed				: [ (sN): "speed",				(sT): sENUM,		(sO): ["low", "medium-low", "medium", "medium-high", "high", sON, sOFF, "auto"],						],
	status				: [ (sN): "status",				(sT): sENUM,		(sO): ["playing", "stopped"],						],
//	status				: [ (sN): "status",				(sT): sSTR,											],
	steps				: [ (sN): "steps",				(sT): sINT,		(sR): [0, null],									],
	(sSWITCH)			: [ (sN): sSWITCH,				(sT): sENUM,		(sO): [sOFF, sON],		(sP): true,					],
	tamper				: [ (sN): "tamper",				(sT): sENUM,		(sO): [sCLEAR, sDETECTED],						],
	temperature			: [ (sN): "temperature",		(sT): sDEC,		(sR): [-460, 10000],	u: '°?',						],
	thermostatFanMode	: [ (sN): "fan mode",			(sT): sENUM,		(sO): ["auto", "circulate", sON],						],
	thermostatMode		: [ (sN): "thermostat mode",	(sT): sENUM,		(sO): ["auto", "cool", "eco", "emergency heat", "heat", sOFF],		],
	thermostatOperatingState	: [ (sN): "operating state",		(sT): sENUM,		(sO): ["cooling", "fan only", "heating", "idle", "pending cool", "pending heat", "vent economizer"],	],
	thermostatSetpoint	: [ (sN): "setpoint",			(sT): sDEC,		(sR): [-127, 127],		u: '°?',						],
	threeAxis			: [ (sN): "vector",				(sT): "vector3",											],
	tilt				: [ (sN): "tilt",				(sT): sINT,		(sR): [0, 100],		u: "%",							],
	timeRemaining		: [ (sN): "time remaining",		(sT): sINT,		(sR): [0, null],		u: sS,							],
	touch				: [ (sN): "touch",				(sT): sENUM,		(sO): ["touched"],								],
	trackData			: [ (sN): "track data",			(sT): "object",											],
	trackDescription	: [ (sN): "track description",		(sT): sSTR,											],
	ultravioletIndex	: [ (sN): "UV index",			(sT): sINT,		(sR): [0, null],									],
// custom for Leak sensor
	underHeat			: [ (sN): "under heat",			(sT): sENUM,		(sO): [sCLEAR, sDETECTED],						],
	valve				: [ (sN): "valve",				(sT): sENUM,		(sO): [sCLOSED, sOPEN],							],
//	variable			: [ (sN): "variable value",	(sT): sSTR,											],
	voltage				: [ (sN): "voltage",			(sT): sDEC,		(sR): [null, null],	u: "V",							],
	volume				: [ (sN): "volume",				(sT): sINT,		(sR): [0, 100],		u: "%",							],
	water				: [ (sN): "water",				(sT): sENUM,		(sO): ["dry", "wet"],							],
	windowShade			: [ (sN): "window shade",		(sT): sENUM,		(sO): [sCLOSED, "closing", sOPEN, "opening", "partially open", "unknown"],	],
	windowBlind			: [ (sN): "window blind",		(sT): sENUM,		(sO): [sCLOSED, "closing", sOPEN, "opening", "partially open", "unknown"],	],
//webCoRE Presence Sensor
	altitude			: [ (sN): "altitude (usc)",		(sT): sDEC,	(sR): [null, null],	u: "ft",						],
	altitudeMetric		: [ (sN): "altitude (metric)",	(sT): sDEC,	(sR): [null, null],	u: sM,							],
	floor				: [ (sN): "floor",				(sT): sINT,	(sR): [null, null],								],
	distance			: [ (sN): "distance (usc)",		(sT): sDEC,	(sR): [null, null],	u: "mi",						],
	distanceMetric		: [ (sN): "distance (metric)",	(sT): sDEC,	(sR): [null, null],	u: "km",						],
	currentPlace		: [ (sN): "current place",		(sT): sSTR,											],
	previousPlace		: [ (sN): "previous place",		(sT): sSTR,											],
	closestPlace		: [ (sN): "closest place",		(sT): sSTR,											],
	arrivingAtPlace		: [ (sN): "arriving at place",	(sT): sSTR,											],
	leavingPlace		: [ (sN): "leaving place",		(sT): sSTR,											],
	places				: [ (sN): "places",				(sT): sSTR,											],
	horizontalAccuracyMetric	: [ (sN): "horizontal accuracy (metric)",	(sT): sDEC,	(sR): [null, null],	u: sM,							],
	horizontalAccuracy	: [ (sN): "horizontal accuracy (usc)",	(sT): sDEC,	(sR): [null, null],	u: "ft",						],
	verticalAccuracy	: [ (sN): "vertical accuracy (usc)",	(sT): sDEC,	(sR): [null, null],	u: "ft",						],
	verticalAccuracyMetric		: [ (sN): "vertical accuracy (metric)",	(sT): sDEC,	(sR): [null, null],	u: sM,							],
	latitude			: [ (sN): "latitude",			(sT): sDEC,	(sR): [null, null],	u: "°",							],
	longitude			: [ (sN): "longitude",			(sT): sDEC,	(sR): [null, null],	u: "°",							],
	closestPlaceDistance		: [ (sN): "distance to closest place (usc)",	(sT): sDEC,	(sR): [null, null],	u: "mi",					],
	closestPlaceDistanceMetric	: [ (sN): "distance to closest place (metric)",	(sT): sDEC,	(sR): [null, null],	u: "km",					],
//don't confuse with fanspeed
	speedUSC			: [ (sN): "speed (usc)",		(sT): sDEC,	(sR): [null, null],	u: "ft/s",						],
	speedMetric			: [ (sN): "speed (metric)",		(sT): sDEC,	(sR): [null, null],	u: "m/s",						],
	bearing				: [ (sN): "bearing",			(sT): sDEC,	(sR): [0, 360],		u: "°",							],
	doubleTapped		: [ (sN): "double tapped button",	(sT): sINT,	(sR): [null, null],	(sM): true,	/*s: "numberOfButtons",	(sI): "buttonNumber"*/			],
	held				: [ (sN): "held button",		(sT): sINT,	(sR): [null, null],	(sM): true,	/*s: "numberOfButtons",	(sI): "buttonNumber"*/			],
	released			: [ (sN): "released button",	(sT): sINT,	(sR): [null, null],	(sM): true,	/*s: "numberOfButtons",	(sI): "buttonNumber"*/			],
	pushed				: [ (sN): "pushed button",		(sT): sINT,	(sR): [null, null],	(sM): true,	/*s: "numberOfButtons",	(sI): "buttonNumber"*/			]
]

/*private Map attributes(){
	return attributesFLD
}*/

/* Push command has multiple overloads in hubitat */
// the command r: is replaced with command c.
private static Map<String,Map> commandOverrides(){
	return ( [ //s: command signature
		push	: [(sC): "push",	(sS): null , (sR): "pushMomentary"], // for capability momentary
		flash	: [(sC): "flash",	(sS): null , (sR): "flashNative"] //flash native command conflicts with flash emulated command. Also needs "o" option on command described later
	] ) as HashMap
}

Map getChildCommands(){
	Map<String,Map> result=commands()
	Map<String,Map> cleanResult=[:]
	Map defv=[(sN):sA]
	Map t0
	String hasA,hasV
	for(it in result){
		t0=[:]
		hasA=it.value.a
		hasV=it.value.v
		if(hasA) t0=t0 + [(sA):hasA]
		if(hasV) t0=t0 + [(sV):hasV]
		if(t0==[:]) t0=defv
		cleanResult[it.key.toString()]=t0
	}
	return cleanResult
}

@Field final Map<String,Map> commandsFLD=[
	armAway				: [ (sN): "Arm Away",				(sA): "securityKeypad",		(sV): "armed away",				],
	armHome				: [ (sN): "Arm Home",				(sA): "securityKeypad",		(sV): "armed home",				],
	auto				: [ (sN): "Set to Auto",			(sA): sTHERM,					(sV): "auto",						],
	beep				: [ (sN): "Beep",																				],
	both				: [ (sN): "Strobe and Siren",		(sA): "alarm",					(sV): "both",						],
	(sCANCEL)			: [ (sN): "Cancel",																			],
	close				: [ (sN): "Close",					(sA): "door",					(sV): sCLOSED,						],
	configure			: [ (sN): "Configure",		(sI): 'cog',															],
	cool				: [ (sN): "Set to Cool",		(sI): 'snowflake', is: 'l',	(sA): sTHERM,		(sV): "cool",			],
	cycleSpeed			: [ (sN): "Cycle speed",																	],
	deleteCode			: [ (sN): "Delete Code...",		(sD): "Delete code {0}",			(sP): [[(sN):"Code position", (sT):sINT]],					],
	deviceNotification	: [ (sN): "Send device notification...",	(sD): "Send device notification \"{0}\"",			(sP): [[(sN):"Message", (sT):sSTR]],				],
	disarm				: [ (sN): "Disarm",				(sA): "securityKeypad",				(sV): sDISARMD,						],
	eco					: [ (sN): "Set to Eco",		(sI): 'leaf',	(sA): sTHERM,				(sV): "eco",						],
	emergencyHeat		: [ (sN): "Set to Emergency Heat",			(sA): sTHERM,				(sV): "emergency heat",					],
	fanAuto				: [ (sN): "Set fan to Auto",			(sA): sTHERFM,				(sV): "auto",						],
	fanCirculate		: [ (sN): "Set fan to Circulate",			(sA): sTHERFM,				(sV): "circulate",						],
	fanOn				: [ (sN): "Set fan to On",				(sA): sTHERFM,				(sV): sON,							],
	flip				: [ (sN): "Flip",																		],
	getAllActivities	: [ (sN): "Get all activities",																],
	getCodes			: [ (sN): "Get Codes",																	],
	getCurrentActivity	: [ (sN): "Get current activity",																],
	heat				: [ (sN): "Set to Heat",		(sI): 'fire',	(sA): sTHERM,				(sV): "heat",						],
	indicatorNever		: [ (sN): "Disable indicator",																],
	indicatorWhenOff	: [ (sN): "Enable indicator when off",															],
	indicatorWhenOn		: [ (sN): "Enable indicator when on",															],
	lock				: [ (sN): "Lock",			(sI): "lock",	(sA): "lock",					(sV): "locked",						],
	mute				: [ (sN): "Mute",			(sI): 'volume-off',	(sA): "mute",				(sV): "muted",						],
	nextTrack			: [ (sN): "Next track",																	],
	off					: [ (sN): "Turn off",		(sI): 'circle-notch',	(sA): sSWITCH,				(sV): sOFF,						],
	on					: [ (sN): "Turn on",		(sI): "power-off",		(sA): sSWITCH,				(sV): sON,						],
	open				: [ (sN): "Open",						(sA): "door",				(sV): sOPEN,						],
	pause				: [ (sN): "Pause",																		],
	play				: [ (sN): "Play",																		],
	playSound			: [ (sN): "Play Sound",				(sD): "Play Sound {0}",		(sP): [[(sN):"Sound Number", (sT):sINT]],					],
	playText			: [ (sN): "Speak text...",				(sD): "Speak text \"{0}\"{1}",	(sP): [[(sN):"Text", (sT):sSTR], [(sN):sVOLUME, (sT):sLVL, (sD):sATVOL]]	],
	playTextAndRestore	: [ (sN): "Speak text and restore...",		(sD): "Speak text \"{0}\"{1} and restore",	(sP): [[(sN):"Text", (sT):sSTR], [(sN):sVOLUME, (sT):sLVL, (sD):sATVOL]],			],
	playTextAndResume	: [ (sN): "Speak text and resume...",		(sD): "Speak text \"{0}\"{1} and resume",	(sP): [[(sN):"Text", (sT):sSTR], [(sN):sVOLUME, (sT):sLVL, (sD):sATVOL]],			],
	playTrack			: [ (sN): "Play track...",					(sD): "Play track {0}{1}",					(sP): [[(sN):"Track URL", (sT):"uri"], [(sN):sVOLUME, (sT):sLVL, (sD):sATVOL]],			],
	playTrackAndRestore	: [ (sN): "Play track and restore...",		(sD): "Play track {0}{1} and restore",		(sP): [[(sN):"Track URL", (sT):"uri"], [(sN):sVOLUME, (sT):sLVL, (sD):sATVOL]],	],
	playTrackAndResume	: [ (sN): "Play track and resume...",		(sD): "Play track {0}{1} and resume",		(sP): [[(sN):"Track URL", (sT):"uri"], [(sN):sVOLUME, (sT):sLVL, (sD):sATVOL]],	],
	poll				: [ (sN): "Poll",						(sI): 'question',											],
	presetLevel			: [ (sN): "Set preset level...",		(sI): 'signal',	(sD): "Set preset level to {0}",			(sA): "presetLevel",			(sP): [[(sN):"Preset Level", (sT):"levelPreset"]],	],
//	presetPosition		: [ (sN): "Move to preset position",		(sA): "windowShade",		(sV): "partially open",	],
	previousTrack		: [ (sN): "Previous track",										],

	refresh				: [ (sN): "Refresh",					(sI): 'sync',											],
	restoreTrack		: [ (sN): "Restore track...",				(sD): "Restore track <uri>{0}</uri>",							(sP): [[(sN):"Track URL", (sT):"url"]],			],
	resumeTrack			: [ (sN): "Resume track...",				(sD): "Resume track <uri>{0}</uri>",							(sP): [[(sN):"Track URL", (sT):"url"]],			],
	setCode				: [ (sN): "Set Code...",				(sD): "Set code {0} to {1} {2}",						(sP): [[(sN):"Code Position", (sT):sINT], [(sN):"Pin", (sT):sSTR], [(sN):"Name", (sT):sSTR]],							],
	setCodeLength		: [ (sN): "Set Code Max Length...",		(sD): "Set code length to {0}",						(sP): [[(sN):"Code Length", (sT):sINT]],						],
	setColor			: [ (sN): "Set color...",		(sI): 'palette', is: "l",	(sD): "Set color to {0}{1}",			(sA): sCOLOR,				(sP): [[(sN):sCCOLOR, (sT):sCOLOR], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],							],
	setColorTemperature	: [ (sN): "Set color temperature...",		(sD): "Set color temperature to {0}°K{1}",			(sA): "colorTemperature",			(sP): [[(sN):"Color Temperature", (sT):"colorTemperature"], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY],[(sN):"Level", (sT):sLVL],[(sN):"Transition duration (seconds)", (sT):sINT,(sD):" over {v} seconds"]]	],
	setConsumableStatus	: [ (sN): "Set consumable status...",		(sD): "Set consumable status to {0}",								(sP): [[(sN):"Status", (sT):"consumable"]],		],
	setCoolingSetpoint	: [ (sN): "Set cooling point...",			(sD): "Set cooling point at {0}{T}",			(sA): "thermostatCoolingSetpoint",		(sP): [[(sN):"Desired temperature", (sT):"thermostatSetpoint"]],	],
	setEffect			: [ (sN): "Set Light Effect...",			(sD): "Set light effect to {0}",									(sP): [[(sN):"Effect number", (sT):sINT]],				],
	setEntryDelay		: [ (sN): "Set Entry Delay...",			(sD): "Set entry delay to {0}",									(sP): [[(sN):"Entry Delay", (sT):sINT]],				],
	setExitDelay		: [ (sN): "Set Exit Delay...",			(sD): "Set exit delay to {0}",									(sP): [[(sN):"Exit Delay", (sT):sINT]],				],
	setHeatingSetpoint	: [ (sN): "Set heating point...",			(sD): "Set heating point at {0}{T}",			(sA): "thermostatHeatingSetpoint",		(sP): [[(sN):"Desired temperature", (sT):"thermostatSetpoint"]],																	],
	setHue				: [ (sN): "Set hue...",		(sI): 'palette', is: "l",	(sD): "Set hue to {0}°{1}",			(sA): "hue",				(sP): [[(sN):"Hue", (sT):"hue"], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],								],
	setInfraredLevel	: [ (sN): "Set infrared level...",	(sI): 'signal',	(sD): "Set infrared level to {0}%{1}",			(sA): "infraredLevel",			(sP): [[(sN):"Level", (sT):"infraredLevel"], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],					],
	setLevel			: [ (sN): "Set level...",		(sI): 'signal',	(sD): "Set level to {0}%{2}{1}",				(sA): sLVL,				(sP): [[(sN):"Level", (sT):sLVL], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY],[(sN):"Transition duration (seconds)", (sT):sINT,(sD):" over {v} seconds"]],							],
	setNextEffect		: [ (sN): "Set next light effect",																					],
	setPreviousEffect	: [ (sN): "Set previous light effect",																					],
	setPosition			: [ (sN): "Move to position",			(sD): "Set position to {0}",				(sA): "position",				(sP): [[(sN):"Position", (sT):"position"]],		],
	setSaturation		: [ (sN): "Set saturation...",			(sD): "Set saturation to {0}{1}",				(sA): "saturation",			(sP): [[(sN):"Saturation", (sT):"saturation"], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],					],
//	setSchedule			: [ (sN): "Set thermostat schedule...",		(sD): "Set schedule to {0}",				(sA): "schedule",				(sP): [[(sN):"Schedule", (sT):"object"]],			],
	setSpeed			: [ (sN): "Set fan speed...",			(sD): "Set fan speed to {0}",				(sA): "speed",				(sP): [[(sN):"Fan Speed", (sT):"speed"]],			],
	setThermostatFanMode		: [ (sN): "Set fan mode...",			(sD): "Set fan mode to {0}",				(sA): sTHERFM,			(sP): [[(sN):"Fan mode", (sT):sTHERFM]],	],
	setThermostatMode	: [ (sN): "Set thermostat mode...",		(sD): "Set thermostat mode to {0}",			(sA): sTHERM,			(sP): [[(sN):"Thermostat mode", (sT):sTHERM]],	],
	setTiltLevel		: [ (sN): "Move to tilt",				(sD): "Set tilt to {0}",					(sA): "tilt",				(sP): [[(sN):"Tilt", (sT):"tilt"]],		],
	setTimeRemaining	: [ (sN): "Set remaining time...",			(sD): "Set remaining time to {0}s",			(sA): "timeRemaining",			(sP): [[(sN):"Remaining time [seconds]", (sT):"number"]],	],
	setTrack			: [ (sN): "Set track...",				(sD): "Set track to <uri>{0}</uri>",								(sP): [[(sN):"Track URL", (sT):"url"]],			],
//	setVariable			: [ (sN): "Set Device Variable...",		(sD): "Set Device Variable to {0}",			(sA): sVARIABLE,				(sP):[[(sN):"device variable value", (sT):sVARIABLE]],			],
	setVolume			: [ (sN): "Set Volume...",				(sD): "Set Volume to {0}",					(sA): "volume",				(sP):[[(sN):"Level", (sT):"volume"]],			],
	siren				: [ (sN): "Siren",												(sA): "alarm",				(sV): "siren",					],
	speak				: [ (sN): "Speak...",				(sD): "Speak \"{0}\"{1}",								(sP): [[(sN):"Message", (sT):sSTR],[(sN):sVOLUME, (sT):sLVL,(sD):sATVOL ]],			],
	start				: [ (sN): "Start",																							],
	startActivity		: [ (sN): "Start activity...",			(sD): "Start activity \"{0}\"",									(sP): [[(sN):"Activity", (sT):sSTR]],		],
	startLevelChange	: [ (sN): "Start Level Change...",			(sD): "Start Level Change \"{0}\"",				(sP): [[(sN):"Direction", (sT):sSTR]],						],
	stopLevelChange		: [ (sN): "Stop Level Change...",			(sD): "Stop Level Change",																],
	startPositionChange	: [ (sN): "Start Position Change...",		(sD): "Start Position Change \"{0}\"",				(sP): [[(sN):"Direction", (sT):sENUM, (sO):[sOPEN, sCLOSE]]],						],
	stopPositionChange	: [ (sN): "Stop Position Change...",		(sD): "Stop Position Change",																],
	stop				: [ (sN): "Stop",																							],
	strobe				: [ (sN): "Strobe",											(sA): "alarm",				(sV): "strobe",					],
	take				: [ (sN): "Take a picture",																					],
	unlock				: [ (sN): "Unlock",		(sI): 'unlock-alt',							(sA): "lock",				(sV): "unlocked",					],
	unmute				: [ (sN): "Unmute",		(sI): 'volume-up',								(sA): "mute",				(sV): "unmuted",					],
	volumeDown			: [ (sN): "Lower volume",																					],
	volumeUp			: [ (sN): "Raise volume",																					],

// these are device virtual commands
	doubleTap			: [ (sN): "Double Tap",			(sD): "Double tap button {0}",			(sA): "doubleTapped",			(sP):[[(sN): "Button #", (sT): sINT]]	],
	hold				: [ (sN): "Hold",					(sD): "Hold Button {0}",				(sA): "held",					(sP):[[(sN):"Button #", (sT): sINT]]	],
	push				: [ (sN): "Push",					(sD): "Push button {0}",				(sA): "pushed",				(sP):[[(sN): "Button #", (sT): sINT]]	],
	release				: [ (sN): "Release",				(sD): "Release button {0}",			(sA): "released",				(sP):[[(sN): "Button #", (sT): sINT]]	],
// non standard ES commands
	parallelPlayAnnouncement	: [ (sN): "Parallel Play Announcement...",			(sD): "Parallel Play Announcement \"{0}\"",								(sP): [[(sN):"Message",(sT):sSTR], [(sN):"Title",(sT):sSTR]],			],
	parallelSpeak		: [ (sN): "Parallel Speak...",			(sD): "Parallel Speak \"{0}\"",								(sP): [[(sN):"Message", (sT):sSTR]],			],
	parallelSpeakIgnoreDnd		: [ (sN): "Parallel Speak Ignore Dnd...",			(sD): "Parallel Speak Ignore Dnd \"{0}\"",								(sP): [[(sN):"Message", (sT):sSTR]],			],
/* predfined commands below */
	//general
	quickSetCool		: [ (sN): "Quick set cooling point...",	(sD): "Set quick cooling point at {0}{T}",				(sP): [[(sN):"Desired temperature", (sT):"thermostatSetpoint"]],		],
	quickSetHeat		: [ (sN): "Quick set heating point...",	(sD): "Set quick heating point at {0}{T}",				(sP): [[(sN):"Desired temperature", (sT):"thermostatSetpoint"]],		],
	toggle				: [ (sN): "Toggle",																						],
	reset				: [ (sN): "Reset",																							],
	//hue
	startLoop			: [ (sN): "Start color loop",																					],
	stopLoop			: [ (sN): "Stop color loop",																					],
	setLoopTime			: [ (sN): "Set loop duration...",			(sD): "Set loop duration to {0}",				(sP): [[(sN):sDURATION, (sT):sDUR]]							],
//	setDirection		: [ (sN): "Switch loop direction",																					],
	alert				: [ (sN): "Alert with lights...",			(sD): "Alert \"{0}\" with lights",				(sP): [[(sN):"Alert type", (sT):sENUM, (sO):["Blink","Breathe","Okay","Stop"]]],			],
	setAdjustedColor	: [ (sN): "Transition to color...",		(sD): "Transition to color {0} in {1}{2}",			(sP): [[(sN):sCCOLOR, (sT):sCOLOR], [(sN):sDURATION, (sT):sDUR],[(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],																	],
	setAdjustedHSLColor	: [ (sN): "Transition to HSL color...",		(sD): "Transition to color H:{0}° / S:{1}% / L:{2}% in {3}{4}",			(sP): [[(sN):"Hue", (sT):"hue"],[(sN):"Saturation", (sT):"saturation"],[(sN):"Level", (sT):sLVL],[(sN):sDURATION, (sT):sDUR],[(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],																	],
	//harmony
	allOn				: [ (sN): "Turn all on",																						],
	allOff				: [ (sN): "Turn all off",																						],
	hubOn				: [ (sN): "Turn hub on",																						],
	hubOff				: [ (sN): "Turn hub off",																						],
	//blink camera
	enableCamera		: [ (sN): "Enable camera",																						],
	disableCamera		: [ (sN): "Disable camera",																					],
	monitorOn			: [ (sN): "Turn monitor on",																					],
	monitorOff			: [ (sN): "Turn monitor off",																					],
	ledOn				: [ (sN): "Turn LED on",																						],
	ledOff				: [ (sN): "Turn LED off",																						],
	ledAuto				: [ (sN): "Set LED to Auto",																					],
	setVideoLength		: [ (sN): "Set video length...",			(sD): "Set video length to {0}",				(sP): [[(sN):sDURATION, (sT):sDUR]],							],
	//dlink camera
	pirOn				: [ (sN): "Enable PIR motion detection",																				],
	pirOff				: [ (sN): "Disable PIR motion detection",																				],
	nvOn				: [ (sN): "Set Night Vision to On",																				],
	nvOff				: [ (sN): "Set Night Vision to Off",																				],
	nvAuto				: [ (sN): "Set Night Vision to Auto",																				],
	vrOn				: [ (sN): "Enable local video recording",																				],
	vrOff				: [ (sN): "Disable local video recording",																				],
	left				: [ (sN): "Pan camera left",																					],
	right				: [ (sN): "Pan camera right",																					],
	up					: [ (sN): "Pan camera up",																						],
	down				: [ (sN): "Pan camera down",																					],
	home				: [ (sN): "Pan camera to the Home",																				],
	presetOne			: [ (sN): "Pan camera to preset #1",																				],
	presetTwo			: [ (sN): "Pan camera to preset #2",																				],
	presetThree			: [ (sN): "Pan camera to preset #3",																				],
	presetFour			: [ (sN): "Pan camera to preset #4",																				],
	presetFive			: [ (sN): "Pan camera to preset #5",																				],
	presetSix			: [ (sN): "Pan camera to preset #6",																				],
	presetSeven			: [ (sN): "Pan camera to preset #7",																				],
	presetEight			: [ (sN): "Pan camera to preset #8",																				],
	presetCommand		: [ (sN): "Pan camera to preset...",		(sD): "Pan camera to preset #{0}",				(sP): [[(sN):"Preset #", (sT):sINT,(sR):[1,99]]],						],

	flashNative			: [ (sN): "Flash",																						],
	pushMomentary		: [ (sN): "Push"																						]
]

private Map commands(){
	return commandsFLD
}

static Map getChildVirtCommands(){
	Map<String,Map> result=virtualCommands()
	Map<String,Map> cleanResult=[:]
	Map defv=[(sN):sA]
	Map t0
	Boolean hasA,hasO
	for(it in result){
		t0=[:]
		hasA=it.value.a
		hasO=it.value.o
		if(hasA!=null) t0=t0 + [(sA):hasA]
		if(hasO!=null) t0=t0 + [(sO):hasO]
		if(t0==[:]) t0=defv
		cleanResult[it.key.toString()]=t0
	}
	return cleanResult
}

	//a=aggregate (only execute once for a list of devices)
	//d=display
	//n=name
	//t=type
	//i=icon
	//o=override physical with virtual
	//p=parameters
@Field static final String sBVB='{v}'
private static Map<String,Map> virtualCommands(){
	List<String> tileIndexes=['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16']
	Map<String,Map> a= [
		noop					: [ (sN): "No operation",				(sA): true,	(sI): "circle",				(sD): "No operation",						],
		wait					: [ (sN): "Wait...",					(sA): true,	(sI): sCLOCK, is: sR,		(sD): "Wait {0}",						(sP): [[(sN):sDURATION, (sT):sDUR]],				],
		waitRandom				: [ (sN): "Wait randomly...",			(sA): true,	(sI): sCLOCK, is: sR,		(sD): "Wait randomly between {0} and {1}",									(sP): [[(sN):"At least", (sT):sDUR],[(sN):"At most", (sT):sDUR]],	],
		waitForTime				: [ (sN): "Wait for time...",			(sA): true,	(sI): sCLOCK, is: sR,		(sD): "Wait until {0}",													(sP): [[(sN):"Time", (sT):sTIME]],	],
		waitForDateTime			: [ (sN): "Wait for date & time...",	(sA): true,	(sI): sCLOCK, is: sR,		(sD): "Wait until {0}",													(sP): [[(sN):"Date & Time", (sT):sDTIME]],	],
		executePiston			: [ (sN): "Execute piston...",			(sA): true,	(sI): sCLOCK, is: sR,		(sD): "Execute piston \"{0}\"{1}",											(sP): [[(sN):"Piston", (sT):"piston"], [(sN):"Arguments", (sT):"variables", (sD):" with arguments {v}"],[(sN):"Wait for execution", (sT):sBOOLN,(sD):" and wait for execution to finish",w:"webCoRE can only wait on piston executions of pistons within the same instance as the caller. Please note that global variables updated in the callee piston do NOT get reflected immediately in the caller piston, the new values will be available on the next run."]],	],
		pausePiston				: [ (sN): "Pause piston...",			(sA): true,	(sI): sCLOCK, is: sR,		(sD): "Pause piston \"{0}\"",												(sP): [[(sN):"Piston", (sT):"piston"]],	],
		resumePiston			: [ (sN): "Resume piston...",			(sA): true,	(sI): sCLOCK, is: sR,		(sD): "Resume piston \"{0}\"",												(sP): [[(sN):"Piston", (sT):"piston"]],	],
		executeRule				: [ (sN): "Execute Rule...",			(sA): true,	(sI): sCLOCK, is: sR,		(sD): "Execute Rule \"{0}\" with action {1}",											(sP): [[(sN):"Rule", (sT):"rule"], [(sN):"Argument", (sT):sENUM, (sO):['Run','Stop','Pause','Resume','Evaluate','Set Boolean True','Set Boolean False']] ]	],
		toggle					: [ (sN): "Toggle", (sR): [sON, sOFF],			(sI): sTOGON																				],
		toggleRandom			: [ (sN): "Random toggle", (sR): [sON, sOFF],		(sI): sTOGON,				(sD): "Random toggle{0}",													(sP): [[(sN):"Probability for on", (sT):sLVL, (sD):" with a {v}% probability for on"]],	],
		setSwitch				: [ (sN): "Set switch...", (sR): [sON, sOFF],		(sI): sTOGON,				(sD): "Set switch to {0}",													(sP): [[(sN):"Switch value", (sT):sSWITCH]],																],
		setHSLColor				: [ (sN): "Set color... (hsl)",				(sI): "palette", is: "l",			(sD): "Set color to H:{0}° / S:{1}% / L%:{2}{3}",				(sR): ["setColor"],				(sP): [[(sN):"Hue", (sT):"hue"], [(sN):"Saturation", (sT):"saturation"], [(sN):"Level", (sT):sLVL], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],							],
		toggleLevel				: [ (sN): "Toggle level...",				(sI): "toggle-off",					(sD): "Toggle level between 0% and {0}%",	(sR): [sON, sOFF, "setLevel"],	(sP): [[(sN):"Level", (sT):sLVL]],																																	],
		sendNotification		: [ (sN): "Send notification...",		(sA): true,	(sI): "comment-alt", is: sR,			(sD): "Send notification \"{0}\"",											(sP): [[(sN):"Message", (sT):sSTR]],												],
		sendPushNotification	: [ (sN): "Send PUSH notification...",	(sA): true,	(sI): "comment-alt", is: sR,			(sD): "Send PUSH notification \"{0}\"{1}",									(sP): [[(sN):"Message", (sT):sSTR],[(sN):"Store in Messages", (sT):sBOOLN, (sD):" and store in Messages", (sS):1]],	],
		sendSMSNotification		: [ (sN): "Send SMS notification...",	(sA): true,	(sI): "comment-alt", is: sR,			(sD): "Send SMS notification \"{0}\" to {1}{2}",							(sP): [[(sN):"Message", (sT):sSTR],[(sN):"Phone number", (sT):"phone",w:"HE requires +countrycode in phone number."],[(sN):"Store in Messages", (sT):sBOOLN, (sD):" and store in Messages", (sS):1]],	],
		log						: [ (sN): "Log to console...",			(sA): true,	(sI): "bug",				(sD): "Log {0} \"{1}\"{2}",												(sP): [[(sN):"Log type", (sT):sENUM, (sO):["info","trace","debug","warn","error"]],[(sN):"Message", (sT):sSTR],[(sN):"Store in Messages", (sT):sBOOLN, (sD):" and store in Messages", (sS):1]],	],
		httpRequest				: [ (sN): "Make a web request",			(sA): true,	(sI): "anchor", is: sR,		(sD): "Make a {1} request to {0}",					(sP): [[(sN):"URL", (sT):"uri"],[(sN):"Method", (sT):sENUM, (sO):["GET","POST","PUT","DELETE","HEAD"]],[(sN):"Request body type", (sT):sENUM, (sO):["JSON","FORM","CUSTOM"]],[(sN):"Send variables", (sT):"variables", (sD):"data {v}"],[(sN):"Request body", (sT):sSTR, (sD):"data {v}"],[(sN):"Request content type", (sT):sENUM, (sO):["text/plain","text/html",sAPPJSON,"application/x-www-form-urlencoded","application/xml"]],[(sN):"Authorization header", (sT):sSTR, (sD):sBVB]],	],
		setVariable				: [ (sN): "Set variable...",			(sA): true,	(sI): "superscript", is:sR,	(sD): "Set variable {0} = {1}",											(sP): [[(sN):"Variable", (sT):sVARIABLE],[(sN):"Value", (sT):sDYN]],	],
		setState				: [ (sN): "Set piston state...",		(sA): true,	(sI): "align-left", is:"l",	(sD): "Set piston state to \"{0}\"",										(sP): [[(sN):"State", (sT):sSTR]],	],
		setTileColor			: [ (sN): "Set piston tile colors...",	(sA): true,	(sI): "info-square", is:"l",	(sD): "Set piston tile #{0} colors to {1} over {2}{3}",					(sP): [[(sN):"Tile Index", (sT):sENUM,(sO):tileIndexes],[(sN):"Text Color", (sT):sCOLOR],[(sN):"Background Color", (sT):sCOLOR],[(sN):"Flash mode", (sT):sBOOLN,(sD):" (flashing)"]],	],
		setTileTitle			: [ (sN): "Set piston tile title...",	(sA): true,	(sI): "info-square", is:"l",	(sD): "Set piston tile #{0} title to \"{1}\"",								(sP): [[(sN):"Tile Index", (sT):sENUM,(sO):tileIndexes],[(sN):"Title", (sT):sSTR]],	],
		setTileOTitle			: [ (sN): "Set piston tile mouseover title...",	(sA): true,	(sI): "info-square", is:"l",	(sD): "Set piston tile #{0} mouseover title to \"{1}\"",								(sP): [[(sN):"Tile Index", (sT):sENUM,(sO):tileIndexes],[(sN):"Title", (sT):sSTR]],	],
		setTileText				: [ (sN): "Set piston tile text...",	(sA): true,	(sI): "info-square", is:"l",	(sD): "Set piston tile #{0} text to \"{1}\"",								(sP): [[(sN):"Tile Index", (sT):sENUM,(sO):tileIndexes],[(sN):"Text", (sT):sSTR]],	],
		setTileFooter			: [ (sN): "Set piston tile footer...",	(sA): true,	(sI): "info-square", is:"l",	(sD): "Set piston tile #{0} footer to \"{1}\"",							(sP): [[(sN):"Tile Index", (sT):sENUM,(sO):tileIndexes],[(sN):"Footer", (sT):sSTR]],	],
		setTile					: [ (sN): "Set piston tile...",			(sA): true,	(sI): "info-square", is:"l",	(sD): "Set piston tile #{0} title to \"{1}\", text to \"{2}\", footer to \"{3}\", and colors to {4} over {5}{6}",		(sP): [[(sN):"Tile Index", (sT):sENUM,(sO):tileIndexes],[(sN):"Title", (sT):sSTR],[(sN):"Text", (sT):sSTR],[(sN):"Footer", (sT):sSTR],[(sN):"Text Color", (sT):sCOLOR],[(sN):"Background Color", (sT):sCOLOR],[(sN):"Flash mode", (sT):sBOOLN,(sD):" (flashing)"]],	],
		clearTile				: [ (sN): "Clear piston tile...",		(sA): true,	(sI): "info-square", is:"l",	(sD): "Clear piston tile #{0}",											(sP): [[(sN):"Tile Index", (sT):sENUM,(sO):tileIndexes]],	],
		setLocationMode			: [ (sN): "Set location mode...",		(sA): true,	(sI): sBLK,						(sD): "Set location mode to {0}",											(sP): [[(sN):"Mode", (sT):"mode"]],																														],
		sendEmail				: [ (sN): "Send email...",				(sA): true,	(sI): "envelope",				(sD): "Send email with subject \"{1}\" to {0}",							(sP): [[(sN):"Recipient", (sT):"email"],[(sN):"Subject", (sT):sSTR],[(sN):"Message body", (sT):sSTR]],																							],
		wolRequest				: [ (sN): "Wake a LAN device",			(sA): true,	(sI): sBLK,						(sD): "Wake LAN device at address {0}{1}",									(sP): [[(sN):"MAC address", (sT):sSTR],[(sN):"Secure code", (sT):sSTR,(sD):" with secure code {v}"]],	],
		adjustLevel				: [ (sN): "Adjust level...",	(sR): ["setLevel"],	(sI): sTOGON,				(sD): "Adjust level by {0}%{1}",											(sP): [[(sN):"Adjustment", (sT):sINT,(sR):[-100,100]], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],																],
		adjustInfraredLevel		: [ (sN): "Adjust infrared level...",	(sR): ["setInfraredLevel"],	(sI): sTOGON,	(sD): "Adjust infrared level by {0}%{1}",								(sP): [[(sN):"Adjustment", (sT):sINT,(sR):[-100,100]], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],																],
		adjustSaturation		: [ (sN): "Adjust saturation...",	(sR): ["setSaturation"],	(sI): sTOGON,		(sD): "Adjust saturation by {0}%{1}",										(sP): [[(sN):"Adjustment", (sT):sINT,(sR):[-100,100]], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],																],
		adjustHue				: [ (sN): "Adjust hue...",	(sR): ["setHue"],		(sI): sTOGON,					(sD): "Adjust hue by {0}°{1}",												(sP): [[(sN):"Adjustment", (sT):sINT,(sR):[-360,360]], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],																],
		adjustColorTemperature	: [ (sN): "Adjust color temperature...",	(sR): ["setColorTemperature"],	(sI): sTOGON,				(sD): "Adjust color temperature by {0}°K%{1}",		(sP): [[(sN):"Adjustment", (sT):sINT,(sR):[-29000,29000]], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],																],
		fadeLevel				: [ (sN): "Fade level...",	(sR): ["setLevel"],		(sI): sTOGON,				(sD): "Fade level{0} to {1}% in {2}{3}",									(sP): [[(sN):"Starting level", (sT):sLVL,(sD):" from {v}%"],[(sN):"Final level", (sT):sLVL],[(sN):sDURATION, (sT):sDUR], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],																],
		fadeInfraredLevel		: [ (sN): "Fade infrared level...",	(sR): ["setInfraredLevel"],		(sI): sTOGON,				(sD): "Fade infrared level{0} to {1}% in {2}{3}",		(sP): [[(sN):"Starting infrared level", (sT):sLVL,(sD):" from {v}%"],[(sN):"Final infrared level", (sT):sLVL],[(sN):sDURATION, (sT):sDUR], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],																],
		fadeSaturation			: [ (sN): "Fade saturation...",	(sR): ["setSaturation"],		(sI): sTOGON,				(sD): "Fade saturation{0} to {1}% in {2}{3}",					(sP): [[(sN):"Starting saturation", (sT):sLVL,(sD):" from {v}%"],[(sN):"Final saturation", (sT):sLVL],[(sN):sDURATION, (sT):sDUR], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],																],
		fadeHue					: [ (sN): "Fade hue...",			(sR): ["setHue"],		(sI): sTOGON,				(sD): "Fade hue{0} to {1}° in {2}{3}",								(sP): [[(sN):"Starting hue", (sT):"hue",(sD):" from {v}°"],[(sN):"Final hue", (sT):"hue"],[(sN):sDURATION, (sT):sDUR], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],																],
		fadeColorTemperature	: [ (sN): "Fade color temperature...",		(sR): ["setColorTemperature"],		(sI): sTOGON,				(sD): "Fade color temperature{0} to {1}°K in {2}{3}",									(sP): [[(sN):"Starting color temperature", (sT):"colorTemperature",(sD):" from {v}°K"],[(sN):"Final color temperature", (sT):"colorTemperature"],[(sN):sDURATION, (sT):sDUR], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],																],
//		flash					: [ (sN): "Flash...",	(sR): [sON, sOFF],		(sI): sTOGON,				(sD): "Flash on {0} / off {1} for {2} times{3}",							(sP): [[(sN):"On duration", (sT):sDUR],[(sN):"Off duration", (sT):sDUR],[(sN):sNUMFLASH, (sT):sINT], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],																],
		flashLevel				: [ (sN): "Flash (level)...",	(sR): ["setLevel"],	(sI): sTOGON,		(sD): "Flash {0}% {1} / {2}% {3} for {4} times{5}",						(sP): [[(sN):"Level 1", (sT):sLVL],[(sN):"Duration 1", (sT):sDUR],[(sN):"Level 2", (sT):sLVL],[(sN):"Duration 2", (sT):sDUR],[(sN):sNUMFLASH, (sT):sINT], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],																],
		flashColor				: [ (sN): "Flash (color)...",	(sR): ["setColor"],	(sI): sTOGON,		(sD): "Flash {0} {1} / {2} {3} for {4} times{5}",							(sP): [[(sN):"Color 1", (sT):sCOLOR],[(sN):"Duration 1", (sT):sDUR],[(sN):"Color 2", (sT):sCOLOR],[(sN):"Duration 2", (sT):sDUR],[(sN):sNUMFLASH, (sT):sINT], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],																],
		lifxScene				: [ (sN): "LIFX - Activate scene...",              (sA): true,                        (sD): "Activate LIFX Scene '{0}'{1}",                                                                              (sP): [[(sN): "Scene", (sT):"lifxScene"],[(sN): sDURATION, (sT):sDUR, (sD):" for {v}"]],                                   ],
		lifxState				: [ (sN): "LIFX - Set State...",                   (sA): true,                        (sD): "Set LIFX lights matching {0} to {1}{2}{3}{4}{5}",                                   (sP): [[(sN): "Selector", (sT):"lifxSelector"],[(sN): "Switch (power)", (sT):sENUM,(sO):[sON,sOFF],(sD):" switch '{v}'"],[(sN): sCCOLOR, (sT):sCOLOR,(sD):" color '{v}'"],[(sN): "Level (brightness)", (sT):sLVL,(sD):" level {v}%"],[(sN): "Infrared level", (sT):"infraredLevel",(sD):" infrared {v}%"],[(sN): sDURATION, (sT):sDUR,(sD):" in {v}"]], ],
		lifxToggle				: [ (sN): "LIFX - Toggle...",		(sA): true,                (sD): "Toggle LIFX lights matching {0}{1}",                                                                (sP): [[(sN): "Selector", (sT):"lifxSelector"],[(sN): sDURATION, (sT):sDUR,(sD):" in {v}"]], ],
		lifxBreathe				: [ (sN): "LIFX - Breathe...",		(sA): true,                (sD): "Breathe LIFX lights matching {0} to color {1}{2}{3}{4}{5}{6}{7}",   (sP): [[(sN): "Selector", (sT):"lifxSelector"],[(sN): sCCOLOR, (sT):sCOLOR],[(sN): "From color", (sT):sCOLOR,(sD):" from color '{v}'"],[(sN): "Period", (sT):sDUR, (sD):" with a period of {v}"],[(sN): "Cycles", (sT):sINT, (sD):" for {v} cycles"],[(sN):"Peak", (sT):sLVL,(sD):" with a peak at {v}% of the period"],[(sN):"Power on", (sT):sBOOLN,(sD):" and power on at start"],[(sN):"Persist", (sT):sBOOLN,(sD):" and persist"] ], ],
		lifxPulse				: [ (sN): "LIFX - Pulse...",		(sA): true,                (sD): "Pulse LIFX lights matching {0} to color {1}{2}{3}{4}{5}{6}",                (sP): [[(sN): "Selector", (sT):"lifxSelector"],[(sN): sCCOLOR, (sT):sCOLOR],[(sN): "From color", (sT):sCOLOR,(sD):" from color '{v}'"],[(sN): "Period", (sT):sDUR, (sD):" with a period of {v}"],[(sN): "Cycles", (sT):sINT, (sD):" for {v} cycles"],[(sN):"Power on", (sT):sBOOLN,(sD):" and power on at start"],[(sN):"Persist", (sT):sBOOLN,(sD):" and persist"] ], ],

		writeToFuelStream		: [ (sN): "Append to fuel stream...",		(sA): true,			(sD): "Append data point '{2}' to fuel stream {0}{1}{3}",	(sP): [[(sN): "Canister", (sT):sTXT, (sD):"{v} \\ "], [(sN):"Fuel stream name", (sT):sTXT], [(sN): "Data", (sT):sDYN], [(sN): "Data source", (sT):sTXT, (sD):" from source '{v}'"]],			],
		iftttMaker				: [ (sN): "Send an IFTTT Maker event...",	(sA): true,			(sD): "Send the {0} IFTTT Maker event{1}{2}{3}",			(sP): [[(sN):"Event", (sT):sTXT], [(sN):"Value 1", (sT):sSTR, (sD):", passing value1 = '{v}'"], [(sN):"Value 2", (sT):sSTR, (sD):", passing value2 = '{v}'"], [(sN):"Value 3", (sT):sSTR, (sD):", passing value3 = '{v}'"]],				],
		storeMedia				: [ (sN): "Store media...",				(sA): true,				(sD): "Store media",														(sP): [],					],
		saveStateLocally		: [ (sN): "Capture attributes to local store...",				(sD): "Capture attributes {0} to local state{1}{2}",						(sP): [[(sN): "Attributes", (sT):"attributes"],[(sN):'State container name', (sT):sSTR,(sD):sSPC+sBVB],[(sN):'Prevent overwriting existing state', (sT):sENUM, (sO):['true','false'], (sD):' only if store is empty']], ],
		saveStateGlobally		: [ (sN): "Capture attributes to global store...",				(sD): "Capture attributes {0} to global state{1}{2}",						(sP): [[(sN): "Attributes", (sT):"attributes"],[(sN):'State container name', (sT):sSTR,(sD):sSPC+sBVB],[(sN):'Prevent overwriting existing state', (sT):sENUM, (sO):['true','false'], (sD):' only if store is empty']], ],
		loadStateLocally		: [ (sN): "Restore attributes from local store...",				(sD): "Restore attributes {0} from local state{1}{2}",						(sP): [[(sN): "Attributes", (sT):"attributes"],[(sN):'State container name', (sT):sSTR,(sD):sSPC+sBVB],[(sN):'Empty state after restore', (sT):sENUM, (sO):['true','false'], (sD):' and empty the store']], ],
		loadStateGlobally		: [ (sN): "Restore attributes from global store...",			(sD): "Restore attributes {0} from global state{1}{2}",						(sP): [[(sN): "Attributes", (sT):"attributes"],[(sN):'State container name', (sT):sSTR,(sD):sSPC+sBVB],[(sN):'Empty state after restore', (sT):sENUM, (sO):['true','false'], (sD):' and empty the store']], ],
		parseJson				: [ (sN): "Parse JSON data...",			(sA): true,				(sD): "Parse JSON data {0}",												(sP): [[(sN): "JSON string", (sT):sSTR]],																											],
		cancelTasks				: [ (sN): "Cancel all pending tasks",	(sA): true,				(sD): "Cancel all pending tasks",											(sP): [],																											],

		readFile				: [ (sN): "Read from file...",		(sA): true,					(sD): "Read from file {0} to \$file",					(sP): [[(sN): "File name", (sT):sSTR ], [(sN):"Username", (sT):'email', (sD):", username {v}"], [(sN): "Password", (sT):"uri", (sD):", password {v}"] ],					],
		writeFile				: [ (sN): "Write to file...",		(sA): true,					(sD): "Write to file {0}",					(sP): [[(sN): "File name", (sT):sSTR ], [(sN):"Data", (sT):sSTR, ],[(sN):"Username", (sT):'email', (sD):", username {v}"], [(sN): "Password", (sT):"uri", (sD):", password {v}"] ],					],
		appendFile				: [ (sN): "Append to file...",		(sA): true,					(sD): "Append to file {0}",					(sP): [[(sN): "File name", (sT):sSTR ], [(sN):"Data", (sT):sSTR, ],[(sN):"Username", (sT):'email', (sD):", username {v}"], [(sN): "Password", (sT):"uri", (sD):", password {v}"] ],					],

		setAlarmSystemStatus	: [ (sN): "Set Hubitat Safety Monitor status...",	(sA): true, (sI): sBLK,				(sD): "Set Hubitat Safety Monitor status to {0}",							(sP): [[(sN):"Status", (sT):sENUM, (sO): getAlarmSystemStatusActions().collect {[(sN): it.value, (sV): it.key]}]],																										],
		//keep emulated flash to not break old pistons
		emulatedFlash			: [ (sN): "(Old do not use) Emulated Flash",	(sR): [sON, sOFF],			(sI): sTOGON,				(sD): "(Old do not use)Flash on {0} / off {1} for {2} times{3}",							(sP): [[(sN):"On duration", (sT):sDUR],[(sN):"Off duration", (sT):sDUR],[(sN):sNUMFLASH, (sT):sINT], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],																], //add back emulated flash with "o" option so that it overrides the native flash command
		flash					: [ (sN): "Flash...",	(sR): [sON, sOFF],		(sI): sTOGON,				(sD): "Flash on {0} / off {1} for {2} times{3}",							(sP): [[(sN):"On duration", (sT):sDUR],[(sN):"Off duration", (sT):sDUR],[(sN):sNUMFLASH, (sT):sINT], [(sN):sONLYIFSWIS, (sT):sENUM,(sO):[sON,sOFF], (sD):sIFALREADY]],		(sO): true /*override physical command*/													]
	]
	if(graphsOn()){
		a = a + [
			readFuelStream		: [ (sN): "Read fuel stream...",		(sA): true,		(sD): "Read entire fuel stream {0}{1} to \$fuel",			(sP): [[(sN): "Canister", (sT):sTXT, (sD):"{v} \\ "], [(sN):"Fuel stream name", (sT):sTXT] ],					],
			writeFuelStream		: [ (sN): "Overwrite fuel stream...",	(sA): true,		(sD): "Write entire fuel stream {0}{1}{3}",					(sP): [[(sN): "Canister", (sT):sTXT, (sD):"{v} \\ "], [(sN):"Fuel stream name", (sT):sTXT], [(sN): "Data", (sT):sDYN],	[(sN): "Data source", (sT):sTXT, (sD):" from source '{v}'"]],			],
			clearFuelStream		: [ (sN): "Clear fuel stream...",		(sA): true,		(sD): "Clear fuel stream {0}{1}{2}",						(sP): [[(sN): "Canister", (sT):sTXT, (sD):"{v} \\ "], [(sN):"Fuel stream name", (sT):sTXT], 							[(sN): "Data source", (sT):sTXT, (sD):" from source '{v}'"] ],			],
			addToFuelStream		: [ (sN): "Add to fuel stream...",		(sA): true,		(sD): "Add data point '{2}' to fuel stream {0}{1}{3}",		(sP): [[(sN): "Canister", (sT):sTXT, (sD):"{v} \\ "], [(sN):"Fuel stream name", (sT):sTXT], [(sN): "Data", (sT):sDYN],	[(sN): "Time stamp", (sT): sDTIME ],	[(sN): "Data source", (sT):sTXT, (sD):" from source '{v}'"]],			],
				// listFuelStreams(includeLTS)
				// existsFuelStream (canister, name)
				// removeFuelStream (canister,name)

				// listLTS
				// createLTS (device,attribute,keepinfo)
				// existsLTS (device,attribute)
				// removeLTS(device,attribute)

				// quantStream(istream, dstream, quantparams)
				// graphStream(istream, graphparams, quantparams)
		] as Map<String,Map>
	}
	return a
}


Map getChildComparisons(){
	Map<String,Map<String,Map>> result=comparisonsFLD
	Map<String,Map<String,Map>> cleanResult=[:]
	cleanResult.conditions=[:]
	Map defv=[(sN):sA]
	Map t0
	def hasP,hasT
	for(it in result.conditions){
		t0=[:]
		hasP=it.value.p
		hasT=it.value.t
		if(hasP!=null) t0=t0+[(sP):hasP.toInteger()]
		if(hasT!=null) t0=t0+[(sT):hasT.toInteger()]
		if(t0==[:]) t0=defv
		cleanResult.conditions[it.key.toString()]=t0
	}
	cleanResult.triggers=[:]
	for(it in result.triggers){
		t0=[:]
		hasP=it.value.p
		hasT=it.value.t
		if(hasP!=null) t0=t0+[(sP):hasP.toInteger()]
		if(hasT!=null) t0=t0+[(sT):hasT.toInteger()]
		if(t0==[:]) t0=defv
		cleanResult.triggers[it.key.toString()]=t0
	}
	return cleanResult
}

// m - multiple
// p - parameter count
// t - timed
// used by ide
// g types
//      t = timed
//      f = image
//      s = string
//      m = momentary
//      v = virtual device
//      d = decimal
//      i = integer
//		b = boolean
//		n = number (decimal)
//		e = email (not implemented)
@Field static final String sDD='dd'
@Field static final String sDI='di'
@Field final Map<String,Map> comparisonsFLD=[
	conditions: [
		changed				: [ (sD): "changed",									(sG):"bdfis",				(sT): i1	],
		did_not_change		: [ (sD): "did not change",								(sG):"bdfis",				(sT): i1	],
		is					: [ (sD): "is",				(sDD): "are",					(sG):"bs",		(sP): i1					],
		is_not				: [ (sD): "is not",			(sDD): "are not",					(sG):"bs",		(sP): i1					],
		is_any_of			: [ (sD): "is any of",			(sDD): "are any of",				(sG):sS,		(sP): i1,	(sM): true,			],
		is_not_any_of		: [ (sD): "is not any of",			(sDD): "are not any of",				(sG):sS,		(sP): i1,	(sM): true,			],
		is_equal_to			: [ (sD): "is equal to",			(sDD): "are equal to",				(sG):sDI,		(sP): i1					],
		is_different_than	: [ (sD): "is different than",		(sDD): "are different than",			(sG):sDI,		(sP): i1					],
		is_less_than		: [ (sD): "is less than",			(sDD): "are less than",				(sG):sDI,		(sP): i1					],
		is_less_than_or_equal_to	: [ (sD): "is less than or equal to",	(sDD): "are less than or equal to",		(sG):sDI,		(sP): i1					],
		is_greater_than		: [ (sD): "is greater than",		(sDD): "are greater than",				(sG):sDI,		(sP): i1					],
		is_greater_than_or_equal_to	: [ (sD): "is greater than or equal to",	(sDD): "are greater than or equal to",		(sG):sDI,		(sP): i1					],
		is_inside_of_range	: [ (sD): "is inside of range",		(sDD): "are inside of range",			(sG):sDI,		(sP): i2					],
		is_outside_of_range	: [ (sD): "is outside of range",		(sDD): "are outside of range",			(sG):sDI,		(sP): i2					],
		is_even				: [ (sD): "is even",			(sDD): "are even",					(sG):sDI,							],
		is_odd				: [ (sD): "is odd",			(sDD): "are odd",					(sG):sDI,							],
//		is_true				: [ (sD): "is true",			(sDD): "are true",					(sG):"bs",		(sP): iZ					],
//		is_false			: [ (sD): "is false",			(sDD): "are false",					(sG):"bs",		(sP): iZ					],
		was					: [ (sD): "was",				(sDD): "were",					(sG):"bs",		(sP): i1,			(sT): i2,	],
		was_not				: [ (sD): "was not",			(sDD): "were not",					(sG):"bs",		(sP): i1,			(sT): i2,	],
		was_any_of			: [ (sD): "was any of",			(sDD): "were any of",				(sG):sS,		(sP): i1,	(sM): true,	(sT): i2,	],
		was_not_any_of		: [ (sD): "was not any of",		(sDD): "were not any of",				(sG):sS,		(sP): i1,	(sM): true,	(sT): i2,	],
		was_equal_to		: [ (sD): "was equal to",			(sDD): "were equal to",				(sG):sDI,		(sP): i1,			(sT): i2,	],
		was_different_than	: [ (sD): "was different than",		(sDD): "were different than",			(sG):sDI,		(sP): i1,			(sT): i2,	],
		was_less_than		: [ (sD): "was less than",			(sDD): "were less than",				(sG):sDI,		(sP): i1,			(sT): i2,	],
		was_less_than_or_equal_to	: [ (sD): "was less than or equal to",	(sDD): "were less than or equal to",		(sG):sDI,		(sP): i1,			(sT): i2,	],
		was_greater_than	: [ (sD): "was greater than",		(sDD): "were greater than",			(sG):sDI,		(sP): i1,			(sT): i2,	],
		was_greater_than_or_equal_to	: [ (sD): "was greater than or equal to",	(sDD): "were greater than or equal to",		(sG):sDI,		(sP): i1,			(sT): i2,	],
		was_inside_of_range	: [ (sD): "was inside of range",		(sDD): "were inside of range",			(sG):sDI,		(sP): i2,			(sT): i2,	],
		was_outside_of_range	: [ (sD): "was outside of range",		(sDD): "were outside of range",			(sG):sDI,		(sP): i2,			(sT): i2,	],
		was_even			: [ (sD): "was even",			(sDD): "were even",				(sG):sDI,					(sT): i2,	],
		was_odd				: [ (sD): "was odd",			(sDD): "were odd",					(sG):sDI,					(sT): i2,	],
//		was_true			: [ (sD): "was true",			(sDD): "were true",					(sG):"bs",		(sP): iZ					],
//		was_false			: [ (sD): "was false",			(sDD): "were false",				(sG):"bs",		(sP): iZ					],
		is_any				: [ (sD): "is any",									(sG):sT,		(sP): iZ					],
		is_before			: [ (sD): "is before",									(sG):sT,		(sP): i1					],
		is_after			: [ (sD): "is after",									(sG):sT,		(sP): i1					],
		is_between			: [ (sD): "is between",									(sG):sT,		(sP): i2					],
		is_not_between		: [ (sD): "is not between",								(sG):sT,		(sP): i2					],
	],
	triggers: [
		happens_daily_at	: [ (sD): "happens daily at",								(sG):sT,		(sP): i1					],
		arrives				: [ (sD): "arrives",									(sG):"e",		(sP): i2					],
		executes			: [ (sD): "executes",									(sG):sV,		(sP): i1					],
		changes				: [ (sD): "changes",			(sDD): "change",					(sG):"bdfis",						],
		changes_to			: [ (sD): "changes to",			(sDD): "change to",				(sG):"bdis",	(sP): i1,					],
		changes_away_from	: [ (sD): "changes away from",		(sDD): "change away from",				(sG):"bdis",	(sP): i1,					],
		changes_to_any_of	: [ (sD): "changes to any of",		(sDD): "change to any of",				(sG):"dis",	(sP): i1,	(sM): true,			],
		changes_away_from_any_of	: [ (sD): "changes away from any of",	(sDD): "change away from any of",			(sG):"dis",	(sP): i1,	(sM): true,			],
		drops				: [ (sD): "drops",				(sDD): "drop",					(sG):sDI,							],
		does_not_drop		: [ (sD): "does not drop",			(sDD): "do not drop",				(sG):sDI,							],
		drops_below			: [ (sD): "drops below",			(sDD): "drop below",				(sG):sDI,		(sP): i1,					],
		drops_to_or_below	: [ (sD): "drops to or below",		(sDD): "drop to or below",				(sG):sDI,		(sP): i1,					],
		remains_below		: [ (sD): "remains below",			(sDD): "remains below",				(sG):sDI,		(sP): i1,					],
		remains_below_or_equal_to	: [ (sD): "remains below or equal to",	(sDD): "remains below or equal to",		(sG):sDI,		(sP): i1,					],
		rises				: [ (sD): "rises",				(sDD): "rise",					(sG):sDI,							],
		does_not_rise		: [ (sD): "does not rise",			(sDD): "do not rise",				(sG):sDI,							],
		gets				: [ (sD): "gets",										(sG):sM,		(sP): i1					],
		gets_any			: [ (sD): "gets any",									(sG):sM,							],
		event_occurs		: [ (sD): "event occurs",									(sG):sS,						],
		receives			: [ (sD): "receives",			(sDD): "receive",					(sG):"bdis",	(sP): i1,					],
		rises_above			: [ (sD): "rises above",			(sDD): "rise above",				(sG):sDI,		(sP): i1,					],
		rises_to_or_above	: [ (sD): "rises to or above",		(sDD): "rise to or above",				(sG):sDI,		(sP): i1,					],
		remains_above		: [ (sD): "remains above",			(sDD): "remains above",				(sG):sDI,		(sP): i1,					],
		remains_above_or_equal_to	: [ (sD): "remains above or equal to",	(sDD): "remains above or equal to",		(sG):sDI,		(sP): i1,					],
		enters_range		: [ (sD): "enters range",			(sDD): "enter range",				(sG):sDI,		(sP): i2,					],
		remains_outside_of_range	: [ (sD): "remains outside of range",	(sDD): "remain outside of range",			(sG):sDI,		(sP): i2,					],
		exits_range			: [ (sD): "exits range",			(sDD): "exit range",				(sG):sDI,		(sP): i2,					],
		remains_inside_of_range		: [ (sD): "remains inside of range",	(sDD): "remain inside of range",			(sG):sDI,		(sP): i2,					],
		becomes_even		: [ (sD): "becomes even",			(sDD): "become even",				(sG):sDI,							],
		remains_even		: [ (sD): "remains even",			(sDD): "remain even",				(sG):sDI,							],
		becomes_odd			: [ (sD): "becomes odd",			(sDD): "become odd",				(sG):sDI,							],
		remains_odd			: [ (sD): "remains odd",			(sDD): "remain odd",				(sG):sDI,							],
		stays_unchanged		: [ (sD): "stays unchanged",	(sDD): "stay unchanged",				(sG):"bdfis",				(sT): i1,	],
		stays				: [ (sD): "is now and stays",		(sDD): "are now and stay",				(sG):"bdis",	(sP): i1,			(sT): i1,	],
		stays_not			: [ (sD): "is not and stays not",		(sDD): "are not and stay not",			(sG):"bdis",	(sP): i1,			(sT): i1,	],
		stays_away_from		: [ (sD): "is away and stays away from",		(sDD): "are away and stay away from",	(sG):"bdis",	(sP): i1,			(sT): i1,	],
		stays_any_of		: [ (sD): "is any and stays any of",		(sDD): "are any and stay any of",				(sG):"dis",	(sP): i1,	(sM): true,	(sT): i1,	],
		stays_away_from_any_of		: [ (sD): "is away and stays away from any of",	(sDD): "are away and stay away from any of",		(sG):"bdis",	(sP): i1,	(sM): true,	(sT): i1,	],
		stays_equal_to		: [ (sD): "is equal to and stays equal to",	(sDD): "are equal or stay equal to",			(sG):sDI,		(sP): i1,			(sT): i1,	],
		stays_different_than		: [ (sD): "is different and stays different than",	(sDD): "are different and stay different than",		(sG):sDI,		(sP): i1,			(sT): i1,	],
		stays_less_than		: [ (sD): "is less and stays less than",		(sDD): "are less and stay less than",			(sG):sDI,		(sP): i1,			(sT): i1,	],
		stays_less_than_or_equal_to	: [ (sD): "is less or equal and stays less than or equal to",	(sDD): "are less or equal and stay less than or equal to",		(sG):sDI,		(sP): i1,			(sT): i1,	],
		stays_greater_than	: [ (sD): "is greater and stays greater than",	(sDD): "are greater and stay greater than",		(sG):sDI,		(sP): i1,			(sT): i1,	],
		stays_greater_than_or_equal_to	: [ (sD): "is greater or equal and stays greater than or equal to",	(sDD): "are greater or equal stay greater than or equal to",	(sG):sDI,		(sP): i1,			(sT): i1,	],
		stays_inside_of_range		: [ (sD): "is inside and stays inside of range",	(sDD): "are inside and stay inside of range",		(sG):sDI,		(sP): i2,			(sT): i1,	],
		stays_outside_of_range		: [ (sD): "is outside and stays outside of range",	(sDD): "stay outside of range",		(sG):sDI,		(sP): i2,			(sT): i1,	],
		stays_even			: [ (sD): "is even and stays even",		(sDD): "are even and stay even",		(sG):sDI,					(sT): i1,	],
		stays_odd			: [ (sD): "is odd and stays odd",			(sDD): "are odd and stay odd",		(sG):sDI,					(sT): i1,	],
	]
]

/*private Map comparisons(){
	return comparisonsFLD
}*/

@Field final Map<String,Map> functionsFLD=[
	age				: [ (sT): sINT,						],
	previousage		: [ (sT): sINT,	(sD): "previousAge",	],
	previousvalue	: [ (sT): sDYN,	(sD): "previousValue",	],
	newer			: [ (sT): sINT,						],
	older			: [ (sT): sINT,						],
	least			: [ (sT): sDYN,						],
	most			: [ (sT): sDYN,						],
	avg				: [ (sT): sDEC,						],
	variance		: [ (sT): sDEC,						],
	median			: [ (sT): sDEC,						],
	stdev			: [ (sT): sDEC,						],
	round			: [ (sT): sDEC,						],
	ceil			: [ (sT): sDEC,						],
	ceiling			: [ (sT): sDEC,						],
	floor			: [ (sT): sDEC,						],
	sort			: [ (sT): sDYN+'[]',				],
	min				: [ (sT): sDEC,						],
	max				: [ (sT): sDEC,						],
	sum				: [ (sT): sDEC,						],
	count			: [ (sT): sINT,						],
	size			: [ (sT): sINT,						],
	left			: [ (sT): sSTR,						],
	right			: [ (sT): sSTR,						],
	mid				: [ (sT): sSTR,						],
	substring		: [ (sT): sSTR,						],
	sprintf			: [ (sT): sSTR,						],
	format			: [ (sT): sSTR,						],
	string			: [ (sT): sSTR,						],
	replace			: [ (sT): sSTR,						],
	indexof			: [ (sT): sINT,	(sD): "indexOf",		],
	lastindexof		: [ (sT): sINT,	(sD): "lastIndexOf",	],
	concat			: [ (sT): sSTR,						],
	(sTXT)			: [ (sT): sSTR,						],
	lower			: [ (sT): sSTR,						],
	upper			: [ (sT): sSTR,						],
	title			: [ (sT): sSTR,						],
	int				: [ (sT): sINT,						],
	integer			: [ (sT): sINT,						],
	float			: [ (sT): sDEC,						],
	(sDEC)			: [ (sT): sDEC,						],
	number			: [ (sT): sDEC,						],
	(sBOOL)			: [ (sT): sBOOLN,					],
	(sBOOLN)		: [ (sT): sBOOLN,					],
	power			: [ (sT): sDEC,						],
	sqr				: [ (sT): sDEC,						],
	sqrt			: [ (sT): sDEC,						],
	dewpoint		: [ (sT): sDEC,	(sD): "dewPoint",	],
	fahrenheit		: [ (sT): sDEC,						],
	celsius			: [ (sT): sDEC,						],
	converttemperatureifneeded	: [ (sT):sDEC, (sD): "convertTemperatureIfNeeded",	],
	dateAdd			: [ (sT): sTIME,		(sD): "dateAdd",		],
	startswith		: [ (sT): sBOOLN,	(sD): "startsWith",	],
	endswith		: [ (sT): sBOOLN,	(sD): "endsWith",		],
	contains		: [ (sT): sBOOLN,					],
	matches			: [ (sT): sBOOLN,					],
	exists			: [ (sT): sBOOLN,					],
	eq				: [ (sT): sBOOLN,					],
	lt				: [ (sT): sBOOLN,					],
	le				: [ (sT): sBOOLN,					],
	gt				: [ (sT): sBOOLN,					],
	ge				: [ (sT): sBOOLN,					],
	not				: [ (sT): sBOOLN,					],
	isempty			: [ (sT): sBOOLN,	(sD): "isEmpty",		],
	if				: [ (sT): sDYN,						],
	datetime		: [ (sT): sDTIME,					],
	date			: [ (sT): sDATE,					],
	time			: [ (sT): sTIME,					],
	addseconds		: [ (sT): sDTIME,	(sD): "addSeconds"		],
	addminutes		: [ (sT): sDTIME,	(sD): "addMinutes"		],
	addhours		: [ (sT): sDTIME,	(sD): "addHours"		],
	adddays			: [ (sT): sDTIME,	(sD): "addDays"		],
	addweeks		: [ (sT): sDTIME,	(sD): "addWeeks"		],
	isbetween		: [ (sT): sBOOLN,	(sD): "isBetween"		],
	formatduration	: [ (sT): sSTR,	(sD): "formatDuration"	],
	formatdatetime	: [ (sT): sSTR,	(sD): "formatDateTime"	],
	random			: [ (sT): sDYN,					],
	strlen			: [ (sT): sINT,					],
	length			: [ (sT): sINT,					],
	coalesce		: [ (sT): sDYN,					],
	weekdayname		: [ (sT): sSTR,	(sD): "weekDayName"	],
	monthname		: [ (sT): sSTR,	(sD): "monthName"		],
	arrayitem		: [ (sT): sDYN,	(sD): "arrayItem"		],
	trim			: [ (sT): sSTR							],
	trimleft		: [ (sT): sSTR,	(sD): "trimLeft"		],
	ltrim			: [ (sT): sSTR							],
	trimright		: [ (sT): sSTR,	(sD): "trimRight"		],
	rtrim			: [ (sT): sSTR							],
	hsltohex		: [ (sT): sSTR,	(sD): "hslToHex"		],
	abs				: [ (sT): sDYN						],
	rangevalue		: [ (sT): sDYN,	(sD): "rangeValue"		],
	rainbowvalue	: [ (sT): sSTR,	(sD): "rainbowValue"	],
	distance		: [ (sT): sDEC						],
	json			: [ (sT): sDYN						],
	urlencode		: [ (sT): sSTR,	(sD): "urlEncode"				],
	encodeuricomponent	: [ (sT): sSTR,	(sD): "encodeURIComponent"			],
]

/*private Map functions(){
	return functionsFLD
}

def getIftttKey(){
	def module=state.modules?.IFTTT
	return (module && module.connected ? module.key : null)
}*/
/*
def getLifxToken(){
	def module=state.modules?.LIFX
	return (module && module.connected ? module.token : null)
}
*/
private Map getLocationModeOptions(Boolean updateCache=false){
	Map result=[:]
	for (mode in location.modes){
		if(mode) result[hashId((Long)mode.getId())]=(String)mode.name
	}
	return result
}
private static Map getAlarmSystemStatusActions(){
	return [
		armAway:		"Arm Away",
		armHome:		"Arm Home",
		armNight:		"Arm Night",
		disarm:			"Disarm",
		armRules:		"Arm Monitor Rules",
		disarmRules:	"Disarm Monitor Rules",
		disarmAll:		"Disarm All",
		armAll:			"Arm All",
		cancelAlerts:	"Cancel Alerts"
	]
}

/*
private static Map getAlarmSystemStatusOptions(){
	return [
	off:	"Disarmed",
	stay:	"Armed/Stay",
	away:	"Armed/Away"
	]
}
*/

private static Map getHubitatAlarmSystemStatusOptions(){
	return [
		armedAway:		"Armed Away",
		armingAway:		"Arming Away Pending exit delay",
		armedHome:		"Armed Home",
		armingHome:		"Arming Home pending exit delay",
		armedNight:		"Armed Night",
		armingNight:	"Arming Night pending exit delay",
		(sDISARMD):		"Disarmed",
		allDisarmed:	"All Disarmed"
	]
}

private static Map getAlarmSystemAlertOptions(){
	return [
		intrusion:		"Intrusion Away",
		"intrusion-home":	"Intrusion Home",
		"intrusion-night":	"Intrusion Night",
		smoke:			"Smoke",
		water:			"Water",
		rule:			"Rule",
		cancel:			"Alerts cancelled",
		arming:			"Arming failure"
	]
}

private static Map getAlarmSystemRuleOptions(){
	return [
		armedRule:	"Armed Rule",
		disarmedRule:	"Disarmed Rule"
	]
}


/*
private Map getRoutineOptions(updateCache=false){
	def routines=location.helloHome?.getPhrases()
	def result=[:]
	if(routines){
		routines=routines.sort{ it?.label ?: sBLK }
		for(routine in routines){
			if(routine && routine?.label)
				result[hashId(routine.id, updateCache)]=routine.label
		}
	}
	return result
}

private Map getAskAlexaOptions(){
	return state.askAlexaMacros ?: [null:"AskAlexa not installed - please install or open AskAlexa"]
}

private Map getEchoSistantOptions(){
	return state.echoSistantProfiles ?: [null:"EchoSistant not installed - please install or open EchoSistant"]
}
*/

import hubitat.helper.RMUtils

private Map getRuleOptions(Boolean updateCache){
	Map result=[:]
	['4.1', '5.0'].each { String ver ->
		def rules=RMUtils.getRuleList(ver ?: sNULL)
		rules.each{rule->
			rule.each{pair->
				result[hashId(pair.key)]=pair.value
			}
		}
	}
	return result
}

Map getChildVirtDevices(){
	Map<String,Map> result=virtualDevices()
	Map cleanResult=[:]
	Map defv=[(sN):sA]
	Map t0
	def hasAC, hasO
	//result.each{
	for(it in result){
		t0=[:]
		hasAC=it.value.ac
		hasO=it.value.o
		if(hasAC!=null) t0=t0+[ac:hasAC]
		if(hasO!=null) t0=t0+[(sO):hasO]
		if(t0==[:]) t0=defv
		cleanResult[it.key.toString()]=t0
	}
	return cleanResult
}

private Map<String,Map> virtualDevices(Boolean updateCache=false){
	return [
		date:			[ (sN): 'Date',				(sT): sDATE,		],
		datetime:		[ (sN): 'Date & Time',		(sT): sDTIME,	],
		time:			[ (sN): 'Time',				(sT): sTIME,		],
		email:			[ (sN): 'Email',			(sT): 'email',						(sM): true	],
		powerSource:	[ (sN): 'Hub power source',	(sT): sENUM,	(sO): [battery: 'battery', mains: 'mains'],					x: true	],
		ifttt:			[ (sN): 'IFTTT',			(sT): sSTR,						(sM): true	],
		mode:			[ (sN): 'Location mode',	(sT): sENUM,	(sO): getLocationModeOptions(updateCache),	x: true],
		tile:			[ (sN): 'Piston tile',		(sT): sENUM,	(sO): ['1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16'],		(sM): true	],
		pistonResume: 	[ (sN): 'Piston Resumed',	(sT): sSTR,		x: true],
// HE specific events
		rule:			[ (sN): 'Rule',				(sT): sENUM,	(sO): getRuleOptions(updateCache),		(sM): true ],
		systemStart:	[ (sN): 'System Start',		(sT): sSTR,		x: true],
		severeLoad:		[ (sN): 'Severe Load',		(sT): sSTR,		x: true],
		zigbeeOff:		[ (sN): 'Zibee Off',		(sT): sSTR,		x: true],
		zigbeeOn:		[ (sN): 'Zigbee On',		(sT): sSTR,		x: true],
		zwaveCrashed:	[ (sN): 'Z-Wave crashed',	(sT): sSTR,		x: true],
		sunriseTime:	[ (sN): 'Sunrise Time',		(sT): sSTR,		x: true],
		sunsetTime:		[ (sN): 'Sunset Time',		(sT): sSTR,		x: true],
//ac - actions. hubitat doesn't reuse the status for actions
		alarmSystemStatus:	[ (sN): 'Hubitat Safety Monitor status',	(sT): sENUM,		(sO): getHubitatAlarmSystemStatusOptions(), ac: getAlarmSystemStatusActions(),		x: true],
		alarmSystemEvent:	[ (sN): 'Hubitat Safety Monitor event',		(sT): sENUM,		(sO): getAlarmSystemStatusActions(),	(sM): true],
		alarmSystemAlert:	[ (sN): 'Hubitat Safety Monitor alert',		(sT): sENUM,		(sO): getAlarmSystemAlertOptions(),	(sM): true,			x: true],
		alarmSystemRule:	[ (sN): 'Hubitat Safety Monitor rule',		(sT): sENUM,		(sO): getAlarmSystemRuleOptions(),		(sM): true]
	]
}

@Field final List theColorsFLD=[
		[(sNM): "Alice Blue", (sRGB): "#F0F8FF", (sH): 208, (sS): 100, (sL): 97], [(sNM): "Antique White", (sRGB): "#FAEBD7", (sH): 34, (sS): 78, (sL): 91],
		[(sNM): "Aqua", (sRGB): "#00FFFF", (sH): 180, (sS): 100, (sL): 50], [(sNM): "Aquamarine", (sRGB): "#7FFFD4", (sH): 160, (sS): 100, (sL): 75],
		[(sNM): "Azure", (sRGB): "#F0FFFF", (sH): 180, (sS): 100, (sL): 97], [(sNM): "Beige", (sRGB): "#F5F5DC", (sH): 60, (sS): 56, (sL): 91],
		[(sNM): "Bisque", (sRGB): "#FFE4C4", (sH): 33, (sS): 100, (sL): 88], [(sNM): "Blanched Almond", (sRGB): "#FFEBCD", (sH): 36, (sS): 100, (sL): 90],
		[(sNM): "Blue", (sRGB): "#0000FF", (sH): 240, (sS): 100, (sL): 50], [(sNM): "Blue Violet", (sRGB): "#8A2BE2", (sH): 271, (sS): 76, (sL): 53],
		[(sNM): "Brown", (sRGB): "#A52A2A", (sH): iZ, (sS): 59, (sL): 41], [(sNM): "Burly Wood", (sRGB): "#DEB887", (sH): 34, (sS): 57, (sL): 70],
		[(sNM): "Cadet Blue", (sRGB): "#5F9EA0", (sH): 182, (sS): 25, (sL): 50], [(sNM): "Chartreuse", (sRGB): "#7FFF00", (sH): 90, (sS): 100, (sL): 50],
		[(sNM): "Chocolate", (sRGB): "#D2691E", (sH): 25, (sS): 75, (sL): 47], [(sNM): "Cool White", (sRGB): "#F3F6F7", (sH): 187, (sS): 19, (sL): 96],
		[(sNM): "Coral", (sRGB): "#FF7F50", (sH): 16, (sS): 100, (sL): 66], [(sNM): "Corn Flower Blue", (sRGB): "#6495ED", (sH): 219, (sS): 79, (sL): 66],
		[(sNM): "Corn Silk", (sRGB): "#FFF8DC", (sH): 48, (sS): 100, (sL): 93], [(sNM): "Crimson", (sRGB): "#DC143C", (sH): 348, (sS): 83, (sL): 58],
		[(sNM): "Cyan", (sRGB): "#00FFFF", (sH): 180, (sS): 100, (sL): 50], [(sNM): "Dark Blue", (sRGB): "#00008B", (sH): 240, (sS): 100, (sL): 27],
		[(sNM): "Dark Cyan", (sRGB): "#008B8B", (sH): 180, (sS): 100, (sL): 27], [(sNM): "Dark Golden Rod", (sRGB): "#B8860B", (sH): 43, (sS): 89, (sL): 38],
		[(sNM): "Dark Gray", (sRGB): "#A9A9A9", (sH): iZ, (sS): iZ, (sL): 66], [(sNM): "Dark Green", (sRGB): "#006400", (sH): 120, (sS): 100, (sL): 20],
		[(sNM): "Dark Khaki", (sRGB): "#BDB76B", (sH): 56, (sS): 38, (sL): 58], [(sNM): "Dark Magenta", (sRGB): "#8B008B", (sH): 300, (sS): 100, (sL): 27],
		[(sNM): "Dark Olive Green", (sRGB): "#556B2F", (sH): 82, (sS): 39, (sL): 30], [(sNM): "Dark Orange", (sRGB): "#FF8C00", (sH): 33, (sS): 100, (sL): 50],
		[(sNM): "Dark Orchid", (sRGB): "#9932CC", (sH): 280, (sS): 61, (sL): 50], [(sNM): "Dark Red", (sRGB): "#8B0000", (sH): iZ, (sS): 100, (sL): 27],
		[(sNM): "Dark Salmon", (sRGB): "#E9967A", (sH): 15, (sS): 72, (sL): 70], [(sNM): "Dark Sea Green", (sRGB): "#8FBC8F", (sH): 120, (sS): 25, (sL): 65],
		[(sNM): "Dark Slate Blue", (sRGB): "#483D8B", (sH): 248, (sS): 39, (sL): 39], [(sNM): "Dark Slate Gray", (sRGB): "#2F4F4F", (sH): 180, (sS): 25, (sL): 25],
		[(sNM): "Dark Turquoise", (sRGB): "#00CED1", (sH): 181, (sS): 100, (sL): 41], [(sNM): "Dark Violet", (sRGB): "#9400D3", (sH): 282, (sS): 100, (sL): 41],
		[(sNM): "Daylight White", (sRGB): "#CEF4FD", (sH): 191, (sS): 9, (sL): 90], [(sNM): "Deep Pink", (sRGB): "#FF1493", (sH): 328, (sS): 100, (sL): 54],
		[(sNM): "Deep Sky Blue", (sRGB): "#00BFFF", (sH): 195, (sS): 100, (sL): 50], [(sNM): "Dim Gray", (sRGB): "#696969", (sH): iZ, (sS): iZ, (sL): 41],
		[(sNM): "Dodger Blue", (sRGB): "#1E90FF", (sH): 210, (sS): 100, (sL): 56], [(sNM): "Fire Brick", (sRGB): "#B22222", (sH): iZ, (sS): 68, (sL): 42],
		[(sNM): "Floral White", (sRGB): "#FFFAF0", (sH): 40, (sS): 100, (sL): 97], [(sNM): "Forest Green", (sRGB): "#228B22", (sH): 120, (sS): 61, (sL): 34],
		[(sNM): "Fuchsia", (sRGB): "#FF00FF", (sH): 300, (sS): 100, (sL): 50], [(sNM): "Gainsboro", (sRGB): "#DCDCDC", (sH): iZ, (sS): iZ, (sL): 86],
		[(sNM): "Ghost White", (sRGB): "#F8F8FF", (sH): 240, (sS): 100, (sL): 99], [(sNM): "Gold", (sRGB): "#FFD700", (sH): 51, (sS): 100, (sL): 50],
		[(sNM): "Golden Rod", (sRGB): "#DAA520", (sH): 43, (sS): 74, (sL): 49], [(sNM): "Gray", (sRGB): "#808080", (sH): iZ, (sS): iZ, (sL): 50],
		[(sNM): "Green", (sRGB): "#008000", (sH): 120, (sS): 100, (sL): 25], [(sNM): "Green Yellow", (sRGB): "#ADFF2F", (sH): 84, (sS): 100, (sL): 59],
		[(sNM): "Honeydew", (sRGB): "#F0FFF0", (sH): 120, (sS): 100, (sL): 97], [(sNM): "Hot Pink", (sRGB): "#FF69B4", (sH): 330, (sS): 100, (sL): 71],
		[(sNM): "Indian Red", (sRGB): "#CD5C5C", (sH): iZ, (sS): 53, (sL): 58], [(sNM): "Indigo", (sRGB): "#4B0082", (sH): 275, (sS): 100, (sL): 25],
		[(sNM): "Ivory", (sRGB): "#FFFFF0", (sH): 60, (sS): 100, (sL): 97], [(sNM): "Khaki", (sRGB): "#F0E68C", (sH): 54, (sS): 77, (sL): 75],
		[(sNM): "Lavender", (sRGB): "#E6E6FA", (sH): 240, (sS): 67, (sL): 94], [(sNM): "Lavender Blush", (sRGB): "#FFF0F5", (sH): 340, (sS): 100, (sL): 97],
		[(sNM): "Lawn Green", (sRGB): "#7CFC00", (sH): 90, (sS): 100, (sL): 49], [(sNM): "Lemon Chiffon", (sRGB): "#FFFACD", (sH): 54, (sS): 100, (sL): 90],
		[(sNM): "Light Blue", (sRGB): "#ADD8E6", (sH): 195, (sS): 53, (sL): 79], [(sNM): "Light Coral", (sRGB): "#F08080", (sH): iZ, (sS): 79, (sL): 72],
		[(sNM): "Light Cyan", (sRGB): "#E0FFFF", (sH): 180, (sS): 100, (sL): 94], [(sNM): "Light Golden Rod Yellow", (sRGB): "#FAFAD2", (sH): 60, (sS): 80, (sL): 90],
		[(sNM): "Light Gray", (sRGB): "#D3D3D3", (sH): iZ, (sS): iZ, (sL): 83], [(sNM): "Light Green", (sRGB): "#90EE90", (sH): 120, (sS): 73, (sL): 75],
		[(sNM): "Light Pink", (sRGB): "#FFB6C1", (sH): 351, (sS): 100, (sL): 86], [(sNM): "Light Salmon", (sRGB): "#FFA07A", (sH): 17, (sS): 100, (sL): 74],
		[(sNM): "Light Sea Green", (sRGB): "#20B2AA", (sH): 177, (sS): 70, (sL): 41], [(sNM): "Light Sky Blue", (sRGB): "#87CEFA", (sH): 203, (sS): 92, (sL): 75],
		[(sNM): "Light Slate Gray", (sRGB): "#778899", (sH): 210, (sS): 14, (sL): 53], [(sNM): "Light Steel Blue", (sRGB): "#B0C4DE", (sH): 214, (sS): 41, (sL): 78],
		[(sNM): "Light Yellow", (sRGB): "#FFFFE0", (sH): 60, (sS): 100, (sL): 94], [(sNM): "Lime", (sRGB): "#00FF00", (sH): 120, (sS): 100, (sL): 50],
		[(sNM): "Lime Green", (sRGB): "#32CD32", (sH): 120, (sS): 61, (sL): 50], [(sNM): "Linen", (sRGB): "#FAF0E6", (sH): 30, (sS): 67, (sL): 94],
		[(sNM): "Maroon", (sRGB): "#800000", (sH): iZ, (sS): 100, (sL): 25], [(sNM): "Medium Aquamarine", (sRGB): "#66CDAA", (sH): 160, (sS): 51, (sL): 60],
		[(sNM): "Medium Blue", (sRGB): "#0000CD", (sH): 240, (sS): 100, (sL): 40], [(sNM): "Medium Orchid", (sRGB): "#BA55D3", (sH): 288, (sS): 59, (sL): 58],
		[(sNM): "Medium Purple", (sRGB): "#9370DB", (sH): 260, (sS): 60, (sL): 65], [(sNM): "Medium Sea Green", (sRGB): "#3CB371", (sH): 147, (sS): 50, (sL): 47],
		[(sNM): "Medium Slate Blue", (sRGB): "#7B68EE", (sH): 249, (sS): 80, (sL): 67], [(sNM): "Medium Spring Green", (sRGB): "#00FA9A", (sH): 157, (sS): 100, (sL): 49],
		[(sNM): "Medium Turquoise", (sRGB): "#48D1CC", (sH): 178, (sS): 60, (sL): 55], [(sNM): "Medium Violet Red", (sRGB): "#C71585", (sH): 322, (sS): 81, (sL): 43],
		[(sNM): "Midnight Blue", (sRGB): "#191970", (sH): 240, (sS): 64, (sL): 27], [(sNM): "Mint Cream", (sRGB): "#F5FFFA", (sH): 150, (sS): 100, (sL): 98],
		[(sNM): "Misty Rose", (sRGB): "#FFE4E1", (sH): 6, (sS): 100, (sL): 94], [(sNM): "Moccasin", (sRGB): "#FFE4B5", (sH): 38, (sS): 100, (sL): 85],
		[(sNM): "Navajo White", (sRGB): "#FFDEAD", (sH): 36, (sS): 100, (sL): 84], [(sNM): "Navy", (sRGB): "#000080", (sH): 240, (sS): 100, (sL): 25],
		[(sNM): "Old Lace", (sRGB): "#FDF5E6", (sH): 39, (sS): 85, (sL): 95], [(sNM): "Olive", (sRGB): "#808000", (sH): 60, (sS): 100, (sL): 25],
		[(sNM): "Olive Drab", (sRGB): "#6B8E23", (sH): 80, (sS): 60, (sL): 35], [(sNM): "Orange", (sRGB): "#FFA500", (sH): 39, (sS): 100, (sL): 50],
		[(sNM): "Orange Red", (sRGB): "#FF4500", (sH): 16, (sS): 100, (sL): 50], [(sNM): "Orchid", (sRGB): "#DA70D6", (sH): 302, (sS): 59, (sL): 65],
		[(sNM): "Pale Golden Rod", (sRGB): "#EEE8AA", (sH): 55, (sS): 67, (sL): 80], [(sNM): "Pale Green", (sRGB): "#98FB98", (sH): 120, (sS): 93, (sL): 79],
		[(sNM): "Pale Turquoise", (sRGB): "#AFEEEE", (sH): 180, (sS): 65, (sL): 81], [(sNM): "Pale Violet Red", (sRGB): "#DB7093", (sH): 340, (sS): 60, (sL): 65],
		[(sNM): "Papaya Whip", (sRGB): "#FFEFD5", (sH): 37, (sS): 100, (sL): 92], [(sNM): "Peach Puff", (sRGB): "#FFDAB9", (sH): 28, (sS): 100, (sL): 86],
		[(sNM): "Peru", (sRGB): "#CD853F", (sH): 30, (sS): 59, (sL): 53], [(sNM): "Pink", (sRGB): "#FFC0CB", (sH): 350, (sS): 100, (sL): 88],
		[(sNM): "Plum", (sRGB): "#DDA0DD", (sH): 300, (sS): 47, (sL): 75], [(sNM): "Powder Blue", (sRGB): "#B0E0E6", (sH): 187, (sS): 52, (sL): 80],
		[(sNM): "Purple", (sRGB): "#800080", (sH): 300, (sS): 100, (sL): 25], [(sNM): "Red", (sRGB): "#FF0000", (sH): iZ, (sS): 100, (sL): 50],
		[(sNM): "Rosy Brown", (sRGB): "#BC8F8F", (sH): iZ, (sS): 25, (sL): 65], [(sNM): "Royal Blue", (sRGB): "#4169E1", (sH): 225, (sS): 73, (sL): 57],
		[(sNM): "Saddle Brown", (sRGB): "#8B4513", (sH): 25, (sS): 76, (sL): 31], [(sNM): "Salmon", (sRGB): "#FA8072", (sH): 6, (sS): 93, (sL): 71],
		[(sNM): "Sandy Brown", (sRGB): "#F4A460", (sH): 28, (sS): 87, (sL): 67], [(sNM): "Sea Green", (sRGB): "#2E8B57", (sH): 146, (sS): 50, (sL): 36],
		[(sNM): "Sea Shell", (sRGB): "#FFF5EE", (sH): 25, (sS): 100, (sL): 97], [(sNM): "Sienna", (sRGB): "#A0522D", (sH): 19, (sS): 56, (sL): 40],
		[(sNM): "Silver", (sRGB): "#C0C0C0", (sH): iZ, (sS): iZ, (sL): 75], [(sNM): "Sky Blue", (sRGB): "#87CEEB", (sH): 197, (sS): 71, (sL): 73],
		[(sNM): "Slate Blue", (sRGB): "#6A5ACD", (sH): 248, (sS): 53, (sL): 58], [(sNM): "Slate Gray", (sRGB): "#708090", (sH): 210, (sS): 13, (sL): 50],
		[(sNM): "Snow", (sRGB): "#FFFAFA", (sH): iZ, (sS): 100, (sL): 99], [(sNM): "Soft White", (sRGB): "#B6DA7C", (sH): 83, (sS): 44, (sL): 67],
		[(sNM): "Spring Green", (sRGB): "#00FF7F", (sH): 150, (sS): 100, (sL): 50], [(sNM): "Steel Blue", (sRGB): "#4682B4", (sH): 207, (sS): 44, (sL): 49],
		[(sNM): "Tan", (sRGB): "#D2B48C", (sH): 34, (sS): 44, (sL): 69], [(sNM): "Teal", (sRGB): "#008080", (sH): 180, (sS): 100, (sL): 25],
		[(sNM): "Thistle", (sRGB): "#D8BFD8", (sH): 300, (sS): 24, (sL): 80], [(sNM): "Tomato", (sRGB): "#FF6347", (sH): 9, (sS): 100, (sL): 64],
		[(sNM): "Turquoise", (sRGB): "#40E0D0", (sH): 174, (sS): 72, (sL): 56], [(sNM): "Violet", (sRGB): "#EE82EE", (sH): 300, (sS): 76, (sL): 72],
		[(sNM): "Warm White", (sRGB): "#DAF17E", (sH): 72, (sS): 20, (sL): 72], [(sNM): "Wheat", (sRGB): "#F5DEB3", (sH): 39, (sS): 77, (sL): 83],
		[(sNM): "White", (sRGB): "#FFFFFF", (sH): iZ, (sS): iZ, (sL): 100], [(sNM): "White Smoke", (sRGB): "#F5F5F5", (sH): iZ, (sS): iZ, (sL): 96],
		[(sNM): "Yellow", (sRGB): "#FFFF00", (sH): 60, (sS): 100, (sL): 50], [(sNM): "Yellow Green", (sRGB): "#9ACD32", (sH): 80, (sS): 61, (sL): 50]
]

List getColors(){
	return theColorsFLD
}

private Boolean isHubitat(){
	return hubUID!=null
}

private static String sectionTitleStr(String title)	{ return '<h3>'+title+'</h3>' }
private static String inputTitleStr(String title)	{ return '<u>'+title+'</u>' }
//private static String pageTitleStr(String title)	{ return '<h1>'+title+'</h1>' }
//private static String paraTitleStr(String title)	{ return '<b>'+title+'</b>' }

@CompileStatic
private static String imgTitle(String imgSrc,String titleStr,String color=sNULL,Integer imgWidth=30,Integer imgHeight=0){
	String imgStyle=sBLK
	String myImgSrc='https://raw.githubusercontent.com/ady624/webCoRE/master/resources/icons/'+imgSrc
	imgStyle += imgWidth>0 ? 'width: '+imgWidth.toString()+'px !important;':sBLK
	imgStyle += imgHeight>0 ? imgWidth!=0 ? sSPC:sBLK+'height:'+imgHeight.toString()+'px !important;':sBLK
	if(color!=sNULL){ return """<div style="color: ${color}; font-weight:bold;"><img style="${imgStyle}" src="${myImgSrc}"> ${titleStr}</img></div>""".toString() }
	else{ return """<img style="${imgStyle}" src="${myImgSrc}"> ${titleStr}</img>""".toString() }
}

static String myObj(obj){
	if(obj instanceof String)return 'String'
	else if(obj instanceof Map)return 'Map'
	else if(obj instanceof List)return 'List'
	else if(obj instanceof ArrayList)return 'ArrayList'
	else if(obj instanceof BigInteger)return 'BigInt'
	else if(obj instanceof Long)return 'Long'
	else if(obj instanceof Integer)return 'Int'
	else if(obj instanceof Boolean)return 'Bool'
	else if(obj instanceof BigDecimal)return 'BigDec'
	else if(obj instanceof Double)return 'Double'
	else if(obj instanceof Float)return 'Float'
	else if(obj instanceof Byte)return 'Byte'
	else if(obj instanceof com.hubitat.app.DeviceWrapper)return 'Device'
	else return 'unknown'
}

@SuppressWarnings('GroovyAssignabilityCheck')
Map<String,Object> fixHeGType(Boolean toHubV, String typ, v, String dtyp){
	Map ret
	ret=[:]
	def myv
	myv=v
	if(toHubV){ // from webcore(9 types) -> global(5 types + 3 overloads + sDYN becomes sSTR)
		//noinspection GroovyFallthrough
		switch(typ) {
			case sINT:
				ret=[(sINT): v]
				break
			case sBOOLN:
				ret=[(sBOOLN): v]
				break
			case sDEC:
				ret=['bigdecimal': v]
				break
			case sDEV:
				// HE this is a List<String> -> String of words separated by a space (can split())
				List<String> dL= v instanceof List ? (List<String>)v : (v ? (List<String>)[v]:[])
				String res
				res=sNULL
				Boolean ok
				ok=true
				dL.each{ String it->
					if(ok && it && it.size()==34 && it.startsWith(sCOLON) && it.endsWith(sCOLON)){
						res= res ? res+sSPC+it : it
					} else ok=false
				}
				if(ok){
					ret=[(sSTR):res]
					break
				}
			case sDYN:
			case sSTR:
				ret=[(sSTR): v]
				break
			case sTIME:
				if(eric())warn "got time $v"
				Long aaa= ("$v".isNumber()) ? v as Long : null
				if(aaa!=null){
					if(aaa<lMSDAY && aaa>=0L) {
						Long t0=getMidnightTime()
						Long aa=t0+aaa
						TimeZone tz=mTZ()
						myv=aa+(tz.getOffset(t0)-tz.getOffset(aa))
						if(eric())warn "extended midnight time by $aaa  +($t0) $myv"
					} else {
						Date t1=new Date(aaa)
						Long t2=Math.round((t1.hours*3600+t1.minutes*60+t1.seconds)*1000.0D)
						myv=t2
						if(eric())warn "strange time $aaa new myv is $myv"
					}
				} else if(eric())warn "trying to convert nonnumber time"
			case sDATE:
			case sDTIME: //@@
				//if(eric())warn "found myv is $myv"
				Date nTime=new Date((Long)myv)
				/*TimeZone aa=mTZ()
				Boolean a= aa.inDaylightTime(nTime)
				if(eric())warn "found inDaylight  $a"
				if(eric())warn "found current offset is  ${aa.getOffset(wnow())}"
				if(eric())warn "found rawoffset is  ${aa.rawOffset}"*/
				String format="yyyy-MM-dd'T'HH:mm:ss.sssXX"
				SimpleDateFormat formatter=new SimpleDateFormat(format)
				formatter.setTimeZone(mTZ())
				String tt=(String) formatter.format(nTime)
				if(eric())warn "found time tt is $tt"
				String[] t1=tt.split('T')

				if(typ==sDATE) {
					// comes in long format should be string -> 2021-10-13T99:99:99:999-9999
					String t2=t1[0]+'T99:99:99:999-9999'
					ret=[(sDTIME): t2]
					break
				}
				if(typ==sTIME) {
					//comes in long format should be string -> 9999-99-99T14:25:09.009-0700
					String t2='9999-99-99T'+t1[1]
					ret=[(sDTIME): t2]
					break
				}
				//	if(typ==sDTIME) {
				// this comes in as a long, needs to be string -> 2021-10-13T14:25:09.009-0700
				ret=[(sDTIME): tt]
				break
				//	}
		}
	} else { // from global(5 types + 3 overloads ) -> to webcore(8 (cannot restore sDYN)
		switch(typ) {
			case sINT:
				ret=[(sINT):v]
				break
			case sBOOLN:
				ret=[(sBOOLN):v]
				break
				// these match
			case 'bigdecimal':
				ret=[(sDEC):v]
				break
			case sSTR:
				// if(dtyp==sDEV)
				List<String> dvL=[]
				Boolean ok
				ok=true
				String[] t1=((String)v).split(sSPC)
				t1.each{ String it ->
					// sDEV is a string in global, need to detect if it is really devices :xxxxx:
					if(ok && it && it.size()==34 && it.startsWith(sCOLON) && it.endsWith(sCOLON)){
						dvL.push(it)
					} else ok=false
				}
				if(ok){ ret=[(sDEV):dvL]}
				else ret=[(sSTR):v]
				break
				// cannot really return a string to dynamic type here res=sDYN
			case sDTIME: // global times: everything is datetime -> these come in as a string and needs to be a long of appropriate type
				String iD=v
				String mtyp,res
				mtyp=sDTIME
				res=v
				if(iD.endsWith("9999") || iD.startsWith("9999")) {
					Date nTime=new Date()
					String format="yyyy-MM-dd'T'HH:mm:ss.sssXX"
					SimpleDateFormat formatter=new SimpleDateFormat(format)
					formatter.setTimeZone(mTZ())
					String tt= (String)formatter.format(nTime)
					String[] mystart=tt.split('T')

					String[] t1= iD.split('T')

					if(iD.endsWith("9999")) {
						mtyp=sDATE
						res= t1[0]+'T'+mystart[1] // 00:15:00.000'+myend //'-9999'
					} else if(iD.startsWith("9999")) {
						mtyp=sTIME
						// we are ignoring the -0000 offset at end and using our current one
						String withOutEnd=t1[1][0..-6]
						String myend=tt[-5..-1]
						//if(eric())warn "tt: ${tt}  myend: ${myend}  iD: ${iD}  mystart: ${mystart}  withOutEnd: ${withOutEnd}"
						res= mystart[0]+'T'+withOutEnd+myend
						//res= mystart[0]+'T'+t1[1]
					}
				}
				Date tt1=(Date)toDateTime(res)
				Long lres
				lres=tt1.getTime()
				if(mtyp==sTIME){
					Date m1=new Date(lres)
					Long m2=Math.round((m1.hours*3600+m1.minutes*60+m1.seconds)*1000.0D)
					//if(eric())warn "fixing $res $lres to $m2"
					lres=m2
				}
				//if(eric())warn "returning $lres"
				ret=[(mtyp):lres]
		}
	}
	return ret
}

@CompileStatic
private static String generateMD5_A(String s){
	MessageDigest.getInstance('MD5').digest(s.bytes).encodeHex().toString()
}

@CompileStatic
private static String md5(String md5){
	MessageDigest md= MessageDigest.getInstance('MD5')
	byte[] array=md.digest(md5.getBytes())
	String result=sBLK
	Integer l=array.size()
	for(Integer i=0; i<l; ++i){
		result += Integer.toHexString((array[i] & 0xFF)| 0x100).substring(i1,i3)
	}
	return result
}

@CompileStatic
static void clearHashMap(String wName){
	theHashMapVFLD[wName]=[:]
	theHashMapVFLD=theHashMapVFLD
}

private String sAppId(){ return ((Long)app.id).toString() }

private String hashPID(id){
	if(acctANDloc()) return hashId(locationSid()+id.toString())
	return hashId(id)
}

private String hashId(id){
	//enabled hash caching for faster processing
	String result
	String myId=id.toString()
	//String wName= parent ? parent.id.toString() : sAppId()
	String wName= sAppId()
	if(theHashMapVFLD[wName]==null){ theHashMapVFLD[wName]= [:]; theHashMapVFLD=theHashMapVFLD }
	result=(String)theHashMapVFLD[wName][myId]
	if(result==sNULL){
		result=sCOLON+md5('core.' + myId)+sCOLON
		theHashMapVFLD[wName][myId]=result
		theHashMapVFLD=theHashMapVFLD
		mb()
	}
	return result
}

@Field static Semaphore theMBLockFLD=new Semaphore(0)

// Memory Barrier
static void mb(String meth=sNULL){
	if((Boolean)theMBLockFLD.tryAcquire()){
		theMBLockFLD.release()
	}
}


@Field static final String sSP='<span>'
@Field static final String sSSP='</span>'
@Field static final String sSPCSB='     │'
@Field static final String sSPCS6='      '
@Field static final String sSPCST='┌─ '
@Field static final String sSPCSM='├─ '
@Field static final String sSPCSE='└─ '
@Field static final String sNL='\n'
@Field static final String sDBNL='\n\n • '
@Field static final String sSPORNG="<span style='color:orange'>"
@Field static final Integer iZ=0
@Field static final Integer i1=1
@Field static final Integer i2=2
@Field static final Integer i3=3

@CompileStatic
static String dumpListDesc(List data,final Integer level,List<Boolean> lastLevel,final String listLabel,Boolean html=false){
	String str=sBLK
	Integer cnt=i1
	List<Boolean> newLevel=lastLevel

	final List list1=data?.collect{it}
	final Integer sz=list1.size()
	for(Object par in list1){
		final String lbl=listLabel+"[${cnt-i1}]".toString()
		if(par instanceof Map){
			Map<String,Object> newmap=[:]
			newmap[lbl]=(Map)par
			Boolean t1=cnt==sz
			newLevel[level]=t1
			str+=dumpMapDesc(newmap,level,newLevel,!t1,html)
		}else if(par instanceof List || par instanceof ArrayList){
			Map<String,Object> newmap=[:]
			newmap[lbl]=par
			Boolean t1=cnt==sz
			newLevel[level]=t1
			str+=dumpMapDesc(newmap,level,newLevel,!t1,html)
		}else{
			String lineStrt=sNL
			for(Integer i=iZ; i<level; i++)lineStrt+=(i+i1<level)? (!lastLevel[i] ? sSPCSB:sSPCS6):sSPCS6
			lineStrt+=cnt==i1 && sz>i1 ? sSPCST:(cnt<sz ? sSPCSM:sSPCSE)
			if(html)str+=sSP
			str+=lineStrt+lbl+": ${par} (${objType(par)})".toString()
			if(html)str+=sSSP
		}
		cnt+=i1
	}
	return str
}

@CompileStatic
static String dumpMapDesc(Map<String,Object> data,final Integer level,List<Boolean> lastLevel,Boolean listCall=false,Boolean html=false){
	String str=sBLK
	Integer cnt=i1
	final Integer sz=data?.size()
	Map<String,Object> svMap=[:]
	Map<String,Object> svLMap=[:]
	Map<String,Object> newMap=[:]
	for(par in data){
		final String k=(String)par.key
		final def v=par.value
		if(v instanceof Map){
			svMap+=[(k): v]
		}else if(v instanceof List || v instanceof ArrayList){
			svLMap+=[(k): v]
		}else newMap+=[(k):v]
	}
	newMap+=svMap+svLMap
	final Integer lvlpls=level+i1
	for(par in newMap){
		String lineStrt
		List<Boolean> newLevel=lastLevel
		final Boolean thisIsLast=cnt==sz && !listCall
		if(level>iZ)newLevel[(level-i1)]=thisIsLast
		Boolean theLast=thisIsLast
		if(level==iZ)lineStrt=sDBNL
		else{
			theLast=theLast && thisIsLast
			lineStrt=sNL
			for(Integer i=iZ; i<level; i++)lineStrt+=(i+i1<level)? (!newLevel[i] ? sSPCSB:sSPCS6):sSPCS6
			lineStrt+=((cnt<sz || listCall) && !thisIsLast) ? sSPCSM:sSPCSE
		}
		final String k=(String)par.key
		final def v=par.value
		String objType=objType(v)
		if(v instanceof Map){
			if(html)str+=sSP
			str+=lineStrt+"${k}: (${objType})".toString()
			if(html)str+=sSSP
			newLevel[lvlpls]=theLast
			str+=dumpMapDesc((Map)v,lvlpls,newLevel,false,html)
		}
		else if(v instanceof List || v instanceof ArrayList){
			if(html)str+=sSP
			str+=lineStrt+"${k}: [${objType}]".toString()
			if(html)str+=sSSP
			newLevel[lvlpls]=theLast
			str+=dumpListDesc((List)v,lvlpls,newLevel,sBLK,html)
		}
		else{
			if(html)str+=sSP
			str+=lineStrt+"${k}: (${v}) (${objType})".toString()
			if(html)str+=sSSP
		}
		cnt+=i1
	}
	return str
}

@CompileStatic
static String objType(obj){ return sSPORNG+myObj(obj)+sSSP }

@CompileStatic
static String getMapDescStr(Map<String,Object> data){
	List<Boolean> lastLevel=[true]
	String str=dumpMapDesc(data,iZ,lastLevel,false,true)
	return str!=sBLK ? str:'No Data was returned'
}

@Field static final String sPDPC='pageDumpPCache'
def pageDumpPCache(){
	String wName=sAppId()
	Map a=base_resultFLD[wName]
	String message=getMapDescStr(a)
	return dynamicPage((sNM):sPDPC,(sTIT):sBLK,uninstall:false){
		section('base result dump'){
			paragraph message
		}
	}
}

def pageDumpPiston1(){
	String message=getMapDescStr((Map)rtD.piston)
	return dynamicPage((sNM):sPDPIS1,(sTIT):sBLK,uninstall:false){
		section('Cached Piston dump'){
			paragraph message
		}
	}
}
