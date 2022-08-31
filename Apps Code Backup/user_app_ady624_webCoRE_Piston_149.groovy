/*
 *  webCoRE - Community's own Rule Engine - Web Edition for HE
 *
 *  Copyright 2016 Adrian Caramaliu <ady624("at" sign goes here)gmail.com>
 *
 *  webCoRE Piston
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
 *  along with this program.  If not see <http://www.gnu.org/licenses/>.
 *
 * Last update August 22, 2022 for Hubitat
 */

//file:noinspection GroovySillyAssignment
//file:noinspection GrDeprecatedAPIUsage
//file:noinspection GroovyDoubleNegation
//file:noinspection GroovyUnusedAssignment
//file:noinspection unused
//file:noinspection GroovyAssignabilityCheck
//file:noinspection GroovyFallthrough
//file:noinspection SpellCheckingInspection
//file:noinspection GrMethodMayBeStatic

@Field static final String sVER='v0.3.114.20220203'
@Field static final String sHVER='v0.3.114.20220822_HE'

static String version(){ return sVER }
static String HEversion(){ return sHVER }

/** webCoRE DEFINITION	**/

static String handle(){ return 'webCoRE' }

import groovy.json.*
import hubitat.helper.RMUtils
import groovy.transform.CompileStatic
import groovy.transform.Field

import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.concurrent.Semaphore

definition(
	name:handle()+' Piston',
	namespace:'ady624',
	author:'Adrian Caramaliu',
	description:'Do not install this directly, use webCoRE instead',
	category:'Convenience',
	parent:'ady624:'+handle(),
	iconUrl:gimg('app-CoRE.png'),
	iconX2Url:gimg('app-CoRE@2x.png'),
	iconX3Url:gimg('app-CoRE@3x.png'),
	importUrl:'https://raw.githubusercontent.com/imnotbob/webCoRE/hubitat-patches/smartapps/ady624/webcore-piston.src/webcore-piston.groovy'
)

@Field static final String sPMAIN='pageMain'
@Field static final String sPRUN='pageRun'
@Field static final String sPCLR='pageClear'
@Field static final String sPCLRALL='pageClearAll'
@Field static final String sPDPIS='pageDumpPiston'
@Field static final String sPDPIS1='pageDumpPiston1'
@Field static final String sPDPIS2='pageDumpPiston2'
@Field static final String sPDPC='pageDumpPCache'
@Field static final String sPREM='pageRemove'
preferences{
	page((sNM):sPMAIN)
	page((sNM):sPRUN)
	page((sNM):sPCLR)
	page((sNM):sPCLRALL)
	page((sNM):sPDPC)
	page((sNM):sPDPIS)
	page((sNM):sPDPIS1)
	page((sNM):sPDPIS2)
	page((sNM):sPREM)
}

@CompileStatic
static Boolean eric(){ return false }
@CompileStatic
static Boolean eric1(){ return false }

//#include ady624.webCoRElib1

@Field static final String sNL=(String)null
@Field static final String sSNULL='null'
@Field static final String sBOOLN='boolean'
@Field static final String sLONG='long'
@Field static final String sSTR='string'
@Field static final String sINT='integer'
@Field static final String sDEC='decimal'
@Field static final String sDYN='dynamic'
@Field static final String sDTIME='datetime'
@Field static final String sTRUE='true'
@Field static final String sFALSE='false'
@Field static final String sTIME='time'
@Field static final String sTIMER='timer'
@Field static final String sDATE='date'
@Field static final String sDEV='device'
@Field static final String sDBL='double'
@Field static final String sNUMBER='number'
@Field static final String sFLOAT='float'
@Field static final String sVARIABLE='variable'
@Field static final String sMODE='mode'
@Field static final String sNM='name'
@Field static final String sREQ='required'
@Field static final String sTYPE='type'
@Field static final String sTIT='title'
@Field static final String sVAL='value'
@Field static final String sERROR='error'
@Field static final String sWARN='warn'
@Field static final String sTRC='trace'
@Field static final String sDBG='debug'
@Field static final String sON='on'
@Field static final String sOFF='off'
@Field static final String sSWITCH='switch'
@Field static final String sTRIG='trigger'
@Field static final String sCONDITION='condition'
@Field static final String sDURATION='duration'
@Field static final String sDLLRINDX='$index'
@Field static final String sDLLRDEVS='$devices'
@Field static final String sDLLRDEVICE='$device'
@Field static final String sTEXT='text'
@Field static final String sENUM='enum'
@Field static final String sTHREAX='threeAxis'
@Field static final String sBLK=''
@Field static final String sCOMMA=','
@Field static final String sSPC=' '
@Field static final String sDI='di'
@Field static final String sVT='vt'
@Field static final String sA='a'
@Field static final String sB='b'
@Field static final String sC='c'
@Field static final String sD='d'
@Field static final String sE='e'
@Field static final String sF='f'
@Field static final String sG='g'
@Field static final String sH='h'
@Field static final String sI='i'
@Field static final String sL='l'
@Field static final String sM='m'
@Field static final String sN='n'
@Field static final String sO='o'
@Field static final String sP='p'
@Field static final String sR='r'
@Field static final String sS='s'
@Field static final String sT='t'
@Field static final String sU='u'
@Field static final String sV='v'
@Field static final String sX='x'
@Field static final String sZ='z'
@Field static final String sXI='xi'
@Field static final String sMS='ms'
@Field static final String sLB='['
@Field static final String sRB=']'
@Field static final String sLRB='[]'
@Field static final String sOB='{'
@Field static final String sCB='}'
@Field static final String sAT='@'
@Field static final String sAT2='@@'
@Field static final String sDLR='$'
@Field static final String sRULE='rule'
@Field static final String sHSMSTS='hsmStatus'
@Field static final String sHSMALRT='hsmAlert'
@Field static final String sHSMSARM='hsmSetArm'
@Field static final String sPEVDATE='$previousEventDate'
@Field static final String sPEVDELAY='$previousEventDelay'
@Field static final String sPEVDEV='$previousEventDevice'
@Field static final String sPEVDEVINDX='$previousEventDeviceIndex'
@Field static final String sPEVATTR='$previousEventAttribute'
@Field static final String sPEVDESC='$previousEventDescription'
@Field static final String sPEVVALUE='$previousEventValue'
@Field static final String sPEVUNIT='$previousEventUnit'
@Field static final String sPEVPHYS='$previousEventDevicePhysical'
@Field static final String sCURDATE='$currentEventDate'
@Field static final String sCURDELAY='$currentEventDelay'
@Field static final String sCURDEV='$currentEventDevice'
@Field static final String sCURDEVINDX='$currentEventDeviceIndex'
@Field static final String sCURATTR='$currentEventAttribute'
@Field static final String sCURDESC='$currentEventDescription'
@Field static final String sCURVALUE='$currentEventValue'
@Field static final String sCURUNIT='$currentEventUnit'
@Field static final String sCURPHYS='$currentEventDevicePhysical'
@Field static final String sAPPJSON='application/json'
@Field static final String sAPPFORM='application/x-www-form-urlencoded'
@Field static final String sASYNCREP='wc_async_reply'
@Field static final String sCHNK='chunk:'
@Field static final String sCLRC='clearc'
@Field static final String sCLRL='clearl'
@Field static final String sCLRA='cleara'
@Field static final String sGET='GET'
@Field static final String sDELETE='DELETE'
@Field static final String sHEAD='HEAD'
@Field static final String sLVL='level'
@Field static final String sSTLVL='setLevel'
@Field static final String sIFLVL='infraredLevel'
@Field static final String sSTIFLVL='setInfraredLevel'
@Field static final String sSATUR='saturation'
@Field static final String sSTSATUR='setSaturation'
@Field static final String sSTVAR='setVariable'
@Field static final String sHUE='hue'
@Field static final String sSTHUE='setHue'
@Field static final String sSTCLR='setColor'
@Field static final String sCLRTEMP='colorTemperature'
@Field static final String sSTCLRTEMP='setColorTemperature'

@Field static final String sPEP='pep'
@Field static final String sLOGNG='logging'
@Field static final String sID='id'
@Field static final String spId='pId'
@Field static final String snId='nId'
@Field static final String sBIN='bin'
@Field static final String sATHR='author'
@Field static final String sCTGRY='category'
@Field static final String sSTATS='stats'
@Field static final String sDEVS='devices'
@Field static final String sCUREVT='currentEvent'
@Field static final String sTMSTMP='timestamp'
@Field static final String sST='state'
@Field static final String sSYSVARS='systemVars'
@Field static final String sLOGS='logs'
@Field static final String sSTACK='stack'
@Field static final String sSTORE='store'
@Field static final String sPIS='piston'
@Field static final String sCACHE='cache'
@Field static final String sVARS='vars'
@Field static final String sNSCH='nextSchedule'
@Field static final String sSCHS='schedules'
@Field static final String sLEVT='lastEvent'
@Field static final String sLEXEC='lastExecuted'
@Field static final String sSTMTL='stmtLvl'
@Field static final String sMODFD='modified'
@Field static final String sCREAT='created'
@Field static final String sBLD='build'
@Field static final String sACT='active'
@Field static final String sSVLBL='svLabel'
@Field static final String sCACHEP='cachePersist'
@Field static final String sNWCACHE='newCache'
@Field static final String sCNCLATNS='cancelations'
@Field static final String sCNDTNSTC='cndtnStChgd'
@Field static final String sPSTNSTC='pstnStChgd'
@Field static final String sLSTPCQ='lastPCmdQ'
@Field static final String sLSTPCSNT='lastPCmdSnt'
@Field static final String sDBGLVL='debugLevel'
@Field static final String sLOCID='locationId'
@Field static final String sTCP='tcp'
@Field static final String sOLD='old'
@Field static final String sNEW='new'
@Field static final String sMSTATS='maxStats'
@Field static final String sMLOGS='maxLogs'
@Field static final String sLOGHE='logsToHE'

@Field static final String sZ6='000000'
@Field static final String sHTTPR='httpRequest'
@Field static final String sLIFX='lifx'
@Field static final String sSENDE='sendEmail'
@Field static final String sANY='any'
@Field static final String sALL='all'
@Field static final String sAND='and'
@Field static final String sOR='or'
@Field static final String sIF='if'
@Field static final String sWHILE='while'
@Field static final String sREPEAT='repeat'
@Field static final String sFOR='for'
@Field static final String sEACH='each'
@Field static final String sACTION='action'
@Field static final String sEVERY='every'
@Field static final String sRESTRIC='restriction'
@Field static final String sGROUP='group'
@Field static final String sDO='do'
@Field static final String sEVENT='event'
@Field static final String sEXIT='exit'
@Field static final String sBREAK='break'
@Field static final String sEXPR='expression'
@Field static final String sOPER='operator'
@Field static final String sOPERAND='operand'
@Field static final String sFUNC='function'
@Field static final String sIS='is'
@Field static final String sISINS='is_inside_of_range'
@Field static final String sPLUS='+'
@Field static final String sMINUS='-'
@Field static final String sDOT='.'
@Field static final String sORIENT='orientation'
@Field static final String sAXISX='axisX'
@Field static final String sAXISY='axisY'
@Field static final String sAXISZ='axisZ'
@Field static final String sEXPECTING='Expecting '
@Field static final String sINT32='int32'
@Field static final String sINT64='int64'
@Field static final String sBOOL='bool'
@Field static final String sPHONE='phone'
@Field static final String sURI='uri'
@Field static final String sSTOREM='storeMedia'
@Field static final String sIFTTM='iftttMaker'
@Field static final String sDARGS='$args'
@Field static final String sDJSON='$json'
@Field static final String sDRESP='$response'
@Field static final String sEND='end'
@Field static final String sHTTPCNTN='$httpContentType'
@Field static final String sHTTPCODE='$httpStatusCode'
@Field static final String sHTTPOK='$httpStatusOk'
@Field static final String sIFTTTCODE='$iftttStatusCode'
@Field static final String sIFTTTOK='$iftttStatusOk'
@Field static final String sTSLF='theSerialLockFLD'
@Field static final String sTCL='cacheLock'
@Field static final String sTGBL='theGlobal'
@Field static final String sLCK1='lockOrQueue1'
@Field static final String sLCK2='lockOrQueue2'
@Field static final String sGETTRTD='getTempRtd'
@Field static final String sHNDLEVT='handleEvents'
@Field static final String sVALUEN='(value1, value2,..., valueN)'
@Field static final String sDATTRH='([device:attribute])'
@Field static final String sDATTRHT='([device:attribute] [,.., [device:attribute]],threshold)'
@Field static final String sPSTNRSM='pistonResume'
@Field static final String sTILE='tile'

@Field static final String sMULP='*'
@Field static final String sQM='?'
@Field static final String sCLN=':'
@Field static final String sPWR='**'
@Field static final String sAMP='&'
@Field static final String sBOR='|'
@Field static final String sBXOR='^'
@Field static final String sBNOT='~'
@Field static final String sBNAND='~&'
@Field static final String sBNOR='~|'
@Field static final String sBNXOR='~^'
@Field static final String sLTH='<'
@Field static final String sGTH='>'
@Field static final String sLTHE='<='
@Field static final String sGTHE='>='
@Field static final String sEQ='=='
@Field static final String sNEQ='!='
@Field static final String sNEQA='<>'
@Field static final String sMOD='%'
@Field static final String sMOD1='\\'
@Field static final String sSBL='<<'
@Field static final String sSBR='>>'
@Field static final String sNEG='!'
@Field static final String sDNEG='!!'
@Field static final String sDIV='/'
@Field static final String sLAND='&&'
@Field static final String sLNAND='!&'
@Field static final String sLOR='||'
@Field static final String sLNOR='!|'
@Field static final String sLXOR='^^'
@Field static final String sLNXOR='!^'
@Field static final String sUNDS='_'

@Field static final String s0='0'
@Field static final String s1='1'
@Field static final String s2='2'
@Field static final Long lZ=0L
@Field static final Integer iN1=-1
@Field static final Integer iN2=-2
@Field static final Integer iN3=-3
@Field static final Integer iN5=-5
@Field static final Integer iN9=-9
@Field static final Integer iZ=0
@Field static final Integer i1=1
@Field static final Integer i2=2
@Field static final Integer i3=3
@Field static final Integer i4=4
@Field static final Integer i5=5
@Field static final Integer i6=6
@Field static final Integer i7=7
@Field static final Integer i8=8
@Field static final Integer i9=9
@Field static final Integer i10=10
@Field static final Integer i12=12
@Field static final Integer i20=20
@Field static final Integer i50=50
@Field static final Integer i1000=1000
@Field static final Long l500=500L
@Field static final Long lTHOUS=1000L
@Field static final Long lMSDAY=86400000L
@Field static final Double dZ=0.0D
@Field static final Double d1=1.0D
@Field static final Double d2=2.0D
@Field static final Double d100=100.0D
@Field static final Double d3d6=3.6D
@Field static final Double d1000=1000.0D
@Field static final Double d60=60.0D
@Field static final Double dSECHR=3600.0D
@Field static final Double dMSECHR=3600000.0D
@Field static final Double dMSMINT=60000.0D
@Field static final Double dMSDAY=86400000.0D

/** CONFIGURATION PAGES	**/

def pageMain(){
	return dynamicPage((sNM):sPMAIN,(sTIT):sBLK,install:true,uninstall:false){
		if(parent==null || !(Boolean)parent.isInstalled()){
			section(){
				paragraph 'Sorry you cannot install a piston directly from HE console; please use the webCoRE dashboard (dashboard.webcore.co) instead.'
			}
			section(sectionTitleStr('Installing webCoRE')){
				paragraph 'If you are trying to install webCoRE please go back one step and choose webCoRE, not webCoRE Piston. You can also visit wiki.webcore.co for more information on how to install and use webCoRE'
				if(parent!=null){
					String t0=(String)parent.getWikiUrl()
					href sBLK,(sTIT):imgTitle('app-CoRE.png',inputTitleStr('More information')),description:t0,style:'external',url:t0,(sREQ):false
				}
			}
		}else{
			section(sectionTitleStr('General')){
				label([(sNM):sNM,(sTIT):'Name',(sREQ):true,(sST):(name ? 'complete':sNL),defaultValue:(String)parent.generatePistonName(),submitOnChange:true])
			}

			section(sectionTitleStr('Dashboard')){
				String dashboardUrl=(String)parent.getDashboardUrl()
				if(dashboardUrl!=sNL){
					dashboardUrl=dashboardUrl+'piston/'+hashPID(sAppId())
					href sBLK,(sTIT):imgTitle('dashboard.png',inputTitleStr('View piston in dashboard')),style:'external',url:dashboardUrl,(sREQ):false
				}else paragraph 'Sorry your webCoRE dashboard does not seem to be enabled; please go to the parent app and enable the dashboard if needed.'
			}

			section(sectionTitleStr('Application Info')){
				LinkedHashMap r9; r9=getTemporaryRunTimeData()
				if(!isEnbl(r9))paragraph 'Piston is disabled by webCoRE'
				if(!isAct(r9))paragraph 'Piston is paused'
				if(sMs(r9,sBIN)!=sNL){
					paragraph 'Automatic backup bin code: '+sMs(r9,sBIN)
				}
				paragraph 'Version: '+sVER
				paragraph 'VersionH: '+sHVER
				paragraph 'Memory Usage: '+mem()
				paragraph 'RunTime History: '+runTimeHis(r9)
				r9=null
			}

			section(sectionTitleStr('Recovery')){
				href sPRUN,(sTIT):'Test run this piston'
				href sPCLR,(sTIT):'Clear logs',description:'This will remove logs but no variables'
				href sPCLRALL,(sTIT):'Clear all data',description:'This will reset all data stored in local variables'
			}

			section(){
				input 'dev',"capability.*",(sTIT):'Devices',description:'Piston devices',multiple:true
				input sLOGNG,sENUM,(sTIT):'Logging Level',options:[(s0):"None",(s1):"Minimal",(s2):"Medium","3":"Full"],description:'Piston logging',defaultValue:state[sLOGNG] ? state[sLOGNG].toString():s0
				input sLOGHE,sBOOL,(sTIT):'Piston logs are also displayed in HE console logs?',description:"Logs are available in webCoRE console; also display in HE console 'Logs'?",defaultValue:false
				input sMSTATS,sNUMBER,(sTIT):'Max number of timing history stats',description:'Max number of stats',range:'2..300',defaultValue:i50
				input sMLOGS,sNUMBER,(sTIT):'Max number of history logs',description:'Max number of logs',range:'0..300',defaultValue:i50
			}
			if(eric() || ((String)gtSetting(sLOGNG))?.toInteger()>i2){
				section('Debug'){
					href sPDPIS,(sTIT):'Dump piston structure',description:sBLK
					href sPDPIS1,(sTIT):'Dump cached piston structure',description:sBLK
					href sPDPIS2,(sTIT):'To IDE piston structure',description:sBLK
					href sPDPC,(sTIT):'Dump piston Cache',description:sBLK
				}
			}
			section(){
				href sPREM,(sTIT):'Remove this Piston',description:sBLK
			}
		}
	}
}

def pageRun(){
	test()
	return dynamicPage((sNM):sPRUN,(sTIT):sBLK,uninstall:false){
		section('Run'){
			paragraph 'Piston tested'
			Map<String,String> t0=(Map)parent.getWCendpoints()
			String t1="/execute/${hashPID(sAppId())}?access_token=${t0.at}".toString()
			paragraph "Cloud Execute endpoint ${t0.ep}${t1}".toString()
			paragraph "Local Execute endpoint ${t0.epl}${t1}".toString()
		}
	}
}

def pageClear(){
	clear1(false,true,true,false)
	return dynamicPage((sNM):sPCLR,(sTIT):sBLK,uninstall:false){
		section('Clear'){
			paragraph 'All non-essential data has been cleared.'
		}
	}
}

void clear1(Boolean ccache=false,Boolean some=true,Boolean most=false,Boolean all=false,Boolean reset=false){
	String meth
	meth='clear1'
	if(some||all)state[sLOGS]=[]
	if(most||all){ state[sTRC]=[:];state[sSTATS]=[:] }
	if(reset){app.removeSetting(sMLOGS); app.removeSetting(sMSTATS); app.removeSetting(sLOGHE) }
	cleanState()
	if(all){
		meth+=' all'
		LinkedHashMap tRtData,r9
		tRtData=getTemporaryRunTimeData()
		Boolean act=isAct(tRtData)
		Boolean dis=!isEnbl(tRtData)
		String pNm=sMs(tRtData,snId)
		readDataFLD[pNm]=sNL; readTmpFLD[pNm]=sNL
		fuelDataFLD[pNm]=[]
		tRtData=null
		state[sCACHE]=[:]
		state[sVARS]=[:]
		state[sSTORE]=[:]
		state.pauses=lZ
		clearMyCache(meth)

		getTheLock(pNm,meth)
		theSemaphoresVFLD[pNm]=lZ
		theSemaphoresVFLD=theSemaphoresVFLD
		theQueuesVFLD[pNm]=[]
		theQueuesVFLD=theQueuesVFLD // forces volatile cache flush
		releaseTheLock(pNm)

		if(act && !dis){
			tRtData=getTemporaryRunTimeData()
			r9=getRunTimeData(tRtData,null,true,true,true) //reinitializes cache variables; caches piston
			r9=null
			tRtData=null
		}
	}
	clearMyCache(meth)
	if(ccache)clearMyPiston(meth)
}

def pageClearAll(){
	clear1(true,true,true,true)
	return dynamicPage((sNM):sPCLRALL,(sTIT):sBLK,uninstall:false){
		section('Clear All'){
			paragraph 'All local data has been cleared.'
		}
	}
}

def pageRemove(){
	dynamicPage((sNM): sPREM,(sTIT): sBLK,install: false,uninstall: true){
		section('CAUTION'){
			paragraph "You are about to completely remove this webCoRE piston.",(sREQ): true
			paragraph "This action is irreversible.",(sREQ): true
			paragraph "It is suggested to backup this piston via the webCoRE IDE before proceeding to a local file",(sREQ):true
			paragraph "If you are sure you want to do this, please tap on the Remove button below.",(sREQ): true
		}
	}
}

@Field static final String sSP='<span>'
@Field static final String sSSP='</span>'
@Field static final String sSPCSB7='      │'
@Field static final String sSPCSB6='     │'
@Field static final String sSPCS6 ='      '
@Field static final String sSPCS5 ='     '
@Field static final String sSPCST='┌─ '
@Field static final String sSPCSM='├─ '
@Field static final String sSPCSE='└─ '
@Field static final String sNWL='\n'
@Field static final String sDBNL='\n\n • '
@Field static final String sSPORNG="<span style='color:orange'>"

@CompileStatic
static String doLineStrt(Integer level,List<Boolean>newLevel){
	String lineStrt; lineStrt=sNWL
	Boolean dB; dB=false
	Integer i
	for(i=iZ;i<level;i++){
		if(i+i1<level){
			if(!newLevel[i]) {
				if(!dB){ lineStrt+=sSPCSB7; dB=true }
				else lineStrt+=sSPCSB6
			}else lineStrt+= !dB ? sSPCS6:sSPCS5
		}else lineStrt+= !dB ? sSPCS6:sSPCS5
	}
	return lineStrt
}

@CompileStatic
static String dumpListDesc(List data,Integer level,List<Boolean> lastLevel,String listLabel,Boolean html=false,Boolean reorder=true){
	String str; str=sBLK
	Integer cnt; cnt=i1
	List<Boolean> newLevel=lastLevel

	List list1=data?.collect{it}
	Integer sz=list1.size()
	for(Object par in list1){
		String lbl=listLabel+"[${cnt-i1}]".toString()
		if(par instanceof Map){
			Map newmap=[:]
			newmap[lbl]=(Map)par
			Boolean t1=cnt==sz
			newLevel[level]=t1
			str+=dumpMapDesc(newmap,level,newLevel,cnt,sz,!t1,html,reorder)
		}else if(par instanceof List || par instanceof ArrayList){
			Map newmap=[:]
			newmap[lbl]=par
			Boolean t1=cnt==sz
			newLevel[level]=t1
			str+=dumpMapDesc(newmap,level,newLevel,cnt,sz,!t1,html,reorder)
		}else{
			String lineStrt
			lineStrt=doLineStrt(level,lastLevel)
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
static String dumpMapDesc(Map data,Integer level,List<Boolean> lastLevel,Integer listCnt=null,Integer listSz=null,Boolean listCall=false,Boolean html=false,Boolean reorder=true){
	String str; str=sBLK
	Integer cnt; cnt=i1
	Integer sz=data?.size()
	Map svMap,svLMap,newMap; svMap=[:]; svLMap=[:]; newMap=[:]
	for(par in data){
		String k=(String)par.key
		def v=par.value
		if(reorder && v instanceof Map){
			svMap+=[(k): v]
		}else if(reorder && (v instanceof List || v instanceof ArrayList)){
			svLMap+=[(k): v]
		}else newMap+=[(k):v]
	}
	newMap+=svMap+svLMap
	Integer lvlpls=level+i1
	for(par in newMap){
		String lineStrt
		List<Boolean> newLevel=lastLevel
		Boolean thisIsLast=cnt==sz && !listCall
		if(level>iZ)newLevel[(level-i1)]=thisIsLast
		Boolean theLast
		theLast=thisIsLast
		if(level==iZ)lineStrt=sDBNL
		else{
			theLast=theLast && thisIsLast
			lineStrt=doLineStrt(level,newLevel)
			if(listSz && listCnt && listCall)lineStrt+=listCnt==i1 && listSz>i1 ? sSPCST:(listCnt<listSz ? sSPCSM:sSPCSE)
			else lineStrt+=((cnt<sz || listCall) && !thisIsLast) ? sSPCSM:sSPCSE
		}
		String k=(String)par.key
		def v=par.value
		String objType=objType(v)
		if(v instanceof Map){
			if(html)str+=sSP
			str+=lineStrt+"${k}: (${objType})".toString()
			if(html)str+=sSSP
			newLevel[lvlpls]=theLast
			str+=dumpMapDesc((Map)v,lvlpls,newLevel,null,null,false,html,reorder)
		}
		else if(v instanceof List || v instanceof ArrayList){
			if(html)str+=sSP
			str+=lineStrt+"${k}: [${objType}]".toString()
			if(html)str+=sSSP
			newLevel[lvlpls]=theLast
			str+=dumpListDesc((List)v,lvlpls,newLevel,sBLK,html,reorder)
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
static String getMapDescStr(Map data,Boolean reorder=true){
	List<Boolean> lastLevel=[true]
	String str=dumpMapDesc(data,iZ,lastLevel,null,null,false,true,reorder)
	return str!=sBLK ? str:'No Data was returned'
}

def pageDumpPCache(){
	LinkedHashMap t0=getCachedMaps(sPDPC,false,false)
	String message=getMapDescStr(t0,false)
	return dynamicPage((sNM):sPDPC,(sTIT):sBLK,uninstall:false){
		section('Piston Data Cache dump'){
			paragraph message
		}
	}
}

def pageDumpHelper(Integer i,String nm,String desc){
	LinkedHashMap pis,r9
	r9=getRunTimeData()
	if(i==iZ){ // full
		pis=recreatePiston(false,false,false)
		r9[sPIS]=pis
		subscribeAll(r9,false,false)
	}else if(i==i1){ // cached
		pis=recreatePiston(true,true,true)
		r9[sPIS]=pis
		subscribeAll(r9,false,true)
	}else if(i==i2){ // returned to iDE cleaned up
		pis=recreatePiston(true,false,false)
		r9[sPIS]=pis
		subscribeAll(r9,false,false)
	}
	pis=null
	String message=getMapDescStr((Map)r9[sPIS])
	r9=null
	return dynamicPage((sNM):nm,(sTIT):sBLK,uninstall:false){
		section(desc+'Piston dump'){
			paragraph message
		}
	}
}

def pageDumpPiston2(){ // dumps what to return to IDE
	pageDumpHelper(i2,sPDPIS2,'To IDE ')
}

def pageDumpPiston1(){ // dumps memory cached piston
	pageDumpHelper(i1,sPDPIS1,'Memory cached ')
}

def pageDumpPiston(){ // dumps full piston
	pageDumpHelper(iZ,sPDPIS,'Full ')
}

void installed(){
	if(app.id==null)return
	Long t=wnow()
	state[sCREAT]=t
	state[sMODFD]=t
	state[sBLD]=iZ
	state[sVARS]=(Map)state[sVARS] ?: [:]
	state.subscriptions=(Map)state.subscriptions ?: [:]
	state[sLOGNG]=iZ
	initialize()
}

void updated(){
	wunsubscribe()
	initialize()
}

void uninstalled(){
	if(eric())log.debug 'uninstalled'
	if(!atomicState.pistonDeleted)Map a=deletePiston()
}

void initialize(){
	svSunTFLD=null; mb()
	String tt1=(String)gtSetting(sLOGNG)
	Integer tt2=iMs(gtState(),sLOGNG)
	String tt3=tt2.toString()
	Map a
	if(tt1==sNL)a=setLoggingLevel(tt2 ? tt3:s0,false)
	else if(tt1!=tt3)a=setLoggingLevel(tt1,false)
	if(bIs(gtState(),sACT))a=resume()
	else clearMyCache('initialize')
}

@Field static final List<String> clST=['hash','piston','cVersion','hVersion','disabled','logPExec','settings','svSunT','temp','debugLevel','lockTime']

void cleanState(){
//cleanups between releases
	String s='sph'
	for(sph in gtState().findAll{ ((String)it.key).startsWith(s)})state.remove(sph.key.toString())
	for(String foo in clST)state.remove(foo)
}

/** PUBLIC METHODS					**/

Boolean isInstalled(){
	return lMs(gtState(),sCREAT)!=null
}

@CompileStatic
Map get(Boolean minimal=false){ // minimal is backup
	LinkedHashMap r9
	r9=getRunTimeData()
	Map rVal
	rVal=[
		meta: [
			(sID): sMs(r9,sID),
			(sATHR): sMs(r9,sATHR),
			(sNM): sMs(r9,sNM),
			(sCREAT): lMs(r9,sCREAT),
			(sMODFD): lMs(r9,sMODFD),
			(sBLD): iMs(r9,sBLD),
			(sBIN): sMs(r9,sBIN),
			(sACT): isAct(r9),
			(sCTGRY): sMs(r9,sCTGRY)
		],
		(sPIS): (LinkedHashMap)r9.piston
	]
	if(!minimal) {
		Map mst=gtState()
		rVal+= [ // use state as getRunTimeData re-initializes these
			(sSYSVARS): getSystemVariablesAndValues(r9),
			subscriptions: (Map)mst.subscriptions,
			(sST): (Map)mst[sST],
			(sLOGNG): mst[sLOGNG]!=null ? iMs(mst,sLOGNG):iZ,
			(sSTATS): (Map)mst[sSTATS],
			(sLOGS): (List)mst[sLOGS],
			(sTRC): (Map)mst[sTRC],
			localVars: (Map)mst[sVARS],
			memory: mem(),
			(sLEXEC): lMs(mst,sLEXEC),
			(sNSCH): lMs(mst,sNSCH),
			(sSCHS): (List)mst[sSCHS]
		]
	}
	r9=null
	return rVal
}

// this is called while the piston is open in IDE
@CompileStatic
Map activity(lastLogTimestamp){
	Map t0
	t0=getCachedMaps('activity')
	if(t0==null)return [:]
	List<Map> logs=[]+(List<Map>)t0[sLOGS]
	Integer lsz=logs.size()
	Long llt=lastLogTimestamp!=null && lastLogTimestamp instanceof String && ((String)lastLogTimestamp).isLong()? ((String)lastLogTimestamp).toLong():lZ
	Integer lidx
	lidx=(llt!=lZ && lsz>iZ)? logs.findIndexOf{Map it-> (Long)it?.t==llt }:iZ
	lidx=lidx>iZ ? lidx:(llt!=lZ ? iZ:lsz)
	Map rVal=[
		(sNM): sMs(t0,sNM),
		(sST): (Map)t0[sST],
		(sLOGS): lidx>iZ ? logs[iZ..lidx-i1]:[],
		(sTRC): (Map)t0[sTRC],
		localVars: (Map)t0[sVARS],// not reporting global or system variable changes
		memory: sMs(t0,'mem'),
		(sLEXEC): lMs(t0,sLEXEC),
		(sNSCH): lMs(t0,sNSCH),
		(sSCHS): (List)t0[sSCHS],
		(sSYSVARS): (Map)t0[sCACHEP]
	]
	t0=null
	return rVal
}

// called by parent if it does not have piston information for IDE dashboard
@CompileStatic
Map curPState(){
	Map t0
	t0=getCachedMaps('curPState',true,false)
	if(t0==null)return null
	Map st=[:]+(Map)t0[sST]
	def a=st.remove(sOLD)
	Map rVal=[
		(sA):isAct(t0),
		(sC):sMs(t0,sCTGRY),
		(sT):lMs(t0,sLEXEC),
		(sN):lMs(t0,sNSCH),
		(sZ):sMs(t0,'pistonZ'),
		(sS):st,
		heCached:bIs(t0,'Cached') ?: false
	]
	t0=null
	return rVal
}

Map clearLogs(){
	clear1()
	return [:]
}

static String decodeEmoji(String value){
	return value.replaceAll(/(\:%[0-9A-F]{2}%[0-9A-F]{2}%[0-9A-F]{2}%[0-9A-F]{2}\:)/,{ m -> URLDecoder.decode(m[0].substring(1,13),'UTF-8')})
}

@Field static Map<String,Map> thePistonCacheFLD=[:]

private void clearMyPiston(String meth=sNL){
	String pNm=sAppId()
	if(pNm.length()==iZ)return
	Boolean clrd; clrd=false
	Map pData=(Map)thePistonCacheFLD[pNm]
	if(pData!=null){
		LinkedHashMap t0=(LinkedHashMap)pData.pis
		if(t0){
			thePistonCacheFLD[pNm].pis=null
			mb()
			clrd=true
		}
	}
	if(eric() && clrd){
		debug 'clearing piston-code-cache '+meth,null
		dumpPCsize()
	}
}

private LinkedHashMap recreatePiston(Boolean shorten=false,Boolean inMem=false,Boolean useCache=true){
	if(shorten && inMem && useCache){
		String pNm=sAppId()
		Map pData; pData=(Map)thePistonCacheFLD[pNm]
		if(pData==null || pData.cnt==null){
			pData=[cnt:iZ,pis:null]
			thePistonCacheFLD[pNm]=pData
			mb()
		}
		if(pData.pis!=null)return (LinkedHashMap)(pData.pis+[cached:true])
	}

	if(eric())debug "recreating piston shorten: $shorten inMem: $inMem useCache: $useCache",null
	String sdata; sdata=sBLK
	Integer i; i=iZ
	String s
	while(true){
		s=(String)settings."${sCHNK}$i"
		if(s!=sNL)sdata+=s
		else break
		i++
	}
	if(sdata!=sBLK){
		def data=(LinkedHashMap)new JsonSlurper().parseText(decodeEmoji(new String(sdata.decodeBase64(),'UTF-8')))
		LinkedHashMap piston=[
			(sO): data.o ?: [:],
			(sR): data.r ?: [],
			rn: !!data.rn,
			rop: data.rop ?: sAND,
			(sS): data.s ?: [],
			(sV): data.v ?: [],
			(sZ): data.z ?: sBLK
		]
		assignSt('pistonZ',sMs(piston,sZ))
		clearMsetIds(piston)
		Integer a=msetIds(shorten,inMem,piston)
		return piston
	}
	return [:]
}

Map setup(LinkedHashMap data,Map<String,String>chunks){
	if(data==null){
		log.error 'setup: no data'
		return [:]
	}
	String meth='setup'
	clearMyCache(meth)

	String mSmaNm=sAppId()
	getTheLock(mSmaNm,meth)

	state[sMODFD]=wnow()
	state[sBLD]=gtSt(sBLD)!=null ? iMs(gtState(),sBLD)+i1:i1
	LinkedHashMap piston=[
		o: data.o ?: [:],
		r: data.r ?: [],
		rn: !!data.rn,
		rop: data.rop ?: sAND,
		s: data.s ?: [],
		(sV): data.v ?: [],
		(sZ): data.z ?: sBLK
	]
	clearMyPiston(meth)
	clearMsetIds(piston)
	Integer a=msetIds(false,false,piston)

	for(chunk in ((Map)settings).findAll{ ((String)it.key).startsWith(sCHNK) && !chunks[(String)it.key] }){
		app.removeSetting((String)chunk.key)
	}
	for(chunk in chunks)app.updateSetting((String)chunk.key,[(sTYPE):sTEXT,(sVAL):chunk.value])
	app.updateSetting(sBIN,[(sTYPE):sTEXT,(sVAL):sMs(gtState(),sBIN) ?: sBLK])
	app.updateSetting(sATHR,[(sTYPE):sTEXT,(sVAL):sMs(gtState(),sATHR) ?: sBLK])

	state[sPEP]=!!gtPOpt([(sPIS):piston],sPEP)

	String lbl=sMs(data,sN)
	if(lbl){
		assignSt(sSVLBL,lbl)
		assignAS(sSVLBL,lbl)
		app.updateLabel(lbl)
	}
	state[sSCHS]=[]
	state[sVARS]=(Map)state[sVARS] ?: [:]
	state.modifiedVersion=sVER

	state[sCACHE]=[:]
	state[sLOGS]=[]
	state[sTRC]=[:]

	Map r9
	r9=[:]
	r9[sPIS]=piston
	releaseTheLock(mSmaNm)
	Integer i= iMs(gtState(),sBLD)
	Boolean b= bIs(gtState(),sACT)
	if(i==i1 || b)r9=resume(piston,false)
	else clearMyCache(meth)
	return [(sACT):b,(sBLD):i,(sMODFD):lMs(gtState(),sMODFD),(sST):(Map)gtSt(sST),rtData:r9]
}

private void clearMsetIds(node){
	if(node==null)return
	for(list in node.findAll{ it.value instanceof List }){
		for(item in ((List)list.value).findAll{ it instanceof Map })clearMsetIds(item)
	}
	if(node instanceof Map && node[sDLR]!=null)node[sDLR]=null

	for(item in node.findAll{ it.value instanceof Map })clearMsetIds(item.value)
}

@Field static List<String> ListCmd=[]
private static List<String> fill_CMD(){ return [sIF,sACTION,sCONDITION,sWHILE,sREPEAT,sFOR,sEACH,sSWITCH,sEVERY,sRESTRIC,sGROUP,sDO,sON,sEVENT,sEXIT,sBREAK] }

private Integer msetIds(Boolean shorten,Boolean inMem,node,Integer mId=iZ,Map<String,Integer> existingIds=[:],List<Map> requiringIds=[],Integer level=iZ){
	List<Map> nodeE=node?.ei
	String nodeT=node?.t
	Integer maxId; maxId=mId
	//Boolean lg= eric() && settings.logging?.toInteger()>i2
	if(!ListCmd)ListCmd=fill_CMD()
	if(nodeT in ListCmd){
		//noinspection GroovyAssignabilityCheck
		Integer id
		id=node[sDLR]!=null ? (Integer)node[sDLR]:iZ
		if(id==iZ || existingIds[id.toString()]!=null){
			Boolean a=requiringIds.push(node)
		}else{
			maxId=maxId<id ? id:maxId
			existingIds[id.toString()]=id
		}
		if(nodeT==sIF && nodeE){
			Boolean a=((List<Map>)node.ei).removeAll{ Map it -> !it.c && !it.s }
			for(Map elseIf in (List<Map>)node.ei){
				id=elseIf[sDLR]!=null ? (Integer)elseIf[sDLR]:iZ
				if(id==iZ || existingIds[id.toString()]!=null)Boolean aa=requiringIds.push(elseIf)
				else{
					maxId=maxId<id ? id:maxId
					existingIds[id.toString()]=id
				}
			}
		}
		if(nodeT==sSWITCH && node.cs){
			for(Map _case in (List<Map>)node.cs){
				id=_case[sDLR]!=null ? (Integer)_case[sDLR]:iZ
				if(id==iZ || existingIds[id.toString()]!=null)Boolean a=requiringIds.push(_case)
				else{
					maxId=maxId<id ? id:maxId
					existingIds[id.toString()]=id
				}
			}
		}
		if(nodeT==sACTION && node.k){
			for(Map task in (List<Map>)node.k){
				id=task[sDLR]!=null ? (Integer)task[sDLR]:iZ
				if(id==iZ || existingIds[id.toString()]!=null)Boolean a=requiringIds.push(task)
				else{
					maxId=maxId<id ? id:maxId
					existingIds[id.toString()]=id
				}
			}
		}
	}
	for(list in node.findAll{ it.value instanceof List }){
		for(item in ((List)list.value).findAll{ it instanceof Map })maxId=msetIds(shorten,inMem,item,maxId,existingIds,requiringIds,level+i1)
	}
	if(level==iZ){
		for(Map item in requiringIds){
			maxId+=i1
			item[sDLR]=maxId
		}
		if(shorten)cleanCode(node,inMem)
	}
	return maxId
}

@Field static List<String> ListAL=[]
@Field static List<String> ListC1=[]
@Field static List<String> ListC2=[]

@Field static List<String> ListStmt=[]
private static List<String> fill_STMT(){ return [sIF,sACTION,sWHILE,sREPEAT,sFOR,sEACH,sSWITCH,sEVERY,sDO,sON,sEXIT,sBREAK] }

// to reduce memory code size or remove cruft for IDE
@CompileStatic
private void cleanCode(i,Boolean inMem){
	if(i==null || !(i instanceof Map))return
	Map<String,Object> item=(Map<String,Object>)i

	if(!ListC1){
		// sP phys/avg (uses a, d, g, p, i, f?)
		// sD devices  (uses d)
		// sV virt (uses v)
		// sS preset (uses s) (operand)   OR it is for switch case 's', 'r' ('r' for range)
		// sX variable (uses x, xi)
		// sC constant (uses c)
		// sE expr (uses exp)
		// sU argument (uses u)
		ListAL=[sP,sD,sV,sS,sX,sC,sE,sU]	//  don't need
		ListC1=[      sV,sS,   sC,sE,sU]	//		g,a
		ListC2=[      sV,sS,sX,sC,sE,sU]	//		d
		mb()
	}
	def a
	if(inMem && bIs(item,sDI)){ // disabled statements
		List<String> b=item.collect{ (String)it.key }
		for (String c in b) if(!(c in [sDI,sDLR]))a=item.remove(c)
		return
	}

	String av='avg'
	String ty=sMt(item)

	//tasks with empty mode restriction
	if(ty==sNL && item.c && item.m instanceof List && !(List)item.m)a=item.remove(sM)

	if(ty==sNL && item.d instanceof List && !item.d && sMs(item,sG)==av && sMs(item,sF)==sL && item[sVT]!=null){
		if(item.size()==i5 && item.c!=null) a=item.remove(sC) // cruft when editing operands
		if(inMem && item.size()==i4){ a=item.remove(sD); a=item.remove(sG) }
	}

	if(ty in ListAL){ // cleanup operands
		// UI important data
		if(inMem){
			String g=sMs(item,sG)
			String vt=sMvt(item)
			if(ty in ListC1){
				if(g in [av,sANY])a=item.remove(sG)
				//if(item.a instanceof String && item.a==sD)a=item.remove(sA)
			}
			if(ty==sX && vt!=sDEV) // operand values that don't need f,g
				if(g in [av,sANY])a=item.remove(sG)
			if(!LT1)LT1=fill_TIM()
			if(ty==sC && !(vt in LT1))a=item.remove(sC)
			if(ty==sE && item[sE]!=null)a=item.remove(sE)
		}
		// cruft when editing operands
		if(ty in ListC2 && item.d instanceof List)a=item.remove(sD)
		if(!(ty in [sE,sC]) && item['exp'])a=item.remove('exp') // evaluateOperand
		if(ty!=sX && item[sX]!=null){ a=item.remove(sX);a=item.remove(sXI)}
		if(ty!=sE && item[sE]!=null)a=item.remove(sE)
		if(ty!=sC && item[sC]!=null)a=item.remove(sC)
		if(ty!=sV && item[sV]!=null)a=item.remove(sV)
		if(ty!=sS && item[sS]!=null)a=item.remove(sS)
		if(ty!=sP && item[sA]!=null)a=item.remove(sA)
	}
	if(inMem && ty==sEXPR && item.i && ((List)item.i).size()==i1){ // simplify un-needed nesting
		List<Map> bb=(List<Map>)item.i
		Map bb1=bb[iZ]
		if(sMt(bb1)==sEXPR){
			if(bb1.i && ((List)bb1.i).size()==i1){
				List<Map> bab=(List<Map>)bb1.i
				Map bab1=bab[iZ]
				if(sMt(bab1)==sEXPR)item.i=bab1.i
				else item.i=bb1.i
			}else item.i=bb1.i
		}
	}

	if(item.data instanceof Map && !(Map)item.data)a=item.remove('data')

	if(inMem){
		// defaults
		if(sMs(item,sF)==sL)a=item.remove(sF) // timeValue.f
		if(sMs(item,'sm')=='auto')a=item.remove('sm') // subscription method
		if(sMs(item,'ctp')==sI)a=item.remove('ctp') // case traversal switch stmt
		if(item.n && ty && sMa(item)==sD)a=item.remove(sA) // variable.a sS -> constant  sD-> dynamic

		// from IDE: cancel on c- condition state change (def), p- piston state change, b- condition or piston state change, ""- never cancel
		// makes 'c' the default empty for the groovy code
		if(!ListStmt)ListStmt=fill_STMT()
		if(ty in ListStmt){
			if(!sMs(item,sTCP))item[sTCP]=sN
			else if(sMs(item,sTCP)==sC)a=item.remove(sTCP)
			if(item.a instanceof String && sMa(item)==s0)a=item.remove(sA) // async
		}
		if(sMs(item,sTCP)==sC)warn "found tcp in $ty",null

		// item.w is warnings
		if(item.w instanceof List)a=item.remove('w')

		if(item.rop && (!item.r || ((List)item.r).size()==iZ)){ a=item.remove('rop');a=item.remove('rn') }
	}

	for(String t in [sD,sR,'cs','fs','ts',sE,'ei',sS,'k',sP,sV,sC]){
		if(item.containsKey(t) && item[t] instanceof List){
			if(inMem && ((List)item[t]).size()==iZ)Boolean b=item.remove(t)
			else if(((List)item[t])[iZ] instanceof Map) for(Map eI in (List<Map>)item[t])cleanCode(eI,inMem)
		}
	}
	for(String t in [sI]){ // expression items
		if(item.containsKey(t) && item[t] instanceof List){
			if(inMem && ((List)item[t]).size()==iZ)Boolean b=item.remove(t)
			else{
				if(((List)item[t])[iZ] instanceof Map) for(Map eI in (List<Map>)item[t])cleanCode(eI,inMem)
				else if(inMem)Boolean b=item.remove(t)
			}
		}
	}

	if(inMem){
		// comments
		if(item[sZ]!=null)a=item.remove(sZ)
		if(item.zc!=null)a=item.remove('zc')
		// UI operand operating keys
		if(item.str!=null)a=item.remove('str')
		if(item.ok!=null)a=item.remove('ok')
		if(item[sL]!=null && item[sL] instanceof String)a=item.remove(sL)
	}

	if(ty==sEVERY){ // scheduleTimer
		if(sMvt((Map)item.lo) in [sMS,sS,sM,sH]){ a=item.remove('lo2'); a=item.remove('lo3') }
		else if(sMt((Map)item.lo2)==sC)a=item.remove('lo3')
	}

	if(item.rn!=null)cleanCode(item.rn,inMem)
	if(item.exp!=null)cleanCode(item.exp,inMem)
	if(item.v instanceof Map)cleanCode(item.v,inMem)
	if(item.lo!=null)cleanCode(item.lo,inMem)
	if(item.lo2!=null)cleanCode(item.lo2,inMem)
	if(item.lo3!=null)cleanCode(item.lo3,inMem)

	if(item.ro!=null){
		if(inMem && (item.ro instanceof String || fndEmptyOper((Map)item.ro)) )a=item.remove('ro')
		else cleanCode(item.ro,inMem)
	}
	for(String t in ['wd','ro2','to','to2']){
		if(item.containsKey(t)){
			Map mt=(Map)item[t]
			if(mt!=null){
				if(inMem && fndEmptyOper(mt))Boolean b=item.remove(t)
				else cleanCode(mt,inMem)
			}
		}
	}
}
@CompileStatic
static Boolean fndEmptyOper(Map oper){
	Integer sz=oper.size()
	return sz==iZ || ( (sz==i2 || sz==i3) && sMt(oper)==sC && !oper.d && sMs(oper,sG)==sANY)
}

Map deletePiston(){
	String meth='deletePiston'
	if(eric())log.debug meth
	wremoveAllInUseGlobalVar()
	atomicState.pistonDeleted=true
	state[sACT]=false
	clear1(true,true,true,true)	// calls clearMyCache(meth) && clearMyPiston
	return [:]
}

private void checkLabel(Map r9=null){
	Boolean act=isAct(r9)
	Boolean dis=!isEnbl(r9)
	String savedLabel=(String)r9[sSVLBL]
	if(savedLabel==sNL){
		log.error "null label"
		return
	}
	String appLbl=savedLabel
	if(savedLabel!=sNL){
		if(act && !dis)app.updateLabel(savedLabel)
		if(!act || dis){
			String tstr='(Paused)'
			if(act && dis)tstr='(Disabled) Kill switch is active'
			String res=appLbl+sSPC+sSPORNG+tstr+sSSP
			app.updateLabel(res)
		}
	}
}

void config(Map data){ // creates a new piston
	if(data==null)return
	if((String)data.bin!=sNL){
		state[sBIN]=sMs(data,sBIN)
		app.updateSetting(sBIN,[(sTYPE):sTEXT,(sVAL):(String)state[sBIN]])
	}
	if((String)data.author!=null){
		state[sATHR]=sMs(data,sATHR)
		app.updateSetting(sATHR,[(sTYPE):sTEXT,(sVAL):(String)state[sATHR]])
	}
	if((String)data.initialVersion!=null)state.initialVersion=(String)data.initialVersion
	clearMyCache('config')
}

Map setBin(String bin){
	String typ='setBin'
	if(!bin || !!state[sBIN]){
		log.error typ+': bad bin'
		return [:]
	}
	state[sBIN]=bin
	app.updateSetting(sBIN,[(sTYPE):sTEXT,(sVAL):bin])
	clearMyCache(typ)
	return [:]
}

Map pausePiston(){
	state[sACT]=false
	cleanState()
	clearMyCache('pauseP')

	LinkedHashMap r9
	r9=getRunTimeData()
	Boolean lg=isInf(r9)
	Map msg
	if(lg){
		info 'Stopping piston...',r9,iZ
		msg=timer 'Piston stopped',r9,iN1
	}
	state[sSCHS]=[]
	((Map)r9[sSTATS])[sNSCH]=lZ
	r9[sNSCH]=lZ
	state[sNSCH]=lZ
	wunsubscribe()
	unschedule()
	wremoveAllInUseGlobalVar()
	app.removeSetting('dev')
//	state[sTRC]=[:]
	state.subscriptions=[:]
	if(lg)//noinspection GroovyVariableNotAssigned
		info msg,r9
	updateLogs(r9)
	state[sACT]=false
	state[sST]=[:]+(Map)r9[sST]
	def a=state.remove(sLEVT)
	clear1(true,false,false,false)	// calls clearMyCache(meth) && clearMyPiston
	Map nRtd=shortRtd(r9)
	r9=null
	return nRtd
}

Map resume(LinkedHashMap piston=null,Boolean sndEvt=true){
	state[sACT]=true
	state.subscriptions=[:]
	state[sSCHS]=[]
	state[sCACHE]=[:] // reset followed by
	cleanState()

	String mSmaNm=sAppId()
	getTheLock(mSmaNm,'resume')
	theSemaphoresVFLD[mSmaNm]=lZ
	theSemaphoresVFLD=theSemaphoresVFLD
	theQueuesVFLD[mSmaNm]=[]
	theQueuesVFLD=theQueuesVFLD
	releaseTheLock(mSmaNm)

	clearMyCache('resumeP')

	LinkedHashMap tmpRtD,r9
	tmpRtD=getTemporaryRunTimeData()
	Map msg=timer 'Piston started',tmpRtD,iN1
	if(piston!=null)tmpRtD[sPIS]=piston
	r9=getRunTimeData(tmpRtD,null,true,false,false) //performs subscribeAll; reinitializes cache variables
	Boolean lg=isInf(r9)
	if(lg)info 'Starting piston... ('+sHVER+')',r9,iZ
	checkVersion(r9)
	if(lg)info msg,r9
	updateLogs(r9)
	state[sST]=[:]+(Map)r9[sST]
	Map nRtd=shortRtd(r9)
	nRtd.result=[(sACT):true,subscriptions:(Map)state.subscriptions]
	tmpRtD=null
	r9=null
	if(sndEvt && bIs(gtState(),'allowResume')) // pistonResume
		wrunInMillis(600L,'resumeHandler',[data: [:]])
	return nRtd
}

@CompileStatic
static Map shortRtd(Map r9){
	Map st=[:]+(Map)r9[sST]
	def a=st.remove(sOLD)
	Map myRt=[
		(sID):sMs(r9,sID),
		(sACT):isAct(r9),
		(sTMSTMP):lMs(r9,sTMSTMP),
		(sCTGRY):sMs(r9,sCTGRY),
		(sSTATS):[
			(sNSCH):lMs(r9,sNSCH)
		],
		(sPIS):[
			(sZ):sMs(r9,'pistonZ')
		],
		(sST):st,
		Cached:bIs(r9,'Cached') ?: false
	]
	return myRt
}

Map setLoggingLevel(String level,Boolean clrC=true){
	Integer mlogging; mlogging=level.isInteger()? level.toInteger():iZ
	mlogging=Math.min(Math.max(iZ,mlogging),i3)
	app.updateSetting(sLOGNG,[(sTYPE):sENUM,(sVAL):mlogging.toString()])
	state[sLOGNG]=mlogging
	if(mlogging==iZ)state[sLOGS]=[]
	if(clrC)clearMyCache('setLoggingLevel')
	return [(sLOGNG):mlogging]
}

Map setCategory(String category){
	state[sCTGRY]=category
	clearMyCache(sCTGRY)
	return [(sCTGRY):category]
}

void resumeHandler(){
	handleEvents([(sDATE): new Date(), (sDEV): gtLocation(), (sNM): sPSTNRSM, (sVAL): wnow()])
}

Map test(){
	handleEvents([(sDATE):new Date(),(sDEV):gtLocation(),(sNM):'test',(sVAL):wnow()])
	return [:]
}

Map execute(Map data,String src){
	handleEvents([(sDATE):new Date(),(sDEV):gtLocation(),(sNM):'execute',(sVAL): src!=null ? src:wnow(),jsonData:data],false)
	return [:]
}

Map clickTile(tidx){
	handleEvents([(sDATE):new Date(),(sDEV):gtLocation(),(sNM):sTILE,(sVAL):tidx])
	return (Map)gtSt(sST) ?: [:]
}

Map clearCache(){
	handleEvents([(sDATE):new Date(),(sDEV):gtLocation(),(sNM):sCLRC,(sVAL):wnow()])
	return [:]
}

Map clearLogsQ(){
	handleEvents([(sDATE):new Date(),(sDEV):gtLocation(),(sNM):sCLRL,(sVAL):wnow()])
	return [:]
}

Map clearAllQ(){
	handleEvents([(sDATE):new Date(),(sDEV):gtLocation(),(sNM):sCLRA,(sVAL):wnow()])
	return [:]
}

@Field volatile static Map<String,Long> lockTimesVFLD=[:]
@Field volatile static Map<String,String> lockHolderVFLD=[:]

@CompileStatic
void getTheLock(String qname,String meth=sNL,Boolean longWait=false){
	Boolean a=getTheLockW(qname,meth,longWait)
}

@CompileStatic
Boolean getTheLockW(String qname,String meth=sNL,Boolean longWait=false){
	Long waitT=longWait? lTHOUS:10L
	Boolean wait; wait=false
	Integer semaNum=semaNum(qname)
	String semaSNum=semaNum.toString()
	Semaphore sema=sema(semaNum)
	while(!(sema.tryAcquire())){
		// did not get lock
		Long t; t=lockTimesVFLD[semaSNum]
		if(t==null){
			t=wnow()
			lockTimesVFLD[semaSNum]=t
			lockTimesVFLD=lockTimesVFLD
		}
		if(eric())warn "waiting for ${qname} ${semaSNum} lock access, $meth, long: $longWait, holder: ${(String)lockHolderVFLD[semaSNum]}",null
		wpauseExecution(waitT)
		wait=true
		if(elapseT(t)>30000L){
			releaseTheLock(qname)
			if(eric())warn "overriding lock $meth",null
		}
	}
	lockTimesVFLD[semaSNum]=wnow()
	lockTimesVFLD=lockTimesVFLD
	lockHolderVFLD[semaSNum]=sAppId()+sSPC+meth
	lockHolderVFLD=lockHolderVFLD
	return wait
}

@CompileStatic
static void releaseTheLock(String qname){
	Integer semaNum=semaNum(qname)
	String semaSNum=semaNum.toString()
	Semaphore sema=sema(semaNum)
	lockTimesVFLD[semaSNum]=(Long)null
	lockTimesVFLD=lockTimesVFLD
//	lockHolderVFLD[semaSNum]=sNL
//	lockHolderVFLD=lockHolderVFLD
	sema.release()
}

@Field static Semaphore theLock0FLD=new Semaphore(1)
@Field static Semaphore theLock1FLD=new Semaphore(1)
@Field static Semaphore theLock2FLD=new Semaphore(1)
@Field static Semaphore theLock3FLD=new Semaphore(1)
@Field static Semaphore theLock4FLD=new Semaphore(1)
@Field static Semaphore theLock5FLD=new Semaphore(1)
@Field static Semaphore theLock6FLD=new Semaphore(1)
@Field static Semaphore theLock7FLD=new Semaphore(1)
@Field static Semaphore theLock8FLD=new Semaphore(1)
@Field static Semaphore theLock9FLD=new Semaphore(1)
@Field static Semaphore theLock10FLD=new Semaphore(1)
@Field static Semaphore theLock11FLD=new Semaphore(1)
@Field static Semaphore theLock12FLD=new Semaphore(1)
@Field static Semaphore theLock13FLD=new Semaphore(1)
@Field static Semaphore theLock14FLD=new Semaphore(1)
@Field static Semaphore theLock15FLD=new Semaphore(1)
@Field static Semaphore theLock16FLD=new Semaphore(1)
@Field static Semaphore theLock17FLD=new Semaphore(1)
@Field static Semaphore theLock18FLD=new Semaphore(1)
@Field static Semaphore theLock19FLD=new Semaphore(1)
@Field static Semaphore theLock20FLD=new Semaphore(1)
@Field static Semaphore theLock21FLD=new Semaphore(1)
@Field static Semaphore theLock22FLD=new Semaphore(1)
@Field static Semaphore theLock23FLD=new Semaphore(1)

@Field static final Integer iStripes=22
@CompileStatic
static Integer semaNum(String name){
	if(name.isNumber())return name.toInteger()%iStripes
	if(name==sTSLF)return iStripes
	if(name==sTGBL)return iStripes+i1
	Integer hash=smear(name.hashCode())
	return Math.abs(hash)%iStripes
}

@CompileStatic
static Semaphore sema(Integer snum){
	switch(snum){
		case 22: return theLock22FLD
		case 0: return theLock0FLD
		case 1: return theLock1FLD
		case 2: return theLock2FLD
		case 3: return theLock3FLD
		case 4: return theLock4FLD
		case 5: return theLock5FLD
		case 6: return theLock6FLD
		case 7: return theLock7FLD
		case 8: return theLock8FLD
		case 9: return theLock9FLD
		case 10: return theLock10FLD
		case 11: return theLock11FLD
		case 12: return theLock12FLD
		case 13: return theLock13FLD
		case 14: return theLock14FLD
		case 15: return theLock15FLD
		case 16: return theLock16FLD
		case 17: return theLock17FLD
		case 18: return theLock18FLD
		case 19: return theLock19FLD
		case 20: return theLock20FLD
		case 21: return theLock21FLD
		case 23: return theLock23FLD
		default: //log.error "bad hash result $snum"
			return null
	}
}

private static Integer smear(Integer hashC){
	Integer hashCode; hashCode=hashC
	hashCode ^= (hashCode >>> i20) ^ (hashCode >>> i12)
	return hashCode ^ (hashCode >>> i7) ^ (hashCode >>> i4)
}

/* wrappers */
private static LinkedHashMap fixEvt(event){
	if(event!=null){
		Map mEvt=[
			(sT):((Date)event.date).getTime(),
			(sNM):(String)event[sNM],
			(sVAL):event[sVAL],
			descriptionText:(String)event.descriptionText,
			unit:event?.unit,
			physical:!!event.physical,
			jsonData:event?.jsonData,
		]
		if(!(event instanceof com.hubitat.hub.domain.Event)){
			if(event.index!=null)mEvt.index=event.index
			if(event.recovery!=null)mEvt.recovery=event.recovery
			if(event.schedule!=null)mEvt.schedule=event.schedule
			if(event.contentType!=null)mEvt.contentType=event.contentType
			if(event.responseData!=null)mEvt.responseData=event.responseData
			if(event.responseCode!=null)mEvt.responseCode=event.responseCode
			if(event.setRtData!=null)mEvt.setRtData=event.setRtData
		}
		def a=event.device
		if(a!=null)mEvt.device=cvtDev(a)
		return mEvt
	}
	return null
}

@CompileStatic
private static Map cleanEvt(Map evt){
	def a
	if(evt.unit==null)a=evt.remove('unit')
	if(evt.descriptionText==null)a=evt.remove('descriptionText')
	if(evt.index==iZ)a=evt.remove('index')
	if(!bIs(evt,'physical'))a=evt.remove('physical')
	return evt
}

@Field volatile static Map<String,List<Map>> theQueuesVFLD=[:]
@Field volatile static Map<String,Long> theSemaphoresVFLD=[:]

// This can a) lock semaphore b) wait for semaphore c) queue event d) just fall through (no locking or waiting)
@CompileStatic
private LinkedHashMap lockOrQueueSemaphore(Boolean synchr,Map event,Boolean queue,Map r9){
	Long tt1,startTime,r_semaphore,semaphoreDelay,lastSemaphore
	tt1=wnow()
	startTime=tt1
	r_semaphore=lZ
	semaphoreDelay=lZ
	String semaphoreName,mSmaNm
	semaphoreName=sNL
	Boolean didQ,waited,clrC
	didQ=false
	waited=false

	if(synchr){
		mSmaNm=sAppId()
		waited=getTheLockW(mSmaNm,sLCK1)
		tt1=wnow()

		clrC=false
		Integer qsize; qsize=iZ
		while(true){
			Long t0=theSemaphoresVFLD[mSmaNm]
			Long tt0=t0!=null ? t0:lZ
			lastSemaphore=tt0
			if(lastSemaphore==lZ || tt1-lastSemaphore>100000L){
				theSemaphoresVFLD[mSmaNm]=tt1
				theSemaphoresVFLD=theSemaphoresVFLD
				semaphoreName=mSmaNm
				semaphoreDelay=waited ? tt1-startTime:lZ
				r_semaphore=tt1
				break
			}
			if(queue){
				if(event!=null){
					Map mEvt=event
					List<Map> evtQ
					evtQ=theQueuesVFLD[mSmaNm]
					evtQ=evtQ!=null ? evtQ:(List<Map>)[]
					qsize=evtQ.size()
					if(qsize>i12)clrC=true
					else{
						Boolean a=evtQ.push(mEvt)
						theQueuesVFLD[mSmaNm]=evtQ
						theQueuesVFLD=theQueuesVFLD
						didQ=true
					}
				}
				break
			}else{
				releaseTheLock(mSmaNm)
				waited=true
				wpauseExecution(100L)
				getTheLock(mSmaNm,sLCK2)
				tt1=wnow()
			}
		}
		releaseTheLock(mSmaNm)
		if(clrC){
			error "large queue size ${qsize} clearing",r9
			clear1(true,true,true,true)
		}
	}
	return [
		semaphore:r_semaphore,
		semaphoreName:semaphoreName,
		semaphoreDelay:semaphoreDelay,
		waited:waited,
		exitOut:didQ
	]
}

@CompileStatic
private static Boolean isEric(Map r9){ eric1() && isDbg(r9) }

@Field volatile static LinkedHashMap<String,LinkedHashMap> theCacheVFLD=[:] // each piston has a map

@Field static final String sCLRMC='clearMyCache'
@CompileStatic
private void clearMyCache(String meth=sNL){
	String appStr=sAppId()
	String myId=appStr
	if(!myId)return
	Boolean clrd; clrd=false
	String mSmaNm=appStr
	getTheLock(mSmaNm,sCLRMC)
	Map t0; t0=theCacheVFLD[myId]
	if(t0){
		theCacheVFLD[myId]=[:] as LinkedHashMap
		theCacheVFLD=theCacheVFLD
		clrd=true
		t0=null
	}
	releaseTheLock(mSmaNm)
	if(eric() && clrd)debug 'clearing piston data cache '+meth,null
}

@CompileStatic
private LinkedHashMap getCachedMaps(String meth=sNL,Boolean retry=true,Boolean Upd=true){
	String s=sAppId()
	String myId=s
	String mSmaNm=s
	LinkedHashMap res,t
	t=[:] as LinkedHashMap
	getTheLock(mSmaNm,sI)
	res=theCacheVFLD[myId]
	if(res){
		if(res[sCACHE] instanceof Map && res[sBLD] instanceof Integer){
			res=(LinkedHashMap)(t+res)
			releaseTheLock(mSmaNm)
			return res
		}
		theCacheVFLD[myId]=t
		theCacheVFLD=theCacheVFLD
	}
	releaseTheLock(mSmaNm)
	if(retry){
		t=getDSCache(meth,Upd)
		if(!Upd)return t
		return getCachedMaps(meth,false,Upd)
	}
//	if(eric())log.warn 'cached map nf'
	return null
}

private Map gtCachedAtomicState(){
	Long atomStart=wnow()
	def aS
	atomicState.loadState()
	aS=atomicState.@backingMap
	if(((String)gtSetting(sLOGNG))?.toInteger()>i2)log.debug "AtomicState generated in ${elapseT(atomStart)}ms"
	return aS
}

@Field static final String sGDS='getDSCache'

@CompileStatic
private LinkedHashMap getDSCache(String meth,Boolean Upd=true){
	String appStr=sAppId()
	String myId=appStr
	String mSmaNm=myId
	LinkedHashMap r9,res,pC
	pC=getParentCache()
	Boolean sendM
	sendM=false
	Long stateStart,stateEnd
	r9=null

	getTheLock(mSmaNm,sGDS)
	res=theCacheVFLD[myId]

	if(!res){
		releaseTheLock(mSmaNm)
		stateStart=wnow()
		Map mst=gtState()
		if(mst[sPEP]==null){ // upgrades of older pistons
			LinkedHashMap piston=recreatePiston()
			assignSt(sPEP, !!gtPOpt([(sPIS):piston],sPEP) )
		}
		Integer bld=iMs(mst,sBLD)
		String ttt
		ttt=sMs(mst,sSVLBL)
		if(ttt==sNL){
			ttt=gtAppN()
			if(bld>iZ){
				assignSt(sSVLBL,ttt)
				assignAS(sSVLBL,ttt)
			}
		}
		LinkedHashMap te1
		te1=[
			(sFFT):iZ,
			(sRUN):true,
			(sLOGNG): mst[sLOGNG]!=null ? iMs(mst,sLOGNG):iZ,
			(sPEP): bIs(mst,sPEP),
			(sCACHE): [:],
			(sNWCACHE):[:],
			(sDEVS): [:],
			(sPIS): null,
			(sTRC): [:],
			(sSCHS):[],
			(sVARS):[:],
			(sST):[:],
			(sLOCID): sNL,
			(snId): appStr,
			(spId): sPAppId(),
			(sLOGS):[],
			(sACT): bIs(mst,sACT),
			(sCACHEP):[:],
			(sTMSTMP):0L,
			(sNSCH): 0L,
			(sLEXEC): 0L,
			(sSTORE): [:],
		]
		Map t1
		t1=te1+[
			(sID): hashPID(appStr),
			(sNM): ttt,
			(sSVLBL): ttt,
			(sCTGRY): mst[sCTGRY] ?: iZ,
			(sCREAT): lMs(mst,sCREAT),
			(sMODFD): lMs(mst,sMODFD),
			(sBLD): bld,
			(sATHR): sMs(mst,sATHR),
			(sBIN): sMs(mst,sBIN),
			runTimeHis: []
		] as Map
		stateEnd=wnow()

		def t0
		Map m0,aS
		List l0
		aS=isPep(t1)? gtCachedAtomicState():gtState()
		m0=(Map)aS.cache; t1[sCACHE]=m0 ? m0:[:]
		m0=(Map)aS.store; t1[sSTORE]=m0 ? m0:[:]
		m0=(Map)aS.state; t1[sST]=m0 ? m0:[:]
		t1.pistonZ=sMs(aS,'pistonZ')
		m0=(Map)aS.trace; t1[sTRC]=m0 ? m0:[:]
		l0=(List)aS.schedules; t1[sSCHS]=l0 ? []+l0:[]
		t1[sNSCH]=lMs(aS,sNSCH)
		t1[sLEXEC]=lMs(aS,sLEXEC)
		l0=(List)aS.logs; t1[sLOGS]=l0 ? []+l0:[]
		m0=(Map)aS.vars; t1[sVARS]=m0 ? [:]+m0:[:]
		t1.mem=mem()
		resetRandomValues(t1)
		def devs=gtSetting('dev')
		t1[sDEVS]=devs && devs instanceof List ? ((List)devs).collectEntries{ Object it -> [(hashD(t1,it)):it]}:[:]

		Boolean a=(Boolean)gtSetting(sLOGHE)
		if(a)t1[sLOGHE]=true

		Integer mS,st,at1
		mS=iMs(getPistonLimits,sMSTATS)
		st=(Integer)gtSetting(sMSTATS)
		at1=st!=null ? st:mS
		if(at1<=iZ)at1=mS
		if(at1<i2)at1=i2
		t1[sMSTATS]=at1

		Integer myL,ml,lim
		myL=iMs(getPistonLimits,sMLOGS)
		ml=(Integer)gtSetting(sMLOGS)
		lim=ml!=null ? ml:myL
		if(lim<iZ)lim=myL
		t1[sMLOGS]=lim

		res= t1 as LinkedHashMap
		r9= (LinkedHashMap)(res+pC)

		sendM=true
		if(Upd){
			t1.Cached=true
			getTheLock(mSmaNm,sGDS+s2)
			theCacheVFLD[myId]= t1 as LinkedHashMap
			theCacheVFLD=theCacheVFLD
			releaseTheLock(mSmaNm)
		}
		te1=null;t1=null;t0=null;aS=null;m0=null;l0=null

		if(eric() && sendM){
			String s= Upd ? '/cached':sBLK
			debug 'creating'+s+' my piston cache '+meth,r9
		}
	}else{
		r9= (LinkedHashMap)(res+pC)
		releaseTheLock(mSmaNm)
	}
	if(stateStart) //noinspection GroovyVariableNotAssigned
		r9.stateAccess=stateEnd-stateStart
	pC=null
	res=null
	if(sendM && iMs(r9,sBLD)!=iZ)checkLabel(r9)
	return r9
}

@Field volatile static LinkedHashMap<String,LinkedHashMap> theParentCacheVFLD=[:]

void clearParentCache(String meth=sNL){
	String lockTyp='clearParentCache'
	String semName=sTSLF
	String wName=sPAppId()
	getTheLock(semName,lockTyp)

	theParentCacheVFLD[wName]=null
	theParentCacheVFLD=theParentCacheVFLD

	theCacheVFLD=[:] // reset all pistons cache
	clearHashMap(wName)
	loadCDB()

	releaseTheLock(semName)
	if(eric())log.debug "clearing parent cache and all piston caches $meth"
}

// load caches
private void loadCDB(){
	theVirtDevicesFLD=null
	Map comparison=ComparisonsF()
	Map vcmd=VirtualCommandsF()
	Map attr=AttributesF()
	List col=getColorsF()
	Map cmd=PhysicalCommandsF()
}

private LinkedHashMap getParentCache(){
	String wName=sPAppId()
	LinkedHashMap res
	res=theParentCacheVFLD[wName]
	if(res==null){
		String lockTyp='getParentCache'
		String semName=sTSLF
		getTheLock(semName,lockTyp)
		res=theParentCacheVFLD[wName]
		Boolean sendM; sendM=false
		if(res==null){
			Map t0=wgtPdata()
			Map t1
			t1=[
				coreVersion: sMs(t0,'sCv'),
				hcoreVersion: sMs(t0,'sHv'),
				powerSource: sMs(t0,'powerSource'),
				region: sMs(t0,'region'),
				instanceId: sMs(t0,'instanceId'),
				settings: (Map)t0.stsettings,
				enabled: isEnbl(t0),
				lifx: (Map)t0.lifx,
				logPExec: bIs(t0,'logPExec'),
				accountId: sMs(t0,'accountId'),
				newAcctSid: bIs(t0,'newAcctSid'),
				(sLOCID): sMs(t0,sLOCID),
				oldLocations: (List)t0.oldLocations,
				allLocations: (List)t0.allLocations,
				incidents: (List)t0.incidents,
				useLocalFuelStreams: bIs(t0,'useLocalFuelStreams')
			]
			res=t1
			theParentCacheVFLD[wName]=t1
			theParentCacheVFLD=theParentCacheVFLD
			t1=null
			sendM=true
		}
		releaseTheLock(semName)
		if(eric() && sendM)debug 'gathering parent cache',null
	}
	return res
}

@CompileStatic
private LinkedHashMap getTemporaryRunTimeData(Long startTime=wnow()){
	if(thePhysCommandsFLD==null){ //do one time load once
		String semName=sTSLF
		getTheLock(semName,sGETTRTD,true)
		if(thePhysCommandsFLD==null) loadCDB()
		releaseTheLock(semName)
	}
	LinkedHashMap r9=getDSCache(sGETTRTD)
	r9.temporary=true
	r9[sTMSTMP]=startTime
	r9[sLOGS]=[[(sT):startTime]]
	r9[sDBGLVL]=iZ
	return r9
}

@CompileStatic
private LinkedHashMap getRunTimeData(LinkedHashMap ir9=null,LinkedHashMap retSt=null,Boolean fetchWrappers=false,Boolean shorten=true,Boolean inMem=false){
	LinkedHashMap r9,piston,m1
	r9=ir9
	Long started=wnow()
	List logs
	logs=[]
	Long lstarted,lended
	lstarted=lZ
	lended=lZ
	Integer dbgLevel
	dbgLevel=iZ
	if(r9!=null){
		logs=r9[sLOGS]!=null ? (List)r9[sLOGS]:[]
		lstarted=r9.lstarted!=null ? lMs(r9,'lstarted'):lZ
		lended=r9.lended!=null ? lMs(r9,'lended'):lZ
		piston=r9[sPIS]!=null ? (LinkedHashMap)r9[sPIS]:null
		dbgLevel=r9[sDBGLVL]!=null ? iMs(r9,sDBGLVL):iZ
	}else r9=getTemporaryRunTimeData(started)
	Long timestamp=lMs(r9,sTMSTMP)

	if(r9.temporary!=null)def a=r9.remove('temporary')

	m1=[:]
	if(retSt!=null) m1=retSt
	r9=(LinkedHashMap)(r9+m1)

	r9[sTMSTMP]=timestamp
	r9.lstarted=lstarted
	r9.lended=lended
	r9[sLOGS]= logs.size()>iZ ? logs:[[(sT):timestamp]]
	r9[sDBGLVL]=dbgLevel

	r9[sTRC]=[(sT):timestamp,points:[:]] as LinkedHashMap
	r9[sSTATS]=[(sNSCH):lZ] as LinkedHashMap
	r9[sNWCACHE]=[:]
	r9[sSCHS]=[]
	r9[sCNCLATNS]=initCncl()
	r9.updateDevices=false
	r9[sSYSVARS]=getSystemVariables()

	Map aS
	aS=getCachedMaps('getRTD')
	aS=aS!=null?aS:[:]
	Map st=(Map)aS[sST]
	Map st1=st!=null && st instanceof Map ? [:]+st:[(sOLD):sBLK,(sNEW):sBLK] as LinkedHashMap
	st1[sOLD]=sMs(st1,sNEW)
	r9[sST]=st1

	r9.pStart=wnow()

	if(piston==null)piston=recreatePiston(shorten,inMem)
	Boolean doSubScribe=!bIs(piston,'cached')

	r9[sPIS]=piston

	getLocalVariables(r9,aS)
	piston=null

	if(doSubScribe || fetchWrappers){
		subscribeAll(r9,fetchWrappers,inMem)
		String pNm=sMs(r9,snId)
		Map pData
		pData=(Map)thePistonCacheFLD[pNm]
		if(shorten && inMem && pNm!=sBLK && pData!=null && pData.pis==null){
			pData.pis=[:]+(LinkedHashMap)r9[sPIS]
			thePistonCacheFLD[pNm]=[:]+pData
			pData=null
			mb()
			if(eric()){
				debug 'creating piston-code-cache',null
				dumpPCsize()
			}
		}
	}
	Long t0=wnow()
	r9.pEnd=t0
	r9.generatedIn=t0-started
	return r9
}

private void dumpPCsize(){
	Map pL
	Integer t0,t1
	t0=iZ
	t1=iZ
	try{
		pL=[:]+thePistonCacheFLD
		t0=pL.size()
		t1="${pL}".size()
	}catch(ignored){}
	pL=null
	String mStr="piston plist is ${t0} elements, and ${t1} bytes".toString()
	doLog(sDBG,mStr)
	if(t1>40000000){
		thePistonCacheFLD=[:]
		mb()
		doLog(sWARN,"clearing entire "+mStr)
	}
}

private void checkVersion(Map r9){
	String ver=sHVER
	String t0=(String)r9.hcoreVersion
	if(ver!=t0){
		String tt0="child app's version($ver)".toString()
		String tt1="parent app's version($t0)".toString()
		String tt2=' is newer than the '
		String msg
		if(ver>t0)msg=tt0+tt2+tt1
		else msg=tt1+tt2+tt0
		warn "WARNING: Results may be unreliable because the "+msg+". Please update both apps to the same version.",r9
	}
	if(mTZ()==null)
		error 'Your location is not setup correctly - timezone information is missing. Please select your location by placing the pin and radius on the map, then tap Save, and then tap Done. You may encounter error or incorrect timing until fixed.',r9
}

/** EVENT HANDLING								**/

void deviceHandler(event){ handleEvents(event) }

void timeHandler(event){ timeHelper(event,false) }

void timeHelper(event,Boolean recovery){
	Long t=lMt(event)
	handleEvents([(sDATE):new Date(t),(sDEV):gtLocation(),(sNM):sTIME,(sVAL):t,schedule:event,recovery:recovery],!recovery)
}

/* wrappers */
void sendExecuteEvt(String pistonId,String val,String desc,Map data){
	String json= JsonOutput.toJson(data)
	sendLocationEvent((sNM):pistonId,(sVAL):val,isStateChange:true,displayed:false,linkText:desc,descriptionText:desc,data:json)
}

private static Boolean stJson(String c){ return c.startsWith(sOB) && c.endsWith(sCB) }

void executeHandler(event){
	Map data; data=null
	def d1=event.data
	if(d1 instanceof String){
		String d=(String)d1
		if(stJson(d))data= (LinkedHashMap)new JsonSlurper().parseText(d)
	}
	handleEvents([(sDATE):event.date,(sDEV):gtLocation(),(sNM):'execute',(sVAL):event[sVAL],jsonData:(data ?: event.jsonData)])
}

@Field static final Map getPistonLimits=[
	scheduleRemain: 15000L, // this or longer remaining executionTime to process additional schedules
	scheduleVariance: 63L,
	executionTime: 40000L, // time we stop execution run
	slTime: 14300L, // time before we start inserting pauses
	useBigDelay: 20000L, // transition from short delay to Long delay
	taskShortDelay: 150L,
	taskLongDelay: 500L,
	taskMaxDelay: 250L,
	deviceMaxDelay: 1000L,
	maxStats: 50,
	maxLogs: 50,
]

@Field static final String sEXS='Execution stage started'
@Field static final String sEXC='Execution stage complete.'

@CompileStatic
void handleEvents(evt,Boolean queue=true,Boolean callMySelf=false){
	Long startTime=wnow()
	LinkedHashMap event,tmpRtD,retSt
	event=fixEvt(evt)
	tmpRtD=getTemporaryRunTimeData(startTime)
	Map msg=timer 'Event processed successfully',tmpRtD,iN1
	String evntName; evntName=sMs(event,sNM)
	String evntVal; evntVal="${event[sVAL]}".toString()
	Long eventDelay=Math.round(d1*startTime-lMt(event))
	Integer lg=iMs(tmpRtD,sLOGNG)
	if(lg!=iZ){
		String devStr=gtLbl(event.device)
		String recStr
		recStr=evntName==sTIME && bIs(event,'recovery') ? '/recovery':sBLK
		recStr+=bIs(event,'physical') ? '/physical':sBLK
		String valStr=evntVal+(evntName==sHSMALRT && evntVal==sRULE ? sCOMMA+(String)event.descriptionText:sBLK)
		String mymsg
		mymsg='Received event ['+devStr+'].'+evntName+recStr+' = '+valStr+" with a delay of ${eventDelay}ms"
		if(lg>i1)mymsg+=", canQueue: ${queue}, calledMyself: ${callMySelf}"
		mymsg=mymsg.toString()
		info mymsg,tmpRtD,iZ
	}

	Boolean clrC=evntName==sCLRC
	Boolean clrL=evntName==sCLRL
	Boolean clrA=evntName==sCLRA

	Boolean act=isAct(tmpRtD)
	Boolean dis=!isEnbl(tmpRtD)
	if(!act || dis){
		if(lg!=iZ){
			String tstr=' active, aborting piston execution.'
			if(!act)msg.m='Piston is not'+tstr+' (Paused)' // pause/resume piston
			if(dis)msg.m='Kill switch is'+tstr
			info msg,tmpRtD
		}
		updateLogs(tmpRtD)
		if(clrL)clear1(true,true,true,false,true)
		else if(clrC)clear1(true,false,false,false)
			else if(clrA)clear1(true,true,true,true)
		return
	}

	Boolean myPep=isPep(tmpRtD)
	Boolean strictSync=true // could be a setting
	Boolean serializationOn=!myPep // && true // on / off switch
	Boolean doSerialization=serializationOn && !callMySelf

	tmpRtD.lstarted=wnow()
	retSt=[semaphore:lZ,semaphoreName:sNL,semaphoreDelay:lZ] as LinkedHashMap
	if(doSerialization){
		retSt=lockOrQueueSemaphore(doSerialization,event,queue,tmpRtD)
		if(bIs(retSt,'exitOut')){
			if(lg!=iZ){
				msg.m='Event queued'
				info msg,tmpRtD
			}
			updateLogs(tmpRtD)
			event=null
			tmpRtD=null
			return
		}
		Long tl= lMs(retSt,'semaphoreDelay')
		if(lg>i2 && tl>lZ)warn 'Piston waited for semaphore '+tl+sMS,tmpRtD
	}
	tmpRtD.lended=wnow()

//measure how Long first state access takes
	Long stAccess; stAccess=lZ
	if(lg>iZ && !myPep){
		if(tmpRtD.stateAccess==null){
			Long stStart,b
			stStart=wnow()
			b=(Long)gtSt(sNSCH)
			List a=(List)gtSt(sSCHS)
			Map pEvt=(Map)gtSt(sLEVT)
			Long stEnd=wnow()
			stAccess=stEnd-stStart
		}else stAccess=lMs(tmpRtD,'stateAccess')
	}

	tmpRtD[sCACHEP]=[:]
	LinkedHashMap r9
	r9=getRunTimeData(tmpRtD,retSt,false,true,true)
	tmpRtD=null
	retSt=null
	checkVersion(r9)

	Long theend=wnow()
	Long le=lMs(r9,'lended')
	Long t0=theend-startTime
	Long t1=le-lMs(r9,'lstarted')
	Long t2=lMs(r9,'generatedIn')
	Long t3=lMs(r9,'pEnd')-lMs(r9,'pStart')
	Long missing=t0-t1-t2
	r9.curStat=[(sI):t0.toInteger(),(sL):t1.toInteger(),(sR):t2.toInteger(),(sP):t3.toInteger(),(sS):stAccess.toInteger()] as LinkedHashMap
	if(lg>i1){
		Long t4=le-startTime
		Long t5=theend-le
		if(lg>i2)debug "RunTime initialize > ${t0} LockT > ${t1}ms > r9T > ${t2}ms > pistonT > ${t3}ms (first state access ${missing} $t4 $t5)".toString(),r9
		String adMsg; adMsg=sBLK
		if(eric())adMsg=" (Init:$t0, Lock: $t1, pistonT $t3 first state access $missing ($t4 $t5) $stAccess".toString()
		trace "Runtime (${"$r9".size()} bytes) initialized in ${t2}ms (${sHVER})".toString()+adMsg,r9
	}
	def aa
	for(String foo in cleanData1)aa=r9.remove(foo)

	resetRandomValues(r9)
	r9.tPause=lZ
	((Map)r9[sSTATS]).timing=[(sT):startTime,(sD):eventDelay>lZ ? eventDelay:lZ,(sL):elapseT(startTime)] as LinkedHashMap

	if(clrC||clrL||clrA){
		if(clrL)clear1(true,true,true,false,true)
		else if(clrA)clear1(true,true,true,true)
			else if(r9[sLEXEC]==null || elapseT(lMs(r9,sLEXEC))>3660000L)clear1(true,false,false,false)
	}else{
		Long eStrt=wnow()
		Map msg2; msg2=null
		if(lg>i1)msg2=timer sEXC,r9,iN1
		Boolean success,firstTime,syncTime
		success=true
		firstTime=true

		//debug
		Long tl=lMs(r9,sNSCH)
		String lsts='lastSchedule'
		if(lMs(r9,lsts)!=tl){
			r9[lsts]=tl
			updateCacheFld(r9,lsts,tl,sT+s1,true)
		}

		if(!(evntName in [sTIME,sASYNCREP])){
			if(lg>i1)trace sEXS,r9,i1
			success=executeEvent(r9,event)
			firstTime=false
		}
		if(evntName==sTIME && !bIs(event,'recovery'))
			chgNextSch(r9,lZ)

		if(!ListAsync)ListAsync=[sHTTPR,sSTOREM,sLIFX,sSENDE,sIFTTM]
		syncTime=true
		Boolean sv_syncTime=syncTime

		List<Map> schedules
		Boolean a
		String mSmaNm=sMs(r9,snId)
		Map sch
		sch=null
		Long sVariance=(Long)getPistonLimits.scheduleVariance
		Long eT=(Long)getPistonLimits.executionTime
		Long schdR=(Long)getPistonLimits.scheduleRemain
		while(success && eT+lMs(r9,sTMSTMP)-wnow()>schdR){
			// if no queued events
			if(!firstTime && serializationOn){
				Boolean inq; inq=false
				getTheLock(mSmaNm,sHNDLEVT)
				List<Map> evtQ=theQueuesVFLD[mSmaNm]
				if(evtQ)inq=true
				releaseTheLock(mSmaNm)
				if(inq){
					if(eric())warn "found pending queued events",null
					break
				}
			}

			schedules=sgetSchedules(sHNDLEVT,myPep)
			if(schedules==null || schedules==(List<Map>)[] || schedules.size()==iZ)break
			Long t=wnow()

			if(evntName==sASYNCREP)
				event.schedule=schedules.sort{ Map it -> lMt(it) }.find{ Map it -> (String)it.r==evntVal }
			else{
				//anything less than scheduleVariance (63ms) in the future is considered due; will do some pause to sync with it
				sch=schedules.sort{ Map it -> lMt(it) }.find{ Map it -> lMt(it)<t+sVariance }

				evntName=sTIME
				evntVal=t.toString()
				event=[(sT):lMt(event),(sDEV):cvtLoc(),(sNM):evntName,(sVAL):t,schedule:sch]
			}

			if(lMs(r9,sNSCH)!=lZ){
				chgNextSch(r9,lZ)
				wunschedule('timeHandler')
			}
			if(event.schedule==null)break
			sch=(Map)event.schedule

			schedules=sgetSchedules(sHNDLEVT+s1,myPep)
			a=schedules.remove(sch)
			updateSchCache(r9,schedules,sHNDLEVT+s1,sX,myPep)

			if(!firstTime){
				r9[sCACHEP]=[:]
				Map<String,Map>sysV=(Map<String,Map>)r9[sSYSVARS]
				sysV[sDLLRINDX].v=null
				sysV[sDLLRDEVICE].v=null
				sysV[sDLLRDEVS].v=null
				sysV[sHTTPCNTN].v=null
				sysV[sHTTPCODE].v=null
				sysV[sHTTPOK].v=null
				sysV[sIFTTTCODE].v=null
				sysV[sIFTTTOK].v=null
				r9[sSYSVARS]=sysV

				event.t=lMt(sch)
			}

			if(isEric(r9))myDetail r9,"async/timer event $event",iN2

			if(evntName==sASYNCREP){
				syncTime=false
				Integer rCode=iMs(event,'responseCode')
				Boolean sOk=rCode>=200 && rCode<=299
				switch(evntVal){
					case sHTTPR:
						Map ee; ee=(Map)sch[sSTACK]
						ee=ee!=null ? ee:[:]
						ee.response=event.responseData
						ee.json=event.jsonData
						((Map)event.schedule)[sSTACK]=ee
						stSysVarVal(r9,sHTTPCNTN,sMs(event,'contentType'))
					case sSTOREM:
						Map m=(Map)event.setRtData
						if(m) for(item in m) r9[(String)item.key]=item.value
					case sLIFX:
					case sSENDE:
						stSysVarVal(r9,sHTTPCODE,rCode)
						stSysVarVal(r9,sHTTPOK,sOk)
						break
					case sIFTTM:
						stSysVarVal(r9,sIFTTTCODE,rCode)
						stSysVarVal(r9,sIFTTTOK,sOk)
						break
					default:
						error "unknown async event "+evntVal,r9
				}
				evntName=sTIME
				event.name= evntName
				event.value= t
				evntVal=t.toString()
			}else{
				String ttyp=(String)sch.r
				if(ttyp in ListAsync){
					error "Timeout Error "+ttyp,r9
					syncTime=false
					Integer rCode=408
					Boolean sOk=false
					switch(ttyp){
						case sHTTPR:
							stSysVarVal(r9,sHTTPCNTN,sBLK)
							if(sch[sSTACK]!=null)((Map)((Map)event.schedule)[sSTACK]).response=null
						case sSTOREM:
							stSysVarVal(r9,sHTTPCODE,rCode)
							stSysVarVal(r9,sHTTPOK,sOk)
							break
						case sLIFX:
						case sSENDE:
							break
						case sIFTTM:
							stSysVarVal(r9,sIFTTTCODE,rCode)
							stSysVarVal(r9,sIFTTTOK,sOk)
							break
					}
				}
			}

			if(syncTime && strictSync){
				Long delay; delay=Math.round(lMt(sch)-d1*wnow())
				if(delay>lZ){
					delay=delay<sVariance ? delay:sVariance
					doPause("Synchronizing scheduled event, waiting for ${delay}ms".toString(),delay,r9,true)
				}
			}
			if(lg>i1 && firstTime){
				msg2=timer sEXC,r9,iN1
				trace sEXS,r9,i1
			}
			success=executeEvent(r9,event)
			firstTime=false
			syncTime=sv_syncTime
		}

		((Map)((Map)r9[sSTATS]).timing).e=elapseT(eStrt)
		if(lg>i1)trace msg2,r9
		if(!success)msg.m='Event processing failed'
		if(eric()&& lg>i1){
			String s; s=(String)msg.m
			s+=' Total pauses ms: '+lMs(r9,'tPause').toString()
			if(firstTime)s+=' found nothing to do'
			msg.m=s
		}
		finalizeEvent(r9,msg,success)

		if(bIs(r9,'logPExec')) sendLEvt(r9)
	}

	String mSmaNm=sMs(r9,'semaphoreName')
	Long lS=lMs(r9,'semaphore')

	event=null
	r9=null

// any queued events?
	String msgt; msgt=sNL
	if(lg>i2)msgt='Exiting'

	while(doSerialization && mSmaNm!=sNL){
		getTheLock(mSmaNm,sHNDLEVT+s2)
		List<Map> evtQ
		evtQ=theQueuesVFLD[mSmaNm]
		if(!evtQ){
			if(theSemaphoresVFLD[mSmaNm]<=lS){
				if(lg>i2)msgt='Released Lock and exiting'
				theSemaphoresVFLD[mSmaNm]=lZ
				theSemaphoresVFLD=theSemaphoresVFLD
			}
			releaseTheLock(mSmaNm)
			break
		}else{
			Map theEvent
			evtQ=theQueuesVFLD[mSmaNm]
			List<Map>evtList=evtQ.sort{ Map it -> lMt(it) }
			theEvent=evtList.remove(0)
			Integer qsize=evtList.size()
			theQueuesVFLD[mSmaNm]=evtList
			theQueuesVFLD=theQueuesVFLD
			releaseTheLock(mSmaNm)

			if(qsize>i8)error "large queue size ${qsize}".toString(),null
			theEvent.date=new Date(lMt(theEvent))
			handleEvents(theEvent,false,true)
		}
	}
	if(lg>i2)debug msgt,null
}

@CompileStatic
void chgNextSch(Map r9,Long v){
	((Map)r9[sSTATS])[sNSCH]=v
	r9[sNSCH]=v
	assignSt(sNSCH,v)
	updateCacheFld(r9,sNSCH,v,sT+s2,true)
}

/* wrappers */
private void sendLEvt(Map r9){
	Map rtCE=(Map)r9[sCUREVT]
	if(rtCE!=null){
		String s=gtAppN()
		String desc='webCoRE piston \''+s+'\' was executed'
		Map t0=(Map)r9[sST]
		sendLocationEvent(
			(sNM):'webCoRE',(sVAL):'pistonExecuted',isStateChange:true,displayed:false,linkText:desc,descriptionText:desc,
			data:[
				(sID):r9[sID],
				(sNM):s,
				(sEVENT):[(sDATE):new Date(lMt(rtCE)),delay:lMs(rtCE,'delay'),(sDURATION):elapseT(lMt(rtCE)),(sDEV):"${getDevice(r9,(String)rtCE[sDEV])}".toString(),(sNM):(String)rtCE[sNM],(sVAL):rtCE[sVAL],physical:bIs(rtCE,'physical'),index:iMs(rtCE,'index')],
				(sST):[(sOLD):sMs(t0,sOLD),(sNEW):sMs(t0,sNEW)]
			]
		)
	}
}

@Field static final List<String>cleanData1=['lstarted','lended','generatedIn','pStart','pEnd']
@Field static final List<String>cleanData2=['recovery','contentType','responseData','responseCode','setRtData']
@Field static final List<String>cleanData3=['schedule','jsonData']

@Field static List<String> ListAsync=[]

@CompileStatic
private Boolean executeEvent(Map r9,Map event){
	String myS; myS=sNL
	String evntName=(String)event[sNM]
	if(isEric(r9)){
		myS='executeEvent '+evntName+sSPC+event[sVAL].toString()
		myDetail r9,myS,i1
	}
	Boolean res,ended; res=false; ended=false
	Boolean lg=isDbg(r9)
	try{
		// see fixEvt for description of event
		Integer index; index=iZ //event?.index ?: iZ
		if(event.jsonData!=null){
			Map attribute=Attributes()[evntName]
			String attrI=attribute!=null ? sMs(attribute,sI):sNL
			if(attrI!=sNL && event.jsonData[attrI]) // .i is the attribute to lookup
				index=((String)((Map)event.jsonData)[attrI]).toInteger()
			if(!index)index=i1
		}

		def targs
		targs=event.jsonData!=null ? event.jsonData:[:]

		Map srcEvent; srcEvent=null
		r9.json=[:]
		r9.response=[:]

		Map es=(Map)event?.schedule
		if(es!=null && evntName==sTIME){
			targs=es.args!=null && es.args instanceof Map ? (Map)es.args:targs
			srcEvent=(Map)es.evt
			Map tMap=(Map)es[sSTACK]
			if(tMap!=null){
				Map<String,Map>sysV=(Map<String,Map>)r9[sSYSVARS]
				sysV[sDLLRINDX].v=tMap.index ?:null
				sysV[sDLLRDEVICE].v=tMap[sDEV] ?:null
				sysV[sDLLRDEVS].v=tMap[sDEVS] ?:[]
				r9[sSYSVARS]=sysV
				r9.json=tMap.json ?: [:]
				r9.response=tMap.response ?: [:]
				index=(Integer)srcEvent?.index ?: iZ
// more to restore here?
			}
		}
		stSysVarVal(r9,sDARGS,targs)

		def theDevice1=event[sDEV] ? event[sDEV]:null
		String a=sMs(r9,sLOCID)
		String theFinalDevice=theDevice1!=null ? (!isDeviceLocation(event[sDEV]) ? hashD(r9,theDevice1):a):a

		def aa
		for(String foo in cleanData2)aa=event.remove(foo)
		aa=event.remove('jsonData')
		def sv=event[sDEV]
		event[sDEV]=theFinalDevice // device from here on is a hashed string
		r9[sEVENT]=event

		Map mEvt,pEvt
		mEvt=[:]+event
		Long d1= (Long)((Map)((Map)r9[sSTATS])?.timing)?.d
		mEvt.delay=d1 ?: lZ
		mEvt.index=index
		mEvt.dev=cvtDev(getDevice(r9,theFinalDevice)) // documentation
		mEvt.isResume=false

		for(String foo in cleanData3)aa=mEvt.remove(foo)

		if(srcEvent!=null){
			mEvt[sNM]=(String)srcEvent[sNM]
			mEvt[sVAL]=srcEvent[sVAL]
			mEvt[sDEV]=srcEvent[sDEV]
			mEvt.dev=srcEvent.dev
			mEvt.descriptionText=(String)srcEvent.descriptionText
			mEvt.unit=srcEvent.unit
			mEvt.index=srcEvent.index
			mEvt.physical=!!srcEvent.physical
			mEvt.isResume=true
		}else {
			String pNm=sMs(r9,snId)
			readDataFLD[pNm]=sNL; readTmpFLD[pNm]=sNL
			fuelDataFLD[pNm]=[]
		}
		pEvt=(Map)gtSt(sLEVT)
		if(pEvt==null)pEvt=[:]
		r9.previousEvent=pEvt

		mEvt=cleanEvt(mEvt)
		r9[sCUREVT]=mEvt
		assignSt(sLEVT,mEvt)

		Integer s=es ? iMs(es,'svs'):null
		if(s)((Map)event.schedule).s=s // dealing with pause/wait before happens_daily_at r9.wakingUp

		r9[sCNDTNSTC]=false
		r9[sPSTNSTC]=false
		chgRun(r9,iZ)
		r9[sSTMTL]=iZ
		r9[sBREAK]=false
		r9.resumed=false
		r9.terminated=false
		r9.followCleanup=[]
		if(evntName==sTIME)chgRun(r9,iMs(es,sI)) // IN1, IN3, IN5, or stmt #

		if(isEric(r9)){
			myDetail r9,sCUREVT+" $mEvt",iN2
			myDetail r9,"event ${r9[sEVENT]}",iN2
			myDetail r9,"currun is ${currun(r9)}",iN2
		}

		r9[sSTACK]=[(sC):iZ,(sS):iZ,cs:[],ss:[]] as LinkedHashMap
		try{
			Map pis=(Map)r9[sPIS]
			Boolean allowed=!pis.r || ((List)pis.r).size()==iZ || evaluateConditions(r9,pis,sR,true)
			Boolean restr=!gtPOpt(r9,'aps') && !allowed //allowPreScheduled tasks to execute during restrictions
			r9.restricted=restr

			if(allowed || currun(r9) in [iN3,iN5,iN9] ) { //if(allowed || ffwd(r9)){   // allow save runs, no iN1 every block, ffwds >1 (waits)?
				if(currun(r9) in [iN3,iN5]){
					if(currun(r9)==iN3){
						//device related time schedules
						Map data=(Map)es.d
						if(data!=null){
							String d=sMs(data,sD)
							String c=sMs(data,sC)
							if(d && c){
								//we have a device schedule, execute it
								def device=getDevice(r9,d)
								if(device!=null){
									if(!restr || bIs(data,'ig')){ // ignore restrictions
										//executing scheduled physical command
										//used by command execution delay, fades, flashes,etc.
										Boolean dco=data.dc!=null ? bIs(data,'dc'):true
										r9.curTsk=[$:data.task] as LinkedHashMap
										executePhysicalCommand(r9,device,c,data.p,lZ,sNL,dco,false,false)
										r9.remove('curTsk')
									}else
										if(lg)debug 'Piston device timer execution aborted due to restrictions in effect',r9
								}
							}
						}
					}else{ // iN5
						if(!restr){
							Map jq=(Map)es.jq
							if(jq!=null){
								Map statement=[$:jq.$] + (jq[sTCP] ? [(sTCP):jq[sTCP]] : [:])
								Map task=[$:jq.task]
								r9.curActn=statement
								r9.curTsk=task
								def ta=((Map)r9[sSTACK]).cs
								((Map)r9[sSTACK]).cs=jq.cs
								runRepeat(r9,jq)
								((Map)r9[sSTACK]).cs=ta
								r9.remove('curTsk')
								r9.remove('curActn')
							}
						}else
							if(lg)debug 'Piston repeat task timer execution aborted due to restrictions in effect',r9
					}
				}else{
					if(executeStatements(r9,(List)pis.s))
						ended=true
					List<List<Map>> fc=(List<List<Map>>)r9.followCleanup
					if(fc){
						for(List<Map> ttcndtns in fc)
							runFBupdates(r9,iZ,ttcndtns.size(),ttcndtns,false)
					}
					r9.remove('followCleanup')
				}
			}else{
				if(lg)debug 'Piston execution aborted due to restrictions in effect; updating piston states',r9
				//run through all to update stuff
				r9[sCACHE]=[:] // reset followed by
				assignSt(sCACHE,[:])
				updateCacheFld(r9,sCACHE,[:],myS,true)
				chgRun(r9,iN9)
				Boolean ya=executeStatements(r9,(List)pis.s)
				ended=true
			}
			res=true
		}catch(Exception all){
			error 'An error occurred while executing event:',r9,iN2,all
		}
	}catch(Exception all){
		error 'An error occurred within executeEvent:',r9,iN2,all
	}
	if(ended && res)tracePoint(r9,sEND,lZ,iZ)
	else tracePoint(r9,sBREAK,lZ,iZ)
	processSchedules r9
	if(isEric(r9))myDetail r9,myS+" Result:${res}"
	return res
}

@Field static List<String> cleanData
private static List<String> fill_cleanData() {
	return ['allDevices', sCACHEP, 'mem', sBREAK, 'powerSource', 'oldLocations', 'incidents', 'semaphoreDelay', sVARS,
			'stateAccess', sATHR, sBIN, sBLD, sNWCACHE, 'mediaData', 'weather', sLOGS, sTRC, sSYSVARS, 'localVars', 'previousEvent', 'json', 'response',
			sCACHE, sSTORE, 'settings', 'locationModeId', 'coreVersion', 'hcoreVersion', sCNCLATNS, sCNDTNSTC, sPSTNSTC, sFFT, sRUN,
			'resumed', 'terminated', 'instanceId', 'wakingUp', sSTMTL, 'args', 'nfl', 'temp']
}

@Field static final String sFINLZ='finalize'
@CompileStatic
private void finalizeEvent(Map r9,Map iMsg,Boolean success=true){
	Long startTime=wnow()
	Boolean myPep=isPep(r9)

	processSchedules(r9,true)
//	Long el1=elapseT(startTime)

	if(success){
		if(isInf(r9))info iMsg,r9
	}else error iMsg,r9

	updateLogs(r9,lMs(r9,sTMSTMP))
//	Long el2=elapseT(startTime)

	((Map)r9[sTRC]).d=elapseT(lMt((Map)r9[sTRC]))

	//save / update changed cache values
	for(item in (Map<String,Map>)r9[sNWCACHE]) ((Map)r9[sCACHE])[(String)item.key]=item.value

//	Long el3=elapseT(startTime)
	//overwrite state might have changed meanwhile
	Map t0; t0=getCachedMaps(sFINLZ)
	String myId=sMs(r9,snId)
	String mSmaNm=myId
	def aa
	if(t0!=null){
		getTheLock(mSmaNm,sFINLZ)
		Map nc=theCacheVFLD[myId]
		if(nc){
			nc[sLSTPCQ]=r9[sLSTPCQ]
			nc[sLSTPCSNT]=r9[sLSTPCSNT]
			if(nc[sLSTPCQ]==null)aa=nc.remove(sLSTPCQ)
			if(nc[sLSTPCSNT]==null)aa=nc.remove(sLSTPCSNT)
			// store across runs
			nc[sCACHE]=[:]+(Map)r9[sCACHE]
			nc[sSTORE]=[:]+(Map)r9[sSTORE]
			nc[sST]=[:]+(Map)r9[sST]
			nc[sTRC]=[:]+(Map)r9[sTRC]
			theCacheVFLD[myId]=nc
			theCacheVFLD=theCacheVFLD
		}
		releaseTheLock(mSmaNm)
	}
	if(myPep){
		assignAS(sCACHE,(Map)r9[sCACHE])
		assignAS(sSTORE,(Map)r9[sSTORE])
		assignAS(sST,[:]+(Map)r9[sST])
		assignAS(sTRC,(Map)r9[sTRC])
	}else{
		assignSt(sCACHE,(Map)r9[sCACHE])
		assignSt(sSTORE,(Map)r9[sSTORE])
		assignSt(sST,[:]+(Map)r9[sST])
		assignSt(sTRC,(Map)r9[sTRC])
	}

//	Long el4=elapseT(startTime)
//remove large stuff
	if(!cleanData) cleanData=fill_cleanData()
	for(String foo in cleanData)aa=r9.remove(foo)

	if(bIs(r9,'updateDevices'))updateDeviceList(r9)
	r9[sDEVS]=[:]

//	Long el5=elapseT(startTime)
	Boolean a
	String s; s='gvCache'
	if(r9[s]!=null || r9.gvStoreCache!=null){
		LinkedHashMap tpiston
		tpiston=(LinkedHashMap)r9[sPIS]
		r9[sPIS]=[:]
		((Map)r9[sPIS]).z=(String)tpiston.z
		tpiston=null
		if(r9[s]!=null){
			String semName=sTGBL
			String wName=sMs(r9,spId)
			getTheLock(semName,sFINLZ)
			for(var in (Map<String,Map>)r9[s]){
				Map vars=globalVarsVFLD[wName]
				String varName=(String)var.key
				if(varName && varName.startsWith(sAT) && vars[varName] && var.value.v!=vars[varName].v){
					globalVarsVFLD[wName][varName].v=var.value.v
					globalVarsVFLD=globalVarsVFLD
				}
			}
			releaseTheLock(semName)
		}
		relaypCall(r9)
		aa=r9.remove(s)
		aa=r9.remove('gvStoreCache')
		aa=r9.remove('globalStore')
		r9.initGStore=false
	}else{
		// update Dashboard
		Map myRt=shortRtd(r9)
		relaypCall(myRt)
	}
//	Long el6=elapseT(startTime)
	r9[sPIS]=null

	((Map)((Map)r9[sSTATS]).timing).u=elapseT(startTime)
//	log.error "el1: $el1  el2: $el2  el3: $el3 el4: $el4 el5: $el5 el6: $el6"

//update graph data
	Map stats
	s=sSTATS
	if(myPep)stats=(Map)gtAS(s)
	else stats=(Map)gtSt(s)
	stats=stats ?: [:]

	List<Map> tlist=(List<Map>)stats.timing ?: []
	Map lastST=tlist.size() ? [:]+tlist.last():null
	Map newMap=[:]+(Map)((Map)r9.stats).timing
	if(lastST && newMap){
		lastST.t=lMt(newMap)-10L
		a=tlist.push(lastST)
	}
	a=tlist.push(newMap)
	Integer t1,t2
	t1=iMs(r9,sMSTATS)
	t2=tlist.size()
	if(t2>t1)tlist=tlist[t2-t1..t2-i1]

	stats.timing=tlist
	if(myPep)assignAS(s,stats)
	else assignSt(s,stats)
	((Map)r9[s]).timing=null

	t0=getCachedMaps(sFINLZ+s1)
	if(t0!=null){

		s=sFINLZ+s1
		getTheLock(mSmaNm,s)
		Map nc=theCacheVFLD[myId]
		releaseTheLock(mSmaNm)
		if(nc){
			t1=i20
			List hisList
			hisList=(List)nc.runTimeHis
			Boolean b=hisList.push( elapseT(lMs(r9,sTMSTMP)).toInteger() )
			t2=hisList.size()
			if(t2>t1)hisList=hisList[t2-t1..t2-i1]
			updateCacheFld(r9,'runTimeHis',hisList,s,false)
		}
		updateCacheFld(r9,'mem',mem(),s,false)
		updateCacheFld(r9,'runStats',[:]+(Map)r9.curStat,s,false)
	}
}

/** Returns active schedules  */
@CompileStatic
private static List<Map> sgtSch(Map r9){ return (List<Map>)r9[sSCHS] }

/** Add to active schedules */
@CompileStatic
private static Boolean spshSch(Map r9,Map sch){ return ((List<Map>)r9[sSCHS]).push(sch) }

/** Returns saved schedules  */
private List<Map> sgetSchedules(String t,Boolean myPep){
	List<Map> schedules
	Map t0=getCachedMaps(t)
	if(t0!=null)schedules=(List<Map>)[]+(List<Map>)t0[sSCHS]
	else schedules=myPep ? (List<Map>)gtAS(sSCHS):(List<Map>)gtSt(sSCHS)
	return schedules
}

/** updates saved schedules  */
private void updateSchCache(Map r9,List<Map> schedules,String t,String lt,Boolean myPep){
	if(myPep)assignAS(sSCHS,schedules)
	else assignSt(sSCHS,(List<Map>)[]+schedules)

	updateCacheFld(r9,sSCHS,[]+schedules,t+lt,true)
}

private static LinkedHashMap initCncl(){ return [statements:[],conditions:[],all:false] as LinkedHashMap }

@Field static final String sPROCS='processSchedules'
@CompileStatic
private void processSchedules(Map r9,Boolean scheduleJob=false){
	//if automatic piston states set it based on the autoNew - if any
	if(!gtPOpt(r9,'mps'))((Map)r9[sST])[sNEW]=(String)((Map)r9[sST]).autoNew ?: sTRUE

	Boolean myPep=isPep(r9)
	List<Map> schedules,ts
	schedules=sgetSchedules(sPROCS,myPep)
	ts=[]+schedules
	Boolean lg=isDbg(r9)

	Boolean a
	if(ts){
		Map cncls=(Map)r9[sCNCLATNS]
		if(bIs(cncls,'all')){
			//cancel all statement and any other pending -3,-5 events (device schedules); does not cancel EVERY blocks -1 iN1 or $:0 condition requests
			if(ts)
				if(lg)whatCnclsA(r9)
				for(Map sch in ts){
					Integer i=iMs(sch,sI)
					if(i>iZ || i in [iN3,iN5]) a=schedules.remove(sch)
				}
			r9[sLSTPCQ]=null
			r9[sLSTPCSNT]=null
			ts=[]+schedules
		}

		//cancel statements
		if(ts){
			a=schedules.removeAll{ Map schedule ->
				!!((List<Map>)cncls.statements).find{ Map cancelation ->
					iMs(cancelation,'id')==iMs(schedule,'s') && (!cancelation.data || sMs(cancelation,'data')==sMs(schedule,'d'))
				}
			}
			ts=[]+schedules

			//cancel on conditions
			if(ts){
				for(Integer cid in (List<Integer>)cncls.conditions){
					ts=[]+schedules
					if(ts)
						for(Map sch in ts)
							if(cid in (List)sch.cs) a=schedules.remove(sch)
				}
				//cancel on piston state change
				ts=[]+schedules
				if(ts && bIs(r9,sPSTNSTC)){
					if(lg){
						updateSchCache(r9,schedules,sPROCS+s0,sT,myPep)
						whatCnclsP(r9)
					}
					for(Map sch in ts)
						if(iMs(sch,'ps')!=iZ)a=schedules.remove(sch)
				}
			}
		}
	}

	r9[sCNCLATNS]=initCncl()

	schedules=(schedules+sgtSch(r9))//.sort{ lMt(it) }
	updateSchCache(r9,schedules,sPROCS+s1,sT,myPep)

	if(scheduleJob){
		Long nextT,t
		nextT=lZ
		Integer ssz=schedules.size()
		if(ssz>iZ){
			Map tnext=schedules.sort{ Map it -> lMt(it) }[iZ]
			nextT=lMt(tnext)
			t=(nextT-wnow())+30L
			Long sVariance=(Long)getPistonLimits.scheduleVariance
			t=(t<sVariance ? sVariance:t)
			wrunInMillis(t,'timeHandler',[data: tnext])
			if(isInf(r9))info 'Setting up scheduled job for '+formatLocalTime(nextT)+' (in '+t.toString()+'ms)'+(ssz>i1 ? ',with '+(ssz-i1).toString()+' more job'+(ssz>i2 ? sS:sBLK)+' pending':sBLK),r9
		}
		if(nextT==lZ){
			if(lMs(r9,sNSCH)!=lZ)wunschedule('timeHandler')
			String pNm=sMs(r9,snId)
			readDataFLD[pNm]=sNL; readTmpFLD[pNm]=sNL
			fuelDataFLD[pNm]=[]
		}
		chgNextSch(r9,nextT)
	}
	r9[sSCHS]=[]
}

@Field static final String sUPDL='updateLogs'
@CompileStatic
private void updateLogs(Map r9,Long lastExecute=null){
	if(!r9)return

	String id=sMs(r9,snId)
	String mSmaNm=id

	Map cacheMap=getCachedMaps(sUPDL)
	getTheLock(mSmaNm,sE)

	Map nc=theCacheVFLD[id]

	if(lastExecute!=null){
		assignSt(sLEXEC,lastExecute)
		if(nc && cacheMap!=null){
			nc[sLEXEC]=lastExecute
			nc['temp']=[:]+(Map)r9.temp
			nc[sCACHEP]=[:]+(Map)r9[sCACHEP]
			theCacheVFLD[id]=nc
			theCacheVFLD=theCacheVFLD
		}
	}

	String s=sLOGS
	if(r9[s] && ((List)r9[s]).size()>i1){
		Boolean myPep=isPep(r9)
		Integer lim; lim=iMs(r9,sMLOGS)

		List t0
		if(cacheMap!=null)t0=[]+(List)cacheMap[s]
		else t0=myPep ? (List)gtAS(s):(List)gtSt(s)
		List logs
		logs=[]+(List)r9[s]+t0
		if(lim>=iZ){
			Integer lsz; lsz=logs.size()
			if(lim==iZ || lsz==iZ)logs=[]
			else{
				if(lim< lsz-i1){
					logs=logs[iZ..lim]
					lsz=logs.size()
				}
				if(lsz>i50){
					assignSt(s,logs) //	this mixes state and AS
					if(gtState().toString().size()>75000){
						lim-=Math.min(i50,(lim/d2).toInteger())
						logs=logs[iZ..lim]
					}
				}
			}
		}
		if(myPep)assignAS(s,logs)
		else assignSt(s,logs)
		if(nc && cacheMap!=null){
			nc[s]=logs
			theCacheVFLD[id]=nc
			theCacheVFLD=theCacheVFLD
		}
	}
	r9[s]=[]
	releaseTheLock(mSmaNm)
}

@CompileStatic
private Boolean executeStatements(Map r9,List<Map>statements,Boolean async=false){
	if(statements==null)return true
	Integer t=iMs(r9,sSTMTL)
	r9[sSTMTL]=t+i1
	for(Map statement in statements){
		//only execute statements that are enabled
		Boolean disab=statement[sDI]!=null && bIs(statement,sDI)
		if(!disab && !executeStatement(r9,statement,async)){
			//stop processing
			r9[sSTMTL]=t
			return false
		}
	}
	//continue processing
	r9[sSTMTL]=t
	return true
}

@Field static List<String> ls0=[]
@Field static List<String> ls1=[]

@CompileStatic
private static Boolean bIs(Map m,String v){ (Boolean)m[v] }

@CompileStatic
private static Boolean isPep(Map m){ bIs(m,sPEP) }
@CompileStatic
private static Boolean isAct(Map m){ bIs(m,sACT) }
@CompileStatic
private static Boolean isEnbl(Map m){ bIs(m,'enabled') }
@CompileStatic
private static Boolean isBrk(Map m){ bIs(m,sBREAK) }

@CompileStatic
private static String sLs(List l,Integer v){ (String)l[v] }

@CompileStatic
private static String sMs(Map m,String v){ (String)m[v] }

/** m.a  */
@CompileStatic
private static String sMa(Map m){ sMs(m,sA) }

/** m.t  */
@CompileStatic
private static String sMt(Map m){ sMs(m,sT) }

/** m.vt  */
@CompileStatic
private static String sMvt(Map m){ sMs(m,sVT) }

/** m.t  */
@CompileStatic
private static Long lMt(Map m){ (Long)m[sT] }
@CompileStatic
private static Long lMs(Map m,String v){ (Long)m[v] }
@CompileStatic
private static Integer iMs(Map m,String v){ (Integer)m[v] }
@CompileStatic
private static Boolean isDbg(Map r9){ iMs(r9,sLOGNG)>i2 }
@CompileStatic
private static Boolean isTrc(Map r9){ iMs(r9,sLOGNG)>i1 }
@CompileStatic
private static Boolean isInf(Map r9){ iMs(r9,sLOGNG)>iZ }

@Field static final String sRUN='running'
@Field static final String sFFT='ffTo'
@CompileStatic
private static Boolean prun(Map r9){ bIs(r9,sRUN) }
@CompileStatic
private static Boolean ffwd(Map r9){ !prun(r9) }
@CompileStatic
private static Integer currun(Map r9){ (Integer)r9[sFFT] }
@CompileStatic
private static void chgRun(Map r9,Integer num){
	r9[sFFT]=num
	r9[sRUN]=num==iZ
}

@CompileStatic
private static Integer gtPOpt(Map r9,String nm){
	Map o=(Map)((Map)r9[sPIS])?.o
	if(o && o.containsKey(nm))return iMs(o,nm)
	return null
}

@CompileStatic
private static Integer stmtNum(Map stmt){ return stmt?.$!=null ? iMs(stmt,sDLR):iZ }

@Field static final String sEXST='executeStatement '

@CompileStatic
private static Integer pushStk(Map r9, Integer stmtNm, Boolean stacked){
	Map stk=(Map)r9[sSTACK]
	Boolean a; a=((List<Integer>)stk.ss).push(iMs(stk,sS))
	stk.s=stmtNm
	Integer c=iMs(stk,sC)
	if(stacked)a=((List<Integer>)stk.cs).push(c)
	r9[sSTACK]=stk
	return c
}

@CompileStatic
private static void popStk(Map r9, Integer v, Boolean stacked){
	Map stk=(Map)r9[sSTACK]
	stk.c=v
	if(stacked) Integer tc=((List<Integer>)stk.cs).pop()
	stk.s=(Integer)((List<Integer>)stk.ss).pop()
	r9[sSTACK]=stk
}

@CompileStatic
private Boolean executeStatement(Map r9,Map statement,Boolean asynch=false){
	//if r9.ffTo is a positive non-zero number, we need to fast forward through all branches
	//until we locate statement with a matching id, then we continue
	if(statement==null)return false
	Boolean lg=isDbg(r9)
	Integer stmtNm=stmtNum(statement)
	Boolean cchg=bIs(r9,sCNDTNSTC)
	// Task Execution Policy - only execute on: ""- always (def), c-condition state change, p- piston state change, b-condition or piston change
	String tep=sMs(statement,'tep')
	if(tep && prun(r9)){
		String s="Skipping execution for statement #${stmtNm} because "
		Boolean pchg=bIs(r9,sPSTNSTC)
		switch(tep){
			case sC:
				if(!cchg){
					if(lg)debug s+'condition state did not change',r9
					return true
				}
				break
			case sP:
				if(!pchg){
					if(lg)debug s+'piston state did not change',r9
					return true
				}
				break
			case sB:
				if(!cchg && !pchg){
					if(lg)debug s+'neither condition state nor piston state changed',r9
					return true
				}
				break
		}
	}
	String stateType=sMt(statement)
	String mySt; mySt=sNL
	if(isEric(r9)){
		mySt=sEXST+("#${stmtNm} "+sffwdng(r9)+stateType+sSPC+"async: $asynch").toString()
		myDetail r9,mySt,i1
	}
	Long t=wnow()

	Boolean stacked=true /* cancelable on condition change */
	Integer c=pushStk(r9,stmtNm,stacked)

	Boolean svCSC=cchg
	Boolean value; value=true
	Map<String,Map>sysV=(Map<String,Map>)r9[sSYSVARS]
	Double svIndex=(Double)sysV[sDLLRINDX].v
	List svDevice=(List)sysV[sDLLRDEVICE].v

	if(!ls0){ ls0=[sEVERY,sON]; if(!ls1)ls1=[sWHILE,sREPEAT,sFOR,sEACH] }

	Boolean selfAsync=sMa(statement)==s1 || stateType in ls0 // execution method (async)
	Boolean async=asynch||selfAsync

	Boolean myPep=isPep(r9)
	Boolean allowed=!(List)statement.r || ((List)statement.r).size()==iZ || evaluateConditions(r9,statement,sR,async)
	if(allowed || ffwd(r9)){
		String evntName; evntName=sMs((Map)r9[sEVENT],sNM)
		Boolean perform,repeat,isIf,isEach
		perform=false
		repeat=true
		isIf=false
		isEach=false
		Double index; index=null
		while(repeat){
			switch(stateType){
				case sACTION:
					value=executeAction(r9,statement,async)
					break
				case sIF:
					isIf=true
				case sWHILE:
					//check condition for if and while
					perform=evaluateConditions(r9,statement,sC,async)
					//override current condition so child statements can cancel on it
					((Map)r9[sSTACK]).c=stmtNm
					if(isIf && prun(r9) && !gtPOpt(r9,'mps') && iMs(r9,sSTMTL)==i1){
						//automatic piston state
						((Map)r9[sST]).autoNew= perform ? sTRUE:sFALSE
					}
					if(perform || ffwd(r9)){
						if(!executeStatements(r9,(List)statement.s,async)){
							//stop processing
							value=false
							if(prun(r9))break
						}
						value=true
						if(prun(r9))break
					}
					if((!perform || ffwd(r9)) && isIf){
						//look for else-if
						if(statement.ei){
							Integer tstmtNm=stmtNum((Map)((List<Map>)statement.ei)[iZ])
							String mySt1
							mySt1=sNL
							if(isEric(r9)){
								mySt1=sEXST+("#${tstmtNm} "+sffwdng(r9)+'elseif'+sSPC+"async: $async").toString()
								myDetail r9,mySt1,i1
							}

							Integer cc=pushStk(r9,tstmtNm,stacked)
							Integer st=iMs(r9,sSTMTL)
							r9[sSTMTL]=st+i1
							Boolean cchg1=bIs(r9,sCNDTNSTC)

							for(Map elseIf in (List<Map>)statement.ei){
								perform=evaluateConditions(r9,elseIf,sC,async)
								//override current condition so child statements can cancel on it
								((Map)r9[sSTACK]).c=tstmtNm
								if(perform || ffwd(r9)){
									if(!executeStatements(r9,(List)elseIf.s,async)){
										//stop processing
										value=false
										if(prun(r9))break
									}
									value=true
									if(prun(r9))break
								}
							}

							r9[sCNDTNSTC]=cchg1
							r9[sSTMTL]=st
							popStk(r9,cc,stacked)

							Boolean ret=value || ffwd(r9)
							if(isEric(r9))myDetail r9,mySt1+" result:"+ret.toString()
						}
						//look for else
						if((!perform || ffwd(r9)) && !executeStatements(r9,(List)statement.e,async)){
							//stop processing
							value=false
							if(prun(r9))break
						}
					}
					break
				case sEVERY:
					//only execute the every if i=-1 (for rapid timers with large restrictions i.e. every second, but only on Mondays)
					Map es=(Map)((Map)r9[sEVENT])?.schedule
					Boolean ownEvent=evntName==sTIME && es!=null && iMs(es,sS)==stmtNm && iMs(es,sI)==iN1
					if(ownEvent)chgRun(r9,iZ)

					List<Map> schedules=sgetSchedules(sEXST,myPep)
					if(ownEvent || !schedules.find{ Map it -> iMs(it,'s')==stmtNm && iMs(it,'i')==-1 }){ //iN1 compiler bug
						//ensure every timer is scheduled
						scheduleTimer(r9,statement,ownEvent ? lMt(es):lZ)
					}
					if(ffwd(r9) || ownEvent){
						Boolean canR=ownEvent && allowed && !bIs(r9,'restricted') // honor restrictions
						if(ffwd(r9) || canR){
							//override current condition so child statements can cancel on it
							((Map)r9[sSTACK]).c=stmtNm
							// note we can end ffwding in the timer block on scheduled task
							Boolean a=executeStatements(r9,(List)statement.s,async)
						}else if(ownEvent && !canR && lg)debug 'Piston Every timer execution aborted due to restrictions in effect',r9
						//if we wanted to / ran any timer block statements, exit
						if(prun(r9) || ownEvent){
							r9.terminated=true
							if(lg)debug "Exiting piston at end of Every timer block",r9
						}
						value=false
						break
					}
					value=true
					break
				case sREPEAT:
					//override current condition so child statements can cancel on it
					((Map)r9[sSTACK]).c=stmtNm
					if(!executeStatements(r9,(List)statement.s,async)){
						//stop processing
						value=false
						if(prun(r9))break
					}
					value=true
					perform=!evaluateConditions(r9,statement,sC,async)
					break
				case sON:
					perform=false
					if(prun(r9)){
						//look to see if any of the events match
						Map ce=(Map)r9[sCUREVT]
						String deviceId= sMs(ce,sDEV) ?: sNL
						evntName=sMs(ce,sNM)
						for(Map event in (List<Map>)statement.c){
							Map operand=(Map)event?.lo
							if(operand!=null && sMt(operand)){
								switch(sMt(operand)){
									case sP:
										if(deviceId!=sNL && evntName==sMa(operand) && (List)operand.d!=[] && deviceId in expandDeviceList(r9,(List)operand.d,true))perform=true
										break
									case sV:
										if(evntName==sMs(operand,sV))perform=true
										break
									case sX:
										String operX=sMs(operand,sX)
										if(ce[sVAL]==operX && evntName==sMs(r9,'instanceId')+sDOT+operX)perform=true
										break
								}
							}
							if(perform)break
						}
					}
					value=ffwd(r9) || perform ? executeStatements(r9,(List)statement.s,async):true
					break
				case sEACH:
					isEach=true
				case sFOR:
					Double startValue,endValue,stepValue
					startValue=dZ
					stepValue=d1
					List devices; devices=[]
					Integer dsiz; dsiz=devices.size()
					if(isEach){
						List t0=(List)((Map)evaluateOperand(r9,null,(Map)statement.lo)).v
						devices=t0 ?: []
						dsiz=devices.size()
						endValue=dsiz-d1
					}else{
						startValue=evalDecimalOperand(r9,(Map)statement.lo)
						endValue=evalDecimalOperand(r9,(Map)statement.lo2)
						Double t0=evalDecimalOperand(r9,(Map)statement.lo3)
						stepValue=t0 ?: d1
					}
					String ts=sMs(statement,sX)
					String cntrVar=ts && sMt(getVariable(r9,ts))!=sERROR ? ts:sNL
					String sidx='f:'+stmtNm.toString()
					if( (startValue<=endValue && stepValue>dZ) || (startValue>=endValue && stepValue<dZ) || ffwd(r9)){
						//initialize the for loop
						if(ffwd(r9))index=dcast(r9,((Map)r9[sCACHE])[sidx])
						if(index==null){
							index=dcast(r9,startValue)
							//index=startValue
							((Map)r9[sCACHE])[sidx]=index
						}
						stSysVarVal(r9,sDLLRINDX,index)
						List dvc; dvc=[]
						if(isEach){
							dvc= index<dsiz ? [devices[index.toInteger()]]:[]
							if(currun(r9) in [iZ,iN9])stSysVarVal(r9,sDLLRDEVICE,dvc)
						}
						if(cntrVar!=sNL && prun(r9))Map m=setVariable(r9,cntrVar,isEach ? dvc:index)
						//do the loop
						perform=executeStatements(r9,(List)statement.s,async)
						if(!perform){
							//stop processing
							value=false
							if(isBrk(r9)){
								//reached a break continue execution outside of the for
								value=true
								r9[sBREAK]=false
								//perform=false
							}
							break
						}
						//don't do the rest if fast forwarding
						if(ffwd(r9))break
						index=index+stepValue
						stSysVarVal(r9,sDLLRINDX,index)
						dvc=[]
						if(isEach){
							dvc= index<dsiz ? [devices[index.toInteger()]]:[]
							if(prun(r9))stSysVarVal(r9,sDLLRDEVICE,dvc)
						}
						if(cntrVar!=sNL && prun(r9))Map m=setVariable(r9,cntrVar,isEach ? dvc:index)
						((Map)r9[sCACHE])[sidx]=index
						if((stepValue>dZ && index>endValue) || (stepValue<dZ && index<endValue)){
							perform=false
							break
						}
					}
					break
				case sSWITCH:
					Map lo=[operand: (Map)statement.lo,values: (List)evaluateOperand(r9,statement,(Map)statement.lo)]
					Boolean fnd,fallThru
					fnd=false
					Boolean implctBr=sMs(statement,'ctp')!=sE // case traversal policy, i- autobreak (def), e- fall thru
					fallThru=!implctBr
					perform=false
					if(lg)debug "Evaluating switch with values $lo.values",r9
					//go through all cases
					for(Map _case in (List<Map>)statement.cs){
						Map ro=[operand: (Map)_case.ro,values: (List)evaluateOperand(r9,_case,(Map)_case.ro)]
						Boolean isR=sMt(_case)==sR // _case.t - r- range, s- single value
						Map ro2=isR ? [operand: (Map)_case.ro2,values: (List)evaluateOperand(r9,_case,(Map)_case.ro2,null,false,true)]:null
						perform=perform || evaluateComparison(r9,(isR ? sISINS:sIS),lo,ro,ro2)
						fnd=fnd || perform
						if(perform || (fnd && fallThru) || ffwd(r9)){
							Integer ffTo=currun(r9)
							if(!executeStatements(r9,(List)_case.s,async)){
								//stop processing
								value=false
								if(isBrk(r9)){
									//reached a break continue execution outside of switch
									value=true
									fnd=true
									fallThru=false
									r9[sBREAK]=false
								}
								if(prun(r9))break
							}
							//if fast forwarding ended during execution, assume fnd is true
							fnd=fnd || ffTo!=currun(r9)
							value=true
							//if implicit breaks
							if(implctBr && prun(r9)){
								fallThru=false
								break
							}
						}
					}
					if(statement.e && ((List)statement.e).size() && (value || ffwd(r9)) && (!fnd || fallThru || ffwd(r9))){
						//no case found, do the default
						if(!executeStatements(r9,(List)statement.e,async)){
							//stop processing
							value=false
							if(isBrk(r9)){
								//reached a break, want to continue execution outside of switch
								value=true
								r9[sBREAK]=false
							}
							if(prun(r9))break
						}
					}
					break
				case sDO:
					Integer ov=iMs(r9,sSTMTL)
					r9[sSTMTL]=ov-i1
					value=executeStatements(r9,(List)statement.s,async)
					r9[sSTMTL]=ov
					break
				case sBREAK:
					if(prun(r9))r9[sBREAK]=true
					value=false
					break
				case sEXIT:
					if(prun(r9)){
						def ss=((Map)evaluateOperand(r9,null,(Map)statement.lo)).v
						Long l=vcmd_setState(r9,null,[scast(r9,ss)])
						r9.terminated=true
						if(lg)debug "Exiting piston due to exit statement",r9
					}
					value=false
					break
			}
			if(ffwd(r9) || isIf)perform=false

			Boolean loop=(stateType in ls1)
			//break the loop
			if(loop && !value && isBrk(r9)){
				//someone requested a break from loop
				r9[sBREAK]=false
				//allowing rest to continue
				value=true
				perform=false
			}
			//repeat the loop?
			repeat=perform && value && loop && prun(r9)

			if(prun(r9)){
				Long overBy=checkForSlowdown(r9)
				if(overBy>lZ){
					Long delay=calcDel(overBy)
					String mstr=sEXST+":Execution time exceeded by ${overBy}ms, ".toString()
					if(repeat && overBy>(Long)getPistonLimits.executionTime){
						r9.terminated=true
						error mstr+'Terminating',r9
						repeat=false
					}else doPause(mstr+'Waiting for '+delay+sMS,delay,r9)
				}
			}
		}
	}
	Map sch
	if(stateType==sEVERY){
		sch=sgtSch(r9).find{ Map it -> iMs(it,'s')==stmtNm} // compiler bug
		if(sch==null){
			List<Map> schs=sgetSchedules(sEXST+s1,myPep)
			sch=schs.find{ Map it -> iMs(it,'s')==stmtNm }
		}
	}
	String myS="s:${stmtNm}".toString()
	Long myL=elapseT(t)
	if(sch!=null){ //timers need to show the remaining time
		Long v=elapseT(lMt(sch))
		tracePoint(r9,myS,myL,v)
	}else if(prun(r9)){
		tracePoint(r9,myS,myL,value)
	}
	if(selfAsync){ //if in async mode return true (to continue execution)
		// if resumed from a timed event; only execute sub tasks / statements during the resume as other statements already ran
		value=!bIs(r9,'resumed')
		r9.resumed=false
	}
	if(bIs(r9,'terminated'))value=false

	//restore current condition
	popStk(r9,c,stacked)

	stSysVarVal(r9,sDLLRINDX,svIndex)
	stSysVarVal(r9,sDLLRDEVICE,svDevice)
	r9[sCNDTNSTC]=svCSC
	Boolean ret=value || ffwd(r9)
	if(isEric(r9))myDetail r9,mySt+" result:"+ret.toString()
	return ret
}

@CompileStatic
private static Long calcDel(Long overBy){
	return overBy>(Long)getPistonLimits.useBigDelay ? (Long)getPistonLimits.taskLongDelay:(Long)getPistonLimits.taskShortDelay
}

@CompileStatic
private Long checkForSlowdown(Map r9){
	//return how long over the time limit
	Long t2; t2=lMs(r9,'tPause')
	t2=t2!=null ? t2: lZ
	Long overby=elapseT(lMs(r9,sTMSTMP))-t2-(Long)getPistonLimits.slTime
	return overby>lZ ? overby:lZ
}

@CompileStatic
private void doPause(String mstr,Long delay,Map r9,Boolean ign=false){
	Long actDelay,t1,t2
	actDelay=lZ
	Long t0=wnow()
	String s='lastPause'
	if(lMs(r9,s)==null || ign || (t0-lMs(r9,s))>(Long)getPistonLimits.slTime){
		if(isTrc(r9))trace mstr+'; lastPause: '+r9[s],r9
		r9[s]=t0
		wpauseExecution(delay)
		t1=wnow()
		actDelay=t1-t0
		t2=lMs(r9,'tPause')
		t2=t2!=null ? t2:lZ
		r9.tPause=t2+actDelay
		r9[s]=t1
		t2=(Long)gtSt('pauses')
		t2=t2!=null ? t2:lZ
		assignSt('pauses',t2+1L)
	}
}

@CompileStatic
private Boolean executeAction(Map r9,Map statement,Boolean async){
	String mySt; mySt=sNL
	Integer stmtNm=stmtNum(statement)
	if(isEric(r9)){
		mySt='executeAction '+("#${stmtNm} "+sffwdng(r9)+"async: ${async} ").toString()
		myDetail r9,mySt,i1
	}
	List svDevices=(List)gtSysVarVal(r9,sDLLRDEVS)
	Boolean res,isCurEvtDev
	res=true
	isCurEvtDev= false
	List sd= statement.d ? (List)statement.d:[]
	List<String> deviceIds; deviceIds=expandDeviceList(r9,sd)
	List devices; devices= deviceIds ? deviceIds.collect{ String it -> getDevice(r9,it)}:[]

	def device=devices[iZ]
	Boolean allowMul=sMs(statement,'tsp')==sA // Task scheduling policy - a- allow multiple schedules, ""-override existing (def)
	isCurEvtDev= sd.size()==i1 && (String)sd[iZ]==sCURDEV && device
	String data= isCurEvtDev && allowMul ? "s:${stmtNm}:"+ ( !isDeviceLocation(device) ? dString(device):"Loc" ) : sNL // make cancel per device
	if(prun(r9) && (data || !allowMul))
		cancelStatementSchedules(r9,stmtNm,data)

	r9.curActn=statement
	List<Map>sk=(List<Map>)statement.k
	if(sk){
		for(Map task in sk){
			Integer tskNm=stmtNum(task)
			if(tskNm!=null && tskNm==currun(r9)){
				//resuming a waiting task need to bring back the devices
				Map e=(Map)r9[sEVENT]
				Map es=(Map)e?.schedule
				Map ess=(Map)e?.stack
				if(es && ess){
					stSysVarVal(r9,sDLLRINDX,(Double)ess.index)
					stSysVarVal(r9,sDLLRDEVICE,(List)ess[sDEV])
					if(ess[sDEVS] instanceof List){
						deviceIds=(List<String>)ess[sDEVS]
						devices= deviceIds ? deviceIds.collect{ String it -> getDevice(r9,it)}:[]
					}
				}
			}
			stSysVarVal(r9,sDLLRDEVS,deviceIds)
			r9.curTsk=[$:stmtNum(task)] as LinkedHashMap
			res=executeTask(r9,devices,statement,task,async,data)
			r9.remove('curTsk')
			if(!res && prun(r9))break
		}
	}
	r9.remove('curActn')
	stSysVarVal(r9,sDLLRDEVS,svDevices)
	if(isEric(r9))myDetail r9,mySt+"resumed: ${bIs(r9,'resumed')} result:$res".toString()
	return res
}

@Field static List<String> LWCMDS
private static List<String> fill_WCMDS(){ return [sSTLVL,sSTIFLVL,sSTHUE,sSTSATUR,sSTCLRTEMP,sSTCLR,'setAdjustedColor','setAdjustedHSLColor','setLoopDuration','setVideoLength',sSTVAR] }

private Boolean executeTask(Map r9,List devices,Map statement,Map task,Boolean async,String data){
	Long t=wnow()
	Integer tskNm=stmtNum(task)
	String myS='t:'+tskNm.toString()
	if(ffwd(r9)){
		if(tskNm==currun(r9)){
			//finally reached the resuming point play nicely from hereon
			tracePoint(r9,myS,elapseT(t),null)
			chgRun(r9,iZ)
			r9.resumed=true
		}
		//not doing anything we are fast forwarding
		return true
	}
	List<String> mds=(List)task.m
	if(mds?.size()>iZ){
		String m; m=sMs(r9,'locationModeId')
		if(m==sNL){
			Map mode=gtCurrentMode()
			m=mode!=null ? hashId(r9,(Long)mode.id):sNL
			r9.locationModeId=m
		}
		if(!(m in mds)){
			if(isDbg(r9))debug "Skipping task ${tskNm} because of mode restrictions",r9
			return true
		}
	}
	String mySt; mySt=sNL
	if(isEric(r9)){
		mySt=("executeTask #${tskNm} "+sMs(task,sC)+" async:${async} devices: ${devices.size()} ").toString()
		myDetail r9,mySt,i1
	}

	//handle duplicate command "push" which was replaced with fake command "pushMomentary"
	def override=CommandsOverrides.find{ (String)it.value.r==sMs(task,sC) }
	String command=override ? (String)override.value.c:sMs(task,sC)

	//parse parameter
	List prms=[]
	Boolean emptyIndex; emptyIndex=false
	for(Map prm in (List<Map>)task.p){
		def p; p=null
		String vt=sMvt(prm)
		switch(vt){
			case sVARIABLE: // vcmd_setVariable command, first argument is the variable name
				if(sMt(prm)==sX){
					p=prm.x instanceof List ? (List)prm.x:sMs(prm,sX)+(sMs(prm,sXI)!=sNL ? sLB+sMs(prm,sXI)+sRB:sBLK)
				}
				break
			default:
				Map v=(Map)evaluateOperand(r9,null,prm)
				String tt1=vt.replace(sLRB,sBLK)
				emptyIndex= tt1!=vt &&
						command=='setVariable' &&
						prms[0] instanceof String &&
						!( ((String)prms[0]).contains(sRB) )
				def t0=v.v
				//if not selected, return the null to fill in parameter
				p=t0==null || emptyIndex || matchCast(r9,t0,tt1) ? t0:evaluateExpression(r9,v,tt1).v
		}
		//ensure value type is successfully passed through
		Boolean a=prms.push(p)
	}

	def virtualDevice=devices.size()!=iZ ? null:gtLocation()
// If the VirtualCommand exists and has o:true use that virtual command otherwise try the physical command
	Map vcmd=VirtualCommands()[command]
	Long delay; delay=lZ
	if(isEric(r9))myDetail r9,mySt+"prms: $prms",iN2
	for(device in (virtualDevice!=null ? [virtualDevice]:devices)){
		if(virtualDevice==null && device?.hasCommand(command) && !(vcmd && vcmd.o /* virtual command does not override physical command */)){
			if(!LWCMDS) LWCMDS=fill_WCMDS()
			Map msg=timer "Executed [$device].${command}",r9
			if(command in LWCMDS){
				try{
					delay="cmd_${command}"(r9,device,prms) // some physical commands have a wrapper method (11)
					msg.m+=" W"
				}catch(e){
					msg.m += " SKIP"
					error "Error while executing command $device.$command($prms):",r9,iN2,e
				}
			}else
				executePhysicalCommand(r9,device,command,prms)
			if(isTrc(r9))trace msg,r9
		}else{
			if(vcmd!=null){
				delay=executeVirtualCommand(r9,vcmd.a ? devices:device,command,prms)
				//aggregate commands only run once for all devices at the same time
				if(vcmd.a)break
			}
		}
	}
	//negative delays force us to reschedule
	Boolean reschedule=delay<lZ
	delay=reschedule ? -delay:delay

	//if we don't have to wait, home free
	String pStr="executeTask: Waiting for "
	if(delay!=lZ){
		//get remaining piston time
		if(reschedule || async || delay>(Long)getPistonLimits.taskMaxDelay){
			//schedule a wake up
			String msg; msg=sBLK
			if(isTrc(r9)) msg="Requesting"
			tracePoint(r9,myS,elapseT(t),-delay) //timers need to show the remaining time
			requestWakeUp(r9,statement,task,delay,data,true,sMs(task,sC),sNL,msg) //allow statement cancels to work
			if(isEric(r9))myDetail r9,mySt+"result:FALSE"
			return false
		}else doPause(pStr+"${delay}ms",delay,r9,true)
	}
	tracePoint(r9,myS,elapseT(t),delay)

	//get remaining piston time
	Long overBy=checkForSlowdown(r9)
	if(overBy>lZ){
		Long mdelay=calcDel(overBy)
		doPause(pStr+"${mdelay}ms, Execution time exceeded by ${overBy}ms",mdelay,r9)
	}
	if(isEric(r9))myDetail r9,mySt+"result:TRUE"
	return true
}

private Long executeVirtualCommand(Map r9,devices,String command,List prms){
	Map msg=timer "Executed virtual command ${devices ? (devices instanceof List ? "$devices.":"[$devices]."):sBLK}${command}",r9
	Long delay; delay=lZ
	try{
		delay="vcmd_${command}"(r9,devices,prms)
		if(isTrc(r9))trace msg,r9
	}catch(all){
		msg.m="Error executing virtual command ${devices instanceof List ? "$devices":"[$devices]"}.${command}:"
		error msg,r9,iN2,all
	}
	return delay
}

@CompileStatic
private static String gTCP(Map statement){ return sMs(statement,sTCP) ?: sC }

@CompileStatic
private static List<Integer> svCS(Map r9,Map statement){
	// cancel on "" == c-> condition state change (def), p- piston state change, b- condition or piston state change, sN- never cancel
	List<Integer> cs=(List<Integer>)[]+ (gTCP(statement) in [sB,sC] ? (List<Integer>)((Map)r9[sSTACK]).cs:(List<Integer>)[] ) // task cancelation policy
	cs.removeElement(iZ)
	return cs
}

@CompileStatic
private static Integer svPS(Map statement){ return gTCP(statement) in [sB,sP] ? i1:iZ }

@CompileStatic
private static Long cedIs(Map r9){
	Integer a=gtPOpt(r9,'ced') // command execution delay
	Long ced; ced= a ? a.toLong():lZ
	if(ced>lZ){
		Long t1=(Long)getPistonLimits.deviceMaxDelay
		ced=ced>t1 ? t1:ced
	}
	return ced
}

@CompileStatic
private String gtSwitch(Map r9,device){ return (String)getDeviceAttributeValue(r9,device,sSWITCH) }

@CompileStatic
private void executePhysicalCommand(Map r9,device,String command,prms=[],Long idel=lZ,String isched=sNL,Boolean dco=false,Boolean doced=true,Boolean canq=true){
	Long delay
	delay=idel
	String s,s1,scheduleDevice
	scheduleDevice=isched
	Boolean willQ,ignRest
	willQ=delay!=lZ && scheduleDevice!=sNL

	Boolean doL=isTrc(r9)
	//delay on device commands is not supported in hubitat; using schedules instead
	s=sBLK
	s1=sBLK
	if(doL && delay)s1="wait before command delay: $delay "
	ignRest=false
	Long ced=cedIs(r9)
	if(doced && canq){
		if(ced>lZ){
			Long cmdqt=lMs(r9,sLSTPCQ) ?: lZ
			Long cmdsnt=lMs(r9,sLSTPCSNT) ?: lZ
			Long lastcmdSent=cmdqt&&cmdsnt ? Math.max(cmdqt,cmdsnt):(cmdqt ?: cmdsnt)
			Long waitT=ced+lastcmdSent-wnow()
			String sst; sst=sBLK
			if(isEric(r9))sst=s1+"cmdqt: $cmdqt cmdsnt: $cmdsnt waitT: $waitT lastcmdSent: $lastcmdSent ced: $ced "
			if(doL)s="No command execution delay required "+s1
			if(waitT>ced/i4){
				Long t1=delay
				ignRest=!willQ
				delay=waitT>delay ? waitT:delay
				scheduleDevice=scheduleDevice ?: hashD(r9,device)
				willQ=true
				if(doL && waitT>t1)s="Injecting command execution delay of ${waitT-t1}ms before [$device].$command() added schedule "
			}
			if(isEric(r9))s+=sst+"updated delay: $delay ignore restrictions: $ignRest"
		}
	}

	if(willQ && canq){
		Map statement=(Map)r9.curActn
		Map task=(Map)r9.curTsk
		List<Integer> cs=svCS(r9,statement)
		Integer ps=svPS(statement)
		Long ttt=Math.round(wnow()*d1+delay)
		if(ced)r9[sLSTPCQ]=ttt
		Map schedule=[
			(sT):ttt,
			(sS):stmtNum(statement),
			(sI):iN3,
			cs:cs,
			ps:ps,
			(sD):[
				(sD):scheduleDevice,
				(sC):command,
				(sP):prms,
				task:stmtNum(task)
			]
		]
		if(ignRest){
			((Map)schedule.d).dc=dco
			((Map)schedule.d).ig=ignRest
		}
		if(doL)debug s+wakeS('Requesting a physical command',schedule),r9
		Boolean a=spshSch(r9,schedule)
	}else{
		List nprms=(prms instanceof List) ? (List)prms:(prms!=null ? [prms]:[])
		try{
			//cleanup the prms so that SONOS works
			Integer psz; psz=nprms.size()
			def a
			while (psz>iZ && nprms[psz-i1]==null){ a=nprms.pop(); psz=nprms.size() }
			String tailStr; tailStr=sNL
			if(!canq && delay>lZ){
				Long t1=(Long)getPistonLimits.deviceMaxDelay
				delay=delay>t1 ? t1:delay
				doPause("PAUSE wait before device command: Waiting for ${delay}ms",delay,r9,true)
				if(doL)tailStr="[delay: $delay])".toString()
			}
			Map msg; msg=null
			if(doL)msg=timer sBLK,r9

			Boolean skip; skip=false
			// disableCommandOptimization
			if(!gtPOpt(r9,'dco') && !dco && !(command in [sSTCLRTEMP,sSTCLR,sSTHUE,sSTSATUR])){
				Map cmd=PhysicalCommands()[command]
				if(cmd!=null && sMa(cmd)!=sNL){
					if(cmd.v!=null && psz==iZ){
						//commands with no parameter that set an attribute to a preset value
						if((String)getDeviceAttributeValue(r9,device,sMa(cmd))==sMs(cmd,sV))skip=true
					}else if(psz==i1){
						if(getDeviceAttributeValue(r9,device,sMa(cmd))==nprms[iZ])
							skip=(command in [sSTLVL,sSTIFLVL] ? gtSwitch(r9,device)==sON:true)
					}
				}
			}

			String tstr; tstr=sNL
			if(doL){
				tstr=' physical command ['+gtLbl(device)+'].'+command+'('
				if(skip) msg.m='Skipped execution of'+tstr+"$nprms".toString()+') because it would make no change to the device.'+s
			}
			if(!skip){
				if(ced)r9[sLSTPCSNT]=wnow()
				if(doL){
					tstr='Executed'+tstr
					if(psz>iZ) msg.m=tstr+nprms.join(sCOMMA)+"${tailStr ? sCOMMA+tailStr:')'}"
					else msg.m=tstr+"${tailStr ?: ')'}"
				}
				pcmd(device,command,nprms)
			}
			if(doL)trace msg,r9
		}catch(all){
			error "Error while executing physical command $device.$command($nprms):",r9,iN2,all
		}
	}
}

private static void pcmd(device,String cmd,List nprms=[]){
	if(nprms.size()>iZ) device."$cmd"(nprms as Object[])
	else device."$cmd"()
}

private void scheduleTimer(Map r9,Map timer,Long lastRun=lZ){
	//if already scheduled once during run, don't do it again
	Integer iTD=stmtNum(timer)
	if(sgtSch(r9).find{ Map it -> iMs(it,'s')==iTD })return
	String mySt; mySt=sNL
	if(isEric(r9)){
		mySt="scheduleTimer ${iTD} ${timer.lo} ${timer.lo2} ${timer.lo3} $lastRun"
		myDetail r9,mySt,i1
	}
	//complicated stuff follows
	String tinterval="${((Map)evaluateOperand(r9,null,(Map)timer.lo)).v}".toString()
	Boolean exitOut,priorActivity
	exitOut=false
	Integer interval,level,cycles
	interval=iZ
	if(tinterval.isInteger()){
		interval=tinterval.toInteger()
		if(interval<=iZ)exitOut=true
	}else exitOut=true
	if(exitOut){
		if(isEric(r9))myDetail r9,mySt
		return
	}
	Map tlo=(Map)timer.lo
	Map tlo2=(Map)timer.lo2
	String intervalUnit=sMvt(tlo)
	level=iZ
	Long delta,time,rightNow,nxtSchd
	delta=lZ
	switch(intervalUnit){
		case sMS: level=i1; delta=1L; break
		case sS: level=i2; delta=lTHOUS; break
		case sM: level=i3; delta=dMSMINT.toLong(); break
		case sH: level=i4; delta=dMSECHR.toLong(); break
		case sD: level=i5; break
		case 'w': level=i6; break
		case sN: level=i7; break
		case 'y': level=i8; break
	}
	if(isEric(r9))
		myDetail r9,"interval: $interval delta: $delta level: $level intervalUnit: $intervalUnit",iN2
	time=lZ
	if(delta==lZ){
		//let's get the offset
		time=longEvalExpr(r9,(Map)evaluateOperand(r9,null,(Map)timer.lo2),sDTIME)
		if(sMt(tlo2)!=sC){
			Map offset=(Map)evaluateOperand(r9,null,(Map)timer.lo3)
			time+=longEvalExpr(r9,rtnMap1(offset.v,sMvt(offset)))
		}
		//result is sDTIME
		if(lastRun==lZ) //first run, just adjust the time so in the future
			time=pushTimeAhead(time,wnow())
	}
	delta=Math.round(delta*interval*d1)
	priorActivity=lastRun!=lZ

	rightNow=wnow()
	Long lastR=lastRun!=lZ ? lastRun:rightNow
	nxtSchd=lastR

	if(lastR>rightNow) //sometimes timers run early, so make sure at least in the near future
		rightNow=Math.round(lastR+d1)

	if(intervalUnit==sH){
		Long min=lcast(r9,tlo.om)
		nxtSchd=Math.round(dMSECHR*Math.floor(nxtSchd/dMSECHR)+(min*dMSMINT))
	}

	//next date
	cycles=100
	while(cycles!=iZ){
		if(delta!=lZ){
			if(nxtSchd<(rightNow-delta)){
				//behind, catch up to where the next future occurrence
				Long cnt=Math.floor((rightNow-nxtSchd)/delta*d1).toLong()
				//if(isDbg(r9))debug "Timer fell behind by $cnt interval${cnt>i1 ? sS:sBLK}, catching up",r9
				nxtSchd+=Math.round(delta*cnt*d1)
			}
			nxtSchd+=delta
		}else{
			//advance ahead of rightNow if in the past
			time=pushTimeAhead(time,rightNow)
			Long lastDay=Math.floor(nxtSchd/dMSDAY).toLong()
			Long thisDay=Math.floor(time/dMSDAY).toLong()

			Date adate=new Date(time)
			Integer dyYear=adate.year
			Integer dyMon=adate.month
			Integer dyDay=adate.day

			//the repeating interval is not necessarily constant
			switch(intervalUnit){
				case sD:
					if(priorActivity){
						//add the required number of days
						nxtSchd=time+Math.round(dMSDAY*(interval-(thisDay-lastDay)))
					}else nxtSchd=time
					break
				case 'w':
					//figure out the first day of the week matching the requirement
					Long currentDay=dyDay //(new Date(time)).day
					Long requiredDay=lcast(r9,tlo.odw)
					if(currentDay>requiredDay)requiredDay+=i7
					//move to first matching day
					nxtSchd=time+Math.round(dMSDAY*(requiredDay-currentDay))
					if(nxtSchd<rightNow)nxtSchd+=Math.round(604800000.0D*interval)
					break
				case sN:
				case 'y':
					//figure out the first day of the week matching the requirement
					Integer odm=icast(r9,tlo.odm)
					def odw=tlo.odw
					Integer omy=intervalUnit=='y' ? icast(r9,tlo.omy):iZ
					Integer day,year,month
					Date date=adate // new Date(time)
					year=dyYear //date.year
					month=Math.round((intervalUnit==sN ? dyMon /*date.month*/:omy)+(priorActivity ? interval:((nxtSchd<rightNow)? d1:dZ))*(intervalUnit==sN ? d1:i12)).toInteger()
					if(month>=i12){
						year+=Math.floor(month/i12).toInteger()
						month=month%i12
					}
					date.setDate(i1)
					date.setMonth(month)
					date.setYear(year)

					Integer lastDayOfMonth= (new Date(date.year,date.month+i1,0)).date
					if(odw==sD){
						if(odm>iZ)day=(odm<=lastDayOfMonth)? odm:iZ
						else{
							day=lastDayOfMonth+i1+odm
							day=(day>=i1)? day:iZ
						}
					}else{
						Integer iodw=icast(r9,odw)
						Double d7=7.0D
						//locate the nth week day of the month
						if(odm>iZ){
							//going forward
							Integer firstDayOfMonthDOW=(new Date(date.year,date.month,1)).day
							//locate the first matching day
							Integer firstMatch=Math.round(i1+iodw-firstDayOfMonthDOW+(iodw<firstDayOfMonthDOW ? d7:dZ)).toInteger()
							day=Math.round(firstMatch+d7*(odm-d1)).toInteger()
							day=(day<=lastDayOfMonth)? day:iZ
						}else{
							//going backwards
							Integer lastDayOfMonthDOW=(new Date(date.year,date.month+i1,0)).day
							//locate the first matching day
							Integer firstMatch=lastDayOfMonth+iodw-lastDayOfMonthDOW-(iodw>lastDayOfMonthDOW ? i7:iZ)
							day=Math.round(firstMatch+d7*(odm+i1)).toInteger()
							day=(day>=i1)? day:iZ
						}
					}
					if(day){
						date.setDate(day)
						nxtSchd=date.getTime()
					}
					break
			}
		}
		//check to see if it fits the restrictions
		if(nxtSchd>=rightNow){
			Long offset=checkTimeRestrictions(r9,(Map)timer.lo,nxtSchd,level,interval)
			if(offset==lZ)break
			if(offset>lZ)nxtSchd+=offset
		}
		time=nxtSchd
		priorActivity=true
		cycles-=i1
	}

	if(nxtSchd>lastR){
		Boolean a=((List<Map>)r9[sSCHS]).removeAll{ Map it -> iMs(it,'s')==iTD }
		requestWakeUp(r9,timer,[(sDLR):iN1],nxtSchd,sNL,false)
	}
	if(isEric(r9))myDetail r9,mySt
}

@CompileStatic
private static Long pushTimeAhead(Long pastTime,Long curTime){
	Long retTime
	retTime=pastTime
	TimeZone mtz=mTZ()
	while(retTime<curTime){
		Long t0=Math.round(retTime+dMSDAY)
		Long t1=Math.round(t0+(mtz.getOffset(retTime)-mtz.getOffset(t0))*d1)
		retTime=t1
	}
	return retTime
}

@CompileStatic
private void scheduleTimeCondition(Map r9,Map cndtn){
	String mySt; mySt=sNL
	if(isEric(r9)){
		mySt='scheduleTimeCondition'
		myDetail r9,mySt,i1
	}
	Integer cndNm=stmtNum(cndtn)
	//if already scheduled once during run, don't do it again
	if(sgtSch(r9).find{ Map it -> iMs(it,'s')==cndNm && iMs(it,'i')==0 })return
	String co=sMs(cndtn,'co')
	Map comparison; comparison=Comparisons().conditions[co]
	Boolean trigger; trigger=false
	if(comparison==null){
		comparison=Comparisons().triggers[co]
		if(comparison==null)return
		trigger=true
	}
	cancelStatementSchedules(r9,cndNm)
	Integer pCnt=comparison.p!=null ? iMs(comparison,sP):iZ
	if(!pCnt)return
	Map cLO=(Map)cndtn.lo

	Long v1,v2,n,n1
	Map tv1,tv2
	tv1=cndtn.ro!=null && sMt((Map)cndtn.ro)!=sC ? (Map)evaluateOperand(r9,null,(Map)cndtn.to):null
	v1=longEvalExpr(r9,(Map)evaluateOperand(r9,null,(Map)cndtn.ro),sDTIME) + (tv1!=null ? longEvalExpr(r9,rtnMap1(tv1.v,sMvt(tv1))) : lZ)
	tv2=cndtn.ro2!=null && sMt((Map)cndtn.ro2)!=sC && pCnt>i1 ? (Map)evaluateOperand(r9,null,(Map)cndtn.to2):null
	v2=trigger ? v1:(pCnt>i1 ? (longEvalExpr(r9,(Map)evaluateOperand(r9,null,(Map)cndtn.ro2,null,false,true),sDTIME) + (tv2!=null ? longEvalExpr(r9,rtnMap1(tv2.v,sMvt(tv2))) :lZ)) : sMs(cLO,sV)==sTIME ? getMidnightTime():v1 )
	n=Math.round(d1*wnow()+2000L)
	if(sMs(cLO,sV)==sTIME){
		v1=pushTimeAhead(v1,n)
		v2=pushTimeAhead(v2,n)
	}
	//figure out the next time
	v1=v1<n ? v2:v1
	v2=v2<n ? v1:v2
	n=v1<v2 ? v1:v2

	n1=n
	if(sMs(cLO,sV)==sTIME && trigger){
		Integer iyr=1461 // 4 years
		Integer cnt
		cnt=iyr
		if(isEric(r9))debug mySt+" checking for schedule restrictions for $cLO", r9
		while(cnt){
			//repeat until we find a day that's matching the restrictions
			if(checkTimeRestrictions(r9,cLO,n1,5,1)==lZ) break
			cnt-=i1
			n1=pushTimeAhead(n1,n1+1L)
		}
		if(isDbg(r9) && cnt!=iyr)debug "Adding ${iyr-cnt} days, $n >>> $n1" ,r9
		if(cnt==iZ)n1=n
	}

	if(n1>wnow()){
		String msg; msg=sBLK
		if(isDbg(r9))msg= "Requesting time schedule"
		requestWakeUp(r9,cndtn,[(sDLR):iZ],n1,sNL,false,sNL,msg)
	}
	if(isEric(r9))myDetail r9,mySt
}

@CompileStatic
private static Boolean listWithSz(obj){ return obj instanceof List && ((List)obj).size()>iZ }

@CompileStatic
private static Long checkTimeRestrictions(Map r9,Map operand,Long time,Integer level,Integer interval){
	//returns 0 if restrictions are passed
	//returns a positive number as millisecond offset to apply to nextSchedule for fast forwarding
	//returns a negative number as a failed restriction with no fast forwarding offset suggestion

	// on minute of hour
	List<Integer> om=level<=i2 && listWithSz(operand.om)? (List<Integer>)operand.om:null
	// on hours
	List<Integer> oh=level<=i3 && listWithSz(operand.oh) ? (List<Integer>)operand.oh:null
	// on day(s) of week
	List<Integer> odw=level<=i5 && listWithSz(operand.odw) ? (List<Integer>)operand.odw:null
	// on day(s) of month
	List<Integer> odm
	odm=level<=i6 && listWithSz(operand.odm) ? (List<Integer>)operand.odm:null
	// on weeks of month
	List<Integer> owm=level<=i6 && odm==null && listWithSz(operand.owm) ? (List<Integer>)operand.owm:null
	// on month of year
	List<Integer> omy=level<=i7 && listWithSz(operand.omy) ? (List<Integer>)operand.omy:null

	if(om==null && oh==null && odw==null && odm==null && owm==null && omy==null)return lZ
	Date date=new Date(time)
	Integer dyYear=date.year
	Integer dyMon=date.month
	Integer dyDate=date.date
	Integer dyDay=date.day
	Integer dyHr=date.hours
	Integer dyMins=date.minutes

	Double dminDay=1440.0D
	Double dsecDay=86400.0D

	Long lMO=-1L
	Long res; res=lMO
	//month restrictions
	Integer dyMonPlus=dyMon+i1
	if(omy!=null && omy.indexOf(dyMonPlus)<iZ){
		List<Integer> tI=omy.sort{ Integer it -> it }
		Integer month
		month=(tI.find{ Integer it -> it>dyMonPlus } ?: i12+tI[iZ]) -i1
		Integer year=dyYear+(month>=i12 ? i1:iZ)
		month=(month>=i12 ? month-i12:month)
		Long ms=(new Date(year,month,1)).getTime()-time
		switch(level){
			case i2: //by second
				Double tt=Math.floor((ms/(d1000*interval)).toDouble())
				res=Math.round(interval*(tt-d2)*d1000)
				break
			case i3: //by minute
				Double tt=Math.floor((ms/(dMSMINT*interval)).toDouble())
				res=Math.round(interval*(tt-d2)*dMSMINT)
				break
		}
		return pRes(r9,res)
	}

	Double d7=7.0D
	//week of month restrictions
	if(owm!=null && !(owm.indexOf(getWeekOfMonth(date))>=iZ || owm.indexOf(getWeekOfMonth(date,true))>=iZ)){
		switch(level){
			case i2: //by second
				Double tt= Math.floor( ( ( (d7-dyDay)*dsecDay -dyHr*dSECHR -dyMins*d60)/interval ).toDouble() )
				res=Math.round(interval*(tt-d2)*d1000)
				break
			case i3: //by minute
				Double tt= Math.floor( ( ( (d7-dyDay)*dminDay -dyHr*d60 -dyMins)/interval).toDouble() )
				res=Math.round(interval*(tt-d2)*dMSMINT)
				break
		}
		return pRes(r9,res)
	}

	//day of month restrictions
	if(odm!=null && odm.indexOf(dyDate)<iZ){
		Integer lastDayOfMonth=new Date(dyYear,dyMonPlus,0).date
		if(odm.find{ Integer it -> it<1 }){
			//we need to add the last days
			odm= []+odm as List<Integer> //copy the array
			if(odm.indexOf(iN1)>=iZ)Boolean a=odm.push(lastDayOfMonth)
			if(odm.indexOf(iN2)>=iZ)Boolean a=odm.push(lastDayOfMonth-i1)
			if(odm.indexOf(iN3)>=iZ)Boolean a=odm.push(lastDayOfMonth-i2)
			Boolean a=odm.removeAll{ Integer it -> it<1 }
		}
		List<Integer> tI=odm.sort{ Integer it -> it }
		switch(level){
			case i2: //by second
				Double tt= Math.floor( ((((tI.find{ Integer it -> it>dyDate } ?: lastDayOfMonth+tI[iZ])-dyDate)*dsecDay-dyHr*dSECHR-dyMins*d60)/interval).toDouble() )
				res=Math.round(interval*(tt-d2)*d1000)
				break
			case i3: //by minute
				Double tt= Math.floor(((((tI.find{ Integer it -> it>dyDate } ?: lastDayOfMonth+tI[iZ])-dyDate)*dminDay-dyHr*d60-dyMins)/interval).toDouble())
				res=Math.round(interval*(tt-d2)*dMSMINT)
				break
		}
		return pRes(r9,res)
	}

	//day of week restrictions
	if(odw!=null && odw.indexOf(dyDay)<iZ ){
		List<Integer> tI=odw.sort{ Integer it -> it }
		switch(level){
			case i2: //by second
				Double tt= Math.floor(((((tI.find{ Integer it -> it>dyDay } ?: d7+tI[iZ])-dyDay)*dsecDay-dyHr*dSECHR-dyMins*d60)/interval).toDouble())
				res=Math.round(interval*(tt-d2)*d1000)
				break
			case i3: //by minute
				Double tt= Math.floor(((((tI.find{ Integer it -> it>dyDay } ?: d7+tI[iZ])-dyDay)*dminDay-dyHr*d60-dyMins)/interval).toDouble())
				res=Math.round(interval*(tt-d2)*dMSMINT)
				break
		}
		return pRes(r9,res)
	}

	//hour restrictions
	if(oh!=null && oh.indexOf(dyHr)<iZ ){
		Double d24=24.0D
		List<Integer> tI=oh.sort{ Integer it -> it }
		switch(level){
			case i2: //by second
				Double tt= Math.floor(((((tI.find{ Integer it -> it>dyHr } ?: d24+tI[iZ])-dyHr)*dSECHR-dyMins*d60)/interval).toDouble())
				res=Math.round(interval*(tt-d2)*d1000)
				break
			case i3: //by minute
				Double tt= Math.floor(((((tI.find{ Integer it -> it>dyHr } ?: d24+tI[iZ])-dyHr)*d60-dyMins)/interval).toDouble())
				res=Math.round(interval*(tt-d2)*dMSMINT)
				break
		}
		return pRes(r9,res)
	}

	//minute restrictions
	if(om!=null && om.indexOf(dyMins)<iZ ){
		//get the next highest minute
		//suggest an offset to reach the next minute
		List<Integer> tI=om.sort{ Integer it -> it }
		Double tt= Math.floor((((tI.find{ Integer it -> it>dyMins } ?: d60+tI[iZ])-dyMins-d1)*d60/interval).toDouble())
		res=Math.round(interval*(tt-d2)*d1000)
		return pRes(r9,res)
	}
	return lZ
}

@CompileStatic
private static Long pRes(Map r9, Long result){
	Long res= result>lZ ? result: -1L
	return res
}

//return the number of occurrences of same day of week up until the date or from the end of the month if backwards,i.e. last Sunday is -1, second-last Sunday is -2
@CompileStatic
private static Integer getWeekOfMonth(Date date,Boolean backwards=false){
	Integer day=date.date
	if(backwards){
		Integer month=date.month
		Integer year=date.year
		Integer lastDayOfMonth=(new Date(year,month+i1,0)).date
		return -(i1+Math.floor( ((lastDayOfMonth-day)/i7).toDouble() ))
	}else return i1+Math.floor(((day-i1)/i7).toDouble()) //1 based
}

@CompileStatic
private void requestWakeUp(Map r9,Map statement,Map task,Long timeOrDelay,String data=sNL,Boolean toResume=true, String reason=sNL,String msg=sNL,String tmsg=sNL){
	Long time=timeOrDelay>9999999999L ? timeOrDelay:wnow()+timeOrDelay
	List<Integer> cs=svCS(r9,statement)
	Integer ps=svPS(statement)
	Map mmschedule=[
		(sT):time,
		(sS):stmtNum(statement),
		(sI):stmtNum(task), // timers start at .i
		cs:cs,
		ps:ps
	]
	if(reason!=sNL)mmschedule.r=reason
	if(data!=sNL)mmschedule.d=data
	Boolean fnd

	//not all wakeups are suspend/resume
	if(toResume){ // state to save across a sleep
		Map e=(Map)r9[sEVENT]
		Map es=(Map)e.schedule
		if(sMs(e,sNM)==sTIME && es!=null && iMs(es,sS) && stmtNum(task)>=iZ && data!=sNL && !data.startsWith(sCLN))
			mmschedule.svs=iMs(es,sS) // dealing a sleep before r9.wakingUp

		fnd=false
		def myResp,myJson,a

		myResp=r9.response
		if(myResp.toString().size()>10000){ myResp=[:]; fnd=true } // state can only be total 100KB

		myJson=r9.json
		if(myJson.toString().size()>10000){ myJson=[:]; fnd=true }
		if(fnd)debug 'trimming from scheduled wakeup saved $response and/or $json due to large size',r9

		fnd=false
		Map mstk,evt
		mstk=[:]
		a=(Double)gtSysVarVal(r9,sDLLRINDX); if(a!=null)fnd=true
		mstk.index=a
		a=(List)gtSysVarVal(r9,sDLLRDEVICE); if(a!=null)fnd=true
		mstk[sDEV]=a
		a=(List)gtSysVarVal(r9,sDLLRDEVS); if(a)fnd=true
		mstk[sDEVS]=a
		if(myJson)fnd=true
		mstk.json=myJson
		if(myResp)fnd=true
		mstk.response=myResp
		if(fnd)mmschedule[sSTACK]=mstk
// what about previousEvent httpContentType httpStatusCode httpStatusOk iftttStatusCode iftttStatusOk "\$mediaId" "\$mediaUrl" "\$mediaType" mediaData (big)

		evt=[:]+(Map)r9[sCUREVT]
		if(evt)evt=cleanEvt(evt)
		mmschedule.evt=evt

		a=gtSysVarVal(r9,sDARGS)
		if(a)mmschedule.args=a
	}
	fnd=spshSch(r9,mmschedule)
	if(msg||tmsg){
		String s= wakeS(sBLK,mmschedule)
		if(msg)debug msg+s,r9 else trace tmsg+s,r9
	}
}

@CompileStatic
private String wakeS(String m,Map sch){ Long t=lMt(sch); return m+" wake up at ${formatLocalTime(t)} (in ${t-wnow()}ms) for "+cnlS(sch) }

/** Returns true if switch does not match mat  */
@CompileStatic
private Boolean ntMatSw(Map r9,String mat,device,String cmd){
	if(mat!=sNL && gtSwitch(r9,device)!=mat){
		if(isTrc(r9))trace "Skipping ${cmd} as switch is not $mat",r9
		return true
	}
	return false
}

@CompileStatic
private Long do_setLevel(Map r9,device,List prms,String cmd,Integer val=null){
	Integer arg=val!=null ? val:icast(r9,prms[iZ])
	Integer psz=prms.size()
	String mat=psz>i1 ? sLs(prms,i1):sNL
	if(ntMatSw(r9,mat,device,cmd))return lZ
	Integer delay; delay=iZ
	Boolean a
	List larg=[arg]
	if(cmd==sSTLVL){ // setLevel takes seconds duration argument (optional)
		delay=psz>i2 ? icast(r9,prms[i2]):iZ
	} else if(cmd==sSTCLRTEMP){ // setColorTemp takes level and seconds duration arguments (optional)
		if(psz>i2){
			Integer lvl=prms[i2]!=null ? icast(r9,prms[i2]):null
			a=larg.push(lvl)
			delay=psz>i3 ? icast(r9,prms[i3]):iZ
		}
	}
	if(delay>iZ)a=larg.push(delay)
	executePhysicalCommand(r9,device,cmd,larg)
	if(delay>=iZ)return Math.round(delay*d1000)
	return lZ
}

// cmd_ are wrappers for physical commands with added parameters by webCoRE - these are in LWCMDS
private Long cmd_setLevel(Map r9,device,List prms){ return do_setLevel(r9,device,prms,sSTLVL) }

private Long cmd_setInfraredLevel(Map r9,device,List prms){ return do_setLevel(r9,device,prms,sSTIFLVL) }

private Long cmd_setHue(Map r9,device,List prms){
	Integer hue=Math.round((Integer)prms[iZ]/d3d6).toInteger()
	return do_setLevel(r9,device,prms,sSTHUE,hue)
}

private Long cmd_setSaturation(Map r9,device,List prms){ return do_setLevel(r9,device,prms,sSTSATUR) }

private Long cmd_setColorTemperature(Map r9,device,List prms){ return do_setLevel(r9,device,prms,sSTCLRTEMP) }

@CompileStatic
private static Map gtColor(Map r9,String colorValue){
	Map color; color=(colorValue=='Random')? getRandomColor():getColorByName(colorValue)
	if(color==null) color=hexToColor(colorValue)
	if(color!=null){
		color=[
			hex:sMs(color,'rgb'),
			(sHUE):Math.round((iMs(color,sH)/d3d6).toDouble()).toInteger(),
			(sSATUR):iMs(color,sS),
			(sLVL):iMs(color,sL)
		]
	}
	return color
}

private Long cmd_setColor(Map r9,device,List prms){
	Map color=gtColor(r9,sLs(prms,iZ))
	if(!color){
		error "ERROR: Invalid color $prms",r9
		return lZ
	}
	Integer psz=prms.size()
	String mat=psz>i1 ? sLs(prms,i1):sNL
	if(ntMatSw(r9,mat,device,sSTCLR))return lZ
	Long delay=psz>i2 ? (Long)prms[i2]:lZ
	executePhysicalCommand(r9,device,sSTCLR,color,delay)
	if(delay>=lZ)return delay
	return lZ
}

private Long cmd_setAdjustedColor(Map r9,device,List prms){
	Map color=gtColor(r9,sLs(prms,iZ))
	if(!color){
		error "ERROR: Invalid color $prms",r9
		return lZ
	}
	Integer psz=prms.size()
	String mat=psz>i2 ? sLs(prms,i2):sNL
	String cmd='setAdjustedColor'
	if(ntMatSw(r9,mat,device,cmd))return lZ
	Long duration=matchCastL(r9,prms[i1])
	Long delay=psz>i3 ? (Long)prms[i3]:lZ
	executePhysicalCommand(r9,device,cmd,[color,duration],delay)
	if(delay>=lZ)return delay
	return lZ
}

private Long cmd_setAdjustedHSLColor(Map r9,device,List prms){
	Integer psz=prms.size()
	String mat=psz>i4 ? sLs(prms,i4):sNL
	if(ntMatSw(r9,mat,device,'setAdjustedHSLColor'))return lZ

	Long duration=matchCastL(r9,prms[i3])
	Integer hue=Math.round((Integer)prms[iZ]/d3d6).toInteger()
	Integer saturation=(Integer)prms[i1]
	Integer level=(Integer)prms[i2]
	Map color=[
		(sHUE):hue,
		(sSATUR):saturation,
		(sLVL):level
	]
	Long delay=psz>i5 ? (Long)prms[i5]:lZ
	executePhysicalCommand(r9,device,'setAdjustedColor',[color,duration],delay)
	if(delay>=lZ)return delay
	return lZ
}

private Long cmd_setLoopDuration(Map r9,device,List prms){
	Integer duration=Math.round(matchCastL(r9,prms[iZ])/d1000).toInteger()
	executePhysicalCommand(r9,device,'setLoopDuration',duration)
	return lZ
}

private Long cmd_setVideoLength(Map r9,device,List prms){
	Integer duration=Math.round(matchCastL(r9,prms[iZ])/d1000).toInteger()
	executePhysicalCommand(r9,device,'setVideoLength',duration)
	return lZ
}

private Long cmd_setVariable(Map r9,device,List prms){
	def var=prms[i1]
	executePhysicalCommand(r9,device,sSTVAR,var)
	return lZ
}

/* virtual commands */

private Long vcmd_log(Map r9,device,List prms){
	String command=prms[iZ] ? sLs(prms,iZ):sBLK
	String message=sLs(prms,i1)
	Map a=log(message,r9,iN2,null,command.toLowerCase().trim(),true)
	return lZ
}

@CompileStatic
private Long vcmd_setState(Map r9,device,List prms){
	String value=prms[iZ]
	if(gtPOpt(r9,'mps')){
		Map t0=(Map)r9[sST]
		t0[sNEW]=value
		if(!bIs(r9,sPSTNSTC)){
			Boolean t= sMs(t0,sOLD)!=sMs(t0,sNEW)
			r9[sPSTNSTC]=t
			if(t && isDbg(r9)) debug "Piston state changed",r9
		}
	}else error "Cannot set the piston state while in automatic mode. Please edit the piston settings to disable the automatic piston state if you want to manually control the state.",r9
	return lZ
}

private static Long vcmd_setTileColor(Map r9,device,List prms){
	Integer index=matchCastI(r9,prms[iZ])
	if(index<i1 || index>16)return lZ
	String sIdx=index.toString()
	Map t0=(Map)r9[sST]
	t0[sC+sIdx]=(String)gtColor(r9,(String)prms[i1])?.hex
	t0[sB+sIdx]=(String)gtColor(r9,(String)prms[i2])?.hex
	t0[sF+sIdx]=!!prms[i3]
	return lZ
}

private static Long vcmd_setTileTitle(Map r9,device,List prms){ return helper_setTile(r9,sI,prms) }

private static Long vcmd_setTileText(Map r9,device,List prms){ return helper_setTile(r9,sT,prms) }

private static Long vcmd_setTileFooter(Map r9,device,List prms){ return helper_setTile(r9,sO,prms) }

private static Long vcmd_setTileOTitle(Map r9,device,List prms){ return helper_setTile(r9,sP,prms) }

private static Long helper_setTile(Map r9,String typ,List prms){
	Integer index=matchCastI(r9,prms[iZ])
	if(index<i1 || index>16)return lZ
	r9[sST]["${typ}$index".toString()]=sLs(prms,i1)
	return lZ
}

private static Long vcmd_setTile(Map r9,device,List prms){
	Integer index=matchCastI(r9,prms[iZ])
	if(index<i1 || index>16)return lZ
	String sIdx=index.toString()
	Map t0=(Map)r9[sST]
	t0[sI+sIdx]=sLs(prms,i1)
	t0[sT+sIdx]=sLs(prms,i2)
	t0[sO+sIdx]=sLs(prms,i3)
	t0[sC+sIdx]=(String)gtColor(r9,sLs(prms,i4))?.hex
	t0[sB+sIdx]=(String)gtColor(r9,sLs(prms,i5))?.hex
	t0[sF+sIdx]=!!prms[i6]
	return lZ
}

private static Long vcmd_clearTile(Map r9,device,List prms){
	Integer index=matchCastI(r9,prms[iZ])
	if(index<i1 || index>16)return lZ
	String sIdx=index.toString()
	Map t0=(Map)r9[sST]
	t0.remove(sI+sIdx)
	t0.remove(sT+sIdx)
	t0.remove(sC+sIdx)
	t0.remove(sO+sIdx)
	t0.remove(sB+sIdx)
	t0.remove(sF+sIdx)
	t0.remove(sP+sIdx)
	return lZ
}

/* wrappers */
private Long vcmd_setLocationMode(Map r9,device,List prms){
	String mIdOrNm=sLs(prms,iZ)
	def mode=((List)location.getModes())?.find{ hashId(r9,(Long)it.id)==mIdOrNm || (String)it.name==mIdOrNm }
	if(mode) location.setMode((String)mode.name)
	else error "Error setting location mode. Mode '$mIdOrNm' does not exist.",r9
	return lZ
}

/* wrappers */
private Long vcmd_setAlarmSystemStatus(Map r9,device,List prms){
	String sIdOrNm=sLs(prms,iZ)
	Map vd=VirtualDevices()['alarmSystemStatus']
	Map<String,String> options=(Map<String,String>)vd?.ac
	List status=options?.find{ (String)it.key==sIdOrNm || (String)it.value==sIdOrNm }?.collect{ [(sID):(String)it.key,(sNM):it.value] }

	if(status && status.size()!=iZ) sendLocationEvent((sNM):sHSMSARM,(sVAL):status[iZ].id)
	else error "Error setting HSM status. Status '$sIdOrNm' does not exist.",r9
	return lZ
}

private Long vcmd_sendEmail(Map r9,device,List prms){
	Map<String,String> data=[
		(sI):sMs(r9,sID),
		(sN):gtAppN(),
		(sT):sLs(prms,iZ),
		(sS):sLs(prms,i1),
		(sM):sLs(prms,i2)
	]

	Map requestParams=[
		uri: 'https://api.webcore.co/email/send/'+sMs(r9,sLOCID),
		query: null,
		headers: [:],//(auth ? [Authorization: auth]:[:]),
		requestContentType: sAPPJSON,
		body: data,
		timeout:i20
	]
	String msg='Unknown error'

	try{
		asynchttpPost('ahttpRequestHandler',requestParams,[command:sSENDE,em:data])
		return 24000L
	}catch(all){
		error "Error sending email to ${data.t}: $msg",r9,iN2,all
	}
	return lZ
}

private static Long vcmd_noop(Map r9,device,List prms){
	return lZ
}

@CompileStatic
private static Long vcmd_wait(Map r9,device,List prms){
	return matchCastL(r9,prms[iZ])
}

private static Long vcmd_waitRandom(Map r9,device,List prms){
	Long min,max
	min=matchCastL(r9,prms[iZ])
	max=matchCastL(r9,prms[i1])
	if(max<min){
		Long t=max
		max=min
		min=t
	}
	return min+Math.round((max-min)*Math.random())
}

private Long vcmd_waitForTime(Map r9,device,List prms){
	Long time; time=(Long)cast(r9,(Long)cast(r9,prms[iZ],sTIME),sDTIME,sTIME)
	Long rightNow=wnow()
	time=pushTimeAhead(time,rightNow)
	return time-rightNow
}

private Long vcmd_waitForDateTime(Map r9,device,List prms){
	Long time=(Long)cast(r9,prms[iZ],sDTIME)
	Long rightNow=wnow()
	return time>rightNow ? time-rightNow:lZ
}

private Long vcmd_setSwitch(Map r9,device,List prms){
	//noinspection GroovyAssignabilityCheck
	executePhysicalCommand(r9,device,bcast(r9,prms[iZ]) ? sON:sOFF)
	return lZ
}

private Long vcmd_toggle(Map r9,device,List prms){
	smart_toggle(r9,device)
	return lZ
}

private Long vcmd_toggleRandom(Map r9,device,List prms){
	Integer probability; probability=matchCastI(r9,prms.size()==i1 ? prms[iZ]:i50)
	if(probability<=iZ)probability=i50
	smart_toggle(r9,device, (Integer)Math.round(d100*Math.random()).toInteger()<=probability)
	return lZ
}

@Field static List<List<String>> cls1=[
	// attr          value      cmd1    cmd2
	['switch',       'off',     'on',   'off'],
	['door',       'closed',   'open', 'close'],
	['windowShade','closed',   'open', 'close'],
	['windowBlind','closed',   'open', 'close'],
	['valve',      'closed',   'open', 'close'],
	['alarm',       'off',     'siren', 'off'],
	['lock',       'unlocked', 'lock', 'unlock'],
	['mute',       'unmuted',  'mute', 'unmute']
]

private void smart_toggle(Map r9,device,Boolean prob=null){
	Boolean fnd; fnd=false
	String a0,c; c= 'toggle'
	if(device?.hasCommand(c)){
		fnd=true
	}else{
		List da= (List)device?.getSupportedAttributes()
		def b
		for(List<String> a in cls1){
			a0=a[iZ]
			b= da.find{ (String)it.name==a0 }
			if(b){
				Boolean t= prob==null ? (String)getDeviceAttributeValue(r9,device,a0)==a[i1] : !!prob
				c= t ? a[i2]:a[i3]
				if(device?.hasCommand(c)){
					fnd=true
					break
				}
			}
		}
	}
	if(fnd) executePhysicalCommand(r9,device,c)
	else warn "toggle not found for device $device",r9
}

private Long vcmd_toggleLevel(Map r9,device,List prms){
	Integer level=(Integer)prms[iZ]
	executePhysicalCommand(r9,device,sSTLVL,(Integer)getDeviceAttributeValue(r9,device,sLVL)==level ? iZ:level)
	return lZ
}

@CompileStatic
private Long do_adjustLevel(Map r9,device,List prms,String attr,String cmd,Integer val=null,Boolean big=false){
	Integer arg; arg=val!=null ? val:matchCastI(r9,prms[iZ])
	Integer psz=prms.size()
	String mat=psz>i1 ? sLs(prms,i1):sNL
	if(ntMatSw(r9,mat,device,cmd))return lZ
	Long delay=psz>i2 ? (Long)prms[i2]:lZ
	arg=arg+matchCastI(r9,getDeviceAttributeValue(r9,device,attr))
	Integer low=big ? 1000:iZ
	Integer hi=big ? 30000:100
	arg=(arg<low)? low:((arg>hi)? hi:arg)
	executePhysicalCommand(r9,device,cmd,arg,delay)
	if(delay>=lZ)return delay
	return lZ
}

private Long vcmd_adjustLevel(Map r9,device,List prms){ return do_adjustLevel(r9,device,prms,sLVL,sSTLVL) }

private Long vcmd_adjustInfraredLevel(Map r9,device,List prms){ return do_adjustLevel(r9,device,prms,sIFLVL,sSTIFLVL) }

private Long vcmd_adjustSaturation(Map r9,device,List prms){ return do_adjustLevel(r9,device,prms,sSATUR,sSTSATUR) }

private Long vcmd_adjustHue(Map r9,device,List prms){
	Integer hue=Math.round((Integer)prms[iZ]/d3d6).toInteger()
	return do_adjustLevel(r9,device,prms,sHUE,sSTHUE,hue)
}

private Long vcmd_adjustColorTemperature(Map r9,device,List prms){ return do_adjustLevel(r9,device,prms,sCLRTEMP,sSTCLRTEMP,null,true) }

@CompileStatic
private Long do_fadeLevel(Map r9,device,List prms,String attr,String cmd,Integer val=null,Integer val1=null,Boolean big=false){
	Integer startLevel,endLevel
	if(val==null){
		def d=prms[iZ]
		def d1=d!=null ? d:getDeviceAttributeValue(r9,device,attr)
		startLevel=matchCastI(r9,d1)
		endLevel=matchCastI(r9,prms[i1])
	}else{
		startLevel=val
		endLevel=val1
	}
	String mat=prms.size()>i3 ? sLs(prms,i3):sNL
	if(ntMatSw(r9,mat,device,cmd))return lZ
	Long duration=matchCastL(r9,prms[i2])
	Integer low=big ? 1000:iZ
	Integer hi=big ? 30000:100
	startLevel=(startLevel<low)? low:((startLevel>hi)? hi:startLevel)
	endLevel=(endLevel<low)? low:((endLevel>hi)? hi:endLevel)
	return vcmd_internal_fade(r9,device,cmd,startLevel,endLevel,duration)
}

private Long vcmd_fadeLevel(Map r9,device,List prms){ return do_fadeLevel(r9,device,prms,sLVL,sSTLVL) }

private Long vcmd_fadeInfraredLevel(Map r9,device,List prms){ return do_fadeLevel(r9,device,prms,sIFLVL,sSTIFLVL) }

private Long vcmd_fadeSaturation(Map r9,device,List prms){ return do_fadeLevel(r9,device,prms,sSATUR,sSTSATUR) }

private Long vcmd_fadeHue(Map r9,device,List prms){
	Integer startLevel=prms[iZ]!=null ? Math.round((Integer)prms[iZ]/d3d6).toInteger() : matchCastI(r9,getDeviceAttributeValue(r9,device,sHUE))
	Integer endLevel=Math.round((Integer)prms[i1]/d3d6).toInteger()
	return do_fadeLevel(r9,device,prms,sHUE,sSTHUE,startLevel,endLevel)
}

private Long vcmd_fadeColorTemperature(Map r9,device,List prms){
	return do_fadeLevel(r9,device,prms,sCLRTEMP,sSTCLRTEMP,null,null,true)
}

@CompileStatic
private Long vcmd_internal_fade(Map r9,device,String command,Integer startLevel,Integer endLevel,Long idur){
	Long duration=idur

	Long minInterval,interval
	minInterval=l500
	//this attempts to adjust for command delays ced
	Long ced=cedIs(r9)
	if(ced>lZ) minInterval=ced>minInterval ? ced:minInterval
	if(startLevel==endLevel || duration<minInterval){
		//if the fade is too fast, or not changing anything, go to the end level directly
		executePhysicalCommand(r9,device,command,endLevel)
		return lZ
	}
	Integer delta=endLevel-startLevel
	//the max number of steps we can do
	Integer steps; steps=delta>iZ ? delta:-delta
	//figure out the interval
	interval=Math.round((duration/steps).toDouble())
	if(interval<minInterval){
		//interval is too small adjust to do one change per minInterval
		steps=Math.floor((d1*duration/minInterval).toDouble()).toInteger()
		interval=Math.round((d1*duration/steps).toDouble())
	}
	String scheduleDevice=hashD(r9,device)
	Integer oldLevel=startLevel
	executePhysicalCommand(r9,device,command,startLevel)
	Map jq=[
		s:i1,
		cy:steps,
		f1C:command,
		f1P: startLevel,
		f1Padd: delta*d1/steps,
		f1ID:interval,
		f1D:interval,
		s2C:sNL,
		s2P: null,
		s2D:lZ,
		sDev:scheduleDevice,
		l1C:command,
		l1P: endLevel,
		l1D:l500,
		l2C:sNL,
		l2P: null,
		l2D:lZ
	]
	Long wt=stRepeat(r9,jq)
	return wt+750L
}

private Long vcmd_emulatedFlash(Map r9,device,List prms){ vcmd_flash(r9,device,prms) }

private Long vcmd_flash(Map r9,device,List prms){
	Long onDuration=matchCastL(r9,prms[iZ])
	Long offDuration=matchCastL(r9,prms[i1])
	//if the flash is too fast, ignore it
	if((onDuration+offDuration)<l500)return lZ

	String mat=prms.size()>i3 ? sLs(prms,i3):sNL
	String currentState=gtSwitch(r9,device)
	if(mat!=sNL && currentState!=mat)return lZ

	Integer cycles=matchCastI(r9,prms[i2])
	String firstCommand=currentState==sON ? sOFF:sON
	Long firstDuration=firstCommand==sON ? onDuration:offDuration
	String secondCommand=firstCommand==sON ? sOFF:sON
	Long secondDuration=firstCommand==sON ? offDuration:onDuration
	String scheduleDevice=hashD(r9,device)
	Map jq=[
		s:i1,
		cy:cycles,
		f1C:firstCommand,
		f1P: null,
		f1ID:lZ,
		f1D:firstDuration,
		s2C:secondCommand,
		s2P: null,
		s2D:secondDuration,
		sDev:scheduleDevice,
		l1C:currentState,
		l1P: [],
		l1D:l500,
		l2C:sNL,
		l2P: null,
		l2D:lZ
	]
	Long wt=stRepeat(r9,jq)
	return wt+750L
}

/**  return duration estimate */
@CompileStatic
private Long stRepeat(Map r9,Map jq){
	Integer start=iMs(jq,sS)
	Integer cycles=iMs(jq,'cy')
	String fCmd=(String)jq.f1C
	Long firstDuration,secondDuration,dur
	firstDuration=lMs(jq,'f1D')
	String sCmd=(String)jq.s2C
	secondDuration=lMs(jq,'s2D')

	//this attempts to add command delays ced
	Long ced=cedIs(r9)
	if(ced>lZ){
		firstDuration=ced>firstDuration ? ced:firstDuration
		if(sCmd)secondDuration=ced>secondDuration ? ced:secondDuration
	}

	dur=(Long)jq.f1ID
	Integer i
	for(i=start;i<=cycles;i++){
		dur+=firstDuration
		if(sCmd)dur+=secondDuration
	}
	dur+=(Long)jq.l1D+(Long)jq.l2D
	runRepeat(r9,jq)
	return dur
}

@CompileStatic
void runRepeat(Map r9,Map ijq){
	Map jq=[:]+ijq

	String scheduleDevice=(String)jq.sDev
	def device=getDevice(r9,scheduleDevice)
	if(device!=null){
		Integer start
		start=iMs(jq,sS)
		Integer cycles=iMs(jq,'cy')
		String fCmd=(String)jq.f1C

		def p1
		p1=jq.f1P
		Boolean doNotSend
		doNotSend=false
		Double i=(Double)jq.f1Padd
		if(i!=null){
			Integer p=(Integer)p1
			Integer oldL=Math.round(p+i*(start-i1)).toInteger()
			Integer newL=Math.round(p+i*start).toInteger()
			p1=newL
			if(oldL==newL)doNotSend=true
		}

		Long firstDuration=(Long)jq.f1D
		String sCmd=(String)jq.s2C
		def p2=jq.s2P
		Long secondDuration=(Long)jq.s2D

		Long dur
		dur=start==i1 ? (Long)jq.f1ID:lZ
		if(start<=cycles){
			if(!doNotSend){
				executePhysicalCommand(r9,device,fCmd,p1,dur,scheduleDevice,true)
				dur+=firstDuration
			}
			if(sCmd){
				executePhysicalCommand(r9,device,sCmd,p2,dur,scheduleDevice,true)
				dur+=secondDuration
			}
			start++
		}
		if(start>cycles){
			Long d=(Long)jq.l1D
			String c
			c=(String)jq.l1C
			if(c){
				executePhysicalCommand(r9,device,c,jq.l1P,dur,scheduleDevice,true)
				dur+=d
			}
			c=(String)jq.l2C
			if(c){
				executePhysicalCommand(r9,device,c,jq.l2P,dur,scheduleDevice,true)
			}
		}else{
			jq.s=start
			qrunRepeat(r9,dur,jq)
		}
	}
}

@CompileStatic
void qrunRepeat(Map r9,Long dur,Map jq){
	//void executePhysicalCo iN3
	Map statement=(Map)r9.curActn
	Map task=(Map)r9.curTsk

	List<Integer> cs=svCS(r9,statement)
	Map jq1
	jq1=[ // items to save for later requeues
		$:stmtNum(statement),
		cs:cs,
		task:stmtNum(task)
	]+jq
	if(statement[sTCP]) jq1+=[(sTCP):statement[sTCP]]

	Integer ps=svPS(statement)
	Long ttt=Math.round(wnow()*d1+dur)
	Map schedule=[
		(sT):ttt,
		(sS):stmtNum(statement),
		(sI):iN5,
		cs:cs,
		ps:ps,
		jq:jq1,
	]
	if(isEric(r9))trace wakeS('Requesting a repeat task',schedule),r9
	Boolean a=spshSch(r9,schedule)
}

private Long vcmd_flashLevel(Map r9,device,List prms){
	Integer level1=matchCastI(r9,prms[iZ])
	Long duration1=matchCastL(r9,prms[i1])
	Integer level2=matchCastI(r9,prms[i2])
	Long duration2=matchCastL(r9,prms[i3])
	Integer cycles=matchCastI(r9,prms[i4])
	String mat=prms.size()>i5 ? sLs(prms,i5):sNL
	String currentState=gtSwitch(r9,device)
	if(mat!=sNL && currentState!=mat)return lZ
	//if the flash is too fast, ignore it
	if((duration1+duration2)<l500)return lZ
	Integer currentLevel=(Integer)getDeviceAttributeValue(r9,device,sLVL)
	String scheduleDevice=hashD(r9,device)
	Map jq=[
		s:i1,
		cy:cycles,
		f1C:sSTLVL,
		f1P: [level1],
		f1ID:lZ,
		f1D:duration1,
		s2C:sSTLVL,
		s2P: [level2],
		s2D:duration2,
		sDev:scheduleDevice,
		l1C:sSTLVL,
		l1P: [currentLevel],
		l1D:l500,
		l2C:currentState,
		l2P: [],
		l2D:200L
	]
	Long wt=stRepeat(r9,jq)
	return wt+750L
}

private Long vcmd_flashColor(Map r9,device,List prms){
	Map color1=gtColor(r9,sLs(prms,iZ))
	Long duration1=matchCastL(r9,prms[i1])
	Map color2=gtColor(r9,sLs(prms,i2))
	Long duration2=matchCastL(r9,prms[i3])
	Integer cycles=matchCastI(r9,prms[i4])
	String mat=prms.size()>i5 ? sLs(prms,i5):sNL
	String currentState=gtSwitch(r9,device)
	if(mat!=sNL && currentState!=mat)return lZ
	//if the flash is too fast, ignore it
	if((duration1+duration2)<l500)return lZ
	String scheduleDevice=hashD(r9,device)
	Map jq=[
		s:i1,
		cy:cycles,
		f1C:sSTCLR,
		f1P: [color1],
		f1ID:lZ,
		f1D:duration1,
		s2C:sSTCLR,
		s2P: [color2],
		s2D:duration2,
		sDev:scheduleDevice,
		l1C:currentState,
		l1P: [],
		l1D:l500,
		l2C:sNL,
		l2P: [],
		l2D:lZ
	]
	Long wt=stRepeat(r9,jq)
	return wt+750L
}

private Long vcmd_sendNotification(Map r9,device,List prms){
	String message="Hubitat does not support sendNotification "+sLs(prms,iZ)
	Map a=log(message,r9,iN2,null,sWARN,true)
	//sendNotificationEvent(message)
	return lZ
}

private Long vcmd_sendPushNotification(Map r9,device,List prms){
	String message; message=sLs(prms,iZ)
	if(r9.initPush==null){
		r9.pushDev=wgetPushDev()
		r9.initPush=true
	}
	List t0=(List)r9.pushDev
	try{
		t0*.deviceNotification(message)
	}catch(ignored){
		message="Default push device not set properly in webCoRE "+message
		error message,r9
	}
	return lZ
}

private Long vcmd_sendSMSNotification(Map r9,device,List prms){
	String msg; msg=sLs(prms,iZ)
	msg="HE SMS notifications are being removed,please convert to a notification device "+msg
	warn msg,r9
	return lZ
}

private Long vcmd_sendNotificationToContacts(Map r9,device,List prms){
	// HE does not have Contact Book; falling back onto PUSH notifications
	String message=sLs(prms,iZ)
	Boolean save=!!prms[i2]
	return vcmd_sendPushNotification(r9,device,[message,save])
}

@CompileStatic
private static Map<String,String> parseVariableName(String name){
	Map res; res=[
		(sNM): name,
		index: sNL
	]
	if(name!=sNL && !name.startsWith(sDLR) && name.endsWith(sRB)){
		List<String> parts=name.replace(sRB,sBLK).tokenize(sLB)
		if(parts.size()==i2){
			res=[
				(sNM): parts[iZ],
				index: parts[i1]
			]
		}
	}
	return res
}

@CompileStatic
private Long vcmd_setVariable(Map r9,device,List prms){
	String name=sLs(prms,iZ)
	def value=prms[i1]
	Map t0=setVariable(r9,name,value)
	if(sMt(t0)==sERROR){
		String message=sMs(t0,sV)+sSPC+name
		error message,r9
	}
	return lZ
}

private Long vcmd_executePiston(Map r9,device,List prms){
	String selfId=(String)r9[sID]
	String pistonId=sLs(prms,iZ)
	List<String> arguments=(prms[i1] instanceof List ? (List<String>)prms[i1]:prms[i1].toString().tokenize(sCOMMA)).unique()
	//noinspection GroovyAssignabilityCheck
	Boolean wait; wait=prms.size()>i2 ? bcast(r9,prms[i2]):false
	String desc="webCoRE: Piston ${gtAppN()} requested execution of piston $pistonId".toString()
	Map data=[:]
	for(String argument in arguments) if(argument)data[argument]=getVariable(r9,argument).v
	if(wait) wait=wexecutePiston(pistonId,data,selfId)
	if(!wait) sendExecuteEvt(pistonId,selfId,desc,data)
	return lZ
}

private Long vcmd_pausePiston(Map r9,device,List prms){
	String selfId=(String)r9[sID]
	String pistonId=sLs(prms,iZ)
	if(!wpausePiston(pistonId,selfId)){
		String message="Piston not found "+pistonId
		error message,r9
	}
	return lZ
}

private Long vcmd_resumePiston(Map r9,device,List prms){
	String selfId=(String)r9[sID]
	String pistonId=sLs(prms,iZ)
	if(!wresumePiston(pistonId,selfId)){
		String message="Piston not found "+pistonId
		error message,r9
	}
	return lZ
}

/* wrappers */
private Long vcmd_executeRule(Map r9,device,List prms){
	String ruleId=sLs(prms,iZ)
	String action=sLs(prms,i1)
	//Boolean wait=(prms.size()>i2)? bcast(r9,prms[i2]):false
	String ruleAction
	ruleAction=sNL
	if(action=="Run")ruleAction="runRuleAct"
	if(action=="Stop")ruleAction="stopRuleAct"
	if(action=="Pause")ruleAction="pauseRule"
	if(action=="Resume")ruleAction="resumeRule"
	if(action=="Evaluate")ruleAction="runRule"
	if(action=="Set Boolean True")ruleAction="setRuleBooleanTrue"
	if(action=="Set Boolean False")ruleAction="setRuleBooleanFalse"
	if(!ruleAction){
		String message="No Rule action found "+action
		error message,r9
	}else{
		Boolean sent; sent=false
		for(String ver in ['4.1','5.0']){
			List<Map> rules=RMUtils.getRuleList(ver ?: sNL)
			List myRule; myRule=[]
			for(rule in rules){
				List t0=rule.find{ hashId(r9,(String)it.key)==ruleId }.collect{(String)it.key}
				myRule+=t0
			}
			if(myRule){
				RMUtils.sendAction(myRule,ruleAction,gtAppN(),ver ?: sNL)
				sent=true
			}
		}
		if(!sent){
			String message="Rule not found "+ruleId
			error message,r9
		}
	}
	return lZ
}

private Long vcmd_setHSLColor(Map r9,device,List prms){
	Integer hue=Math.round((Integer)prms[iZ]/d3d6).toInteger()
	Integer saturation=(Integer)prms[i1]
	Integer level=(Integer)prms[i2]
	Map color=[
		(sHUE): hue,
		(sSATUR): saturation,
		(sLVL): level
	]
	Integer psz=prms.size()
	String mat=psz>i3 ? sLs(prms,i3):sNL
	if(ntMatSw(r9,mat,device,'setHSLColor'))return lZ
	Long delay=psz>i4 ? (Long)prms[i4]:lZ
	executePhysicalCommand(r9,device,sSTCLR,color,delay)
	if(delay>=lZ)return delay
	return lZ
}

private Long vcmd_wolRequest(Map r9,device,List prms){
	String mac; mac=sLs(prms,iZ)
	String secureCode=sLs(prms,i1)
	mac=mac.replace(sCLN,sBLK).replace(sMINUS,sBLK).replace(sDOT,sBLK).replace(sSPC,sBLK).toLowerCase()

	sendHubCommand(HubActionClass().newInstance(
		"wake on lan $mac".toString(),
		HubProtocolClass().LAN,
		null,
		secureCode ? [secureCode: secureCode]:[:]
	))
	return lZ
}

private Long vcmd_iftttMaker(Map r9,device,List prms){
	String key; key=sNL
	if(r9.settings==null){
		error "no settings",r9
	}else{
		key=((String)((Map)r9.settings).ifttt_url ?: sBLK).trim().replace('https://',sBLK).replace('http://',sBLK).replace('maker.ifttt.com/use/',sBLK)
	}
	if(!key){
		error "Failed to send IFTTT event, because the IFTTT integration is not properly set up. Please visit Settings in your webCoRE dashboard and configure the IFTTT integration.",r9
		return lZ
	}
	String event=prms[iZ]
	Integer psz=prms.size()
	def v1=psz>i1 ? prms[i1]:sBLK
	def v2=psz>i2 ? prms[i2]:sBLK
	def v3=psz>i3 ? prms[i3]:sBLK
	def body=[:]
	if(v1)body.value1=v1
	if(v2)body.value2=v2
	if(v3)body.value3=v3
	Map data=[
		t:event,
		p1:v1,
		p2:v2,
		p3:v3
	]
	Map requestParams=[
		uri: "https://maker.ifttt.com/trigger/${java.net.URLEncoder.encode(event,"UTF-8")}/with/key/".toString()+key,
		requestContentType: sAPPJSON,
		body: body,
		timeout:i20
	]
	try{
		asynchttpPost('ahttpRequestHandler',requestParams,[command:sIFTTM,em: data])
		return 24000L
	}catch(all){
		error "Error iftttMaker to ${requestParams.uri} ${data.t}: ${data.p1}, ${data.p2}, ${data.p3}",r9,iN2,all
	}
	return lZ
}


private Long do_lifx(Map r9,String cmd,String path,Map body,duration,String c){
	String token=((Map)r9.settings)?.lifx_token
	if(!token){
		error "Sorry, enable the LIFX integration in the dashboard's Settings section before trying to execute a LIFX operation.",r9
		return lZ
	}
	Map requestParams=[
		uri: "https://api.lifx.com",
		path: path,
		headers: [ "Authorization": "Bearer $token" ],
		requestContentType: sAPPJSON,
		timeout:i10,
		body: body
	]
	try{
		if(isDbg(r9))debug "Sending lifx ${c} web request to: $path",r9
		(void)"asynchttp${cmd}"('ahttpRequestHandler',requestParams,[command:sLIFX,em: [(sT):c]])
		Long ldur=duration ? Math.round(duration * d1000):lZ
		return ldur>11000L ? ldur:11000L
	}catch(all){
		error "Error while activating LIFX $c:",r9,iN2,all
	}
	return lZ
}

private Long vcmd_lifxScene(Map r9,device,List prms){
	String sceneId; sceneId=sLs(prms,iZ)
	Long duration=prms.size()>i1 ? Math.round( matchCastL(r9,prms[i1]) / d1000):lZ
	Map scn=(Map)r9.lifx?.scenes
	if(!scn){
		error "Sorry, there seems to be no available LIFX scenes, please ensure the LIFX integration is working.",r9
		return lZ
	}
	sceneId=scn.find{ (String)it.key==sceneId || (String)it.value==sceneId }?.key
	if(!sceneId){
		error "Sorry, could not find the specified LIFX scene.",r9
		return lZ
	}
	String path="/v1/scenes/scene_id:${sceneId}/activate"
	Map body=duration ? [duration: duration]:null
	return do_lifx(r9,'Put',path,body,duration,'scene')
}

private Long lifxErr(Map r9){
	error "Sorry, could not find the specified LIFX selector.",r9
	return lZ
}

private static String getLifxSelector(Map r9,String selector){
	String selectorId=sBLK
	if(selector=='all')return selector
	Integer i; i=iZ
	List<String> a=['scene_',sBLK,'group_','location_']
	for(String m in ['scenes','lights','groups','locations']){
		String obj=((Map)r9.lifx."${m}")?.find{ it.key==selector || it.value==selector }?.key
		if(obj)return "${a[i]}id:${obj}".toString()
		i+=i1
	}
	return selectorId
}

private Long vcmd_lifxState(Map r9,device,List prms){
	String selector=getLifxSelector(r9,sLs(prms,iZ))
	if(!selector)return lifxErr(r9)
	String power=sLs(prms,i1)
	Map color=gtColor(r9,sLs(prms,i2))
	Integer level=(Integer)prms[i3]
	Integer infrared=(Integer)prms[i4]
	Long duration=Math.round( matchCastL(r9,prms[i5]) / d1000 )
	String path="/v1/lights/${selector}/state"
	Map body= [:]+(power ? ([power: power]) : [:])+(color ? ([color: color.hex]) : [:])+(level!=null ? ([brightness: level / d100]) : [:])+(infrared!=null ? [infrared: infrared] : [:])+(duration ? [duration: duration] : [:])
	return do_lifx(r9,'Put',path,body,duration,sST)
}

private Long vcmd_lifxToggle(Map r9,device,List prms){
	String selector=getLifxSelector(r9,sLs(prms,iZ))
	if(!selector)return lifxErr(r9)
	Long duration=Math.round( matchCastL(r9,prms[i1]) / d1000 )
	String path="/v1/lights/${selector}/toggle"
	Map body= [:]+(duration ? [duration: duration]:[:])
	return do_lifx(r9,'Post',path,body,duration,'toggle')
}

private Long vcmd_lifxBreathe(Map r9,device,List prms){
	String selector=getLifxSelector(r9,sLs(prms,iZ))
	if(!selector)return lifxErr(r9)
	Map color=gtColor(r9,sLs(prms,i1))
	Map fromColor= prms[i2]==null ? null:gtColor(r9,sLs(prms,i2))
	Long period= prms[i3]==null ? null:Math.round( matchCastL(r9,prms[i3]) / d1000)
	Integer cycles=(Integer)prms[i4]
	Integer peak=(Integer)prms[i5]
	Boolean powerOn= prms[i6]==null ? null:bcast(r9,prms[i6])
	Boolean persist= prms[i7]==null ? null:bcast(r9,prms[i7])
	String path="/v1/lights/${selector}/effects/breathe"
	Map body= [color: color.hex]+(fromColor ? ([from_color: fromColor.hex]) : [:])+(period!=null ? ([period: period]) : [:])+(cycles ? ([cycles: cycles]) : [:])+(powerOn!=null ? ([power_on: powerOn]) : [:])+(persist!=null ? ([persist:persist]) : [:])+(peak!=null ? ([peak: peak / 100]) : [:])
	Long ldur=Math.round( (period ? period:i1) * (cycles ? cycles:i1) )
	return do_lifx(r9,'Post',path,body,ldur,'breathe')
}

private Long vcmd_lifxPulse(Map r9,device,List prms){
	String selector=getLifxSelector(r9,sLs(prms,iZ))
	if(!selector)return lifxErr(r9)
	Map color=gtColor(r9,sLs(prms,i1))
	Map fromColor= prms[i2]==null ? null:gtColor(r9,sLs(prms,i2))
	Long period= prms[i3]==null ? null:Math.round( matchCastL(r9,prms[i3]) / d1000)
	Integer cycles=(Integer)prms[i4]
	Boolean powerOn= prms[i5]==null ? null:bcast(r9,prms[i5])
	Boolean persist= prms[i6]==null ? null:bcast(r9,prms[i6])
	String path="/v1/lights/${selector}/effects/pulse"
	Map body= [color: color.hex]+(fromColor ? ([from_color: fromColor.hex]) : [:])+(period!=null ? ([period: period]) : [:])+(cycles ? ([cycles: cycles]) : [:])+(powerOn!=null ? ([power_on: powerOn]) : [:])+(persist!=null ? ([persist:persist]) : [:])
	Long ldur=Math.round( (period ? period:i1) * (cycles ? cycles:i1) )
	return do_lifx(r9,'Post',path,body,ldur,'pulse')
}

private Long vcmd_httpRequest(Map r9,device,List prms){
	String auth,cntntT,uri
	uri=(sLs(prms,iZ)).replace(sSPC,"%20")
	if(!uri){
		error "Error executing external web request:no URI",r9
		return lZ
	}
	String method=sLs(prms,i1)
	Boolean useQryS= (method in [sGET,sDELETE,sHEAD])
	String reqBodyT=sLs(prms,i2)
	def variables=prms[i3]
	auth=sNL
	def requestBody,data
	requestBody=null
	cntntT=sNL
	if(prms.size()==i5){
		auth=sLs(prms,i4)
	}else if(prms.size()==i7){
		requestBody=sLs(prms,i4)
		cntntT=sLs(prms,i5) ?: 'text/plain'
		auth=sLs(prms,i6)
	}
	String protocol,userPart,func
	protocol="https"
	String reqCntntT=(method==sGET || reqBodyT=='FORM')? sAPPFORM : (reqBodyT=='JSON')? sAPPJSON:cntntT
	userPart=sBLK
	String[] uriParts=uri.split("://")
	if(uriParts.size()>i2){
		warn "Invalid URI for web request:$uri",r9
		return lZ
	}
	if(uriParts.size()==i2){
		//remove the httpX:// from the uri
		protocol=(String)uriParts[iZ].toLowerCase()
		uri=(String)uriParts[i1]
	}
	//support for user:pass@IP
	if(uri.contains(sAT)){
		String[] uriSubParts= uri.split(sAT as String)
		userPart=uriSubParts[iZ]+sAT
		uri=uriSubParts[i1]
	}
	data=null
	if(reqBodyT=='CUSTOM' && !useQryS){
		data=requestBody
	}else if(variables instanceof List){
		for(String variable in ((List)variables).findAll{ !!it }){
			data=data ?: [:]
			data[variable]=getVariable(r9,variable).v
		}
	}
	if(!useQryS && reqCntntT==sAPPFORM && data instanceof Map){
		data=data.collect{ String k,v -> encodeURIComponent(k)+'='+encodeURIComponent(v) }.join(sAMP)
	}
	try{
		Map requestParams=[
			uri: protocol+'://'+userPart+uri,
			query: useQryS ? data:null,
			headers: (auth ? (stJson(auth)? (new JsonSlurper().parseText(auth)):[Authorization: auth]):[:]),
			contentType: '*/*',
			requestContentType: reqCntntT,
			body: !useQryS ? data:null,
			timeout:i20
		]
		func=sBLK
		switch(method){
			case sGET:
				func='asynchttpGet'
				break
			case 'POST':
				func='asynchttpPost'
				break
			case 'PUT':
				func='asynchttpPut'
				break
			case sDELETE:
				func='asynchttpDelete'
				break
			case sHEAD:
				func='asynchttpHead'
				break
		}
		if(isDbg(r9))debug "Sending ${func} web request to: $uri",r9
		if(func!=sBLK){
			(void)"$func"('ahttpRequestHandler',requestParams,[command:sHTTPR])
			return 24000L
		}
	}catch(all){
		error "Error executing external web request:",r9,iN2,all
	}
	return lZ
}

void ahttpRequestHandler(resp,Map callbackData){
	Boolean binary; binary=false
	def t0=resp.getHeaders()
	String t1=t0!=null ? (String)t0."Content-Type":sNL
	String mediaType; mediaType=t1 ? t1.toLowerCase()?.tokenize(';')[iZ] :sNL
	switch(mediaType){
		case 'image/jpeg':
		case 'image/png':
		case 'image/gif':
			binary=true
	}
	def data; data=null
	def json=null
	Map setRtData; setRtData=[:]
	String callBackC=(String)callbackData?.command
	Integer responseCode; responseCode=resp.status

	String erMsg; erMsg=sNL
	if(resp.hasError()){
		erMsg=" Response Status: ${resp.status} error Message: ${resp.getErrorMessage()}".toString()
		if(!responseCode) responseCode=500
	}
	Boolean respOk=(responseCode>=200 && responseCode<300)

	switch(callBackC){
		case sHTTPR:
			if(responseCode==204){ // no content
				mediaType=sBLK
			}else{
				if(respOk && resp.data){
					if(!binary){
						data=resp.data
						if(data!=null && !(data instanceof Map) && !(data instanceof List)){
							def ndata=parseMyResp(data,mediaType)
							if(ndata) data=ndata
						}
					}else{
						if(resp.data!=null && resp.data instanceof java.io.ByteArrayInputStream){
							setRtData.mediaType=mediaType
							setRtData.mediaData=resp.data.decodeBase64() // HE binary data is b64encoded resp.data.getBytes()
						}
					}
				}else erMsg='http'+erMsg
			}
			break
		case sLIFX:
			Map em=(Map)callbackData?.em
			if(!respOk) erMsg="lifx Error lifx sending ${em?.t}".toString()+erMsg
			break
		case sSENDE:
			String msg
			msg='Unknown error'
			Map em=(Map)callbackData?.em
			Boolean success
			success=false
			if(respOk){
				data=resp.getJson()
				if(data!=null){
					if(sMs(data,'result')=='OK') success=true
					else msg=sMs(data,'result').replace('ERROR ',sBLK)
				}
			}
			if(!success) erMsg="Error sending email to ${em?.t}: ${msg}".toString()
			break
		case sIFTTM:
			Map em=(Map)callbackData?.em
			if(!respOk) erMsg="ifttt Error iftttMaker to ${em?.t}: ${em?.p1},${em?.p2},${em?.p3} ".toString()+erMsg
			break
		case sSTOREM:
			String mediaId,mediaUrl
			mediaId=sNL; mediaUrl=sNL
			if(respOk){
				data=resp.getJson()
				if(sMs(data,'result')=='OK' && data.url){
					mediaId=sMs(data,sID)
					mediaUrl=sMs(data,'url')
				}else if(sMs(data,'message')) erMsg="storeMedia Error storing media item: $data.message"+erMsg
				data=null
			}else erMsg='storeMedia'+erMsg
			setRtData=[mediaId:mediaId,mediaUrl:mediaUrl]
	}
	if(erMsg!=sNL) error erMsg,null

	handleEvents([(sDATE):new Date(),(sDEV):gtLocation(),(sNM):sASYNCREP,(sVAL):callBackC,contentType:mediaType,responseData:data,jsonData:json,responseCode:responseCode,setRtData:setRtData])
}

private parseMyResp(aa,String mediaType=sNL){
	def ret
	ret=null
	if(aa instanceof String || aa instanceof GString){
		String a=aa.toString()
		Boolean expectJson= mediaType ? mediaType.contains('json'):false
		try{
			if(stJson(a)){
				ret=(LinkedHashMap)new JsonSlurper().parseText(a)
			}else if(a.startsWith(sLB) && a.endsWith(sRB)){
				ret=(List)new JsonSlurper().parseText(a)
			}else if(expectJson || (mediaType in ['application/octet-stream'] && a.size()%i4==iZ) ){ // HE can return data Base64
				String dec=new String(a.decodeBase64())
				if(dec!=sNL){
					def t0=parseMyResp(dec,sBLK)
					ret=t0==null ? dec:t0
				}
			}
		}catch(ignored){}
	}
	return ret
}

private Map securityLogin(String u, String p){
	Boolean res; res=false
	String cookie; cookie=sNL
	try{
		Map params= [
			uri: "http://127.0.0.1:8080",
			path: "/login",
			query: [ loginRedirect: "/" ],
			body: [
				username: u,
				password: p,
				submit: "Login"
			],
			textParser: true,
			ignoreSSLIssues: true
		]
		httpPost(params) { resp ->
			if(resp.data?.text?.contains("The login information you supplied was incorrect."))
				res= false
			else{
				String[] resu= ((String)resp?.headers?.'Set-Cookie')?.split(';')
				cookie= resu.size() ? resu[0] : (String)null
				res= true
			}
		}
	}catch (e){
		log.error "Error logging in: ${e}"
	}
	return [result: res, cookie: cookie]
}

@Field static Map<String,String> readTmpFLD=[:]
@Field static Map<String,String> readDataFLD=[:]

private Boolean readFile(Map r9,List prms,Boolean data){
	String name=sLs(prms,iZ)
	String user=sLs(prms,i1)
	String pass=sLs(prms,i2)
	String pNm=sMs(r9,snId)

	if(data)readDataFLD[pNm]=sBLK else readTmpFLD[pNm]=sBLK

	String cookie=sNL
	if(user && pass) cookie=securityLogin(user,pass).cookie
	String uri = "http://${location.hub.localIP}:8080/local/${name}".toString()

	Map params=[
		uri: uri,
		//contentType: "text/html",
		contentType: "text/plain; charset=UTF-8",
		textParser: true,
		headers: [ "Cookie": cookie, "Accept": 'application/octet-stream' ]
	]

	try{
		httpGet(params){ resp ->
			if(resp.status==200 && resp.data){
				Integer i
				char c
				i=resp.data.read()
				while(i!=-1){
					c=(char)i
					if(data) readDataFLD[pNm]+=c
					else readTmpFLD[pNm]+=c
					i=resp.data.read()
				}
				//log.warn "pNm: ${pNm} data: ${data}  file: ${readDataFLD[pNm]}"
			}else{
				error "Read Response status $resp.status",r9
			}
		}
		return true
	}catch(e){
		String s="Error reading file $name: "
		if( ((String)e.message).contains("Not Found") ){
			error s+"Not found",r9
		} else error s,r9,iN2,e
	}
	if(data)readDataFLD[pNm]=sNL else readTmpFLD[pNm]=sNL
	return false
}

private Long vcmd_readFile(Map r9,device,List prms){
	Boolean a=readFile(r9,prms,true)
	return lZ
}

private Long vcmd_appendFile(Map r9,device,List prms){
	String name=sLs(prms,iZ)
	String user=sLs(prms,i2)
	String pass=sLs(prms,i3)
	String pNm=sMs(r9,snId)
	Boolean ws
	try {
		if(readFile(r9,[name,user,pass],false)){
			Integer sz=readTmpFLD[pNm].length()
			if(sz<=0) readTmpFLD[pNm]=sSPC
			readTmpFLD[pNm]+=sLs(prms,i1)
			ws=writeFile(r9,[name,readTmpFLD[pNm],sNL,sNL])
		}
	}catch(e){
		if( ((String)e.message).contains("Not Found") ){
			ws=writeFile(r9,[name,sLs(prms,i1),sNL,sNL])
			if(eric())log.info "Append FNF write Status: $ws"
		}else{
			error "Error appending file $name: ${e}",r9,iN2,e
		}
	}
	readTmpFLD[pNm]=sNL
	data=sNL
	return lZ
}

private Boolean fileExists(Map r9,String name){
	String uri="http://${location.hub.localIP}:8080/local/${name}".toString()
	Map params=[ uri: uri ]

	Boolean res=false
	try{
		httpGet(params) { resp ->
			if(resp.status!=200){
				if(eric())log.info "File Exist: false"
			}else{
				if(eric())log.info "File Exist: true"
				res=true
			}
		}
	}catch(e){
		if( ((String)e.message).contains("Not Found") ){
			//error "Error file exists $name: ${e}",r9
		}else error "Error file exists $name: ",r9,iN2,e
		if(eric())log.info "File Exist excptn: false"
	}
	return res
}

private Boolean writeFile(Map r9,List prms) {
	String name=sLs(prms,iZ)
	//String user=sLs(prms,i2)
	//String pass=sLs(prms,i3)

	Date d=new Date()
	String encodedString = "thebearmay$d".bytes.encodeBase64().toString()
	try {
		Map params = [
			uri		: 'http://127.0.0.1:8080',
			path	: '/hub/fileManager/upload',
			query	: [ 'folder': '/' ],
			headers	: [ 'Content-Type': "multipart/form-data; boundary=$encodedString" ],
			body	: """--${encodedString}
Content-Disposition: form-data; name="uploadFile"; filename="${name}"
contentType: "text/plain; charset=UTF-8"

${sLs(prms,i1)}

--${encodedString}
Content-Disposition: form-data; name="folder"


--${encodedString}--""",
			timeout	: 300,
			ignoreSSLIssues: true
		]
		//data=sNL
		httpPost(params) { resp ->
			if(resp.status!=200){
				error "Write Response status $resp.status",r9
			}
		}
		return true
	}
	catch(e){
		error "Error writing file $name: ${e}",r9,iN2,e
	}
	return false
}

private Long vcmd_writeFile(Map r9,device,List prms){
	Boolean a=writeFile(r9,prms)
	return lZ
}

@Field static Map<String,List<Map>> fuelDataFLD=[:]

private Long vcmd_readFuelStream(Map r9,device,List prms){
	String canister=sLs(prms,iZ)
	String name=sLs(prms,i1)

	Map req=[
			c: canister,
			n: name,
			s: null,
			d: null,
			i: (String)r9.instanceId
	]
	String pNm=sMs(r9,snId)
	fuelDataFLD[pNm]=[]
	if(bIs(r9,'useLocalFuelStreams') && name!=sNL) fuelDataFLD[pNm]=(List)parent.readFuelStream(req) // store in $fuel
	return lZ
}

private Long vcmd_writeFuelStream(Map r9,device,List prms){
	String canister=sLs(prms,iZ)
	String name=sLs(prms,i1)
	//def data=prms[i2]
	// blow the stack??
	def source=prms[i3]

	Map req=[
			c: canister,
			n: name,
			s: source,
			d: prms[i2],
			i: (String)r9.instanceId
	]
	if(bIs(r9,'useLocalFuelStreams') && name!=sNL) parent.writeFuelStream(req)
	return lZ
}

private Long vcmd_clearFuelStream(Map r9,device,List prms){
	String canister=sLs(prms,iZ)
	String name=sLs(prms,i1)
	def source=prms[i2]

	Map req=[
			c: canister,
			n: name,
			s: source,
			d: null,
			i: (String)r9.instanceId
	]
	if(bIs(r9,'useLocalFuelStreams') && name!=sNL) parent.clearFuelStream(req)
	return lZ
}
/* wrappers */
private Long vcmd_writeToFuelStream(Map r9,device,List prms){
	String canister=sLs(prms,iZ)
	String name=sLs(prms,i1)
	def data=prms[i2]
	def source=prms[i3]

	Map req=[
		c: canister,
		n: name,
		s: source,
		d: data,
		i: (String)r9.instanceId
	]
	if(bIs(r9,'useLocalFuelStreams') && name!=sNL) parent.writeToFuelStream(req)
	else{
		Map requestParams=[
			uri: "https://api-"+(String)r9.region+'-'+((String)r9.instanceId)[32]+".webcore.co:9247",
			path: "/fuelStream/write",
			headers: [ 'ST': (String)r9.instanceId ],
			body: req,
			contentType: sAPPJSON,
			requestContentType: sAPPJSON,
			timeout:i20
		]
		asynchttpPut('asyncFuel',requestParams,[bbb:iZ])
	}
	return lZ
}

void asyncFuel(response,data){
	if(response.status!=200) error "Error storing fuel stream: $response?.data?.message",null
}

private Long vcmd_storeMedia(Map r9,device,List prms){
	if(!r9.mediaData || !r9.mediaType || (Integer)r9.mediaData.size()<=iZ){
		error 'No media is available to store; operation aborted.',r9
		return lZ
	}
	String data=new String(r9.mediaData as byte[],'ISO_8859_1')
	Map requestParams=[
		uri: "https://api-"+(String)r9.region+'-'+((String)r9.instanceId)[32]+".webcore.co:9247",
		path: "/media/store",
		headers: [
			'ST':(String)r9.instanceId,
			'media-type':r9.mediaType
		],
		body: data,
		requestContentType: r9.mediaType,
		timeout:i20
	]
	asynchttpPut('asyncRequestHandler',requestParams,[command:sSTOREM])
	return 24000L
}

private String canisterS(Map r9,device,List prms){ return (prms.size()>i1 ? scast(r9,prms[i1])+sCLN:sBLK)+hashD(r9,device)+sCLN }

private Long vcmd_saveStateLocally(Map r9,device,List prms,Boolean global=false){
	List<String> attributes=scast(r9,prms[iZ]).tokenize(sCOMMA)
	String canister=canisterS(r9,device,prms)
	//noinspection GroovyAssignabilityCheck
	Boolean overwrite=!(prms.size()>i2 ? bcast(r9,prms[i2]):false)
	for(String attr in attributes){
		String n=canister+attr
		if(global && !bIs(r9,'initGStore')){
			r9.globalStore=wgetGStore()
			r9.initGStore=true
		}
		if(overwrite || (global ? (r9.globalStore[n]==null):(r9[sSTORE][n]==null))){
			def value=getDeviceAttributeValue(r9,device,attr)
			if(attr==sHUE)value=value*d3d6
			if(global){
				r9.globalStore[n]=value
				LinkedHashMap cache= (LinkedHashMap)r9.gvStoreCache ?: [:] as LinkedHashMap
				cache[n]=value
				r9.gvStoreCache=cache
			}else r9[sSTORE][n]=value
		}
	}
	return lZ
}

private Long vcmd_saveStateGlobally(Map r9,device,List prms){ return vcmd_saveStateLocally(r9,device,prms,true) }

private Long vcmd_loadStateLocally(Map r9,device,List prms,Boolean global=false){
	List<String> attributes=scast(r9,prms[iZ]).tokenize(sCOMMA)
	String canister=canisterS(r9,device,prms)
	//noinspection GroovyAssignabilityCheck
	Boolean empty=prms.size()>i2 ? bcast(r9,prms[i2]):false

	Map sD=[:]
	List<String> newattrs=[]
	List vals=[]

	for(String attr in attributes){
		String n=canister+attr
		if(global && !bIs(r9,'initGStore')){
			r9.globalStore=wgetGStore()
			r9.initGStore=true
		}
		def value; value=global ? r9.globalStore[n]:r9[sSTORE][n]
		def a
		if(empty){
			if(global){
				a=((Map)r9.globalStore).remove(n)
				Map cache=(Map)r9.gvStoreCache ?: [:]
				cache[n]=null
				r9.gvStoreCache=cache
			}else a=((Map)r9[sSTORE]).remove(n)
		}
		if(value==null)continue

		if(attr==sHUE)value=dcast(r9,value)/d3d6
		if(attr in [sSWITCH,sLVL,sSATUR,sHUE,sCLRTEMP]) sD[attr]=value
		else{
			newattrs.push(attr)
			a=vals.push(value)
		}
	}

	Boolean lg=isDbg(r9)
	String msg='loadState '
	if(isEric(r9)&& sD)debug msg+"${sD}",r9
	Boolean wOn= sD.containsKey(sSWITCH) ? (String)sD[sSWITCH]==sON :null
	Boolean isOn
	isOn= sD.containsKey(sSWITCH) ? gtSwitch(r9,device)==sON :null
	Boolean chgHSL= sD.containsKey(sLVL) && sD.containsKey(sSATUR) && sD.containsKey(sHUE)
	Boolean chgLvl= !chgHSL && sD.containsKey(sLVL) ? true : null
	Boolean chgCtemp= sD.containsKey(sCLRTEMP) ?: null
	String scheduleDevice= hashD(r9,device)
	Long del
	del=lZ
	if((chgLvl || chgCtemp) && isOn==false){
		executePhysicalCommand(r9,device,sON)
		isOn=true
		del=1500L
	}
	if(chgHSL){
		executePhysicalCommand(r9,device,sSTCLR,[(sHUE):sD[sHUE], (sSATUR):sD[sSATUR], (sLVL):sD[sLVL]],del,scheduleDevice)
		del+=100L
	}
	if(chgLvl){
		List larg=[sD[sLVL]]
		executePhysicalCommand(r9,device,sSTLVL,larg,del,scheduleDevice)
		del+=100L
	}
	if(chgCtemp){
		List larg=[sD[sCLRTEMP]]
		executePhysicalCommand(r9,device,sSTCLRTEMP,larg,del,scheduleDevice)
		del+=100L
	}

	def value
	Integer cnt
	cnt=0
	String exactCommand,fuzzyCommand,fuzzyCommand1,t0
	for(String attr in newattrs){
		value=vals[cnt]
		cnt+=i1
		exactCommand=sNL
		fuzzyCommand=sNL
		fuzzyCommand1=sNL
		t0="Restoring attribute '$attr' to value '$value'".toString()
		for(command in PhysicalCommands()){
			if(sMa(command.value)==attr){
				if(command.value.v==null) fuzzyCommand=(String)command.key
				else
					if((String)command.value.v==value){
						t0+=" using command".toString()
						exactCommand=(String)command.key
						break
					}
			}
			if((String)command.value.v==value) // values like open/closed are overloaded to different attributes
				fuzzyCommand1=(String)command.key
		}
		if(exactCommand!=sNL){
			if(lg)debug "${t0} $exactCommand()",r9
			executePhysicalCommand(r9,device,exactCommand,null,del,scheduleDevice)
			del+=100L
			continue
		}
		if(fuzzyCommand!=sNL){
			if(lg)debug "${t0} $fuzzyCommand($value)",r9
			executePhysicalCommand(r9,device,fuzzyCommand,value,del,scheduleDevice)
			del+=100L
			continue
		}
		if(fuzzyCommand1!=sNL){
			if(lg)debug "${t0} $fuzzyCommand1()",r9
			executePhysicalCommand(r9,device,fuzzyCommand1,null,del,scheduleDevice)
			del+=100L
			continue
		}
		warn "Could not find a command to set attribute '$attr' to value '$value'",r9
	}
	if(wOn!=null && wOn!=isOn) {
		del+= wOn ? lZ:1200L
		executePhysicalCommand(r9,device,sMs(sD,sSWITCH),null,del,scheduleDevice)
	}
	return del
}

private Long vcmd_loadStateGlobally(Map r9,device,List prms){ return vcmd_loadStateLocally(r9,device,prms,true) }

private Long vcmd_parseJson(Map r9,device,List prms){
	String data=sLs(prms,iZ)
	try{
		if(stJson(data)){
			r9.json=(LinkedHashMap)new JsonSlurper().parseText(data)
		}else if(data.startsWith(sLB) && data.endsWith(sRB)){
			r9.json=(List)new JsonSlurper().parseText(data)
		}else r9.json=[:]
	}catch(all){
		error "Error parsing JSON data $data",r9,iN2,all
	}
	return lZ
}

private static Long vcmd_cancelTasks(Map r9,device,List prms){
	((Map)r9[sCNCLATNS]).all=true
	return lZ
}

@Field static final String sFF='ffwd: '
private static String sffwdng(Map r9){
	if(prun(r9))return sBLK
	return sFF+sTRUE+": ${currun(r9)} "
}

@CompileStatic
private Boolean evaluateConditions(Map r9,Map cndtns,String collection,Boolean async){
	String myS
	myS=sBLK
	Integer myC=stmtNum(cndtns)
	if(isEric(r9)){
		String s
		s= "$cndtns".toString()
		s= s.substring(0,Math.min(140,s.length()))
		myS=("evaluateConditions #${myC} "+sffwdng(r9)+s+sBLK).toString()
		myDetail r9,myS,i1
	}
	Long t
	t=wnow()
	Map msg
	msg=null
	Boolean lg=isDbg(r9)
	if(lg)msg=timer sBLK,r9
	//override condition id
	Integer c=iMs((Map)r9[sSTACK],sC)
	((Map)r9[sSTACK]).c=myC
	Boolean collC= collection==sC // collection is sR or sC
	Boolean not= collC ? !!cndtns.n:!!cndtns.rn
	String grouping= collC ? (String)cndtns.o:(String)cndtns.rop // operator, restriction operator
	Boolean value
	value= grouping!=sOR
	List<Map> cndtnsCOL=cndtns[collection] ? (List<Map>)cndtns[collection] : []

	Boolean isFlwby= grouping=='followed by'
	Boolean runThru= currun(r9)==iN9 && isFlwby
	if(isFlwby && collC && !runThru){
		if(prun(r9) || currun(r9)==myC){
			//dealing with a followed by condition
			Integer steps= cndtnsCOL.size()
			String sidx='c:fbi:'+myC.toString()
			Integer ladderIndex
			ladderIndex= matchCastI(r9,((Map)r9[sCACHE])[sidx])  // gives back iZ if null
			String sldt='c:fbt:'+myC.toString()
			Long ladderUpdated
			ladderUpdated=(Long)cast(r9,((Map)r9[sCACHE])[sldt],sDTIME) // gives back current dtime if null
			Boolean didC
			didC=false
			if(ladderIndex>=steps) value=false
			else{
				t=wnow()
				Map cndtn
				cndtn=cndtnsCOL[ladderIndex]
				Long duration
				duration=lZ
				if(ladderIndex){
					Map tv=(Map)evaluateOperand(r9,null,(Map)cndtn.wd)
					duration=longEvalExpr(r9,rtnMap1(tv.v,sMvt(tv)))
				}
				// wt: l- loose (ignore unexpected events), s- strict, n- negated (lack of requested event continues group)
				if(ladderUpdated && duration!=lZ && (ladderUpdated+duration)<t){
					//time has expired
					value=((String)cndtn.wt==sN)
					if(!value)
						if(lg)debug "Conditional ladder step failed due to a timeout",r9
				}else{

					value=evaluateCondition(r9,cndtn,sC,async)
					didC=true

					if((String)cndtn.wt==sN){
						if(value) value=false
						else value=null
					}
					//we allow loose matches to work even if other events happen
					if((String)cndtn.wt==sL && !value)value=null // loose
				}
				if(value){
					//successful step, move on
					ladderIndex+= i1
					didC=false
					ladderUpdated=t
					cancelStatementSchedules(r9,myC)
					String ms
					ms=sBLK
					if(lg)ms="Condition group #${myC} made progress up the ladder; currently at step $ladderIndex of $steps"
					if(ladderIndex<steps){
						//delay decision, there are more steps to go through
						value=null
						cndtn=cndtnsCOL[ladderIndex]
						Map tv=(Map)evaluateOperand(r9,null,(Map)cndtn.wd)
						duration=longEvalExpr(r9,rtnMap1(tv.v,sMvt(tv)))
						if(lg)ms+=", Requesting timed"
						requestWakeUp(r9,cndtns,cndtns,duration,sNL,true,sNL,ms)
					} else if(ms)debug ms,r9
				}
			}

			switch(value){
				case null:
					Integer st=ladderIndex +(didC?i1:iZ)
					runFBupdates(r9,st,steps,cndtnsCOL,async) //run through future steps to update trigger cache
					//we need to exit time events set to work out the timeouts
					if(currun(r9)==myC)r9.terminated=true
					break
				case false:
				case true:
					//ladder either collapsed or finished, reset data
					if(ladderIndex!=iZ || !didC)Boolean a=((List)r9.followCleanup).push(cndtnsCOL) // do run thru to update trigger cache
					ladderIndex=iZ
					ladderUpdated=lZ
					cancelStatementSchedules(r9,myC)
					break
			}
			if(currun(r9)==myC)chgRun(r9,iZ)
			((Map)r9[sCACHE])[sidx]=ladderIndex
			((Map)r9[sCACHE])[sldt]=ladderUpdated
		}
	}else{
		if(cndtnsCOL){
			Boolean canopt
			canopt= !gtPOpt(r9,'cto') && grouping in [sOR,sAND] //cto == disable condition traversal optimizations
			if(canopt){
				Integer i
				i=iZ
				for(Map cndtn in cndtnsCOL){
					if( i!=iZ && (cndtn.ct==sT /*&& cndtn.s */) ){ canopt=false; break }
					i++
				}
			}
			if(isEric(r9)) myS+="cto: $canopt "
			Boolean res
			for(Map cndtn in cndtnsCOL){
				res=evaluateCondition(r9,cndtn,collection,async) //run through all to update stuff
				value= grouping==sOR ? value||res : value&&res
				if(prun(r9) && canopt && ((value && grouping==sOR) || (!value && grouping==sAND)))break
			}
		}
	}

	Boolean res; res=false //null
	if(value!=null) res= not ? !value:!!value
	if((value!=null && myC!=iZ) || runThru){
		if(!runThru){
			String mC= "c:${myC}".toString()
			if(prun(r9))tracePoint(r9,mC,elapseT(t),res)
			Boolean oldResult= !!bIs((Map)r9[sCACHE],mC)
			Boolean a= oldResult!=res
			r9[sCNDTNSTC]= a
			if(a) //condition change, perform Task Cancellation Policy TCP
				cancelConditionSchedules(r9,myC)
			((Map)r9[sCACHE])[mC]=res
		}
		//true/false actions
		if(collC){
			List ts= cndtns.ts!=null ? (List)cndtns.ts:[]
			if((res || ffwd(r9)) && ts.size()!=iZ)Boolean a=executeStatements(r9,ts,async)
			List fs= cndtns.fs!=null ? (List)cndtns.fs:[]
			if((!res || ffwd(r9)) && fs.size()!=iZ)Boolean a=executeStatements(r9,fs,async)
		}
		if(prun(r9) && lg){
			msg.m="Condition group #${myC} evaluated $res (condition ".toString()+(bIs(r9,sCNDTNSTC) ? 'changed':'did not change')+')'
			debug msg,r9
		}
	}
	//restore condition id
	((Map)r9[sSTACK]).c=c
	if(isEric(r9))myDetail r9,myS+"result:$res"
	return res
}

@CompileStatic
private void runFBupdates(Map r9,Integer st,Integer sz,List<Map> cndtns,Boolean async){
	if(st<sz){
		String mySS; mySS=sBLK
		if(isEric(r9)){
			mySS="running followed by updates st: $st sz:$sz"
			myDetail r9,mySS,i1
		}
		Boolean r
		Integer svrun=currun(r9)
		chgRun(r9,iN9)
		Integer i
		for(i=st; i<sz; i++){
			Map cndtn=cndtns[i]
			r=evaluateCondition(r9,cndtn,sC,async) //run through future steps to update trigger cache
		}
		chgRun(r9,svrun)
		if(isEric(r9))myDetail r9,mySS
	}
}

@CompileStatic
private evaluateOperand(Map r9,Map node,Map oper,Integer index=null,Boolean trigger=false,Boolean nextMidnight=false){
	String myS,nodeI
	myS=sBLK
	if(isEric(r9)){
		myS="evaluateOperand: "+sffwdng(r9)+"$oper "
		myDetail r9,myS,i1
	}
	List<LinkedHashMap> vals
	vals=[]
	Map operand,movt,mv
	operand=oper
	if(!operand)operand=[(sT):sC] //older pistons don't have the 'to' operand (time offset), simulating an empty one
	String ovt=sMvt(operand)
	movt=(ovt ? [(sVT):ovt] : [:]) as LinkedHashMap
	String nD="${node?.$}:".toString()
	nodeI=nD+"$index:0".toString()
	Long t=wnow()
	mv=null
	switch(sMt(operand)){
		case sBLK: //optional, nothing selected
			mv=rtnMap(ovt,null)
			break
		case sP: //physical device
			String operA=sMa(operand)
			Map attribute=operA ? Attributes()[operA]:[:]
			Map aM=(attribute && attribute.p ? [(sP):operand.p]:[:]) as LinkedHashMap // device support p- physical vs. s- digital, a-any
			Boolean a
			for(String d in expandDeviceList(r9,(List)operand.d)){
				Map value=[(sI): d+sCLN+operA,(sV):getDeviceAttribute(r9,d,operA,operand.i,trigger)+movt+aM]
				updateCache(r9,value,t)
				a=vals.push(value)
			}
			String g=sMs(operand,sG)
			if(vals.size()>i1 && !(g in [sANY,sALL])){
				//if we have multiple values and a grouping other than any or all we need to apply that function
				// count, avg, median, least, most, stdev, min, max, variance etc
				try{
					mv=callFunc(r9,g,vals*.v)+movt
				}catch(ignored){
					error "Error applying grouping method ${g}",r9
				}
			}
			break
		case sD: //devices
			List deviceIds=[]
			Boolean a
			for(String d in expandDeviceList(r9,(List)operand.d))
				if(getDevice(r9,d))a=deviceIds.push(d)
			nodeI=nD+sD
			mv=rtnMap(sDEV,deviceIds.unique())
			break
		case sV: //virtual devices
			Map ce=(Map)r9[sCUREVT]
			String rEN=(String)ce[sNM]
			String evntVal="${ce[sVAL]}".toString()
			nodeI=nD+sV
			String oV=(String)operand.v
			switch(oV){
				case sTIME:
				case sDATE:
				case sDTIME:
					mv=rtnMap(oV,(Long)cast(r9,t,oV,sLONG))
					break
				case sMODE:
				case sHSMSTS:
				case 'alarmSystemStatus':
					mv=getDeviceAttribute(r9,sMs(r9,sLOCID),oV)
					break
				case sHSMALRT:
				case 'alarmSystemAlert':
					String valStr=evntVal+(rEN==sHSMALRT && evntVal==sRULE ? ",${(String)ce.descriptionText}".toString():sBLK)
					mv=rtnMapS((rEN==sHSMALRT ? valStr:sNL))
					break
				case sHSMSARM:
				case 'alarmSystemEvent':
					mv=rtnMapS((rEN==sHSMSARM ? evntVal:sNL))
					break
				case 'alarmSystemRule':
					mv=rtnMapS((rEN=='hsmRules' ? evntVal:sNL))
					break
				case 'powerSource':
					mv=rtnMap(sENUM,r9.powerSource)
					break
				case 'routine':
					mv=rtnMapS((rEN=='routineExecuted' ? hashId(r9,evntVal):sNL))
					break
				case sPSTNRSM:
				case 'systemStart':
				case 'severeLoad':
				case 'zigbeeOff':
				case 'zigbeeOn':
				case 'zwaveCrashed':
				case 'sunriseTime':
				case 'sunsetTime':
				case sTILE:
					mv=rtnMapS((rEN==oV ? evntVal:sNL))
					break
				case 'ifttt':
					mv=rtnMapS((rEN==('ifttt.'+evntVal)? evntVal:sNL))
					break
				case 'email':
					mv=rtnMap('email',(rEN==('email.'+evntVal)? evntVal:sNL))
					break
			}
			break
		case sS: //preset
			switch(ovt){
				case sTIME:
				case sDTIME:
					Long v
					v=lZ
					switch((String)operand.s){
						case 'sunset': v=getSunsetTime(r9); break
						case 'sunrise': v=getSunriseTime(r9); break
						case 'midnight': v=nextMidnight ? getNextMidnightTime():getMidnightTime(); break
						case 'noon': v=getNoonTime(); break
					}
					if(ovt==sTIME && v)v=(Long)cast(r9,v,ovt,sDTIME)
					mv=rtnMap(ovt,v)
					break
				default:
					mv=rtnMap(ovt,operand.s)
					break
			}
			break
		case sX: //variable
			if(ovt==sDEV && operand.x instanceof List){
				//we could have multiple devices selected
				List asum
				asum=[]
				Map avar
				for(String x in (List)operand.x){
					avar=getVariable(r9,x)
					if(avar.v instanceof List){
						List l=(List)avar.v
						//noinspection GroovyAssignabilityCheck
						asum+=l
					}else Boolean a=asum.push(avar.v)
				}
				mv=rtnMap(sDEV,asum)+movt
			}else{
				Boolean hasI=sMs(operand,sXI)!=sNL
				if(hasI)movt=ovt ? [(sVT):ovt.replace(sLRB,sBLK)]:[:]
				mv=getVariable(r9,sMs(operand,sX)+(hasI ? sLB+sMs(operand,sXI)+sRB:sBLK))+movt
			}
			break
		case sC: //constant
			switch(ovt){
				case sTIME:
					Long offset=(operand.c instanceof Integer)? (iMs(operand,sC)).toLong():lcast(r9,operand.c)
					mv=rtnMap(ovt,(offset%1440L)*60000L)	//convert mins to time
					break
				case sDATE:
				case sDTIME:
					mv=rtnMap(ovt,operand.c)
					break
				default:
					Map e= (Map)operand.exp
					List<Map>i= (List<Map>)e?.i
					if((String)e?.t==sEXPR && i?.size()==i1){
						Map val=i[iZ]
						String ty
						ty= sMt(val)
						if(!(ty in [sVARIABLE,sFUNC,sDEV,sOPERAND,sDURATION])){
							def v
							v= val.v
							v= ty==sBOOLN ? bcast(r9,v):v
							if(ovt==sDEC){
								v=dcast(r9,v)
								ty=sDEC
							}
							mv=movt+rtnMap(ty,v)
						}
					}
			}
			if(mv)break
		case sE: //expression
			mv=movt+evaluateExpression(r9,(Map)operand.exp)
			break
		case sU: //argument
			mv=getArgument(r9,(String)operand.u)
			break
	}
	if(mv) vals=[[(sI):nodeI,(sV):mv]] as List<LinkedHashMap>

	if(node==null){ // return a Map instead of a List
		Map ret
		if(vals.size())ret=(Map)vals[iZ].v
		else ret=rtnMap(sDYN,null)
		if(isEric(r9))myDetail r9,myS+"result:$ret"
		return ret
	}
	if(isEric(r9))myDetail r9,myS+"result:$vals"
	return vals
}

private Map callFunc(Map r9,String func,List p){
	return (Map)"func_${func}"(r9,p)
}

private Double evalDecimalOperand(Map r9,Map operand){
	Map value=(Map)evaluateOperand(r9,null,operand)
	return dcast(r9,value ? value.v:sBLK)
}

@Field static final String sEVCN='evaluateCondition '
@CompileStatic
private Boolean evaluateCondition(Map r9,Map cndtn,String collection,Boolean async){
	String myS=sBLK; myS=sBLK
	Integer cndNm=stmtNum(cndtn)
	if(isEric(r9)){
		myS=sEVCN+("#${cndNm} "+sffwdng(r9)+"$cndtn async: ${async}").toString()
		myDetail r9,myS,i1
	}

	Long t=wnow()
	Boolean res
	res=false

	if(sMt(cndtn)==sGROUP){
		res=evaluateConditions(r9,cndtn,collection,async)
		if(isEric(r9))myDetail r9,myS+" result:$res"
		return res
	}

	Map msg; msg=[:]
	if(isDbg(r9))msg=timer sBLK,r9
	//override condition id
	Integer c=iMs((Map)r9[sSTACK],sC)
	((Map)r9[sSTACK]).c=cndNm
	String sIndx="c:${cndNm}".toString()
	Boolean oldResult=!!bIs((Map)r9[sCACHE],sIndx)

	Boolean not=!!cndtn.n
	String co=sMs(cndtn,'co')
	Map comparison
	comparison=Comparisons().triggers[co]
	Boolean trigger=comparison!=null
	if(!trigger)comparison=Comparisons().conditions[co]
	Map e=(Map)r9[sEVENT]
	Map es=(Map)e.schedule
	String rEN=(String)e[sNM]
	Boolean w=rEN==sTIME && es!=null && iMs(es,sS)==cndNm
	r9.wakingUp=w
	if(w && isEric(r9))myDetail r9,sEVCN+"WAKING UP",iN2
	if(ffwd(r9) || comparison!=null){
		Boolean isStays=co.startsWith('stays')
		if(currun(r9) in [iZ,iN9]){
			Integer pCnt=comparison?.p!=null ? iMs(comparison,sP):iZ
			Map lo,ro,ro2,loOp
			lo=null
			ro=null
			ro2=null
			Integer i
			for(i=iZ; i<=pCnt; i++){
				Map operand=(i==iZ ? (Map)cndtn.lo:(i==i1 ? (Map)cndtn.ro:(Map)cndtn.ro2))
				//parse the operand
				List vals=(List)evaluateOperand(r9,cndtn,operand,i,trigger)
				switch(i){
					case iZ:
						lo=[operand:operand,values:vals]
						break
					case i1:
						ro=[operand:operand,values:vals]
						break
					case i2:
						ro2=[operand:operand,values:vals]
						break
				}
			}

			//we now have all the operands,their values, and the comparison, let's get to work
			Boolean t_and_compt=(trigger && comparison.t!=null)
			loOp=(Map)lo.operand
			LinkedHashMap options=[
				//we ask for matching/non-matching devices if the user requested it or if the trigger is timed
				//setting matches to true will force the condition group to evaluate all members (disables evaluation optimizations)
				(sDEVS): [:],
				matches: t_and_compt || loOp.dm!=null || loOp.dn!=null,
				forceAll: t_and_compt
			] as LinkedHashMap
			Map to=(comparison.t!=null || (ro!=null && sMt(loOp)==sV && (String)loOp.v==sTIME && sMt((Map)ro.operand)!=sC)) && cndtn.to!=null ? [operand: (Map)cndtn.to,values: (Map)evaluateOperand(r9,null,(Map)cndtn.to)]:null
			Map to2=ro2!=null && sMt(loOp)==sV && (String)loOp.v==sTIME && sMt((Map)ro2.operand)!=sC && cndtn.to2!=null ? [operand: (Map)cndtn.to2,values: (Map)evaluateOperand(r9,null,(Map)cndtn.to2)]:null

			res=evaluateComparison(r9,co,lo,ro,ro2,to,to2,options)

			//save new values to cache
			if(lo)for(Map value in (List<Map>)lo.values)updateCache(r9,value,t)
			if(ro)for(Map value in (List<Map>)ro.values)updateCache(r9,value,t)
			if(ro2)for(Map value in (List<Map>)ro2.values)updateCache(r9,value,t)
			Map ods=(Map)options[sDEVS]
			List om=(List)ods?.matched
			List oum=(List)ods?.unmatched
			loOp=(Map)lo.operand
			if(loOp.dm!=null && ods!=null)Map m=setVariable(r9,(String)loOp.dm,om!=null ? om:[])
			if(loOp.dn!=null && ods!=null)Map m=setVariable(r9,(String)loOp.dn,oum!=null ? oum:[])

			//do the stays logic here
			if(t_and_compt && prun(r9)){
				//trigger on device:attribute and timed trigger
				if(isEric(r9))myDetail r9,"stays check ${co} isStays: $isStays result: $res options: $options",iN2
				if(to!=null){
					Map tvalue=(Map)to.operand && (Map)to.values ? (Map)to.values+[(sF): ((Map)to.operand).f]:null
					if(tvalue!=null){
						Long delay=longEvalExpr(r9,rtnMap1(tvalue.v,sMvt(tvalue)))

						List<Map> schedules=sgetSchedules(sEVCN,isPep(r9))

						if(sMt(loOp)==sP && sMs(loOp,sG)==sANY && ((List)lo.values).size()>i1){
							List<String> chkList=om
							if(isEric(r9))myDetail r9,"$co stays check device options: $options",iN2
							//if(!isStays) chkList=oum
							for(value in (List<Map>)lo.values){
								String dev=(String)((Map)value.v)?.d
								doStaysProcess(r9,schedules,co,cndtn,cndNm,delay,(dev in chkList),dev)
							}
						}else{
							if(isEric(r9))myDetail r9,"$co stays check",iN2
							doStaysProcess(r9,schedules,co,cndtn,cndNm,delay,res,sNL)
						}
					}else{ error "expecting time for stay and value not found $to $tvalue",r9 }	//; res=false }
				}else{ error "expecting time for stay and operand not found $to",r9 } //;	res=false }
				if(isStays)res=false
			}
			res=not ? !res:res
		}else if(rEN==sTIME && currun(r9)==cndNm){ // we are ffwd & stays timer fired, pickup at result of if statement
			chgRun(r9,iZ)
			r9.resumed=true
			if(isStays) res=!not
		}else{ // continue ffwd
			res=oldResult
		}
	}
	if(prun(r9))tracePoint(r9,sIndx,elapseT(t),res)

	r9.wakingUp=false
	Boolean a= oldResult!=res
	r9[sCNDTNSTC]= a
	if(a) //cndtn change, perform Task Cancellation Policy TCP
		cancelConditionSchedules(r9,cndNm)
	((Map)r9[sCACHE])[sIndx]=res

	//true/false actions
	List ts= cndtn.ts!=null ? (List)cndtn.ts:[]
	if((res || ffwd(r9)) && ts.size()!=iZ)Boolean b=executeStatements(r9,ts,async)
	List fs= cndtn.fs!=null ? (List)cndtn.fs:[]
	if((!res || ffwd(r9)) && fs.size()!=iZ)Boolean b=executeStatements(r9,fs,async)

	//restore condition id
	((Map)r9[sSTACK]).c=c
	if(prun(r9) && isDbg(r9)){
		msg.m="Condition #${cndNm} evaluated $res"
		debug msg,r9
	}
	if(currun(r9)<=iZ && bIs(cndtn,sS) && sMt(cndtn)==sCONDITION && cndtn.lo!=null && sMt((Map)cndtn.lo)==sV){
		if(!LT1) LT1=fill_TIM()
		if((String)((Map)cndtn.lo).v in LT1) scheduleTimeCondition(r9,cndtn)
	}
	if(isEric(r9))myDetail r9,myS+" resumed: ${bIs(r9,'resumed')} result:$res"
	return res
}

@CompileStatic
void doStaysProcess(Map r9,List<Map>schedules,String co,Map cndtn,Integer cndNm,Long delay,Boolean result,String dev){
	Boolean canc,schd
	canc=false
	schd=false
	Boolean isStaysUnchg= co=='stays_unchanged'
	Boolean isStays=co.startsWith('stays')
	Boolean lg=isDbg(r9)
	String s; s=sBLK
	if(isStays && result){
		//if we find the comparison true (ie reason to time stays has begun) set a timer if we haven't already
		if(lg)s= dev ? " $co match in list":" $co result $result"
		if(!schedules.find{ Map it -> (Integer)it.s==cndNm && (!dev || (String)it.d==dev) }){
			//schedule a wake up if there's none otherwise just move on
			if(lg)s+= " scheduling timer "
			schd=true
		}else s+= " found timer "
	}else{ // the comparison failed, normally cancel except for stays_unchanged
		if(lg)s= dev ? " $co device did not match":" $co result $result"
		if(isStaysUnchg){
			if(lg)s+= " $co result $result (it changed)"
			if(!schedules.find{ Map it -> (Integer)it.s==cndNm && (!dev || (String)it.d==dev) }){
				if(lg)s+= " no timer found creating timer "
				schd=true
			}else{
				if(lg)s+= " with timer active cancel timer "
				canc=true
			}
		}else{
			//cancel any schedule
			if(lg)s+= " cancel any timers "
			canc=true
		}
	}
	if(lg){
		String d= dev ? "for device $dev ":sBLK
		s="timed trigger schedule${s}${d}for condition ${cndNm}"
	}
	String msg; msg=sBLK
	if(canc){
		if(lg)debug "Canceling any $s",r9
		cancelStatementSchedules(r9,cndNm,dev)
	}
	if(schd){
		if(lg)msg= "Adding a "+s
		requestWakeUp(r9,cndtn,cndtn,delay,dev,true,sNL,msg)
	}
	if(!schd && !canc){
		if(lg)debug "Doing nothing found $s",r9
	}
}

@CompileStatic
private Boolean evaluateComparison(Map r9,String comparison,Map lo,Map ro=null,Map ro2=null,Map to=null,Map to2=null,Map options=[:]){
	String mySt; mySt=sBLK
	if(isEric(r9)){
		mySt="evaluateComparison "+sffwdng(r9)+"$comparison "
		myDetail r9,mySt,i1
	}
	Boolean lg=isDbg(r9)
	String fn="comp_"+comparison
	String loG= sMs((Map)lo.operand,sG) ?: sANY
	Boolean result,res
	result= loG!=sANY
	Boolean oM=bIs(options,'matches')
	if(oM) options.devices=[matched: [],unmatched: []] as LinkedHashMap
	//if multiple left values go through each
	Map tvalue=to && to.operand && to.values ? (Map)to.values+[(sF): ((Map)to.operand).f]:null
	Map tvalue2=to2 && to2.operand && to2.values ? (Map)to2.values:null
	if(!LT1) LT1=fill_TIM()
	for(Map<String,Map> value in (List<Map>)lo.values){
		res=false
		//x=eXclude- if a momentary attribute is requested and the device does not match the current device, then we must ignore this during comparisons
		if(value && value.v && (!value.v.x || bIs(options,'forceAll'))){
			Map msg; msg=[:]
			try{
				//physical support
				//value.p=lo.operand.p
				if(value && sMt(value.v)==sDEV)value.v=evaluateExpression(r9,(Map)value.v,sDYN)
				if(!ro){
					if(lg)msg=timer sBLK,r9
					if(comparison in ['event_occurs','gets_any']){
						Map ce=(Map)r9[sCUREVT]
						String rEN=ce ? (String)ce[sNM] : sNL
						String compS
						compS=(String)((Map)lo.operand).v
						if(compS=='alarmSystemStatus') compS=sHSMSTS
						else if(compS=='alarmSystemAlert') compS=sHSMALRT
						else if(compS=='alarmSystemEvent') compS=sHSMSARM
						if(sMt((Map)lo.operand)==sV && rEN==compS){
							res=true
						}else if((String)value.v.d==(String)ce?.device && sMa(value.v)==rEN){
							res=true
							compS=sMa(value.v)
						}
						if(lg)msg.m="Comparison (string) ${compS} $comparison = $res"
					}else{
						res=callComp(r9,fn,value,null,null,tvalue,tvalue2)
						if(lg)msg.m="Comparison (${value.v.t}) ${value.v.v} $comparison = $res"
					}
					if(lg)debug msg,r9
				}else{
					Boolean rres
					String roG= sMs((Map)ro.operand,sG) ?: sANY
					res= roG!=sANY
					//if multiple right values go through each
					for(Map<String,Map> rvalue in (List<Map>)ro.values){
						if(rvalue && sMt(rvalue.v)==sDEV)rvalue.v=evaluateExpression(r9,(Map)rvalue.v,sDYN)
						if(!ro2){
							if(lg)msg=timer sBLK,r9
							rres=callComp(r9,fn,value,rvalue,null,tvalue,tvalue2)
							if(lg){
								msg.m="Comparison (${value.v.t}) ${value.v.v} $comparison (${rvalue?.v?.t}) ${rvalue?.v?.v} = $rres"
								debug msg,r9
							}
						}else{
							String ro2G= sMs((Map)ro2.operand,sG) ?: sANY
							rres=ro2G!=sANY
							//if multiple right2 values go through each
							for(Map<String,Map> r2value in (List<Map>)ro2.values){
								if(r2value && sMt(r2value.v)==sDEV)r2value.v=evaluateExpression(r9,r2value.v,sDYN)
								if(lg)msg=timer sBLK,r9
//if(isEric(r9))myDetail r9,"$fn $value $rvalue $r2value $tvalue $tvalue2",i1
								Boolean r2res=callComp(r9,fn,value,rvalue,r2value,tvalue,tvalue2)
//if(isEric(r9))myDetail r9,"$r2res ${myObj(value?.v?.v)} ${myObj(rvalue?.v?.v)} $fn $value $rvalue $r2value $tvalue $tvalue2"
								if(lg){
									msg.m="Comparison (${value.v.t}) ${value.v.v} $comparison (${rvalue?.v?.t}) ${rvalue?.v?.v} .. (${r2value?.v?.t}) ${r2value?.v?.v} = $r2res"
									debug msg,r9
								}
								rres= ro2G==sANY ? rres||r2res : rres&&r2res
								if((ro2G==sANY && rres) || (ro2G!=sANY && !rres))break
							}
						}
						res=(roG==sANY ? res||rres : res&&rres)
						if((roG==sANY && res) || (roG!=sANY && !res))break
					}
				}
			}catch(all){
				error "Error calling comparison $fn:",r9,iN2,all
				res=false
			}

			if(res && sMt((Map)lo.operand)==sV && (String)((Map)lo.operand).v in LT1){
				Boolean pass=(checkTimeRestrictions(r9,(Map)lo.operand,wnow(),5,i1)==lZ)
				if(lg)debug "Time restriction check ${pass ? 'passed':'failed'}",r9
				if(!pass)res=false
			}
		}
		result= loG==sANY ? result||res : result&&res
		if(oM){
			String vVD=(String)value.v.d
			if(vVD){
				Boolean a
				Map ods=(Map)options[sDEVS]
				if(res) a=((List)ods.matched).push(vVD)
				else a=((List)ods.unmatched).push(vVD)
			}
		}else{
			// if not matching, see if we are done
			//logical OR if using the ANY keyword
			if(loG==sANY && res) break
			//logical AND if using the ALL keyword
			if(loG==sALL && !result) break
		}
	}
	if(isEric(r9))myDetail r9,mySt+"result:$result"
	return result
}

private Boolean callComp(Map r9, String fn, Map lv, Map rv, Map rv2, Map tv, Map tv2){
	return (Boolean)"$fn"(r9,lv,rv,rv2,tv,tv2)
}

@CompileStatic
private static String cnlS(Map sch){ return "${sch.s} (st:${sch.i}"+(sch.d?" / ${sch.d}":sBLK)+') ' }

private void whatCnclsA(Map r9){
	List<Map> schedules=sgetSchedules(sPROCS,isPep(r9))
	String s; s=sBLK
	for(Map sch in schedules){
		Integer i=iMs(sch,sI)
		if(i>iZ || i in [iN3,iN5]) s+= cnlS(sch)
	}
	if(s)debug "Cancel ALL task schedules..."+s,r9
}

/** log what timers will be canceled due to piston state change from saved schedules  */
private void whatCnclsP(Map r9){
	List<Map> schedules=sgetSchedules(sPROCS,isPep(r9))
	String s; s=sBLK
	for(Map sch in schedules)
		if(iMs(sch,'ps')!=iZ) s+= cnlS(sch)
	if(s)debug "Cancel piston state changed schedules..."+s,r9
}

/** log what statements timers will be canceled from saved schedules  */
private void whatStatementsCncl(Map r9,Integer stmtId, String data=sNL){
	List<Map> schedules=sgetSchedules(sPROCS,isPep(r9))
	String s; s=sBLK
	for(Map sch in schedules)
		if(stmtId==iMs(sch,sS) && (!data || data==(String)sch.d)) s+= cnlS(sch)
	if(s)debug "Canceling statement #${stmtId}'s schedules..."+s,r9
}

@CompileStatic
private void cancelStatementSchedules(Map r9,Integer stmtId,String data=sNL){
	// data=null - cancel all schedules that are pending for statement stmtId
	Boolean fnd; fnd=false
	for(Map item in (List<Map>)((Map)r9[sCNCLATNS]).statements){
		fnd=(stmtId==iMs(item,sID) && (!data || data==(String)item.data))
		if(fnd)break
	}
	if(isDbg(r9))whatStatementsCncl(r9,stmtId,data)
	if(!fnd) Boolean a=((List<Map>)((Map)r9[sCNCLATNS]).statements).push([(sID): stmtId,data: data])
}

/** log what condition timers will be canceled from saved schedules  */
private void whatConditionsCncl(Map r9,Integer cndtnId){
	List<Map> schedules=sgetSchedules(sPROCS,isPep(r9))
	String s; s=sBLK
	for(Map sch in schedules)
		if(cndtnId in (List)sch.cs) s+= cnlS(sch)
	if(s)debug "Canceling condition #${cndtnId}'s schedules..."+s,r9
}

@CompileStatic
private void cancelConditionSchedules(Map r9,Integer cndtnId){
	//cancel all schedules that are pending for condition cndtnId
	if(isDbg(r9))whatConditionsCncl(r9,cndtnId)
	if(!(cndtnId in (List<Integer>)((Map)r9[sCNCLATNS]).conditions))
		Boolean a=((List<Integer>)((Map)r9[sCNCLATNS]).conditions).push(cndtnId)
}

private static Boolean matchDeviceSubIndex(list,deviceSubIndex){
	//if(!list || !(list instanceof List) || list.size()==iZ)return true
	//return list.collect{ "$it".toString() }.indexOf("$deviceSubIndex".toString())>=iZ
	return true
}

@CompileStatic
private static Boolean matchDeviceInteraction(String option,Map r9){
	Map ce=(Map)r9[sCUREVT]
	Boolean isPhysical=(Boolean)ce?.physical
	// device support p- physical vs. s- digital, a-any
	return !((option==sP && !isPhysical) || (option==sS && isPhysical))
}

private List<Map> listPreviousStates(device,String attr,Long threshold,Boolean excludeLast){
	List<Map> result=[]
	List events=device.events([all: true,max: 100]).findAll{ it -> (String)it.name==attr}
	//if we got any events let's go through them
	//if we need to exclude last event we start at the second event as the first one is the event that triggered execution. The attribute's value has to be different from the current one to qualify for quiet
	Integer sz=events.size()
	if(sz!=iZ){
		Long thresholdTime=elapseT(threshold)
		Long endTime=wnow()
		for(Integer i=iZ; i<sz; i++){
			Long startTime=((Date)events[i].date).getTime()
			Long duration=endTime-startTime
			if(duration>=1L && (i>iZ || !excludeLast)){
				Boolean a=result.push([(sVAL):events[i].value,startTime:startTime,duration:duration])
			}
			if(startTime<thresholdTime) break
			endTime=startTime
		}
	}else{
		def currentState=device.currentState(attr,true)
		if(currentState){
			Long startTime=((Date)currentState.getDate()).getTime()
			Boolean a=result.push([(sVAL):currentState.value,startTime:startTime,duration:elapseT(startTime)])
		}
	}
	return result
}

@CompileStatic
private static void updateCache(Map r9,Map value,Long t){
	String n=(String)value.i
	Map oldValue=(Map)((Map)r9[sCACHE])[n]
	Map valueV=[:]+(Map)value.v
	def a
	if(oldValue==null || sMt(oldValue)!=sMt(valueV) || "${oldValue.v}"!="${valueV.v}"){
		if(valueV.d!=null && valueV.d instanceof Long)a=valueV.remove(sD)
		if(valueV[sVT]!=null)a=valueV.remove(sVT)
		if(valueV.x!=null)a=valueV.remove(sX)
		if(valueV.p!=null)a=valueV.remove(sP)
		((Map<String,Map>)r9[sNWCACHE])[n]=valueV+( [(sS):t] as Map)
	}
}

@CompileStatic
private static Map valueCacheChanged(Map r9,Map comparisonValue){
	String n=(String)comparisonValue.i
	def oV=((Map)r9[sCACHE])[n]
	Map newValue=(Map)comparisonValue.v
	Map oldValue= oV instanceof Map ? oV:null
	return (oldValue!=null && (sMt(oldValue)!=sMt(newValue) || "${oldValue.v}"!="${newValue.v}")) ? [(sI):n,(sV):oldValue] :null
}

@CompileStatic
private static Boolean okComp(Map compV,Map timeValue){
	Map cv=(Map)compV?.v
	return !(compV==null || cv==null || !(String)cv.d || !sMa(cv) || timeValue==null || timeValue.v==null || !sMvt((Map)timeValue))
}

@CompileStatic
private Boolean valueWas(Map r9,Map comparisonValue,Map rightValue,Map rightValue2,Map timeValue,String func){
	if(!okComp(comparisonValue,timeValue))return false
	Map cv=(Map)comparisonValue.v
	String t=(String)cv.d
	def device=t?getDevice(r9,t):null
	if(device==null)return false
	String attr=sMa(cv)
	Long threshold=longEvalExpr(r9,rtnMap1(timeValue.v,sMvt(timeValue)))

	Map e=(Map)r9[sEVENT]
	Boolean thisEventWokeUs=((String)e[sDEV]==hashD(r9,device) && (String)e[sNM]==attr)
	List<Map> states=listPreviousStates(device,attr,threshold,false) // thisEventWokeUs)
	Boolean result
	Long duration; duration=lZ
	Integer i; i=i1
	String comp_t=sMt(cv)
	for(Map stte in states){
		if(!(i==i1 && thisEventWokeUs)){
			if(!callComp(r9,"comp_$func", [(sI):(String)comparisonValue.i,(sV):rtnMap(comp_t,cast(r9,stte[sVAL],comp_t))],
					rightValue,rightValue2,timeValue,null))break
			duration+= lMs(stte,sDURATION)
		}
		i+=i1
	}
	if(duration==lZ)return false
	Boolean fisg=(String)timeValue.f==sG // 'l' or 'g'
	result=fisg? duration>=threshold:duration<threshold
	if(isDbg(r9))
		debug "Duration ${duration}ms for ${func.replace('is_','was_')} ${fisg ? sGTHE:sLTH} ${threshold}ms threshold = ${result}",r9
	return result
}

@CompileStatic
private Boolean valueChanged(Map r9,Map comparisonValue,Map timeValue){
	if(!okComp(comparisonValue,timeValue))return false
	Map cv=(Map)comparisonValue.v
	String t=(String)cv.d
	def device=t?getDevice(r9,t):null
	if(device==null)return false
	String attr=sMa(cv)
	Long threshold=longEvalExpr(r9,rtnMap1(timeValue.v,sMvt(timeValue)))

	List<Map> states=listPreviousStates(device,attr,threshold,false)
	if(states.size()==iZ)return false
	def value=states[iZ][sVAL]
	for(Map tstate in states) if(tstate[sVAL]!=value)return true
	return false
}

private static Boolean match(String str,String pattern){
	Integer sz=pattern.size()
	if(sz>i2 && pattern.startsWith(sDIV) && pattern.endsWith(sDIV)){
		def ppattern= ~pattern.substring(i1,sz-i1)
		return !!(str =~ ppattern)
	}
	return str.contains(pattern)
}

//comparison low level functions
private Boolean comp_is					(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return (strEvalExpr(r9,(Map)lv.v)==strEvalExpr(r9,(Map)rv.v))|| (lv.v.n && scast(r9,lv.v.n)==scast(r9,rv.v.v))}
private Boolean comp_is_not				(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !comp_is(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_is_equal_to		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ String dt= (String)lv?.v?.t==sDEC || (String)rv?.v?.t==sDEC ? sDEC:((String)lv?.v?.t==sINT || (String)rv?.v?.t==sINT ? sINT:sDYN); return evaluateExpression(r9,(Map)lv.v,dt).v==evaluateExpression(r9,(Map)rv.v,dt).v }
private Boolean comp_is_not_equal_to	(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !comp_is_equal_to(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_is_different_than	(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_not_equal_to(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_is_less_than		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return dblEvalExpr(r9,(Map)lv.v)<dblEvalExpr(r9,(Map)rv.v) }
private Boolean comp_is_greater_than_or_equal_to	(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !comp_is_less_than(r9,lv,rv) }
private Boolean comp_is_greater_than	(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return dblEvalExpr(r9,(Map)lv.v)>dblEvalExpr(r9,(Map)rv.v) }
private Boolean comp_is_less_than_or_equal_to	(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !comp_is_greater_than(r9,lv,rv) }
private Boolean comp_is_even			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return intEvalExpr(r9,(Map)lv.v) % i2==iZ }
private Boolean comp_is_odd				(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return intEvalExpr(r9,(Map)lv.v) % i2!=iZ }
private Boolean comp_is_true			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return boolEvalExpr(r9,(Map)lv.v) }
private Boolean comp_is_false			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !boolEvalExpr(r9,(Map)lv.v) }
private Boolean comp_is_inside_of_range		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Double v=dblEvalExpr(r9,(Map)lv.v); Double v1=dblEvalExpr(r9,(Map)rv.v); Double v2=dblEvalExpr(r9,(Map)rv2.v); return (v1<v2) ? (v>=v1 && v<=v2):(v>=v2 && v<=v1)}
private Boolean comp_is_outside_of_range	(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !comp_is_inside_of_range(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_is_any_of			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){
	String v=strEvalExpr(r9,(Map)lv.v)
	Map r=[:]+(Map)rv.v
	String s=(String)r.v
	for(String vi in s.tokenize(sCOMMA)){
		r.v=vi.trim()
		if(v==strEvalExpr(r9,r))return true
	}
	return false
}
private Boolean comp_is_not_any_of		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !comp_is_any_of(r9,lv,rv,rv2,tv,tv2)}

private Boolean comp_was				(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(r9,lv,rv,rv2,tv,sIS)}
private Boolean comp_was_not			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(r9,lv,rv,rv2,tv,'is_not')}
private Boolean comp_was_equal_to		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(r9,lv,rv,rv2,tv,'is_equal_to')}
private Boolean comp_was_not_equal_to	(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(r9,lv,rv,rv2,tv,'is_not_equal_to')}
private Boolean comp_was_different_than		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(r9,lv,rv,rv2,tv,'is_different_than')}
private Boolean comp_was_less_than		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(r9,lv,rv,rv2,tv,'is_less_than')}
private Boolean comp_was_less_than_or_equal_to		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(r9,lv,rv,rv2,tv,'is_less_than_or_equal_to')}
private Boolean comp_was_greater_than	(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(r9,lv,rv,rv2,tv,'is_greater_than')}
private Boolean comp_was_greater_than_or_equal_to	(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(r9,lv,rv,rv2,tv,'is_greater_than_or_equal_to')}
private Boolean comp_was_even			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(r9,lv,rv,rv2,tv,'is_even')}
private Boolean comp_was_odd			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(r9,lv,rv,rv2,tv,'is_odd')}
private Boolean comp_was_true			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(r9,lv,rv,rv2,tv,'is_true')}
private Boolean comp_was_false			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(r9,lv,rv,rv2,tv,'is_false')}
private Boolean comp_was_inside_of_range		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(r9,lv,rv,rv2,tv,sISINS)}
private Boolean comp_was_outside_of_range		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(r9,lv,rv,rv2,tv,'is_outside_of_range')}
private Boolean comp_was_any_of			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(r9,lv,rv,rv2,tv,'is_any_of')}
private Boolean comp_was_not_any_of		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(r9,lv,rv,rv2,tv,'is_not_any_of')}

private Boolean comp_changed			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,tv2=null){ return valueChanged(r9,lv,tv)}
private Boolean comp_did_not_change		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !valueChanged(r9,lv,tv)}

private static Boolean comp_is_any		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return true }
private Boolean comp_is_before			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){
	if(!LT1) LT1=fill_TIM()
	Map l=(Map)lv.v
	String lvt= sMt(l) in LT1 ? sMt(l) : sNL

	Long ttv= longEvalExpr(r9,l,sDTIME)/*+2000L*/
	Long v= lvt ? (Long)cast(r9,ttv,lvt) : ttv

	Long offset1=tv ? longEvalExpr(r9,rtnMap1(tv.v,sMvt(tv))) :lZ
	Long ttv1= longEvalExpr(r9,(Map)rv.v,sDTIME)+offset1
	Long v1= lvt ? (Long)cast(r9,ttv1,lvt) : ttv1

	return v < v1
}
private Boolean comp_is_after			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !comp_is_before(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_is_between			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){
	if(!LT1) LT1=fill_TIM()
	Map l=(Map)lv.v
	String lvt= sMt(l) in LT1 ? sMt(l) : sNL

	Long ttv= longEvalExpr(r9,l,sDTIME)/*+2000L*/
	Long v= lvt ? (Long)cast(r9,ttv,lvt) : ttv

	Long offset1=tv ? longEvalExpr(r9,rtnMap1(tv.v,sMvt(tv))) :lZ
	Long ttv1= longEvalExpr(r9,(Map)rv.v,sDTIME)+offset1
	Long v1= lvt ? (Long)cast(r9,ttv1,lvt) : ttv1

	Long offset2=tv2 ? longEvalExpr(r9,rtnMap1(tv2.v,sMvt(tv2))) :lZ
	Long ttv2= longEvalExpr(r9,(Map)rv2.v,sDTIME)+offset2
	Long v2= lvt ? (Long)cast(r9,ttv2,lvt) : ttv2

	return v1<v2 ? v>=v1 && v<v2 : v<v2 || v>=v1
}
private Boolean comp_is_not_between		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !comp_is_between(r9,lv,rv,rv2,tv,tv2)}

/*triggers*/
private Boolean comp_gets				(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return scast(r9,lv.v.v)==scast(r9,rv.v.v) && matchDeviceSubIndex(lv.v.i,iMs(r9[sCUREVT],'index'))}
private static Boolean comp_receives	(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return "${lv.v.v}"=="${rv.v.v}" && matchDeviceInteraction((String)lv.v.p,r9)}
private static Boolean comp_gets_any	(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return false }
private static Boolean comp_event_occurs		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return false }
private Boolean comp_executes			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_arrives			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return (String)r9[sEVENT][sNM]=='email' && match(r9[sEVENT]?.jsonData?.from ?: sBLK,strEvalExpr(r9,(Map)rv.v)) && match(r9[sEVENT]?.jsonData?.message ?: sBLK,strEvalExpr(r9,(Map)rv2.v))}
private static Boolean comp_happens_daily_at		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ bIs(r9,'wakingUp') }
private static Boolean comp_changes		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueCacheChanged(r9,lv)!=null && matchDeviceInteraction((String)lv.v.p,r9)}
private static Boolean comp_changes_to	(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueCacheChanged(r9,lv)!=null && comp_receives(r9,lv,rv,rv2,tv,tv2)}
private static Boolean comp_changes_away_from		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(r9,lv); return oldValue!=null && "${oldValue.v.v}"=="${rv.v.v}" && matchDeviceInteraction((String)lv.v.p,r9)}
private static Boolean comp_drops				(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(r9,lv); return oldValue!=null && dcast(r9,oldValue.v.v)>dcast(r9,lv.v.v)}
private static Boolean comp_does_not_drop		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !comp_drops(r9,lv,rv,rv2,tv,tv2)}
private static Boolean comp_drops_below		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(r9,lv); return oldValue!=null && dcast(r9,oldValue.v.v)>=dcast(r9,rv.v.v) && dcast(r9,lv.v.v)<dcast(r9,rv.v.v)}
private static Boolean comp_drops_to_or_below	(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(r9,lv); return oldValue!=null && dcast(r9,oldValue.v.v)>dcast(r9,rv.v.v) && dcast(r9,lv.v.v)<=dcast(r9,rv.v.v)}
private static Boolean comp_rises				(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(r9,lv); return oldValue!=null && dcast(r9,oldValue.v.v)<dcast(r9,lv.v.v)}
private static Boolean comp_does_not_rise		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !comp_rises(r9,lv,rv,rv2,tv,tv2)}
private static Boolean comp_rises_above		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(r9,lv); return oldValue!=null && dcast(r9,oldValue.v.v)<=dcast(r9,rv.v.v) && dcast(r9,lv.v.v)>dcast(r9,rv.v.v)}
private static Boolean comp_rises_to_or_above	(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(r9,lv); return oldValue!=null && dcast(r9,oldValue.v.v)<dcast(r9,rv.v.v) && dcast(r9,lv.v.v)>=dcast(r9,rv.v.v)}
private static Boolean comp_remains_below		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(r9,lv); return oldValue!=null && dcast(r9,oldValue.v.v)<dcast(r9,rv.v.v) && dcast(r9,lv.v.v)<dcast(r9,rv.v.v)}
private static Boolean comp_remains_below_or_equal_to		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(r9,lv); return oldValue!=null && dcast(r9,oldValue.v.v)<=dcast(r9,rv.v.v) && dcast(r9,lv.v.v)<=dcast(r9,rv.v.v)}
private static Boolean comp_remains_above		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(r9,lv); return oldValue!=null && dcast(r9,oldValue.v.v)>dcast(r9,rv.v.v) && dcast(r9,lv.v.v)>dcast(r9,rv.v.v)}
private static Boolean comp_remains_above_or_equal_to		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(r9,lv); return oldValue!=null && (dcast(r9,oldValue.v.v)>=dcast(r9,rv.v.v)) && (dcast(r9,lv.v.v)>=dcast(r9,rv.v.v))}
private static Boolean comp_enters_range		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(r9,lv); if(oldValue==null)return false; Double ov=dcast(r9,oldValue.v.v); Double v=dcast(r9,lv.v.v); Double v1=dcast(r9,rv.v.v); Double v2=dcast(r9,rv2.v.v); if(v1>v2){ Double vv=v1; v1=v2; v2=vv }; return (ov<v1 || ov>v2) && v>=v1 && v<=v2}
private static Boolean comp_exits_range		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(r9,lv); if(oldValue==null)return false; Double ov=dcast(r9,oldValue.v.v); Double v=dcast(r9,lv.v.v); Double v1=dcast(r9,rv.v.v); Double v2=dcast(r9,rv2.v.v); if(v1>v2){ Double vv=v1; v1=v2; v2=vv }; return ov>=v1 && ov<=v2 && (v<v1 || v>v2)}
private static Boolean comp_remains_inside_of_range		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(r9,lv); if(oldValue==null)return false; Double ov=dcast(r9,oldValue.v.v); Double v=dcast(r9,lv.v.v); Double v1=dcast(r9,rv.v.v); Double v2=dcast(r9,rv2.v.v); if(v1>v2){ Double vv=v1; v1=v2; v2=vv }; return ov>=v1 && ov<=v2 && v>=v1 && v<=v2}
private static Boolean comp_remains_outside_of_range		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(r9,lv); if(oldValue==null)return false; Double ov=dcast(r9,oldValue.v.v); Double v=dcast(r9,lv.v.v); Double v1=dcast(r9,rv.v.v); Double v2=dcast(r9,rv2.v.v); if(v1>v2){ Double vv=v1; v1=v2; v2=vv }; return (ov<v1 || ov>v2) && (v<v1 || v>v2)}
private static Boolean comp_becomes_even		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(r9,lv);return oldValue!=null && icast(r9,oldValue.v.v)%i2!=iZ && icast(r9,lv.v.v)%i2==iZ}
private static Boolean comp_becomes_odd		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(r9,lv);return oldValue!=null && icast(r9,oldValue.v.v)%i2==iZ && icast(r9,lv.v.v)%i2!=iZ}
private static Boolean comp_remains_even		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(r9,lv);return oldValue!=null && icast(r9,oldValue.v.v)%i2==iZ && icast(r9,lv.v.v)%i2==iZ}
private static Boolean comp_remains_odd		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(r9,lv);return oldValue!=null && icast(r9,oldValue.v.v)%i2!=iZ && icast(r9,lv.v.v)%i2!=iZ}

private Boolean comp_changes_to_any_of			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueCacheChanged(r9,lv)!=null && comp_is_any_of(r9,lv,rv,rv2,tv,tv2) && matchDeviceInteraction((String)lv.v.p,r9)}
private Boolean comp_changes_away_from_any_of		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(r9,lv); return oldValue!=null && comp_is_any_of(r9,oldValue,rv,rv2) && matchDeviceInteraction((String)lv.v.p,r9)}

private Boolean comp_stays				(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is(r9,lv,rv,rv2,tv,tv2)}
//private Boolean comp_stays_unchanged			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return true }
private static Boolean comp_stays_unchanged			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !comp_changes(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_not				(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_not(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_equal_to			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_equal_to(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_different_than		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_different_than(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_less_than			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_less_than(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_less_than_or_equal_to	(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_less_than_or_equal_to(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_greater_than			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_greater_than(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_greater_than_or_equal_to	(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_greater_than_or_equal_to(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_even			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_even(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_odd			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_odd(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_true			(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_true(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_false		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_false(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_inside_of_range		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_inside_of_range(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_outside_of_range		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_outside_of_range(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_any_of		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_any_of(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_away_from	(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_not_equal_to(r9,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_away_from_any_of		(Map r9,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_not_any_of(r9,lv,rv,rv2,tv,tv2)}

private void traverseStatements(node,Closure closure,parentNode=null,Map<String,Boolean> data=null,Map<String,Integer> lvl=null){
	if(!node)return
	//if a statements element, go through each item
	if(node instanceof List){
		Integer lastlvl= iMs(lvl,sV)
		lvl.v=lastlvl+i1
		Boolean lastRes= data!=null && bIs(data,'restrictActive')
		for(Map item in (List<Map>)node)
			if(!item[sDI]){
				Boolean lastTimer= data!=null && bIs(data,sTIMER)
				if(data!=null && sMt(item)==sEVERY) data.timer=true // force downgrade of triggers
				traverseStatements(item,closure,parentNode,data,lvl)
				if(data!=null) data.timer=lastTimer
			}
		lvl.v=lastlvl
		if(data!=null) data.restrictActive=lastRes
		return
	}

	//got a statement
	if(closure instanceof Closure) closure(node,parentNode,data,lvl)

	Boolean lastTimer= data!=null && bIs(data,sTIMER)
	String ty=sMt(node)
	if(ty==sON && data!=null) data.timer=true // force downgrade of triggers
	if(ty in [sIF,sWHILE,sREPEAT]){
		Integer cnt=doCcheck((String)node.o,(List<Map>)node.c)
		if(cnt>i1)
			if(!data.inMem) addWarning(node,'Found multiple trigger comparisons ANDed together')
		if(ty==sIF && node.ei){
			Integer bnt
			for(Map ei in (List<Map>)node.ei){
				if(ei.c){
					bnt=doCcheck((String)ei.o,(List<Map>)ei.c)
					if(bnt>i1)
						if(!data.inMem) addWarning(node,'Found multiple trigger comparisons ANDed together in else if num: '+"${ei.$}")
				}
			}
		}
	}

	Integer lastlvl= iMs(lvl,sV)
	if(ty==sDO) lvl.v=lastlvl-i1 // do does not increase the level
	//if the statement has substatements go through them
	if(node.s instanceof List) traverseStatements((List)node.s,closure,node,data,lvl)
	if(ty==sDO) lvl.v=lastlvl

	if(data!=null) data.timer=lastTimer

	if(node.e instanceof List) traverseStatements((List)node.e,closure,node,data,lvl)
}

@CompileStatic
private static Integer doCcheck(String grouping,List<Map> cndtns){
	Integer cnt
	cnt=iZ
	if(cndtns){
		Boolean isAND=grouping==sAND
		String ty
		for(Map cndtn in cndtns){
			ty=sMt(cndtn)
			if(ty==sGROUP){
				Integer i=doCcheck((String)cndtn.o,(List<Map>)cndtn.c)
				cnt+=i
				if(!isAND && i==i1)cnt--
			}else if(ty==sCONDITION && (String)cndtn.ct==sT && isAND)cnt++
		}
	}
	return cnt
}

private void traverseEvents(node,Closure closure,parentNode=null){
	if(!node)return
	//if a statements element, go through each item
	if(node instanceof List){
		for(item in (List)node) traverseEvents(item,closure,parentNode)
		return
	}
	if(closure instanceof Closure) closure(node,parentNode)
}

private void traverseConditions(node,Closure closure,parentNode=null,Map<String,Boolean> data=null){
	if(!node)return
	//if a statements element, go through each item
	if(node instanceof List){
		for(item in (List)node) traverseConditions(item,closure,parentNode,data)
		return
	}
	//got a condition
	if(node.t==sCONDITION && (closure instanceof Closure)) closure(node,parentNode)
	//if the statement has subcondition go through them
	if(node.c instanceof List){
		if(closure instanceof Closure)closure(node,parentNode)
		traverseConditions((List)node.c,closure,node,data)
	}
}

private void traverseRestrictions(node,Closure closure,parentNode=null,Map<String,Boolean> data=null){
	if(!node)return
	if(data!=null)data.restrictActive=parentNode!=null || bIs(data,'restrictActive')
	//if a statements element, go through each item
	if(node instanceof List){
		for(item in (List)node) traverseRestrictions(item,closure,parentNode,data)
		return
	}
	//got a restriction
	if(node.t==sRESTRIC && (closure instanceof Closure)) closure(node,parentNode,data)
	//if the statement has subrestrictions go through them
	if(node.r instanceof List){
		if(closure instanceof Closure)closure(node,parentNode,data)
		traverseRestrictions((List)node.r,closure,node,data)
	}
}

private void traverseExpressions(node,Closure closure,prm,parentNode=null){
	if(!node)return
	//if a statements element, go through each item
	if(node instanceof List){
		for(item in (List)node) traverseExpressions(item,closure,prm,parentNode)
		return
	}
	//got a statement
	if(closure instanceof Closure) closure(node,parentNode,prm)
	//if the statement has subexpression go through them
	if(node.i instanceof List) traverseExpressions((List)node.i,closure,prm,node)
}

private void updateDeviceList(Map r9){
	List a=((List)((Map)r9[sDEVS])*.value.id).unique()
	if(a){
		app.updateSetting('dev', [(sTYPE): 'capability', (sVAL): a])
		// settings update do not happen till next execution
		updateCacheFld(r9,sDEVS,[:]+r9[sDEVS],'updateDeviceList', true)
	}
	r9.updateDevices=false
}

@CompileStatic
private void updateCacheFld(Map r9,String nm,v,String s,Boolean gm){
	if(!gm || (gm && getCachedMaps(s)!=null)){
		String id=sMs(r9,snId)
		String mSmaNm=id
		getTheLock(mSmaNm,s)
		Map nc=theCacheVFLD[id]
		if(nc){
			nc[nm]=v
			theCacheVFLD[id]=nc
			theCacheVFLD=theCacheVFLD
		}
		releaseTheLock(mSmaNm)
	}
}

@CompileStatic
private static addWarning(Map node,String msg){
	if(!node)return
	node.w=node.w ? (List)node.w:[]
	((List)node.w).push(msg) // modifies the code
}

private void subscribeAll(Map r9,Boolean doit,Boolean inMem){
	String s='subscribeAll '
	if(eric())doLog(sDBG,s+"doit: $doit")
	try{
		if(!r9){ log.error s+"no r9"; return }
		Map<String,Integer> ss=[
			events:iZ,
			controls:iZ,
			devices:iZ,
		]
		state.allowResume=false
		Integer lg=iMs(r9,sLOGNG)
		String LID=sMs(r9,sLOCID)
		List<String> oLIDS= (List<String>)r9.oldLocations
		Map msg=timer "Finished subscribing",r9,iN1
		if(doit){
			wremoveAllInUseGlobalVar()
			wunsubscribe()
			r9[sDEVS]=[:]
			updateCacheFld(r9,sDEVS,[:],s,true)
			if(lg>i1)trace "Subscribing to devices...",r9,i1
		}
		Map<String,Map<String,Integer>> devices=[:]
		Map rawDevices,curStatement,curCondition
		rawDevices=[:]
		Map<String,Map> subscriptions=[:]
		Boolean hasTriggers,dwnGrdTrig
		hasTriggers=false
		Map<String,Boolean> stmtData=[(sTIMER):false,inMem:inMem] // downGrade of triggers
		Map<String,Integer> stmtLvl=[v:iZ]
		dwnGrdTrig=false // EVERY statement only has timer trigger, ON only has its event
		curStatement=null
		curCondition=[:]
		String never='never'
		//traverse all statements
		Closure expressionTraverser
		Closure operandTraverser
		Closure eventTraverser
		Closure conditionTraverser
		Closure restrictionTraverser
		Closure statementTraverser
		expressionTraverser={ Map expression,parentExpression,String cmpTyp ->
			String subsId,deviceId,attr,exprID
			subsId=sNL
			deviceId=sNL
			attr=sNL
			exprID=(String)expression.id
			if(sMt(expression)==sDEV && exprID){
				if(exprID in oLIDS) exprID=LID
				devices[exprID]=[(sC):(cmpTyp ? i1:iZ)+(devices[exprID]?.c ? iMs(devices[exprID],sC):iZ)]
				deviceId=exprID
				attr=sMa(expression)
				subsId=deviceId+attr
			}
			String exprX=(String)expression.x
			if(sMt(expression)==sVARIABLE && exprX && exprX.startsWith(sAT)){
				deviceId=LID
				if(exprX.startsWith(sAT2)){
					String vn=exprX.substring(i2)
					Map hg=wgetGlobalVar(vn) // check if it exists
					if(hg){
						subsId=vn
						attr=sVARIABLE+sCLN+vn
						waddInUseGlobalVar(r9,vn)
					}else warn "hub varible not found while subscribing: $vn",r9
				}else{
					subsId=exprX
					attr=(String)r9.instanceId+sDOT+exprX
					waddInUseGlobalVar(r9,exprX,false)
				}
			}
			if(subsId!=sNL && deviceId!=sNL){
				String ct
				ct=(String)subscriptions[subsId]?.t ?: sNL
				if(ct==sTRIG || cmpTyp==sTRIG){
					ct=sTRIG
				}else ct=ct ?: cmpTyp
				subscriptions[subsId]=[(sD):deviceId,(sA):attr,(sT):ct,(sC):(subscriptions[subsId] ? (List)subscriptions[subsId].c:[])+[curCondition]]
				if(deviceId!=LID && deviceId.startsWith(sCLN)){
					if(doit && !rawDevices[deviceId])rawDevices[deviceId]=getDevice(r9,deviceId)
					devices[deviceId]=[(sC):(cmpTyp ? i1:iZ)+(devices[deviceId]?.c ? iMs(devices[deviceId],sC):iZ)]
				}
			}
		}
		List<String>rg=['receives','gets']
		operandTraverser={ Map node,Map operand,value,String cmpTyp ->
			if(!operand)return
			switch(sMt(operand)){
				case sP: //physical device
					String deviceId
					for(String mdeviceId in expandDeviceList(r9,(List)operand.d,true)){
						deviceId=mdeviceId
						if(deviceId in oLIDS) deviceId=LID
						devices[deviceId]=[(sC):(cmpTyp ? i1:iZ)+(devices[deviceId]?.c ? iMs(devices[deviceId],sC):iZ)]
						String attr=sMa(operand)
						String subsId=deviceId+attr
						//if we have any trigger it takes precedence over anything else
						String ct
						ct=(String)subscriptions[subsId]?.t ?: sNL
						Boolean allowAval
						allowAval=(Boolean)null
						List<String> avals
						avals=[]
						if(ct==sTRIG || cmpTyp==sTRIG){
							ct=sTRIG
							String msgVal
							hasTriggers=true

							allowAval= subscriptions[subsId]?.allowA==null ? true: bIs(subscriptions[subsId],'allowA')
							String attrVal
							attrVal=sNL
							if(allowAval && ((String)node.co in rg) && value && sMt(value)==sC && value.c){
								attrVal=(String)value.c
								//msgVal='Attempting Attribute value'
								avals=(List)subscriptions[subsId]?.avals ?: []
							}else allowAval=false
							if(allowAval && attrVal!=sNL){
								if(! (attrVal in avals)) avals << attrVal
								msgVal="Attempting Attribute $attr value "+avals
							}else{
								allowAval=false
								msgVal="Using Attribute $attr"
								avals=[]
							}
							if(doit && lg>i2)debug msgVal+' subscription',r9
						}else ct=ct ?: cmpTyp
						subscriptions[subsId]=[(sD):deviceId,(sA):attr,(sT):ct,(sC):(subscriptions[subsId] ? (List)subscriptions[subsId].c:[])+(cmpTyp?[node]:[]),allowA: allowAval,avals: avals]
						if(doit && deviceId!=LID && deviceId.startsWith(sCLN) && !rawDevices[deviceId]){
							rawDevices[deviceId]=getDevice(r9,deviceId)
						}
					}
					break
				case sV: //virtual device
					String deviceId=LID
					//if we have any trigger, it takes precedence over anything else
					devices[deviceId]=[(sC):(cmpTyp ? i1:iZ)+(devices[deviceId]?.c ? iMs(devices[deviceId],sC):iZ)]
					String subsId,attr,ct
					subsId=sNL
					attr=sNL
					String operV=(String)operand.v
					String tsubId=deviceId+operV
					switch(operV){
						case sPSTNRSM:
							state.allowResume=true

						case sTIME:
						case sDATE:
						case sDTIME:
						case sMODE:
						case sTILE:
						case 'powerSource':
						case 'systemStart':
						case 'severeLoad':
						case 'zigbeeOff':
						case 'zigbeeOn':
						case 'zwaveCrashed':
						case 'sunriseTime':
						case 'sunsetTime':
							subsId=tsubId
							attr=operV
							break
						case sHSMSTS:
						case 'alarmSystemStatus':
							subsId=tsubId
							attr=sHSMSTS
							break
						case sHSMALRT:
						case 'alarmSystemAlert':
							subsId=tsubId
							attr=sHSMALRT
							break
						case sHSMSARM:
						case 'alarmSystemEvent':
							subsId=tsubId
							attr=sHSMSARM
							break
						case 'alarmSystemRule':
							subsId=tsubId
							attr="hsmRules"
							break
						case 'email':
							subsId="$deviceId${operV}${(String)r9[sID]}".toString()
							attr="email.${(String)r9[sID]}".toString()// receive email does not work
							break
						case 'ifttt':
							if(value && sMt(value)==sC && value.c){
								Map<String,String> options=(Map)VirtualDevices()[operV]?.o
								String item=options ? (String)options[(String)value.c]:(String)value.c
								if(item){
									subsId="$deviceId${operV}${item}".toString()
									String attrVal=".${item}".toString()
									attr="${operV}${attrVal}".toString()
								}
							}
							break
					}
					if(subsId!=sNL && attr!=sNL){
						ct=(String)subscriptions[subsId]?.t ?: sNL
						if(ct==sTRIG || cmpTyp==sTRIG){
							ct=sTRIG
							hasTriggers=true
						}else ct=ct ?: cmpTyp
						subscriptions[subsId]=[(sD):deviceId,(sA):attr,(sT):ct,(sC):(subscriptions[subsId] ? (List)subscriptions[subsId].c:[])+(cmpTyp?[node]:[])]
						break
					}
					break
				case sX: // variable
					String operX=(String)operand.x
					if(operX && operX.startsWith(sAT)){
						String subsId,attr,ct
						subsId=sNL
						attr=sNL
						if(operX.startsWith(sAT2)){
							String vn=operX.substring(i2)
							Map hg=wgetGlobalVar(vn) // check if it exists
							if(hg){
								subsId=vn
								attr=sVARIABLE+sCLN+vn
								waddInUseGlobalVar(r9,vn)
							}else warn "hub varible not found while subscribing: $vn",r9
						}else{
							subsId=operX
							attr="${(String)r9.instanceId}.${operX}".toString()
							waddInUseGlobalVar(r9,operX,false)
						}
						if(subsId!=sNL && attr!=sNL){
							ct=(String)subscriptions[subsId]?.t ?: sNL
							if(ct==sTRIG || cmpTyp==sTRIG){
								ct=sTRIG
								hasTriggers=true
							}else ct=ct ?: cmpTyp
							subscriptions[subsId]=[(sD):LID,(sA):attr,(sT):ct,(sC):(subscriptions[subsId] ? (List)subscriptions[subsId].c:[])+(cmpTyp?[node]:[])]
						}
					}
					break
				case sC: //constant
				case sE: //expression
					traverseExpressions(operand.exp?.i,expressionTraverser,cmpTyp)
					break
			}
		}
		eventTraverser={ Map event,parentEvent ->
			if(event.lo){
				String cmpTyp=sTRIG
				operandTraverser(event,(Map)event.lo,null,cmpTyp)
			}
		}
		List<String> ltsub= ['happens_daily_at']
		List<String> lntrk= ltsub+rg+['arrives','event_occurs','executes','gets','gets_any','receives']
		conditionTraverser={ Map cndtn,parentCondition ->
			Map svCond=curCondition
			curCondition=cndtn
			String co=sMs(cndtn,'co')
			if(co){
				Map comparison
				comparison=Comparisons().conditions[co]
				String cmpTyp
				cmpTyp=sCONDITION
				Boolean isTrig
				isTrig=false
				if(comparison==null){
					comparison=Comparisons().triggers[co]
					if(comparison!=null) isTrig=true
				}
				if(comparison!=null){
					if(isTrig){
						Boolean didDwnGrd= dwnGrdTrig || sMs(cndtn,'sm')==never // not force condition
						if(!didDwnGrd){
							hasTriggers=true
							cmpTyp=sTRIG //subscription method
						}
						String m,tm
						m=sNL
						tm="trigger comparison type"
						String tn=" that relies on runtime "
						Boolean isTracking= !(co in lntrk)
						Boolean isSub= co in ltsub
						Boolean isStays=co.startsWith('stays')
						Map lo=(Map)cndtn.lo
						Integer dsz= lo?.d ? ((List)lo.d).size():iZ
						Boolean isAll= isTracking && !isStays && lo && lo.a && sMs(lo,sG)==sALL &&
								( dsz>i1 || ( dsz==i1 && !(((List<String>)lo.d)[iZ].startsWith(sCLN)) ) )
						if(didDwnGrd)m="downgraded "+tm+" not subscribed to in EVERY or ON statement, or forced never subscribe - should use condition comparison rather than trigger"
						else{
							if(isAll)tm+=" using multiple devices with ALL (ANDed trigger),"
							else if(isTracking)tm+=tn+"event tracking,"
							else if(isSub)tm+=tn+"timer scheduling,"
							if(isTracking||isSub){
								if(isAll)m=tm
								else if(stmtLvl.v>i2)m="nested (level ${stmtLvl.v}) "+tm
								else if(bIs(stmtData,'restrictActive'))m="nested by restriction "+tm
								if(m)m+=" that may cause errors"
							}
						}
						if(m && !inMem)addWarning(curStatement,'Found '+m+"; comparison: $co  num: ${cndtn.$}")
					}
					cndtn.ct=(String)cmpTyp.take(i1) // modifies the code
					Integer pCnt=comparison.p!=null ? iMs(comparison,sP): iZ
					Integer i
					for(i=iZ; i<=pCnt; i++){
						//get the operand to parse
						Map operand=(i==iZ ? (Map)cndtn.lo:(i==i1 ? (Map)cndtn.ro:(Map)cndtn.ro2))
						operandTraverser(cndtn,operand,cndtn.ro,cmpTyp)
					}
				}
			}
			if(cndtn.ts instanceof List)traverseStatements((List)cndtn.ts,statementTraverser,cndtn,stmtData,stmtLvl)
			if(cndtn.fs instanceof List)traverseStatements((List)cndtn.fs,statementTraverser,cndtn,stmtData,stmtLvl)
			//noinspection GrReassignedInClosureLocalVar
			curCondition=svCond
		}
		restrictionTraverser={ Map restriction,Map parentRestriction,Map data ->
			String rco=sMs(restriction,'co')
			if(rco){
				Map comparison
				comparison=Comparisons().conditions[rco]
				if(comparison==null)comparison=Comparisons().triggers[rco]
				if(comparison!=null){
					Integer pCnt=comparison.p!=null ? iMs(comparison,sP): iZ
					Integer i
					for(i=iZ; i<=pCnt; i++){
						//get the operand to parse
						Map operand=(i==iZ ? (Map)restriction.lo:(i==i1 ? (Map)restriction.ro:(Map)restriction.ro2))
						operandTraverser(restriction,operand,null,sNL)
					}
				}
			}
		}
		List<String>lsub=[sIF,sFOR,sWHILE,sREPEAT,sSWITCH,sON,sEACH,sEVERY]
		statementTraverser={ Map node,parentNode,Map<String,Boolean> data,Map<String,Integer> lvl ->
			dwnGrdTrig=data!=null && bIs(data,sTIMER)
			if(node.r)traverseRestrictions(node.r,restrictionTraverser,node,data)
			for(String mdeviceId in (List<String>)node.d){
				String deviceId
				deviceId=mdeviceId
				if(deviceId in oLIDS)deviceId=LID
				devices[deviceId]=devices[deviceId] ?: [(sC):iZ]
				if(doit && deviceId!=LID && deviceId.startsWith(sCLN) && !rawDevices[deviceId]){
					rawDevices[deviceId]=getDevice(r9,deviceId)
				}
			}

			String t=sMt(node)
			Integer lastlvl
			lastlvl=null
			Map lastStatement
			lastStatement=null
			if(t?.length()>i1){
				lastStatement=curStatement
				curStatement=node
				node.remove('w') // modifies the code
				lastlvl=lvl.v
				switch(t){
					case sEVERY: if(lastlvl>1 && !inMem)addWarning(node,'Timers are designed to be top-level statements and should not be used inside other statements. If you need a conditional timer, please look into using a while loop instead.'); break
					case sON: if(lastlvl>1 && !inMem)addWarning(node,'On event statements are designed to be top-level statements and should not be used inside other statements.'); break
				}
				//log.warn "found statement $t level ${lvl.v}"
				if(t in lsub)lvl.v=lastlvl+i1
			}
			switch(t){
				case sACTION:
					if(node.k){
						for(Map k in (List<Map>)node.k)traverseStatements(k.p?:[],statementTraverser,k,data,lvl)
					}
					break
				case sIF:
					if(node.ei){
						Integer lastlvl1=lvl.v
						lvl.v=lastlvl1+i1
						for(Map ei in (List<Map>)node.ei){
							traverseConditions(ei.c?:[],conditionTraverser,ei,data)
							traverseStatements(ei.s?:[],statementTraverser,ei,data,lvl)
						}
						lvl.v=lastlvl1
					}
				case sWHILE:
				case sREPEAT:
					traverseConditions(node.c,conditionTraverser,node,data)
					break
				case sON:
					traverseEvents(node.c?:[],eventTraverser)
					break
				case sSWITCH:
					operandTraverser(node,(Map)node.lo,null,sCONDITION)
					for(Map c in (List<Map>)node.cs){
						operandTraverser(c,(Map)c.ro,null,sNL)
						//if case is a range traverse the second operand too
						if(sMt(c)==sR)operandTraverser(c,(Map)c.ro2,null,sNL)
						if(c.s instanceof List)traverseStatements((List)c.s,statementTraverser,node,data,lvl)
					}
					break
				case sEVERY:
					hasTriggers=true
					break
			}
			if(t?.length()>i1){
				lvl.v=lastlvl
				curStatement=lastStatement
			}
		}

		Map r9p=(Map)r9[sPIS]
		if(r9p.r)traverseRestrictions((List)r9p.r,restrictionTraverser,null,stmtData)
		if(r9p.s)traverseStatements((List)r9p.s,statementTraverser,null,stmtData,stmtLvl)
		//device variables could be device type variable, or another type using device attributes to fill in
		for(Map variable in ((List<Map>)r9p.v).findAll{ Map it -> /*(String)it.t==sDEV && */ it.v!=null && ((Map)it.v).d!=null && ((Map)it.v).d instanceof List}){
			for(String mdeviceId in (List<String>)((Map)variable.v).d){
				String deviceId
				deviceId=mdeviceId
				if(deviceId in oLIDS)deviceId=LID
				devices[deviceId]=[(sC): iZ+(devices[deviceId]?.c ? iMs(devices[deviceId],sC):iZ)]
				if(doit && deviceId!=LID && !rawDevices[deviceId]){
					rawDevices[deviceId]=getDevice(r9,deviceId)
				}
			}
		}
		if(!LT1)LT1=fill_TIM()
		if(!LTHR)LTHR=fill_THR()
		Map<String,Integer> dds=[:]
		String always='always'
		List<String>nosub=[sTILE,sPSTNRSM]
		Boolean des=gtPOpt(r9,'des')
		for(subscription in subscriptions){
			Map sub=(Map)subscription.value
			List<Map> lsc=(List<Map>)sub.c
			String sst=sMt(sub)
			String devStr=sMs(sub,sD)
			String altSub,m
			altSub=never
			if(doit && isEric(r9))myDetail r9,"evaluating sub: $subscription",iN2
			for(Map cndtn in lsc){
				if(cndtn){
					cndtn.s=false // modifies the code
					String tt0=sMs(cndtn,'sm')
					altSub= tt0==always ? tt0:(altSub!=always && tt0!=never ? tt0:altSub)
				}
			}
			m=sBLK
			if(doit && lg>i2){
				m= des ? 'disable event subscriptions, ':sBLK
				m+= !des && !hasTriggers  ? 'no triggers, promoting conditions ':sBLK
				m+= !des && altSub in [never,always] ? 'sub: '+altSub:sBLK
				if(m)debug 'subscriptions: '+m,r9
			}
			Boolean skip,allowA
			skip=true
			String a
			a=sMa(sub)
			allowA=bIs(sub,'allowA')
			// check for disabled event subscriptions
			if(!des && sst && !!lsc && altSub!=never && (sst==sTRIG || altSub==always || !hasTriggers)){
				def device= devStr.startsWith(sCLN)? getDevice(r9,devStr):null
				allowA=!!allowA
				if(a in LTHR){
					a=sTHREAX
					allowA=false
				}
				if(device!=null){
					for(Map cndtn in lsc){
						if(cndtn){
							String ct,t1
							ct=sMs(cndtn,'ct')
							t1=sMs(cndtn,'sm')
							if(sMt(cndtn)==sEVENT && ct==sNL){ cndtn.ct=sT; ct=sT } // modifies the code
							cndtn.s= t1!=never && (ct==sT || t1==always || !hasTriggers) // modifies the code
							if(bIs(cndtn,sS) && a==sPSTNRSM && !bIs(gtState(),'allowResume')){
								state.allowResume=true
								if(isEric(r9))myDetail r9,"Enabling piston resume event due to subscription control: $subscription",iN2
							}
							if(doit && isEric(r9))myDetail r9,"processed condition: $cndtn",iN2
						}
					}
					Integer cnt
					cnt=ss.events
					if(!(a in (LT1+nosub) )){ // timers, tile, pistonResume events don't have hub subscription
						List<String> avals=(List)sub.avals
						if(allowA && avals.size()<i9){
							for(String aval in avals){
								String myattr=a+sDOT+aval
								if(doit){
									if(lg>iZ)info "Subscribing to $device.${myattr}...",r9
									subscribe(device,myattr,deviceHandler)
									skip=false
								}
								cnt+=i1
							}
						}else{
							if(doit){
								if(lg>iZ)info "Subscribing to $device.${a}...",r9
								subscribe(device,a,deviceHandler)
								skip=false
							}
							cnt+=i1
						}
					}
					if(a in nosub) cnt+=i1

					ss.events=cnt
					String didS=dString(device)
					if(!dds[didS]){
						ss.devices+=i1
						dds[didS]=i1
					}
				}else{
					if(doit)error "Failed subscribing to $devStr.${a}, device not found",r9
				}
			}else{
				for(Map cndtn in lsc)
					if(cndtn){
						cndtn.s=false // modifies the code
						if(a==sPSTNRSM && bIs(gtState(),'allowResume')){
							state.allowResume=false
							if(isEric(r9))myDetail r9,"Disabled piston resume event due to subscription control: $subscription",iN2
						}
					}
				if(devices[devStr])devices[devStr].c=iMs(devices[devStr],sC)-i1
			}
			if(skip && doit && isEric(r9))myDetail r9,"SKIPPING sub: $subscription",iN2
		}

		//not using fake subscriptions; piston has devices inuse in settings
		for(d in devices.findAll{ ((Integer)it.value.c<=iZ || des) && (String)it.key!=LID }){
			def device= ((String)d.key).startsWith(sCLN)? getDevice(r9,(String)d.key):null
			if(device!=null && !isDeviceLocation(device)){
				String didS=dString(device)
				if(lg>i1 && doit)trace "Piston utilizes $device...",r9
				ss.controls+=i1
				if(!dds[didS]){
					ss.devices+=i1
					dds[didS]=i1
				}
			}
		}
		if(doit){
			//save devices
			List deviceList=rawDevices.collect{ it && it.value ? it.value:null }
			Boolean a=deviceList.removeAll{ it==null }
			//List deviceIdList=deviceList.collect{ it.id }
			r9[sDEVS]= deviceList.collectEntries{ it -> [(hashD(r9,it)):it] }
			r9[sDEVS]= r9[sDEVS] ?:[:]
			updateDeviceList(r9)
			rawDevices=null

			state.subscriptions=ss
			if(lg>i1)trace msg,r9

			//subscribe(app,appHandler)
			subscribe(location,sMs(r9,sID),executeHandler)
			String t= hashId(r9,r9.nId)
			if(t!=sMs(r9,sID)) subscribe(location,t,executeHandler) //backwards

			Long n=wnow()
			Map event=[(sT):n,(sDEV):cvtLoc(),(sNM):sTIME,(sVAL):n,schedule:[(sT):lZ,(sS):iZ,(sI):iN9]]
			r9[sCACHE]=[:] // reset followed by
			assignSt(sCACHE,[:])
			updateCacheFld(r9,sCACHE,[:],s,true)

			a=executeEvent(r9,event)
			processSchedules r9,true
			//save cache collected through dummy run
			for(item in (Map<String,Map>)r9[sNWCACHE])((Map)r9[sCACHE])[(String)item.key]=item.value
			assignSt(sCACHE,(Map)r9[sCACHE])
			updateCacheFld(r9,sCACHE,[:]+(Map)r9[sCACHE],s,true)

			Map myRt=shortRtd(r9)
			myRt.t=n
			relaypCall(myRt)
		}

	}catch(all){
		error "An error has occurred while subscribing: ",r9,iN2,all
	}
}

@CompileStatic
private List<String> expandDeviceList(Map r9,List devs,Boolean localVarsOnly=false){
	Boolean mlocalVars=false	//allowing global vars
	List<String>devices=devs
	List<String> result
	result=[]
	if(devices){
		for(String deviceId in devices){
			if(deviceId){
				if(isWcDev(deviceId)) Boolean a=result.push(deviceId)
				else{
					if(mlocalVars){
						//during subscriptions we can use local vars only to make sure we don't subscribe to "variable" lists of devices
						Map var=(Map)((Map)r9.localVars)[deviceId]
						if(var && sMt(var)==sDEV && var.v instanceof Map){
							Map m=(Map)var.v
							if(sMt(m)==sD && m.d instanceof List)result+= (List)m.d
						}
					}else{
						Map var=getVariable(r9,deviceId)
						if(sMt(var)==sDEV)
							//noinspection GroovyAssignabilityCheck
							result+= (var.v instanceof List) ? (List)var.v:[]
						else{
							def device=var.v ? getDevice(r9,scast(r9,sMs(var,sV))):null
							if(device!=null)result+= [hashD(r9,device)]
						}
					}
				}
			}
		}
	}
	return result.unique()
}

//def appHandler(evt){
//}

@CompileStatic
private static String sanitizeVariableName(String name){
	String rname=name!=sNL ? name.trim().replace(sSPC,sUNDS):sNL
	return rname
}

@CompileStatic
private getDevice(Map r9,String idOrName){
	if(idOrName in (List<String>)r9.allLocations)return gtLocation()
	if(!idOrName)return null
	r9[sDEVS]= r9[sDEVS] ?:[:]
	Map dM=(Map)r9[sDEVS]
	def t0=dM[idOrName]
	def device
	device=t0!=null ? t0:dM.find{ gtLbl(it.value)==idOrName }?.value
	if(device==null){
		String aD='allDevices'
		if(r9[aD]==null){
			Map msg=timer "Device missing from piston. Loading all from parent",r9
			r9[aD]=wlistAvailableDevices(true)
			if(eric()||isDbg(r9))debug msg,r9
		}
		if(r9[aD]!=null){
			def deviceEnt=((Map)r9[aD]).find{ idOrName==(String)it.key || idOrName==gtLbl(it.value) }
			if(deviceEnt!=null){
				device=deviceEnt.value
				r9.updateDevices=true
				r9[sDEVS][(String)deviceEnt.key]=device
			}
		}
		if(device==null){
			error "Device (${idOrName}) was not found. Please review your piston and webCoRE device access settings.",r9
		}
	}
	return device
}

@Field static List<String> LDAV
@Field static final String sSTS='$status'

private getDeviceAttributeValue(Map r9,device,String attr){
	Map e=(Map)r9[sEVENT]
	String r9EvN=e!=null ? sMs(e,sNM):sBLK
	Boolean r9EdID=e!=null ? sMs(e,sDEV)==hashD(r9,device):false
	if(r9EvN==attr && r9EdID)return e[sVAL]
	else{
		def result
		String msg="Error reading current value for ${device}.".toString()
		if(!LDAV){
			if(!LTHR) LTHR=fill_THR()
			LDAV=[sSTS]+LTHR
			mb()
		}
		if(attr in LDAV){
			switch(attr){
				case sSTS:
					return device.getStatus()
				default:
					Map xyz
					try{ xyz= r9EvN==sTHREAX && r9EdID && e[sVAL] ? e[sVAL]:null }catch(ignored){}
					if(xyz==null){
						try{
							xyz=device.currentValue(sTHREAX,true)
						}catch(al){
							error msg+sTHREAX+sCLN,r9,iN2,al
							break
						}
					}
					switch(attr){
						case sORIENT:
							return getThreeAxisOrientation(xyz)
						case sAXISX:
							return xyz.x
						case sAXISY:
							return xyz.y
						case sAXISZ:
							return xyz.z
					}
			}
		}else{
			try{
				result=device.currentValue(attr,true)
			}catch(all){
				error msg+attr+sCLN,r9,iN2,all
			}
		}
		return result!=null ? result:sBLK
	}
}

@Field static List<String> LTHR
private static List<String> fill_THR(){ return [sORIENT,sAXISX,sAXISY,sAXISZ] }

private static Map devAttrT(Map r9,String attr,device){
	Map attribute=[(sT):sSTR]
	Map a1=null
	if(attr){
		a1=Attributes()[attr]
		if(a1==null && device){
			// ask the device what is the type
			def at=device.getSupportedAttributes().find{ (String)it.name==attr }
			// enum,string,json_object -> string; number, date?, vector3?
			if(at && at.getDataType()=='NUMBER') attribute=[(sT):sDEC]
		}
	}
	Map res= a1 ?: attribute
	return res
}

private Map getDeviceAttribute(Map r9,String deviceId,String attr,subDeviceIndex=null,Boolean trigger=false){
	if(deviceId in (List<String>)r9.allLocations){ //backward compatibility
		//we have the location here
		switch(attr){
			case sMODE:
				Map mode=gtCurrentMode()
				return rtnMapS(hashId(r9,(Long)mode.id))+[(sN):(String)mode.name]
			case sHSMSTS:
			case 'alarmSystemStatus':
				String v=gtLhsmStatus()
				String n=VirtualDevices()['alarmSystemStatus']?.o[v]
				return rtnMapS(v)+[(sN):n]
		}
		return rtnMapS(gtLname())
	}
	def device=getDevice(r9,deviceId)
	if(device!=null){
		def value,t0
		value=gtLbl(device)
		t0=null
		Map attribute=devAttrT(r9,attr,device)
		String atT=sMt(attribute)
		if(attr!=sNL){
			t0=getDeviceAttributeValue(r9,device,attr)
			if(attr==sHUE) t0=t0*d3d6
			if(t0 instanceof BigDecimal){
				if(atT==sINT) t0=t0 as Integer
				else if(atT==sDEC) t0=t0 as Double
			}
			value= matchCast(r9,t0,atT) ? t0:cast(r9,t0,atT)
		}
		//have to compare ids and type for hubitat since the locationid can be the same as the deviceid
		Map ce=(Map)r9[sCUREVT]
		String tt0
		tt0= ce? (sMs(ce,sDEV) ?: sNL) : sNL
		tt0=tt0 ?: sMs(r9,sLOCID)
		Boolean deviceMatch= hashD(r9,device)==tt0 || (isDeviceLocation(device) && tt0 in (List<String>)r9.allLocations)
		//x=eXclude- if a momentary attribute is looked for and the device does not match the current device, then we must ignore during comparisons
		if(!LTHR) LTHR=fill_THR()
		return [
			(sT):atT,
			(sV):value,
			(sD):deviceId,
			(sA):attr,
//			(sI):subDeviceIndex,
			(sX):(attribute.m!=null || trigger) && (!deviceMatch || (ce && (attr in LTHR ? sTHREAX:attr)!=sMs(ce,sNM)))
		]
	}
	return rtnMapE("Device '${deviceId}':${attr} not found".toString())
}

private Map getJsonData(Map r9,data,String name,String feature=sNL){
	if(data){
		String mpart
		mpart=sNL
		try{
			List<String> parts=name.replace('][','].[').tokenize(sDOT)
			def args
			args=(data instanceof Map ? [:]+(Map)data : (data instanceof List ? []+(List)data : new JsonSlurper().parseText((String)data)))
			Integer partIndex
			partIndex=iN1
			for(String part in parts){
				mpart=part
				partIndex+=i1
				if(args instanceof String || args instanceof GString){
					def narg=parseMyResp(args.toString())
					if(narg)args=narg
				}
				if(args instanceof List){
					List largs=(List)args
					Integer sz=largs.size()
					switch(part){
						case 'length':
							return rtnMapI(sz)
						case 'first':
							args=sz>iZ ? largs[iZ]:sBLK
							continue
						case 'second':
							args=sz>i1 ? largs[i1]:sBLK
							continue
						case 'third':
							args=sz>i2 ? largs[i2]:sBLK
							continue
						case 'fourth':
							args=sz>i3 ? largs[i3]:sBLK
							continue
						case 'fifth':
							args=sz>i4 ? largs[i4]:sBLK
							continue
						case 'sixth':
							args=sz ? largs[i5]:sBLK
							continue
						case 'seventh':
							args=sz>i6 ? largs[i6]:sBLK
							continue
						case 'eighth':
							args=sz>i7 ? largs[i7]:sBLK
							continue
						case 'ninth':
							args=sz>i8 ? largs[i8]:sBLK
							continue
						case 'tenth':
							args=sz>i9 ? largs[i9]:sBLK
							continue
						case 'last':
							args=sz>iZ ? largs[sz-i1]:sBLK
							continue
					}
				}
				if(!(args instanceof Map) && !(args instanceof List))return rtnMap(sDYN,sBLK)
				//nfl overrides
				Boolean overrideArgs=false
/*				if(feature=='NFL' && partIndex==i1 && !!args && !!args.games){
					def offset,start,end
					offset=null
					start=null
					end=null
					Date date=localDate()
					Integer dow=date.day
					switch(((String)part.tokenize(sLB)[iZ]).toLowerCase()){
						case 'yesterday':
							offset=iN1
							break
						case 'today':
							offset=iZ
							break
						case 'tomorrow':
							offset=i1
							break
						case 'mon':
						case 'monday':
							offset=dow<=i2 ? i1-dow:i8-dow
							break
						case 'tue':
						case 'tuesday':
							offset=dow<=i2 ? i2-dow:i9-dow
							break
						case 'wed':
						case 'wednesday':
							offset=dow<=i2 ? -i4 -dow:i3-dow
							break
						case 'thu':
						case 'thursday':
							offset=dow<=i2 ? iN3 -dow:i4-dow
							break
						case 'fri':
						case 'friday':
							offset=dow<=i2 ? iN2 -dow:i5-dow
							break
						case 'sat':
						case 'saturday':
							offset=dow<=i2 ? iN1 -dow:i6-dow
							break
						case 'sun':
						case 'sunday':
							offset=dow<=i2 ? iZ -dow:i7-dow
							break
						case 'lastweek':
							start=(dow<=i2 ? -i4 -dow:i3-dow)-i7
							end=(dow<=i2 ? i2 -dow:i9-dow)-i7
							break
						case 'thisweek':
							start=dow<=i2 ? -i4 -dow:i3-dow
							end=dow<=i2 ? i2 -dow:i9-dow
							break
						case 'nextweek':
							start=(dow<=i2 ? -i4 -dow:i3-dow)+i7
							end=(dow<=i2 ? i2 -dow:i9-dow)+i7
							break
					}
					if(offset!=null){
						date.setTime(Math.round(date.getTime()+offset*dMSDAY))
						def game=args.games.find{ it.year==date.year+1900 && it.month==date.month+i1 && it.day==date.date}
						args=game
						continue
					}
					if(start!=null){
						Date startDate=localDate()
						startDate.setTime(Math.round(date.getTime()+start*dMSDAY))
						Date endDate=localDate()
						endDate.setTime(Math.round(date.getTime()+end*dMSDAY))
						Integer i372=372
						Integer i31=31
						start=(startDate.year+1900)*i372+(startDate.month*i31)+(startDate.date-i1)
						end=(endDate.year+1900)*i372+(endDate.month*i31)+(endDate.date-i1)
						if(parts[iZ].size()>i3){
							def games=((List<Map>)args.games).findAll{ Map it -> (it.year*i372+(it.month-i1)*i31+(it.day-i1)>=start) && (it.year*i372+(it.month-i1)*i31+(it.day-i1)<=end)}
							args=games
							overrideArgs=true
						}else{
							def game=((List<Map>)args.games).find{ Map it -> (it.year*i372+(it.month-i1)*i31+(it.day-i1)>=start) && (it.year*i372+(it.month-i1)*i31+(it.day-i1)<=end)}
							args=game
							continue
						}
					}
				} */

				def idx
				idx=iZ
				String newPart
				newPart=part
				if(part.endsWith(sRB)){
					//array index
					Integer start=part.indexOf(sLB)
					if(start>=iZ){
						idx=part.substring(start+i1,part.size()-i1)
						newPart=part.substring(iZ,start)
						if(idx.isInteger()) idx=idx.toInteger()
						else{
							Map var=getVariable(r9,"$idx".toString())
							idx=sMt(var)!=sERROR ? var.v:idx
						}
					}
					if(!overrideArgs && !!newPart)args=args ? args[newPart] :args
					if(args){
						if(args instanceof List){
							Integer i= idx instanceof Integer ? idx:icast(r9,idx)
							args=((List)args)[i]
						}else args=((Map)args)[idx as String]
					}
					continue
				}
				if(!overrideArgs)args=args ? args[newPart] : args
			}
			return rtnMap(sDYN,"$args".toString())
		}catch(all){
			error "Error retrieving JSON data part $mpart",r9,iN2,all
		}
	}
	rtnMap(sDYN,sBLK)
}

private Map getArgument(Map r9,String name){
	def ttt=gtSysVarVal(r9,sDARGS)
	return getJsonData(r9,ttt,name)
}

private Map getJson(Map r9,String name){ return getJsonData(r9,r9.json,name) }

private Map getPlaces(Map r9,String name){ return getJsonData(r9,r9.settings?.places,name) }

private Map getResponse(Map r9,String name){ return getJsonData(r9,r9.response,name) }

private Map getWeather(Map r9,String name){
	if(r9.weather==null){
		Map t0=wgetWData()
		r9.weather=t0!=null ? t0:[:]
	}
	return getJsonData(r9,r9.weather,name)
}

private Map getNFLDataFeature(String dataFeature){
	Map requestParams=[
			uri: "https://api.webcore.co/nfl/$dataFeature".toString(),
			//query: method==sGET ? data:null,
			query: null,
			timeout:i20
	]
	httpGet(requestParams){ response ->
		if(response.status==200 && response.data){
			try{
				return response.data instanceof Map ? response.data:(LinkedHashMap)new JsonSlurper().parseText((String)response.data)
			}catch(ignored){}
		}
		return null
	}
}

private Map getNFL(Map r9,String name){
	List parts=name.tokenize(sDOT)
	r9.nfl=r9.nfl!=null?r9.nfl: [:]
	if(parts.size()>iZ){
		String dataFeature=(String)(((String)parts[iZ]).tokenize(sLB)[iZ])
		if(r9.nfl[dataFeature]==null){
			r9.nfl[dataFeature]=getNFLDataFeature(dataFeature)
		}
	}
	return getJsonData(r9,r9.nfl,name,'NFL')
}

private Map getIncidents(Map r9,String name){
	return getJsonData(r9,r9.incidents,name)
}

@Field volatile static Map<String,Boolean> initGlobalVFLD=[:]
@Field volatile static Map<String,Map<String,Map>> globalVarsVFLD=[:]

void updateGblCache(String lockT,Map v,Boolean v1){
	String semName=sTGBL
	String wName=sPAppId()
	getTheLock(semName,lockT)
	globalVarsVFLD[wName]=v
	globalVarsVFLD=globalVarsVFLD
	initGlobalVFLD[wName]=v1
	initGlobalVFLD=initGlobalVFLD
	releaseTheLock(semName)
	//if(eric())log.debug lockT
}

void clearGlobalCache(String meth=sNL){ updateGblCache('clearGlobalCache '+meth,null,false) }

private void loadGlobalCache(){
	String wName=sPAppId()
	if(!initGlobalVFLD[wName])
		updateGblCache('loadGlobalCache',wlistAvailableVariables(),true)
}

@CompileStatic
private Map getVariable(Map r9,String name){
	Map<String,String> var=parseVariableName(name)
	String tn,mySt,rt
	tn=sanitizeVariableName(var[sNM])
//	if(eric())log.debug "getVariable ${name} ${tn} ${var}"
	mySt=sBLK
	if(isEric(r9)){
		mySt="getVariable ${tn} ${var} ${name} "
		myDetail r9,mySt,i1
	}
	Map res
	if(tn==sNL){
		res=rtnMapE('Invalid empty variable name')
		if(isEric(r9))myDetail r9,mySt+"result:$res"
		return res
	}
	Map err=rtnMapE("Variable '$tn' not found".toString())
	if(tn.startsWith(sAT)){
		if(tn.startsWith(sAT2)){
			tn=var[sNM] // allow spaces
			String vn=tn.substring(i2)
			//get a variable
			Map hg=wgetGlobalVar(vn)
			if(hg){
				waddInUseGlobalVar(r9,vn)
				String typ; typ=sNL
				def vl; vl=null
				Map ta=fixHeGType(false,sMs(hg,sTYPE),hg.value)
				for(t in ta){
					typ=(String)t.key
					vl=t.value
				}
				res=rtnMap(typ,vl)
			}else res=err
			if(eric())debug "getVariable hub variable (${vn}) returning ${res} to webcore",r9
		}else{
			loadGlobalCache()
			String wName=sMs(r9,spId)
			def tresult=globalVarsVFLD[wName][tn]
			if(!(tresult instanceof Map))res=err
			else{
				waddInUseGlobalVar(r9,tn,false)
				res=(Map)tresult
				String t=sMt(res)
				def v=res.v
				res.v= matchCast(r9,v,t) ? v: cast(r9,v,t)
			}
		}
	}else{
		if(tn.startsWith(sDLR)){
			Integer t0=tn.size()
			if(tn.startsWith(sDARGS+sDOT) && t0>i6){ // '$args.'
				res=getArgument(r9,tn.substring(i6))
			}else if(tn.startsWith(sDARGS+sLB) && t0>i6){ //'$args['
				res=getArgument(r9,tn.substring(i5))
			}else if(tn.startsWith(sDRESP+sDOT) && t0>i10){
				res=getResponse(r9,tn.substring(i10))
			}else if(tn.startsWith(sDRESP+sLB) && t0>i10){
				res=getResponse(r9,tn.substring(i9))
			}else if(tn.startsWith('$weather.') && t0>i9){
				res=getWeather(r9,tn.substring(i9))
			}else if(tn.startsWith(sDJSON+sDOT) && t0>i6){
				res=getJson(r9,tn.substring(i6))
			}else if(tn.startsWith(sDJSON+sLB) && t0>i6){
				res=getJson(r9,tn.substring(i5))
			}else if(tn.startsWith('$incidents.') && t0>11){
				res=getIncidents(r9,tn.substring(11))
			}else if(tn.startsWith('$incidents[') && t0>11){
				res=getIncidents(r9,tn.substring(i10))
			}else if(tn.startsWith('$nfl.') && t0>i5){
				res=getNFL(r9,tn.substring(i5))
			}else if(tn.startsWith('$places.') && t0>i8){
				res=getPlaces(r9,tn.substring(i8))
			}else if(tn.startsWith('$places[') && t0>i8){
				res=getPlaces(r9,tn.substring(i7))
			}else{
				def tres=r9[sSYSVARS][tn]
				if(!(tres instanceof Map))res=err
				else{
					res=(Map)tres
					if(res!=null /* && res.d */)res=rtnMap(sMt(res),gtSysVarVal(r9,tn))
				}
			}
		}else{
//			if(eric())log.debug "getVariable ${r9.localVars}"
			Map tlocV=(Map)((Map)r9.localVars)[tn]
			if(!tlocV)res=err
			else{
				res=rtnMap(sMt(tlocV),tlocV.v)
				if(res.v instanceof List)
				//noinspection GroovyAssignabilityCheck
					res.v=[]+(List)res.v //make a local copy of the list
				if(res.v instanceof Map)
				//noinspection GroovyAssignabilityCheck
					res.v=[:]+(Map)res.v //make a local copy of the map
			}
		}
	}
	rt= res!=null ? sMt(res):sNL
	if(rt.endsWith(sRB)){
		if( (res.v instanceof List || res.v instanceof Map) && var.index!=sNL && var.index!=sBLK){
			if(!var.index.isNumber()){
				//indirect variable addressing
				Map indirectVar=getVariable(r9,var.index)
				String t=sMt(indirectVar)
				def v=indirectVar.v
				if(t!=sERROR){
					def value= t==sDEC ? icast(r9,v):v
					String dataType= t==sDEC ? sINT:t
					var.index=(String)cast(r9,value,sSTR,dataType)
				}
			}
			res.t= rt.replace(sLRB,sBLK)
			res.v=res.v[var.index]
		}
	}else{
		if(res.v instanceof Map){
			res=(Map)evaluateOperand(r9,null,(Map)res.v)
			res=(rt && rt==sMt(res)) ? res:evaluateExpression(r9,res,rt)
		}
	}
	def v; v=res.v
	rt=sMt(res)
	if(rt==sDEC && v instanceof BigDecimal)v=v.toDouble()
	res=rtnMap(rt,v)
	if(isEric(r9))myDetail r9,mySt+"result:$res"
	res
}

@CompileStatic
private Map setVariable(Map r9,String name,value){
	Map<String,String> var=parseVariableName(name)
	String tn
	tn=sanitizeVariableName(var[sNM])
	if(tn==sNL){
		if(isEric(r9))myDetail r9,"setVariable ${tn} value: ${value} INVALID NAME",iN2
		return rtnMapE('Invalid empty variable name')
	}
	if(isEric(r9))myDetail r9,"setVariable ${tn} value: ${value}",iN2
	Map err=rtnMapE('Invalid variable')
	if(tn.startsWith(sAT)){
		if(tn.startsWith(sAT2)){
			tn=var[sNM] // allow spaces
			String vn=tn.substring(i2)
			Map hg=wgetGlobalVar(vn)
			if(hg){ // we know it exists and if it has a value we can know its type (overloaded String, datetime)
				waddInUseGlobalVar(r9,vn)
				String typ,wctyp
				typ=sNL
				wctyp=sNL
				def vl; vl=null
				Map tb=fixHeGType(false,sMs(hg,sTYPE),hg.value)
				for(t in tb){
					wctyp=(String)t.key
				}
				if(wctyp){ // if we know current type
					Map ta=fixHeGType(true,wctyp,value)
					Map res; res=null
					for(t in ta){
						typ=(String)t.key
						vl=t.value
						if(isEric(r9))myDetail r9,"setVariable setting Hub ($vn) to $vl with type ${typ} wc original type ${wctyp}",iN2
						Boolean a; a=false
						try{
							a=wsetGlobalVar(vn,vl)
						}catch(all){
							error 'An error occurred while executing set hub variable',r9,iN2,all
						}
						if(a){
							res=rtnMap(wctyp,value)
							if(isEric(r9))myDetail r9,"setVariable returning ${res} to webcore",iN2
						}else err.v='setGlobal failed'
					}
					if(res)return res
				}else err.v='setGlobal unknown wctyp'
			}
		}else{
			loadGlobalCache()
			String lockTyp='setGlobalvar'
			String semName=sTGBL
			String wName=sMs(r9,spId)
			getTheLock(semName,lockTyp)
			def tvariable=globalVarsVFLD[wName][tn]
			if(tvariable instanceof Map){
				Map variable=(Map)globalVarsVFLD[wName][tn]
				variable.v=cast(r9,value,sMt(variable))
				globalVarsVFLD=globalVarsVFLD
				Map<String,Map> cache=r9.gvCache!=null ? (Map<String,Map>)r9.gvCache:[:]
				cache[tn]=variable
				r9.gvCache=cache
				releaseTheLock(semName)
				waddInUseGlobalVar(r9,tn,false)
				return variable
			}
			releaseTheLock(semName)
		}
	}else{
// global vars are removed by setting them to null via webcore dashboard
// local vars are removed by 'clear all data' via HE console
//		if(eric())log.debug "setVariable ${r9.localVars}"
		Map tvariable=(Map)((Map)r9.localVars)[tn]
//		if(eric())log.debug "setVariable tvariable ${tvariable}"
		if(tvariable){
			Map variable=(Map)((Map)r9.localVars)[tn]
			String t=sMt(variable)
//			if(eric())log.debug "setVariable found variable ${variable}"
			if(t.endsWith(sRB)){
				//dealing with a list
				variable.v= variable.v instanceof Map || variable.v instanceof List ? variable.v:[:]
				if(var.index=='*CLEAR') variable.v=[:] //((Map)variable.v).clear()
				else{
					if(var.index!=null){
						if(!var.index.isNumber()){
							//indirect variable addressing
							Map indirectVar=getVariable(r9,var.index)
							String indt=sMt(indirectVar)
							if(indt!=sERROR){
								def a=indirectVar.v
								var.index=(a instanceof String)? (String)a:(String)cast(r9,a,sSTR,indt)
							}
						}
						String at=t.replace(sLRB,sBLK)
						variable.v[var.index]= matchCast(r9,value,at)?value:cast(r9,value,at)
					}else{
						//list of numbers, spread into multiple prms
						def nvalue
						nvalue=value
						if(nvalue instanceof String){
							String s= (String)nvalue
							if(s.startsWith(sLB) && s.endsWith(sRB)){
								try{
									List l= (List)new JsonSlurper().parseText(s)
									nvalue= l
								}catch(ignored){}
							}
						}
						if(nvalue instanceof List){
							if(isEric(r9))myDetail r9,"setVariable list found ${variable} value: ${nvalue}",iN2
							variable.v= nvalue
						} else return err
					}
				}
			}else{
				def v=(value instanceof GString)? "$value".toString():value
				if(!variable.a) // cannot change constants
					variable.v=matchCast(r9,v,t) ? v:cast(r9,v,t)
			}
			if(!variable.f){ // don't save fixed;  (includes constants)
				Map vars
				Map t0=getCachedMaps(sSTVAR)
				if(t0!=null)vars=(Map)t0[sVARS]
				else vars=isPep(r9) ? (Map)gtAS(sVARS):(Map)gtSt(sVARS)

				((Map)r9.localVars)[tn]=variable
				vars[tn]=variable.v
				mb()

				if(isPep(r9))assignAS(sVARS,vars)
				else assignSt(sVARS,vars)

				if(t0!=null)updateCacheFld(r9,sVARS,vars,sV,false)
			}
			return variable
		}
	}
	return err
}

@CompileStatic
private static Integer matchCastI(Map r9,v){ Integer res=matchCast(r9,v,sINT) ? (Integer)v:icast(r9,v); return res }
@CompileStatic
private static Long matchCastL(Map r9,v){ Long res=matchCast(r9,v,sLONG) ? (Long)v:lcast(r9,v); return res }

@Field static List<String> mL=[]
@Field static List<String> mL1=[]

@CompileStatic
private static Boolean matchCastD(Map r9,v,String t){
	if(!LT1) LT1=fill_TIM()
	Boolean match= v!=null && t in LT1 && (v instanceof Long)
	return match
}

@CompileStatic
private static Boolean matchCast(Map r9,v,String t){
	if(!mL){
		if(!LS) LS=fill_LS()
		mL1=[sDYN]+LS
		mL=mL1+[sLONG,sDEC,sINT,sBOOLN,sDEV]
	}
	Boolean match= v!=null && t in mL && (
		(v instanceof String && t in mL1)||
		(t==sDEC && v instanceof Double) ||
		(t==sLONG && v instanceof Long)||
		(t==sINT && v instanceof Integer)||
		(t==sBOOLN && v instanceof Boolean)||
		(t==sDEV && v instanceof List)
	)
	return match
}

Map setLocalVariable(String name,value){ // called by parent (IDE) to set a variable
	String tn=sanitizeVariableName(name)
	if(tn==sNL || tn.startsWith(sAT))return [:]
	Map vars; vars=(Map)gtAS(sVARS)
	vars=vars!=null ? vars:[:]
	vars[tn]=value
	assignAS(sVARS,vars)
	clearMyCache('setLocalVariable')
	return vars
}

/** EXPRESSION FUNCTIONS							**/

Map proxyEvaluateExpression(LinkedHashMap mr9,Map expression,String dataType=sNL){
	LinkedHashMap r9; r9=getRunTimeData(mr9)
	resetRandomValues(r9)
	r9[sEVENT]=[:]
	r9[sCUREVT]=[:]
	try{
		Map result; result=evaluateExpression(r9,expression,dataType)
		if(sMt(result)==sDEV && sMa(result)!=sNL){
			def device=getDevice(r9,(String)((List)result.v)[iZ])
			Map attr=devAttrT(r9,sMa(result),device)
			result=evaluateExpression(r9,result,attr!=null && attr.t!=null ? sMt(attr):sSTR)
		}
		r9=null
		return result
	}catch(all){
		error 'An error occurred while executing the expression',r9,iN2,all
	}
	return rtnMapE('expression error')
}

@CompileStatic
private static Map simplifyExpression(Map express){
	Map expression; expression=express
	while (sMt(expression)==sEXPR && expression.i && ((List)expression.i).size()==i1) expression=(Map)((List)expression.i)[iZ]
	return expression
}

//@Field static List<String> LT0=[]
@Field static List<String> LS=[]
@Field static List<String> L1opt=[]
@Field static List<String> lPLSMIN=[]
@Field static List<String> LT1=[]
@Field static List<String> LN=[]
@Field static List<String> LD=[]
@Field static List<String> LT2=[]
@Field static List<String> tL2=[]
@Field static List<String> tL4=[]
@Field static List<String> tL6=[]
@Field static List<String> tL7=[]
@Field static List<String> tL8=[]
@Field static List<String> tL9=[]
@Field static List<String> tL10=[]
@Field static List<String> tL11=[]
@Field static List<String> tL12=[]
@Field static List<String> tL13=[]
@Field static List<String> tL14=[]
@Field static List<String> pn1=[]
@Field static List<String> pn2=[]
@Field static List<String> pn3=[]
@Field static List<String> pn4=[]

private static List<String> fill_TIM(){ return [sDTIME,sTIME,sDATE] }
private static List<String> fill_LS(){ return [sSTR,sENUM,sERROR,sPHONE,sURI,sTEXT] }

@CompileStatic
private Double dblEvalExpr(Map r9,Map express,String rtndataType=sDEC){
	return (evaluateExpression(r9,express,rtndataType)[sV]) as Double
}

@CompileStatic
private Long longEvalExpr(Map r9,Map express,String rtndataType=sNL){
	if(!LT1) LT1=fill_TIM()
	String rtn= rtndataType!=sSTR ? rtndataType : sNL
	return (evaluateExpression(r9,express,rtndataType)[sV]) as Long
}

@CompileStatic
private Integer intEvalExpr(Map r9,Map express,String rtndataType=sINT){
	return (evaluateExpression(r9,express,rtndataType)[sV]) as Integer
}

@CompileStatic
private Boolean boolEvalExpr(Map r9,Map express,String rtndataType=sBOOLN){
	return !!(evaluateExpression(r9,express,rtndataType)[sV])
}

@CompileStatic
private String strEvalExpr(Map r9,Map express,String rtndataType=sSTR){
	return (String)evaluateExpression(r9,express,rtndataType)[sV]
}

@CompileStatic
private Map evaluateExpression(Map r9,Map express,String rtndataType=sNL){
	//if dealing with an expression that has multiple items let's evaluate each item one by one
	if(!L1opt){
		//LT0=[sSTR,sTEXT]
		//LS=[sSTR,sENUM]
		L1opt=[sPLUS,sMINUS,sPWR,sAMP,sBOR,sBXOR,sBNOT,sBNAND,sBNOR,sBNXOR,sLTH,sGTH,sLTHE,sGTHE,sEQ,sNEQ,sNEQA,sSBL,sSBR,sNEG,sDNEG,sQM]
		mb()
		if(!LS) LS=fill_LS()
		lPLSMIN=[sPLUS,sMINUS]
		if(!LT1) LT1=fill_TIM()
		LN=[sNUMBER,sINT,sLONG] // number is ambiguous for devices
		LD=[sDEC,sFLOAT]
		LT2=[sDEV,sVARIABLE]
		tL2=[sNEG,sDNEG,sBNOT]
		tL4=[sMULP,sDIV,sMOD1,sMOD]
		tL6=[sSBL,sSBR]
		tL7=[sGTH,sLTH,sGTHE,sLTHE]
		tL8=[sEQ,sNEQ,sNEQA]
		tL9=[sAMP,sBNAND]
		tL10=[sBXOR,sBNXOR]
		tL11=[sBOR,sBNOR]
		tL12=[sLAND,sLNAND]
		tL13=[sLXOR,sLNXOR]
		tL14=[sLOR,sLNOR]
		pn1=[sMULP,sDIV,sPWR,sMINUS] // number fixes
		pn2=[sMOD1,sMOD,sAMP,sBOR,sBXOR,sBNAND,sBNOR,sBNXOR,sSBL,sSBR] // int fixes
		pn3=[sLAND,sLOR,sLXOR,sLNAND,sLNOR,sLNXOR,sNEG,sDNEG] // bool fixes
		pn4=[sEQ,sNEQ,sLTH,sGTH,sLTHE,sGTHE,sNEQA]
		mb()
	}
	if(!express)return rtnMapE('Null expression')
	Long time=wnow()
	Map expression=simplifyExpression(express)
	String mySt
	mySt=sNL
	if(isEric(r9)){
		mySt="evaluateExpression $expression rtndataType: $rtndataType".toString()
		myDetail r9,mySt,i1
	}
	Map result
	result=expression
	String exprType=sMt(expression)
	def exprV=expression.v
	switch(exprType){
		case sINT:
		case sLONG:
		case sDEC:
			result=rtnMap(exprType,exprV)
			break
		case sTIME:
		case sDTIME:
			String st0="$exprV".toString()
			try{
				if(st0.isNumber()){
					Double aa= st0 as Double
					Long l1=aa.toLong()
					if( (l1>=lMSDAY && exprType==sDTIME) || (l1<lMSDAY && exprType==sTIME) ){
						result=rtnMap(exprType,l1)
						break
					}
				}
			}catch(ignored){}
		case sINT32:
		case sINT64:
		case sDATE:
			result=rtnMap(exprType,cast(r9,exprV,exprType,exprType))
			break
		case sBOOL:
		case sBOOLN:
			result=rtnMapB(bcast(r9,exprV))
			break
		case sDYN:
			if(rtndataType!=sSTR)break
		case sSTR:
		case sENUM:
		case sERROR:
		case sPHONE:
		case sURI:
		case sTEXT:
			result=rtnMapS(scast(r9,exprV))
			break
		case sNUMBER:
		case sFLOAT:
		case sDBL:
			result=rtnMapD(dcast(r9,exprV))
			break
		case sDURATION:
			String t0=sMvt(expression)
			if(t0==sNL && exprV instanceof Long) result=rtnMap(sLONG,(Long)exprV)
			else result=rtnMap(sLONG,(Long)cast(r9,exprV,t0!=sNL ? t0:sLONG,sINT))
			break
		case sVARIABLE:
			//get variable {n:name,t:type,v:value}
			result=getVariable(r9,sMs(expression,sX)+(sMs(expression,sXI)!=sNL ? sLB+sMs(expression,sXI)+sRB:sBLK))
			break
		case sDEV:
			if(exprV instanceof List){
				//already parsed
				result=expression
			}else{
				List deviceIds; deviceIds=(expression.id instanceof List)? (List)expression.id:(expression.id ? [expression.id]:[])
				Boolean err; err=false
				if(deviceIds.size()==iZ){
					//get variable {n:name,t:type,v:value}
					Map var=getVariable(r9,sMs(expression,sX))
					if(sMt(var)!=sERROR){
						if(sMt(var)==sDEV) //noinspection GroovyAssignabilityCheck
							deviceIds=(List)var.v
						else{
							def device=var.v ? getDevice(r9,sMs(var,sV)):null
							if(device!=null)deviceIds=[hashD(r9,device)]
						}
					}else{
						err=true
						result=var // Invalid variable
					}
				}
				if(!err) result=rtnMap(sDEV,deviceIds)+ ([(sA):sMa(expression)] as LinkedHashMap)
			}
			break
		case sOPERAND:
			result=rtnMapS(scast(r9,exprV))
			break
		case sFUNC:
			String fn=sMs(expression,sN)
			//in a function, we look for device parameter,they may be lists- we need to reformat all parameter to send them to the function
			String myStr
			myStr=sNL
			try{
				List prms=[]
				List<Map> t0=(List<Map>)expression.i
				if(t0 && t0.size()!=iZ){
					Map prm
					Boolean a
					for(Map i in t0){
						prm=simplifyExpression(i)
						if(sMt(prm) in LT2){ // sDEV or sVARIABLE
							prm=evaluateExpression(r9,prm)
							//if multiple devices, or list of numbers, spread into multiple prms
							if(sMt(prm) in [sSTR,sDYN] && prm.v instanceof String){
								String s= prm.v
								if(s.startsWith(sLB) && s.endsWith(sRB)){
									try{
										List l= (List)new JsonSlurper().parseText(s)
										prm.v= l
									}catch(ignored){}
								}
							}
							Integer sz=prm.v instanceof List ? ((List)prm.v).size():i1
							switch(sz){
								case iZ: break
								case i1: a=prms.push(prm); break
								default:
									String t= sMt(prm).replace(sLRB,sBLK)
									String s= sMa(prm)
									for(v in (List)prm.v){
										if(s || v instanceof String)
											a=prms.push( rtnMap(t,[v]) + ((s ? [(sA):s] :[:]) as LinkedHashMap) )
										else
											a=prms.push( rtnMap(t,v) )
									}
							}
						}else a=prms.push(prm)
					}
				}
				if(isEric(r9)){
					myStr='calling function '+fn+" $prms"
					myDetail r9,myStr,i1
				}
				result=callFunc(r9,fn,prms)
			}catch(all){
				error "Error executing $fn: ",r9,iN2,all
				result=rtnMapE("${all}".toString())
			}
			if(isEric(r9))myDetail r9,myStr+sSPC+"${result}".toString()
			break
		case sEXPR:
			//if we have a single item, we simply traverse the expression
			List<Map> items; items=[]
			Integer operand,lastOperand
			operand=iN1
			lastOperand=iN1
			Boolean a
			if(expression.i){
				for(Map item in (List<Map>)expression.i){
					if(sMt(item)==sOPER){
						String ito=sMs(item,sO)
						Map mito=[(sO):ito] as LinkedHashMap
						if(operand<iZ){
							if(ito in L1opt){
								a=items.push(rtnMapI(iZ)+mito)
							}else switch(ito){
								case sCLN:
									if(lastOperand>=iZ){
										//groovy-style support for object ?: value
										a=items.push(items[lastOperand]+mito)
									}else a=items.push(rtnMapI(iZ)+mito)
									break
								case sMULP:
								case sDIV:
									a=items.push(rtnMapI(i1)+mito)
									break
								case sLAND:
								case sLNAND:
									a=items.push(rtnMapB(true)+mito)
									break
								case sLOR:
								case sLNOR:
								case sLXOR:
								case sLNXOR:
									a=items.push(rtnMapB(false)+mito)
									break
							}
						}else{
							items[operand].o=ito
							operand=iN1
						}
					}else{
						//Map tmap= [:]+evaluateExpression(r9,item,rtndataType)
						Map tmap= [:]+evaluateExpression(r9,item)
						a=items.push(tmap)
						operand=items.size()-i1
						lastOperand=operand
					}
				}
			}
			//clean up operators, ensure there's one for each
			Integer idx,itmSz
			idx=iZ
			itmSz=items.size()-i1
			if(items){
				for(Map item in items){
					if(!sMs(item,sO)){
						switch(sMt(item)){
							case sINT:
							case sFLOAT:
							case sDBL:
							case sDEC:
							case sNUMBER:
								String nTyp; nTyp=sSTR
								if(idx<itmSz)nTyp=sMt(items[idx+i1])
								item.o= nTyp in LS ? sPLUS:sMULP // Strings
								break
							default:
								item.o=sPLUS
								break
						}
					}
					idx++
				}
			}
			//do the job
			idx=iZ
			itmSz=items.size()
			def aa
			while (itmSz>i1){
				//ternary
				if(itmSz==i3 && sMs(items[iZ],sO)==sQM && sMs(items[i1],sO)==sCLN){
					//we have a ternary operator
					if(boolEvalExpr(r9,(Map)items[iZ])) items=[items[i1]]
					else items=[items[i2]]
					items[iZ].o=sNL
					break
				}
				//order of operations
				idx=iZ
				//#2	!   !!   ~   -	Logical negation, logical double-negation, bitwise NOT, and numeric negation unary operators
				for(Map item in items){
					String t0=sMs(item,sO)
					if(t0 in tL2 || (sMt(item)==sNL && t0==sMINUS))break
					idx++
				}
				//#3	**	Exponent operator
				if(idx>=itmSz){
					//we then look for power **
					idx=iZ
					for(Map item in items){
						if(sMs(item,sO) in sPWR)break
						idx++
					}
				}
				//#4	*   /   \   % MOD	Multiplication, division, modulo
				if(idx>=itmSz){
					//we then look for * or /
					idx=iZ
					for(Map item in items){
						if(sMs(item,sO) in tL4)break
						idx++
					}
				}
				//#5	+   -	Addition and subtraction
				if(idx>=itmSz){
					idx=iZ
					for(Map item in items){
						if(sMs(item,sO) in lPLSMIN)break
						idx++
					}
				}
				//#6	<<   >>	Shift left and shift right operators
				if(idx>=itmSz){
					idx=iZ
					for(Map item in items){
						if(sMs(item,sO) in tL6)break
						idx++
					}
				}
				//#7	<  <= >  >=	Comparisons: less than, less than or equal to, greater than, greater than or equal to
				if(idx>=itmSz){
					idx=iZ
					for(Map item in items){
						if(sMs(item,sO) in tL7)break
						idx++
					}
				}
				//#8	==   !=	Comparisons: equal and not equal
				if(idx>=itmSz){
					idx=iZ
					for(Map item in items){
						if(sMs(item,sO) in tL8)break
						idx++
					}
				}
				//#9	&	Bitwise AND
				if(idx>=itmSz){
					idx=iZ
					for(Map item in items){
						if(sMs(item,sO) in tL9)break
						idx++
					}
				}
				//#10	^	Bitwise exclusive OR (XOR)
				if(idx>=itmSz){
					idx=iZ
					for(Map item in items){
						if(sMs(item,sO) in tL10)break
						idx++
					}
				}
				//#11	|	Bitwise inclusive (normal)OR
				if(idx>=itmSz){
					idx=iZ
					for(Map item in items){
						if(sMs(item,sO) in tL11)break
						idx++
					}
				}
				//#12	&&	Logical AND
				if(idx>=itmSz){
					idx=iZ
					for(Map item in items){
						if(sMs(item,sO) in tL12)break
						idx++
					}
				}
				//#13	^^	Logical XOR
				if(idx>=itmSz){
					idx=iZ
					for(Map item in items){
						if(sMs(item,sO) in tL13)break
						idx++
					}
				}
				//#14	||	Logical OR
				if(idx>=itmSz){
					idx=iZ
					for(Map item in items){
						if(sMs(item,sO) in tL14)break
						idx++
					}
				}
				//if none selected get the first one
				if(idx>=itmSz-i1)idx=iZ

				String o=sMs(items[idx],sO)

				String a1,a2,t1,t2,t
				def v1,v2,v
				a1=sMa(items[idx])
				t1=sMt(items[idx])
				v1=items[idx].v

				Integer idxPlus=idx+i1
				a2=sMa(items[idxPlus])
				t2=sMt(items[idxPlus])
				v2=items[idxPlus].v

				v=null
				t=t1

				//fix-ups
				if(t1==sDEV && a1!=sNL && a1.length()>iZ){
					List lv1=(v1 instanceof List)? (List)v1:[v1]
					String tm=(String)lv1[iZ]
					def device= tm?getDevice(r9,tm):null
					Map attr=devAttrT(r9,a1,device)
					t1=sMt(attr)
				}
				if(t2==sDEV && a2!=sNL && a2.length()>iZ){
					List lv2=(v2 instanceof List)? (List)v2:[v2]
					String tm=(String)lv2[iZ]
					def device= tm?getDevice(r9,tm):null
					Map attr=devAttrT(r9,a2,device)
					t2=sMt(attr)
				}
				if(t1==sDEV && t2==sDEV && o in lPLSMIN){
					List lv1=(v1 instanceof List)? (List)v1:[v1]
					List lv2=(v2 instanceof List)? (List)v2:[v2]
					v= o==sPLUS ? lv1+lv2:lv1-lv2
					//set the results
					items[idxPlus].t=sDEV
					items[idxPlus].v=v
				}else{
					Boolean t1d,t2d,t1i,t2i,t1f,t2f,t1n,t2n
					t1d= t1 in LT1
					t2d= t2 in LT1
					t1i= t1 in LN //[sNUMBER,sINT,sLONG]
					t2i= t2 in LN
					t1f= t1 in LD //[sDEC,sFLOAT]
					t2f= t2 in LD
					t1n=t1i||t1f
					t2n=t2i||t2f
					//warn "Precalc ($t1) $v1 $o ($t2) $v2 >>> t1d=$t1d, t2d=$t2d, t1n=$t1n, t2n=$t2n",r9
					if(o in lPLSMIN && (t1d || t2d) && (t1d || t1n) && (t2d || t2n)){
						//if dealing with date +/- date/numeric then
						t=sLONG
						if(t1n){
							t= o==sPLUS && t2 in [sDATE,sDTIME] ? sDTIME:t // dtime -> number+dtime number+date
						}else if(t2n){
							t= o==sPLUS && t1 in [sDATE,sDTIME] ? sDTIME:t // dtime -> dtime+number date+number
						}else{
							t1d= t1==sDATE
							Boolean t2t= t2==sTIME
							Boolean t1dt= t1==sDTIME
							t= (t1d||t1dt) && t2t ? sDTIME:t // dtime -> date+/-time dtime+/-time
						}
					}else{
						if(o in lPLSMIN){
							//devices and others play nice
							if(t1==sDEV){
								t=t2
								t1=t2
							}else if(t2==sDEV){
								t=t1
								t2=t1
							}
						}
						t1d= t1 in LT1
						t2d= t2 in LT1
						t1i= t1 in LN
						t2i= t2 in LN
						t1f= t1 in LD
						t2f= t2 in LD
						t1n=t1i||t1f
						t2n=t2i||t2f
						//warn "Precalc ($t1) $v1 $o ($t2) $v2 >>> t1d=$t1d, t2d=$t2d, t1n=$t1n, t2n=$t2n",r9

						// *,/ ** require decimals
						if(o in pn1){ //[sMULP,sDIV,sPWR,sMINUS] number fixes
							t= t1i&&t2i && rtndataType!=sDEC ? typIL(t1,t2):sDEC
							t1=t
							t2=t
						}else if(o in pn2){ //[sMOD1,sMOD,sAMP,sBOR,sBXOR,sBNAND,sBNOR,sBNXOR,sSBL,sSBR] int fixes
							t=typIL(t1,t2)
							t1=t
							t2=t
						}else if(o in pn3){ //[sLAND,sLOR,sLXOR,sLNAND,sLNOR,sLNXOR,sNEG,sDNEG] bool fixes
							t=sBOOLN
							t1=t
							t2=t
						}else if(o==sPLUS && (t1 in LS || t2 in LS)){ // string fixes
							t=sSTR
							t1=t
							t2=t
						}
						t1i=(t1 in LN)
						t2i=(t2 in LN)
						t1f=(t1 in LD)
						t2f=(t2 in LD)
						t1n=t1i || t1f
						t2n=t2i || t2f
						//integer with decimal gives decimal
						if(t1n&&t2n){
							t= t1i&&t2i ? typIL(t1,t2):sDEC
							t1=t
							t2=t
						}
						if(o in pn4){ //[sEQ,sNEQ,sLTH,sGTH,sLTHE,sGTHE,sNEQA]
							if(t1==sDEV)t1=sSTR
							if(t2==sDEV)t2=sSTR
							t1=t1==sSTR ? t2:t1
							t2=t2==sSTR ? t1:t2
							t=sBOOLN
						}
					}

					//v1=evaluateExpression(r9,(Map)items[idx],t1).v
					//v1=((Map)items[idx]).v   // already done
					String tt1
					tt1=sMt(items[idx])
					if(!(v1!=null && tt1!=sNL && (t1==sNL || tt1==t1) && matchCast(r9,v1,tt1))) v1=evaluateExpression(r9,(Map)items[idx],t1).v
					v1=v1==sSNULL ? null:v1
					//v2=evaluateExpression(r9,(Map)items[idxPlus],t2).v
					//v2=((Map)items[idxPlus]).v // already done
					tt1=sMt(items[idxPlus])
					if(!(v2!=null && tt1!=sNL && (t2==sNL || tt1==t2) && matchCast(r9,v2,tt1))) v2=evaluateExpression(r9,(Map)items[idxPlus],t2).v
					v2=v2==sSNULL ? null:v2

					v= doExprMath(r9,o,t,v1,v2)
					if(isDbg(r9))debug "Calculating ($t1)$v1 $o ($t2)$v2 >> ($t)$v",r9

					//set the results
					items[idxPlus].t=t
					v=(v instanceof GString)? "$v".toString():v
					items[idxPlus].v=matchCast(r9,v,t) ? v:cast(r9,v,t)
				}

				aa=items.remove(idx)

				itmSz=items.size()
			}
			result=items[iZ] ? (sMt(items[iZ])==sDEV ? (Map)items[iZ]:evaluateExpression(r9,(Map)items[iZ])):rtnMap(sDYN,null)
			break
	}

	if(rtndataType){
		String ra=sMa(result)
		def ri=result.i
		//when dealing with devices they need to be "converted" unless the request is to return devices
		if(rtndataType!=sDEV && sMt(result)==sDEV){
			List atL= (result.v instanceof List)?(List)result.v:[result.v]
			switch(atL.size()){
				case iZ: result=rtnMapE('Empty device list'); break
				case i1: result=getDeviceAttribute(r9,(String)atL[iZ],ra,ri); break
				default: result=rtnMapS(buildDeviceAttributeList(r9,atL,ra)); break
			}
		}

		String t0=sMt(result)
		if(t0!=sERROR){
			def t1
			t1=result.v
			Boolean match
			match=(rtndataType in LS && t0 in LS && t1 instanceof String)
			if(!match){
				if(!t0 || rtndataType==t0) match=matchCast(r9,t1,rtndataType)
				if(!match && t0 && rtndataType==t0) match=matchCastD(r9,t1,rtndataType)
				if(!match)t1=cast(r9,t1,rtndataType,t0)
			}
			result=rtnMap(rtndataType,t1)+((ra ? [(sA):ra]:[:]) as Map)+((ri!=null ? [(sI):ri]:[:]) as Map)
		}
	}
	result.d=elapseT(time)
	if(isEric(r9))myDetail r9,mySt+" result:$result".toString()
	return result
}

private doExprMath(Map r9,String o,String t,v1,v2){
	def v
	v=null
	switch(o){
		case sQM:
		case sCLN:
			error "Invalid ternary operator. Ternary operator's syntax is (condition ? trueValue:falseValue ). Please check your syntax.",r9
			v=sBLK
			break
		case sMINUS:
			v=v1 - v2
			break
		case sMULP:
			v=v1 * v2
			break
		case sDIV:
			v=(v2!=dZ ? v1/v2:dZ)
			break
		case sMOD1:
			v=(Integer)Math.floor(v2!=iZ ? v1/v2:iZ)
			break
		case sMOD:
			v=(Integer)(v2!=iZ ? v1%v2:iZ)
			break
		case sPWR:
			v=v1 ** v2
			break
		case sAMP:
			v=v1 & v2
			break
		case sBOR:
			v=v1 | v2
			break
		case sBXOR:
			v=v1 ^ v2
			break
		case sBNAND:
			v=~(v1 & v2)
			break
		case sBNOR:
			v=~(v1 | v2)
			break
		case sBNXOR:
			v=~(v1 ^ v2)
			break
		case sBNOT:
			v=~v2
			break
		case sSBL:
			v=v1 << v2
			break
		case sSBR:
			v=v1 >> v2
			break
		case sLAND:
			v=!!v1 && !!v2
			break
		case sLOR:
			v=!!v1 || !!v2
			break
		case sLXOR:
			v=!v1!=!v2
			break
		case sLNAND:
			v=!(!!v1 && !!v2)
			break
		case sLNOR:
			v=!(!!v1 || !!v2)
			break
		case sLNXOR:
			v=!(!v1!=!v2)
			break
		case sEQ:
			v=v1==v2
			break
		case sNEQ:
		case sNEQA:
			v=v1!=v2
			break
		case sLTH:
			v=v1<v2
			break
		case sGTH:
			v=v1>v2
			break
		case sLTHE:
			v=v1<=v2
			break
		case sGTHE:
			v=v1>=v2
			break
		case sNEG:
			v=!v2
			break
		case sDNEG:
			v=!!v2
			break
		case sPLUS:
		default:
			v=t==sSTR ? "$v1$v2".toString():v1+v2
			break
	}
	return v
}

@CompileStatic
private static String typIL(String t1,String t2){ return t1==sLONG || t2==sLONG ? sLONG:sINT }

@CompileStatic
private static String buildList(List list,String suffix=sAND){
	if(!list)return sBLK
	Integer cnt,t0,t1
	cnt=i1
	String res; res=sBLK
	t0=list.size()
	t1=t0-i1
	String a=sCOMMA+sSPC
	for(item in list){
		res+=item.toString()+(cnt<t0 ? (cnt==t1 ? sSPC+suffix+sSPC:a):sBLK)
		cnt++
	}
	return res
}

@CompileStatic
private String buildDeviceList(Map r9,devices,String suffix=sAND){
	if(!devices)return sBLK
	List nlist=(devices instanceof List)? devices:[devices]
	List list=[]
	Boolean a
	def dev
	for(String device in nlist){
		dev=getDevice(r9,device)
		if(dev!=null)a=list.push(dev)
	}
	return buildList(list,suffix)
}

@CompileStatic
private String buildDeviceAttributeList(Map r9,List<String> devices,String attr,String suffix=sAND){
	if(!devices)return sBLK
	List list=[]
	Boolean a
	Map value
	def v
	String t0
	for(String device in devices){
		value=getDeviceAttribute(r9,device,attr)
		t0=sMt(value)
		v= t0!=sERROR ? value.v : sBLK
		a=list.push(v)
	}
	return buildList(list,suffix)
}

@CompileStatic
private static Boolean badParams(Map r9,List prms,Integer minParams){ return (prms==null || prms.size()<minParams) }

@CompileStatic
private static Map rtnMap(String t,v){ return [(sT):t,(sV):v] }
@CompileStatic
private static Map rtnMapS(String v){ return [(sT):sSTR,(sV):v] as LinkedHashMap }
@CompileStatic
private static Map rtnMapI(Integer v){ return [(sT):sINT,(sV):v] as LinkedHashMap }
@CompileStatic
private static Map rtnMapD(Double v){ return [(sT):sDEC,(sV):v] as LinkedHashMap }
@CompileStatic
private static Map rtnMapB(Boolean v){ return [(sT):sBOOLN,(sV):v] as LinkedHashMap }
@CompileStatic
private static Map rtnMapE(String v){ return [(sT):sERROR,(sV):v] as LinkedHashMap }
@CompileStatic
private static Map rtnErr(String msg){ return rtnMapE(sEXPECTING+msg)}

/** Returns duration,v, vt  */
@CompileStatic
private static Map rtnMap1(v,String vt){ return [(sT):sDURATION,(sV):v,(sVT):vt] }

/** dewPoint returns the calculated dew point temperature
 * Usage: dewPoint(temperature,relativeHumidity[, scale]) */
private Map func_dewpoint(Map r9,List<Map> prms){
	if(badParams(r9,prms,i2))return rtnErr('dewPoint(temperature,relativeHumidity[, scale])')
	Double t,rh,result
	t=dblEvalExpr(r9,prms[iZ])
	rh=dblEvalExpr(r9,prms[i1])
	//if no temperature scale is provided we assume the location's temperature scale
	Boolean fahrenheit= (prms.size()>i2 ? strEvalExpr(r9,prms[i2]) :gtLtScale()).toUpperCase()=='F'
	if(fahrenheit) t=(t-32.0D)*5.0D/9.0D
	//convert rh to percentage
	if((rh>dZ) && (rh<d1)) rh=rh*d100
	Double b=(Math.log(rh/d100)+((17.27D*t)/(237.3D+t)))/17.27D
	result=(237.3D*b)/(d1-b)
	if(fahrenheit) result=result*9.0D/5.0D+32.0D
	rtnMapD(result)
}

/** celsius converts temperature from Fahrenheit to Celsius
 * Usage: celsius(temperature)							*/
private Map func_celsius(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('celsius(temperature)')
	Double t=dblEvalExpr(r9,prms[iZ])
	rtnMapD((Double)((t-32.0D)*5.0D/9.0D))
}

/** fahrenheit converts temperature from Celsius to Fahrenheit			**/
/** Usage: fahrenheit(temperature)						**/
private Map func_fahrenheit(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('fahrenheit(temperature)')
	Double t=dblEvalExpr(r9,prms[iZ])
	rtnMapD((Double)(t*9.0D/5.0D+32.0D))
}

/** fahrenheit converts temperature between Celsius and Fahrenheit if the	**/
/** units differ from location.temperatureScale					**/
/** Usage: convertTemperatureIfNeeded(celsiusTemperature,'C')			**/
private Map func_converttemperatureifneeded(Map r9,List<Map> prms){
	if(badParams(r9,prms,i2))return rtnErr('convertTemperatureIfNeeded(temperature,unit)')
	String u=strEvalExpr(r9,prms[i1]).toUpperCase()
	Map a=prms[iZ]
	switch(gtLtScale()){
		case u: // matches return value
			Double t=dblEvalExpr(r9,a)
			return rtnMapD(t)
		case 'F': return func_celsius(r9,[a])
		case 'C': return func_fahrenheit(r9,[a])
	}
	return [:]
}

/** integer converts a decimal to integer value			**/
/** Usage: integer(decimal or string)				**/
private Map func_integer(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('integer(decimal or string)')
	rtnMapI(intEvalExpr(r9,prms[iZ]))
}
private Map func_int(Map r9,List<Map> prms){ return func_integer(r9,prms)}

/** decimal/float converts an integer value to it's decimal value		**/
/** Usage: decimal(integer or string)						**/
private Map func_decimal(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('decimal(integer or string)')
	rtnMapD(dblEvalExpr(r9,prms[iZ]))
}
private Map func_float(Map r9,List<Map> prms){ return func_decimal(r9,prms)}
private Map func_number(Map r9,List<Map> prms){ return func_decimal(r9,prms)}

/** string converts an value to it's string value				**/
/** Usage: string(anything)							**/
private Map func_string(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('string(anything)')
	String res; res=sBLK
	for(Map prm in prms) res+=strEvalExpr(r9,prm)
	rtnMapS(res)
}
private Map func_concat(Map r9,List<Map> prms){ return func_string(r9,prms)}
private Map func_text(Map r9,List<Map> prms){ return func_string(r9,prms)}

/** Boolean converts a value to it's Boolean value				**/
/** Usage: boolean(anything)							**/
private Map func_boolean(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('boolean(anything)')
	rtnMapB(boolEvalExpr(r9,prms[iZ]))
}
private Map func_bool(Map r9,List<Map> prms){ return func_boolean(r9,prms)}

/** sqr converts a decimal to square decimal value			**/
/** Usage: sqr(integer or decimal or string)				**/
private Map func_sqr(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('sqr(integer or decimal or string)')
	rtnMapD(dblEvalExpr(r9,prms[iZ])**i2)
}

/** sqrt converts a decimal to square root decimal value		**/
/** Usage: sqrt(integer or decimal or string)				**/
private Map func_sqrt(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('sqrt(integer or decimal or string)')
	rtnMapD(Math.sqrt(dblEvalExpr(r9,prms[iZ])))
}

/** power converts a decimal to power decimal value			**/
/** Usage: power(integer or decimal or string, power)			**/
private Map func_power(Map r9,List<Map> prms){
	if(badParams(r9,prms,i2))return rtnErr('sqrt(integer or decimal or string, power)')
	rtnMapD(dblEvalExpr(r9,prms[iZ]) ** dblEvalExpr(r9,prms[i1]))
}

/** round converts a decimal to rounded value			**/
/** Usage: round(decimal or string[, precision])		**/
private Map func_round(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('round(decimal or string[, precision])')
	Integer precision=prms.size()>i1 ? intEvalExpr(r9,prms[i1]):iZ
	rtnMapD(Math.round(dblEvalExpr(r9,prms[iZ]) * (i10 ** precision))/(i10 ** precision))
}

/** floor converts a decimal to closest lower integer value		**/
/** Usage: floor(decimal or string)					**/
private Map func_floor(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('floor(decimal or string)')
	rtnMapI(icast(r9,Math.floor(dblEvalExpr(r9,prms[iZ]))))
}

/** ceiling converts a decimal to closest higher integer value	**/
/** Usage: ceiling(decimal or string)						**/
private Map func_ceiling(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('ceiling(decimal or string)')
	rtnMapI(icast(r9,Math.ceil(dblEvalExpr(r9,prms[iZ]))))
}
private Map func_ceil(Map r9,List<Map> prms){ return func_ceiling(r9,prms)}


/** sprintf converts formats a series of values into a string			**/
/** Usage: sprintf(format, arguments)						**/
private Map func_sprintf(Map r9,List<Map> prms){
	if(badParams(r9,prms,i2))return rtnErr('sprintf(format, arguments)')
	String format; format=sNL
	List args=[]
	Boolean a
	try{
		format=strEvalExpr(r9,prms[iZ])
		Integer sz=prms.size()
		Integer x
		for(x=i1; x<sz; x++) a=args.push(evaluateExpression(r9,prms[x]).v)
		return rtnMapS(sprintf(format,args))
	}catch(all){
		return rtnErr("$all $format $args".toString())
	}
}
private Map func_format(Map r9,List<Map> prms){ return func_sprintf(r9,prms)}

/** left returns a substring of a value					**/
/** Usage: left(string, count)						**/
private Map func_left(Map r9,List<Map> prms){
	if(badParams(r9,prms,i2))return rtnErr('left(string, count)')
	String value=strEvalExpr(r9,prms[iZ])
	Integer cnt,sz
	cnt=intEvalExpr(r9,prms[i1])
	sz=value.size()
	if(cnt>sz)cnt=sz
	rtnMapS(value.substring(iZ,cnt))
}

/** right returns a substring of a value				**/
/** Usage: right(string, count)						**/
private Map func_right(Map r9,List<Map> prms){
	if(badParams(r9,prms,i2))return rtnErr('right(string, count)')
	String value=strEvalExpr(r9,prms[iZ])
	Integer cnt,sz
	cnt=intEvalExpr(r9,prms[i1])
	sz=value.size()
	if(cnt>sz)cnt=sz
	rtnMapS(value.substring(sz-cnt,sz))
}

/** strlen returns the length of a string value				**/
/** Usage: strlen(string)						**/
private Map func_strlen(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('strlen(string)')
	String value=strEvalExpr(r9,prms[iZ])
	rtnMapI(value.size())
}
private Map func_length(Map r9,List<Map> prms){ return func_strlen(r9,prms)}

/** coalesce returns the first non-empty parameter				**/
/** Usage: coalesce(value1[, value2[, ..., valueN]])				**/
private Map func_coalesce(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('coalesce(value1[, value2[, ..., valueN]])')
	Integer sz,i
	sz=prms.size()
	for(i=iZ; i<sz; i++){
		Map value=evaluateExpression(r9,prms[i])
		if(!(value.v==null || (value.v instanceof List ? value.v==[null] || value.v==[] || value.v==[sSNULL]:false) || sMt(value)==sERROR || value.v==sSNULL || scast(r9,value.v)==sBLK)){
			return value
		}
	}
	rtnMap(sDYN,null)
}

/** trim removes leading and trailing spaces from a string			**/
/** Usage: trim(value)								**/
private Map func_trim(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('trim(value)')
	String t0=strEvalExpr(r9,prms[iZ])
	String value=t0.trim()
	rtnMapS(value)
}

/** trimleft removes leading spaces from a string				**/
/** Usage: trimLeft(value)							**/
private Map func_trimleft(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('trimLeft(value)')
	String t0=strEvalExpr(r9,prms[iZ])
	String value=t0.replaceAll('^\\s+',sBLK)
	rtnMapS(value)
}
private Map func_ltrim(Map r9,List<Map> prms){ return func_trimleft(r9,prms)}

/** trimright removes trailing spaces from a string				**/
/** Usage: trimRight(value)							**/
private Map func_trimright(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('trimRight(value)')
	String t0=strEvalExpr(r9,prms[iZ])
	String value=t0.replaceAll('\\s+$',sBLK)
	rtnMapS(value)
}
private Map func_rtrim(Map r9,List<Map> prms){ return func_trimright(r9,prms)}

/** substring returns a substring of a value					**/
/** Usage: substring(string, start, count)					**/
private Map func_substring(Map r9,List<Map> prms){
	if(badParams(r9,prms,i2))return rtnErr('substring(string, start, count)')
	String value,result
	value=strEvalExpr(r9,prms[iZ])
	Integer start,cnt
	start=intEvalExpr(r9,prms[i1])
	cnt=prms.size()>i2 ? intEvalExpr(r9,prms[i2]):null
	//def end=null
	result=sBLK
	Integer t0=value.size()
	if(start<t0 && start>-t0){
		if(cnt!=null){
			if(cnt<iZ){
				//reverse
				start=start<iZ ? -start:t0-start
				cnt=-cnt
				value=value.reverse()
			}
			if(start>=iZ){
				if(cnt>t0-start)cnt=t0-start
			}else if(cnt>-start)cnt=-start
		}
		start=start>=iZ ? start:t0+start
		if(cnt>t0-start)cnt=t0-start
		result=cnt==null ? value.substring(start):value.substring(start,start+cnt)
	}
	rtnMapS(result)
}
private Map func_substr(Map r9,List<Map> prms){ return func_substring(r9,prms)}
private Map func_mid(Map r9,List<Map> prms){ return func_substring(r9,prms)}

/** replace replaces a search text inside of a value				**/
/** Usage: replace(string, search, replace[, [..],search, replace])		**/
private Map func_replace(Map r9,List<Map> prms){
	Integer sz,i,cnt
	sz=prms.size()
	if(badParams(r9,prms,i3) || sz%i2!=i1)return rtnErr('replace(string, search, replace[, [..],search, replace])')
	String value,search,replace
	value=strEvalExpr(r9,prms[iZ])
	cnt=Math.floor((sz-i1)/i2).toInteger()
	for(i=iZ; i<cnt; i++){
		search=strEvalExpr(r9,prms[i*i2+i1])
		replace=strEvalExpr(r9,prms[i*i2+i2])
		sz=search.size()
		if((sz>i2)&& search.startsWith(sDIV)&& search.endsWith(sDIV)){
			def ssearch= ~search.substring(i1,sz-i1)
			value=value.replaceAll(ssearch,replace)
		}else value=value.replace(search,replace)
	}
	rtnMapS(value)
}

/** rangeValue returns the matching value in a range					**/
/** Usage: rangeValue(input, defaultValue,point1, value1[, [..],pointN, valueN])	**/
private Map func_rangevalue(Map r9,List<Map> prms){
	Integer sz=prms.size()
	if(badParams(r9,prms,i2) || sz%i2!=iZ)return rtnErr('rangeValue(input, defaultValue,point1, value1[, [..],pointN, valueN])')
	Double input=dblEvalExpr(r9,prms[iZ])
	Map value; value=prms[i1]
	Integer cnt=Math.floor((sz-i2)/i2).toInteger()
	Integer i
	for(i=iZ; i<cnt; i++){
		Double point=dblEvalExpr(r9,prms[i*i2+i2])
		if(input>=point)value=prms[i*i2 +i3]
	}
	return value
}

/** rainbowValue returns the matching value in a range				**/
/** Usage: rainbowValue(input, minInput, minColor,maxInput, maxColor)		**/
private Map func_rainbowvalue(Map r9,List<Map> prms){
	if(badParams(r9,prms,i5))return rtnErr('rainbowValue(input, minColor,minValue,maxInput, maxColor)')
	Integer input,minInput,maxInput
	input=intEvalExpr(r9,prms[iZ])
	minInput=intEvalExpr(r9,prms[i1])
	Map minColor,maxColor
	minColor=gtColor(r9,strEvalExpr(r9,prms[i2]))
	maxInput=intEvalExpr(r9,prms[i3])
	maxColor=gtColor(r9,strEvalExpr(r9,prms[i4]))
	if(minInput>maxInput){
		Integer x=minInput
		minInput=maxInput
		maxInput=x
		Map x1=minColor
		minColor=maxColor
		maxColor=x1
	}
	input=(input<minInput ? minInput:(input>maxInput ? maxInput:input))
	String hx='hex'
	if((input==minInput)|| (minInput==maxInput))return rtnMapS(sMs(minColor,hx))
	if(input==maxInput)return rtnMapS(sMs(maxColor,hx))
	List<Integer> start=hexToHsl(sMs(minColor,hx))
	List<Integer> end=hexToHsl(sMs(maxColor,hx))
	Double alpha=d1*(input-minInput)/(maxInput-minInput+i1)
	Integer h=Math.round(start[iZ]-((input-minInput)*(start[iZ]-end[iZ])/(maxInput-minInput))).toInteger()
	Integer s=Math.round(start[i1]+(end[i1]-start[i1])*alpha).toInteger()
	Integer l=Math.round(start[i2]+(end[i2]-start[i2])*alpha).toInteger()
	rtnMapS(hslToHex(h.toDouble(),s.toDouble(),l.toDouble()))
}

/** indexOf finds the first occurrence of a substring in a string		**/
/** Usage: indexOf(stringOrDeviceOrList, substringOrItem)			**/
private Map func_indexof(Map r9,List<Map> prms){
	Integer sz=prms.size()
	if(badParams(r9,prms,i2) /*|| (sMt(prms[iZ])!=sDEV && sz!=i2)*/)return rtnErr('indexOf(stringOrDeviceOrList, substringOrItem)')
	if(/*sMt(prms[iZ])==sDEV &&*/ sz>i2){
		Integer t0=sz-i1
		String item=strEvalExpr(r9,prms[t0])
		Integer idx
		for(idx=iZ; idx<t0; idx++){
			Map it=evaluateExpression(r9,prms[idx],sSTR)
			if(sMs(it,sV)==item)return rtnMapI(idx)
		}
		return rtnMapI(iN1)
	}else if(prms[iZ].v instanceof Map){
		String item=strEvalExpr(r9,prms[i1],sMt(prms[iZ]))
		String key=((Map)prms[iZ].v).find{ it.value==item }?.key
		return rtnMapS(key)
	}else{
		String value=strEvalExpr(r9,prms[iZ])
		String substring=strEvalExpr(r9,prms[i1])
		return rtnMapI(value.indexOf(substring))
	}
}

/** lastIndexOf finds the last occurrence of a substring in a string		**/
/** Usage: lastIndexOf(string, substring)					**/
private Map func_lastindexof(Map r9,List<Map> prms){
	Integer sz=prms.size()
	if(badParams(r9,prms,i2) /*|| (sMt(prms[iZ])!=sDEV && sz!=i2)*/)return rtnErr('lastIndexOf(string, substring)')
	if(/*sMt(prms[iZ])==sDEV &&*/ sz>i2){
		String item=strEvalExpr(r9,prms[sz-i1])
		Integer idx
		for(idx=sz-i2; idx>=iZ; idx--){
			if(strEvalExpr(r9,prms[idx])==item){ return rtnMapI(idx) }
		}
		return rtnMapI(iN1)
	}else if(prms[iZ].v instanceof Map){
		String item=strEvalExpr(r9,prms[i1],sMt(prms[iZ]))
		String key=((Map)prms[iZ].v).find{ it.value==item }?.key
		return rtnMapS(key)
	}else{
		String value=strEvalExpr(r9,prms[iZ])
		String substring=strEvalExpr(r9,prms[i1])
		return rtnMapI(value.lastIndexOf(substring))
	}
}


/** lower returns a lower case value of a string				**/
/** Usage: lower(string)							**/
private Map func_lower(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('lower(string)')
	String res; res=sBLK
	for(Map prm in prms) res+=strEvalExpr(r9,prm)
	rtnMapS(res.toLowerCase())
}

/** upper returns a upper case value of a string				**/
/** Usage: upper(string)							**/
private Map func_upper(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('upper(string)')
	String res; res=sBLK
	for(Map prm in prms) res+=strEvalExpr(r9,prm)
	rtnMapS(res.toUpperCase())
}

/** title returns a title case value of a string				**/
/** Usage: title(string)							**/
private Map func_title(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('title(string)')
	String res; res=sBLK
	for(Map prm in prms) res+=strEvalExpr(r9,prm)
	//noinspection GroovyAssignabilityCheck
	rtnMapS(res.tokenize(sSPC)*.toLowerCase()*.capitalize().join(sSPC))
}

/** avg calculates the average of a series of numeric values			**/
/** Usage: avg(values)								**/
private Map func_avg(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('avg'+sVALUEN)
	Double s; s=dZ
	for(Map prm in prms) s+=dblEvalExpr(r9,prm)
	rtnMapD(s/prms.size())
}

/** median returns the value in the middle of a sorted array			**/
/** Usage: median(values)							**/
private Map func_median(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('median'+sVALUEN)
	List<Map> data=prms.collect{ Map it -> evaluateExpression(r9,it,sDYN)}.sort{ Map it -> it.v }
	Integer i=Math.floor(data.size()/i2).toInteger()
	if(data)return data[i]
	rtnMap(sDYN,sBLK)
}

/** least returns the value that is least found a series of numeric values	**/
/** Usage: least(values)							**/
private Map func_least(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('least'+sVALUEN)
	Map<Object,Map> data=[:]
	for(Map prm in prms){
		Map value=evaluateExpression(r9,prm,sDYN)
		data[value.v]=rtnMap(sMt(value),value.v)+[(sC):(data[value.v]?.c ?: iZ)+i1]
	}
	Map value=data.sort{ it.value.c }.collect{ it.value }[iZ]
	rtnMap(sMt(value),value.v)
}

/** most returns the value that is most found a series of numeric values	**/
/** Usage: most(values)								**/
private Map func_most(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('most'+sVALUEN)
	Map<Object,Map> data=[:]
	for(Map prm in prms){
		Map value=evaluateExpression(r9,prm,sDYN)
		data[value.v]=rtnMap(sMt(value),value.v)+[(sC):(data[value.v]?.c ?: iZ)+i1]
	}
	Map value=data.sort{ -it.value.c }.collect{ it.value }[iZ]
	rtnMap(sMt(value),value.v)
}

/** sum calculates the sum of a series of numeric values			**/
/** Usage: sum(values)								**/
private Map func_sum(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('sum'+sVALUEN)
	Double s; s=dZ
	for(Map prm in prms) s+=dblEvalExpr(r9,prm)
	rtnMapD(s)
}

/** variance calculates the variance of a series of numeric values	**/
/** Usage: variance(values)							**/
private Map func_variance(Map r9,List<Map> prms){
	if(badParams(r9,prms,i2))return rtnErr('variance'+sVALUEN)
	Double sum,value
	sum=dZ
	List values=[]
	for(Map prm in prms){
		value=dblEvalExpr(r9,prm)
		Boolean a=values.push(value)
		sum+=value
	}
	Integer sz,i
	sz=values.size()
	Double avg=sum/sz
	sum=dZ
	for(i=iZ;i<sz;i++) sum+=((Double)values[i]-avg)**i2
	rtnMapD(sum/sz)
}

/** stdev calculates the [population] standard deviation of a series of numeric values	**/
/** Usage: stdev(values)							**/
private Map func_stdev(Map r9,List<Map> prms){
	if(badParams(r9,prms,i2))return rtnErr('stdev'+sVALUEN)
	Map result=func_variance(r9,prms)
	rtnMapD(Math.sqrt((Double)result.v))
}

/** sort a series of numeric values			**/
/** Usage: sort(values,reverse)						**/
private Map func_sort(Map r9,List<Map> prms){
	if(badParams(r9,prms,i2))return rtnErr('sort(value1, [value2,..., valueN,] reverse)')
	Integer t0=prms.size()-i1
	Boolean rev= boolEvalExpr(r9,prms[t0])
	String typ; typ=sMt(prms[iZ]).replace(sLRB,sBLK)
	Integer i
	List<Map>nprms; nprms=[]
	for(i=iZ; i<t0; i++){ nprms[i]=prms[i] }
	for(i=iZ; i<t0; i++){ if(sMt(prms[i]).replace(sLRB,sBLK)!=typ){ typ=sNL; break } }
	typ= typ?:sDYN
	List<Map> data=nprms.collect{ Map it -> evaluateExpression(r9,(Map)it,typ)}.sort{ Map it -> it.v }
	List res; res=[]
	for(Map m in data){ res << m.v }
	if(rev)res= res.reverse()
	rtnMap(typ+sLRB,res)
}

/** min calculates the minimum of a series of numeric values			**/
/** Usage: min(values)								**/
private Map func_min(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('min'+sVALUEN)
	List<Map> data=prms.collect{ Map it -> evaluateExpression(r9,(Map)it,sDYN)}.sort{ Map it -> it.v }
	if(data)return data[iZ]
	rtnMap(sDYN,sBLK)
}

/** max calculates the maximum of a series of numeric values			**/
/** Usage: max(values)								**/
private Map func_max(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('max'+sVALUEN)
	List<Map> data=prms.collect{ Map it -> evaluateExpression(r9,it,sDYN)}.sort{ Map it -> it.v }
	Integer sz=data.size()
	if(sz)return data[sz-i1]
	rtnMap(sDYN,sBLK)
}

/** abs calculates the absolute value of a number				**/
/** Usage: abs(number)								**/
private Map func_abs(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('abs(value)')
	Double value=dblEvalExpr(r9,prms[iZ])
	String dataType=(value==Math.round(value).toDouble() ? sINT:sDEC)
	rtnMap(dataType,cast(r9,Math.abs(value),dataType,sDEC))
}

/** hslToHex converts a hue/saturation/level trio to it hex #rrggbb representation	**/
/** Usage: hslToHex(hue,saturation,level)						**/
private Map func_hsltohex(Map r9,List<Map> prms){
	if(badParams(r9,prms,i3))return rtnErr('hsl(hue,saturation, level)')
	Double hue=dblEvalExpr(r9,prms[iZ])
	Double saturation=dblEvalExpr(r9,prms[i1])
	Double level=dblEvalExpr(r9,prms[i2])
	rtnMapS(hslToHex(hue,saturation,level))
}

/** count calculates the number of true/non-zero/non-empty items in a series of numeric values		**/
/** Usage: count(values)										**/
private Map func_count(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnMapI(iZ)
	Integer cnt,i
	Integer sz; sz=prms.size()
	cnt=iZ
	if(sz==i1){
		String t= sMt(prms[iZ])
		String tt1=t.replace(sLRB,sBLK)
		//if(isEric(r9)) myDetail r9, "count found t: $t and tt1: $tt1",iN2
		if(t!=tt1 || t in [sSTR,sDYN]){
			Map m; m=null
			List list
			def a= prms[iZ].v
			if(t!=tt1){ //input is a list or map type
				list= a instanceof List ? (List)a : null
				m= a instanceof Map ? (Map)a : null
			}else
				list=strEvalExpr(r9,prms[iZ]).split(sCOMMA)
			//String[] list=strEvalExpr(r9,prms[iZ]).split(sCOMMA)
			//if(isEric(r9)) myDetail r9, "a is list: $list  m: $m ${myObj(a)}",iN2
			Boolean t1
			if(m){
				for(j in m){
					//if(isEric(r9)) myDetail r9, "j.value is $j.value ${myObj(j.value)}",iN2
					t1=bcast(r9,j.value)
					cnt+=t1 ? i1:iZ
				}
			}else{
				sz=list.size()
				for(i=iZ; i<sz; i++){
					t1=bcast(r9,list[i])
					cnt+=t1 ? i1:iZ
				}
			}
			return rtnMapI(cnt)
		}
	}
	for(Map prm in prms) cnt+=boolEvalExpr(r9,prm) ? i1:iZ
	rtnMapI(cnt)
}

/** size returns the number of values provided				**/
/** Usage: size(values)							**/
private Map func_size(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnMapI(iZ)
	Integer sz=prms.size()
	if(sz==i1){
		Integer cnt
		String t= sMt(prms[iZ])
		String tt1=t.replace(sLRB,sBLK)
		if(t!=tt1 || t in [sSTR,sDYN]){
			Map m; m=null
			List list
			def a= prms[iZ].v
			if(t!=tt1){ //input is a list or map type
				list= a instanceof List ? (List)a : null
				m= a instanceof Map ? (Map)a : null
			}else
				list=strEvalExpr(r9,prms[iZ]).split(sCOMMA)
			//String[] list=strEvalExpr(r9,prms[iZ]).split(sCOMMA)
			if(m)
				cnt=m.size()
			else
				cnt=list.size()
			return rtnMapI(cnt)
		}
	}
	rtnMapI(sz)
}

/** age returns the number of milliseconds an attribute had the current value	**/
/** Usage: age([device:attribute])						**/
private Map func_age(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('age'+sDATTRH)
	Map prm=evaluateExpression(r9,prms[iZ],sDEV)
	if(sMt(prm)==sDEV && sMa(prm) && ((List)prm.v).size()){
		def device=getDevice(r9,(String)((List)prm.v)[iZ])
		if(device!=null){
			def dstate=device.currentState(sMa(prm),true)
			if(dstate){
				Long result=elapseT(((Date)dstate.getDate()).getTime())
				return rtnMap(sLONG,result)
			}
		}
	}
	rtnMapE('Invalid device')
}

/** previousAge returns the number of milliseconds an attribute had the previous value		**/
/** Usage: previousAge([device:attribute])							**/
private Map func_previousage(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('previousAge'+sDATTRH)
	Map prm=evaluateExpression(r9,prms[iZ],sDEV)
	if(sMt(prm)==sDEV && sMa(prm) && ((List)prm.v).size()){
		def device=getDevice(r9,(String)((List)prm.v)[iZ])
		if(device!=null && !isDeviceLocation(device)){
			List states=device.statesSince(sMa(prm),new Date(elapseT(604500000L)),[max:i5])
			Integer sz=states.size()
			if(sz>i1){
				def newValue=states[iZ].getValue()
				//some events get duplicated look for the last "different valued" state
				for(Integer i=i1; i<sz; i++){
					if(states[i].getValue()!=newValue){
						Long result=elapseT(((Date)states[i].getDate()).getTime())
						return rtnMap(sLONG,result)
					}
				}
			}
			//saying 7 days though it may be wrong- but we have no data
			return rtnMap(sLONG,604800000L)
		}
	}
	rtnMapE('Invalid device')
}

/** previousValue returns the previous value of the attribute				**/
/** Usage: previousValue([device:attribute])						**/
private Map func_previousvalue(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('previousValue'+sDATTRH)
	Map prm=evaluateExpression(r9,prms[iZ],sDEV)
	if(sMt(prm)==sDEV && sMa(prm) && ((List)prm.v).size()){
		def device=getDevice(r9,(String)((List)prm.v)[iZ])
		Map attribute=devAttrT(r9,sMa(prm),device)
		if(device!=null && !isDeviceLocation(device)){
			List states=device.statesSince(sMa(prm),new Date(elapseT(604500000L)),[max:i5])
			Integer sz=states.size()
			if(sz>i1){
				def newValue=states[iZ].getValue()
				//some events get duplicated want to look for the last "different valued" state
				for(Integer i=i1; i<sz; i++){
					def result=states[i].getValue()
					if(result!=newValue){
						String t=sMt(attribute)
						return rtnMap(t,cast(r9,result,t))
					}
				}
			}
			//saying no value- we have no data
			return rtnMapS(sBLK)
		}
	}
	rtnMapE('Invalid device')
}

/** newer returns the number of devices whose attribute had the current		**/
/** value for less than the specified number of milliseconds			**/
/** Usage: newer([device:attribute] [,.., [device:attribute]],threshold)	**/
private Map func_newer(Map r9,List<Map> prms){
	if(badParams(r9,prms,i2))return rtnErr('newer'+sDATTRHT)
	Integer t0=prms.size()-i1
	Long threshold=longEvalExpr(r9,prms[t0],sLONG)
	Integer result,i
	result=iZ
	for(i=iZ; i<t0; i++){
		Map age=func_age(r9,[prms[i]])
		if(sMt(age)!=sERROR && lMs(age,sV)<threshold)result++
	}
	rtnMapI(result)
}

/** older returns the number of devices whose attribute had the current		**/
/** value for more than the specified number of milliseconds			**/
/** Usage: older([device:attribute] [,.., [device:attribute]],threshold)	**/
private Map func_older(Map r9,List<Map> prms){
	if(badParams(r9,prms,i2))return rtnErr('older'+sDATTRHT)
	Integer t0=prms.size()-i1
	Long threshold=longEvalExpr(r9,prms[t0],sLONG)
	Integer result,i
	result=iZ
	for(i=iZ; i<t0; i++){
		Map age=func_age(r9,[prms[i]])
		if(sMt(age)!=sERROR && lMs(age,sV)>=threshold)result++
	}
	rtnMapI(result)
}

/** startsWith returns true if a string starts with a substring			**/
/** Usage: startsWith(string, substring)					**/
private Map func_startswith(Map r9,List<Map> prms){
	if(badParams(r9,prms,i2))return rtnErr('startsWith(string, substring)')
	String string=strEvalExpr(r9,prms[iZ])
	String substring=strEvalExpr(r9,prms[i1])
	rtnMapB(string.startsWith(substring))
}

/** endsWith returns true if a string ends with a substring				**/
/** Usage: endsWith(string, substring)							**/
private Map func_endswith(Map r9,List<Map> prms){
	if(badParams(r9,prms,i2))return rtnErr('endsWith(string, substring)')
	String string=strEvalExpr(r9,prms[iZ])
	String substring=strEvalExpr(r9,prms[i1])
	rtnMapB(string.endsWith(substring))
}

/** contains returns true if a string contains a substring				**/
/** Usage: contains(string, substring)							**/
private Map func_contains(Map r9,List<Map> prms){
	Integer t0,idx
	t0=prms.size()
	if(badParams(r9,prms,i2) /*|| (sMt(prms[iZ])!=sDEV && t0!=i2)*/)return rtnErr('contains(string, substring)')
	if(/*sMt(prms[iZ])==sDEV &&*/ t0>i2){
		t0=t0-i1
		String item=strEvalExpr(r9,prms[t0])
		for(idx=iZ; idx<t0; idx++){
			Map it=evaluateExpression(r9,prms[idx],sSTR)
			if(it.v==item)return rtnMapB(true)
		}
		return rtnMapB(false)
	}else{
		String string=strEvalExpr(r9,prms[iZ])
		String substring=strEvalExpr(r9,prms[i1])
		rtnMapB(string.contains(substring))
	}
}

/** matches returns true if a string matches a pattern					**/
/** Usage: matches(string, pattern)							**/
private Map func_matches(Map r9,List<Map> prms){
	if(badParams(r9,prms,i2))return rtnErr('matches(string, pattern)')
	String string=strEvalExpr(r9,prms[iZ])
	String pattern=strEvalExpr(r9,prms[i1])
	Boolean r=match(string,pattern)
	rtnMapB(r)
}

/** exists returns true if file exists					**/
/** Usage: exists(value1)							**/
private Map func_exists(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('exists(filename)')
	String string=strEvalExpr(r9,prms[iZ])
	rtnMapB(fileExists(r9,string))
}

/** eq returns true if two values are equal					**/
/** Usage: eq(value1, value2)							**/
private Map func_eq(Map r9,List<Map> prms){
	if(badParams(r9,prms,i2))return rtnErr('eq(value1, value2)')
	String t=sMt(prms[iZ])==sDEV ? sMt(prms[i1]):sMt(prms[iZ])
	Map value1=evaluateExpression(r9,prms[iZ],t)
	Map value2=evaluateExpression(r9,prms[i1],t)
	rtnMapB(value1.v==value2.v)
}

/** lt returns true if value1<value2						**/
/** Usage: lt(value1, value2)							**/
private Map func_lt(Map r9,List<Map> prms,Boolean not=false){
	if(badParams(r9,prms,i2))return rtnErr('lt(value1, value2)')
	Map value1=evaluateExpression(r9,prms[iZ])
	Map value2=evaluateExpression(r9,prms[i1],sMt(value1))
	Boolean a= (value1.v<value2.v)
	rtnMapB( not ? !a:a)
}

/** ge returns true if value1>=value2						**/
/** Usage: ge(value1, value2)							**/
private Map func_ge(Map r9,List<Map> prms){
	if(badParams(r9,prms,i2))return rtnErr('ge(value1, value2)')
	func_lt(r9,prms,true)
}

/** gt returns true if value1>value2						**/
/** Usage: gt(value1, value2)							**/
private Map func_gt(Map r9,List<Map> prms,Boolean not=false){
	if(badParams(r9,prms,i2))return rtnErr('gt(value1, value2)')
	Map value1=evaluateExpression(r9,prms[iZ])
	Map value2=evaluateExpression(r9,prms[i1],sMt(value1))
	Boolean a= (value1.v>value2.v)
	rtnMapB( not ? !a:a)
}

/** le returns true if value1<=value2						**/
/** Usage: le(value1, value2)							**/
private Map func_le(Map r9,List<Map> prms){
	if(badParams(r9,prms,i2))return rtnErr('le(value1, value2)')
	func_gt(r9,prms,true)
}

/** not returns the negative Boolean value					**/
/** Usage: not(value)								**/
private Map func_not(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('not(value)')
	Boolean value=boolEvalExpr(r9,prms[iZ])
	rtnMapB(!value)
}

/** if evaluates a Boolean and returns value1 if true,otherwise value2		**/
/** Usage: if(condition, valueIfTrue,valueIfFalse)				**/
private Map func_if(Map r9,List<Map> prms){
	if(badParams(r9,prms,i3))return rtnErr('if(condition, valueIfTrue,valueIfFalse)')
	Boolean value=boolEvalExpr(r9,prms[iZ])
	Integer i= value ? i1:i2
	return evaluateExpression(r9,prms[i])
}

/** isEmpty returns true if the value is empty					**/
/** Usage: isEmpty(value)							**/
private Map func_isempty(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('isEmpty(value)')
	Map value=evaluateExpression(r9,prms[iZ])
	Boolean result=value.v==null || (value.v instanceof List ? value.v==[null] || value.v==[] || value.v==[sSNULL]:false) || sMt(value)==sERROR || value.v==sSNULL || scast(r9,value.v)==sBLK || "$value.v".toString()==sBLK
	rtnMapB(result)
}

/** datetime returns the value as a datetime type				**/
/** Usage: datetime([value])							**/
private Map func_datetime(Map r9,List<Map> prms){
	Integer sz=prms.size()
	if(badParams(r9,prms,iZ) || sz>i1)return rtnErr('datetime([value])')
	Long value=sz>iZ ? longEvalExpr(r9,prms[iZ],sDTIME) :wnow()
	rtnMap(sDTIME,value)
}

/** date returns the value as a date type					**/
/** Usage: date([value])							**/
private Map func_date(Map r9,List<Map> prms){
	Integer sz=prms.size()
	if(badParams(r9,prms,iZ) || sz>i1)return rtnErr('date([value])')
	Long value=sz>iZ ? longEvalExpr(r9,prms[iZ],sDATE) :(Long)cast(r9,wnow(),sDATE,sDTIME)
	rtnMap(sDATE,value)
}

/** time returns the value as a time type					**/
/** Usage: time([value])							**/
private Map func_time(Map r9,List<Map> prms){
	Integer sz=prms.size()
	if(badParams(r9,prms,iZ) || sz>i1)return rtnErr('time([value])')
	Long value=sz>iZ ? longEvalExpr(r9,prms[iZ],sTIME) :(Long)cast(r9,wnow(),sTIME,sDTIME)
	rtnMap(sTIME,value)
}

private Map addtimeHelper(Map r9,List<Map> prms,Long mulp,String msg){
	Integer sz=prms.size()
	if(badParams(r9,prms,i1) || sz>i2)return rtnErr(msg)
	Long value=sz==i2 ? longEvalExpr(r9,prms[iZ],sDTIME) :wnow()
	Long delta=longEvalExpr(r9,(sz==i2 ? prms[i1]:prms[iZ]),sLONG) *mulp
	Long res=value+delta
	TimeZone mtz=mTZ()
	res+=Math.round((mtz.getOffset(value)-mtz.getOffset(res))*d1)
	return rtnMap(sDTIME,res)
}

/** addSeconds returns the value as a dateTime type						**/
/** Usage: addSeconds([dateTime,]seconds)						**/
private Map func_addseconds(Map r9,List<Map> prms){ return addtimeHelper(r9,prms,lTHOUS,'addSeconds([dateTime,]seconds)') }

/** addMinutes returns the value as a dateTime type						**/
/** Usage: addMinutes([dateTime,]minutes)						**/
private Map func_addminutes(Map r9,List<Map> prms){ return addtimeHelper(r9,prms,dMSMINT.toLong(),'addMinutes([dateTime,]minutes)') }

/** addHours returns the value as a dateTime type						**/
/** Usage: addHours([dateTime,]hours)							**/
private Map func_addhours(Map r9,List<Map> prms){ return addtimeHelper(r9,prms,dMSECHR.toLong(),'addHours([dateTime,]hours)') }

/** addDays returns the value as a dateTime type						**/
/** Usage: addDays([dateTime,]days)							**/
private Map func_adddays(Map r9,List<Map> prms){ return addtimeHelper(r9,prms,lMSDAY,'addDays([dateTime,]days)') }

/** addWeeks returns the value as a dateTime type						**/
/** Usage: addWeeks([dateTime,]weeks)							**/
private Map func_addweeks(Map r9,List<Map> prms){ return addtimeHelper(r9,prms,604800000L,'addWeeks([dateTime,]weeks)') }

/** weekDayName returns the name of the week day					**/
/** Usage: weekDayName(dateTimeOrWeekDayIndex)						**/
private Map func_weekdayname(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('weekDayName(dateTimeOrWeekDayIndex)')
	Long value=longEvalExpr(r9,prms[iZ],sLONG)
	Integer index=((value>=lMSDAY)? utcToLocalDate(value).day:value.toInteger()) % i7
	rtnMapS(weekDaysFLD[index])
}

/** monthName returns the name of the month						**/
/** Usage: monthName(dateTimeOrMonthNumber)						**/
private Map func_monthname(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('monthName(dateTimeOrMonthNumber)')
	Long value=longEvalExpr(r9,prms[iZ],sLONG)
	Integer index=((value>=lMSDAY)? utcToLocalDate(value).month: (value-1L).toInteger())%i12+i1
	rtnMapS(yearMonthsFLD[index])
}

/** arrayItem returns the nth item in the parameter list				**/
/** Usage: arrayItem(index, item0[, item1[, .., itemN]])				**/
private Map func_arrayitem(Map r9,List<Map> prms){
	if(badParams(r9,prms,i2))return rtnErr('arrayItem(index, item0[, item1[, .., itemN]])')
	Map serr=rtnMapE('Array item index is outside of bounds.')
	Integer index=intEvalExpr(r9,prms[iZ])
	Integer sz=prms.size()
	if(sz==i2){
		String t= sMt(prms[i1])
		String tt1=t.replace(sLRB,sBLK)
		if(t!=tt1 || t in [sSTR,sDYN]){
			List list
			def a= prms[iZ].v
			if(t!=tt1 && a instanceof List) //input is a list type
				list= (List)a
			else
				list=strEvalExpr(r9,prms[i1]).split(sCOMMA)
			//String[] list=strEvalExpr(r9,prms[i1]).split(sCOMMA)
			if(index<iZ || index>=list.size())return serr
			String rtnT= t!=tt1 ? tt1 : sSTR
			return rtnMap(rtnT,list[index])
		}
	}
	if(index<iZ || index>=sz-i1)return serr
	return prms[index+i1]
}

/** isBetween returns true if value>=startValue and value<=endValue		**/
/** Usage: isBetween(value,startValue,endValue)				**/
private Map func_isbetween(Map r9,List<Map> prms){
	if(badParams(r9,prms,i3))return rtnErr('isBetween(value,startValue,endValue)')
	Map value=evaluateExpression(r9,prms[iZ])
	Map startValue=evaluateExpression(r9,prms[i1],sMt(value))
	Map endValue=evaluateExpression(r9,prms[i2],sMt(value))
	rtnMapB((value.v>=startValue.v && value.v<=endValue.v))
}

/** formatDuration returns a duration in a readable format					**/
/** Usage: formatDuration(value[, friendly=false[, granularity='s'[, showAdverbs=false]]])	**/
private Map func_formatduration(Map r9,List<Map> prms){
	Integer sz
	sz=prms.size()
	if(badParams(r9,prms,i1) || sz>i4)return rtnErr("formatDuration(value[, friendly=false[, granularity='s'[, showAdverbs=false]]])")
	Long value
	value=longEvalExpr(r9,prms[iZ],sLONG)
	Boolean friendly=sz>i1 ? boolEvalExpr(r9,prms[i1]):false
	String granularity=sz>i2 ? strEvalExpr(r9,prms[i2]):sS
	Boolean showAdverbs=sz>i3 ? boolEvalExpr(r9,prms[i3]):false

	Integer sign=(value>=iZ)? i1:iN1
	if(sign<iZ)value=-value
	Integer ms=(value%1000).toInteger()
	value=Math.floor((value-ms)/d1000).toLong()
	Integer s=(value%60).toInteger()
	value=Math.floor((value-s)/d60).toLong()
	Integer m=(value%60).toInteger()
	value=Math.floor((value-m)/d60).toLong()
	Integer h=(value%24).toInteger()
	value=Math.floor((value-h)/24.0D).toLong()
	Integer d=value.toInteger()

	Integer parts
	String partName
	switch(granularity){
		case sD: parts=i1; partName='day'; break
		case sH: parts=i2; partName='hour'; break
		case sM: parts=i3; partName='minute'; break
		case sMS: parts=i5; partName='millisecond'; break
		default:parts=i4; partName='second'; break
	}
	parts=friendly ? parts:(parts<i3 ? i3:parts)
	String result
	if(friendly){
		List p=[]
		if(d)Boolean a=p.push("$d day"+(d>i1 ? sS:sBLK))
		if(parts>i1 && h)Boolean a=p.push("$h hour"+(h>i1 ? sS:sBLK))
		if(parts>i2 && m)Boolean a=p.push("$m minute"+(m>i1 ? sS:sBLK))
		if(parts>i3 && s)Boolean a=p.push("$s second"+(s>i1 ? sS:sBLK))
		if(parts>4 && ms)Boolean a=p.push("$ms millisecond"+(ms>i1 ? sS:sBLK))
		sz=p.size()
		switch(sz){
			case iZ:
				result=showAdverbs ? 'now':'0 '+partName+sS
				break
			case i1:
				result=p[iZ]
				break
			default:
				result=sBLK
				Integer i
				for(i=iZ; i<sz; i++){
					result+=(i ? (sz>i2 ? sCOMMA:sSPC):sBLK)+(i==sz-i1 ? sAND+sSPC:sBLK)+p[i]
				}
				result=(showAdverbs && (sign>iZ)? 'in ':sBLK)+result+(showAdverbs && (sign<iZ)? ' ago':sBLK)
				break
		}
	}else{
		result=(sign<iZ ? sMINUS:sBLK)+(d>iZ ? sprintf("%dd ",d):sBLK)+sprintf("%02d:%02d",h,m)+(parts>i3 ? sprintf(":%02d",s):sBLK)+(parts>4 ? sprintf(".%03d",ms):sBLK)
	}
	rtnMapS(result)
}

/** formatDateTime returns a datetime in a readable format				**/
/** Usage: formatDateTime(value[, format])						**/
private Map func_formatdatetime(Map r9,List<Map> prms){
	Integer sz=prms.size()
	if(badParams(r9,prms,i1) || sz>i2)return rtnErr('formatDateTime(value[, format])')
	Long value=longEvalExpr(r9,prms[iZ],sDTIME)
	String format=sz>i1 ? strEvalExpr(r9,prms[i1]):sNL
	rtnMapS((format ? formatLocalTime(value,format):formatLocalTime(value)))
}

/** random returns a random value						**/
/** Usage: random([range | value1, value2[, ..,valueN]])			**/
private Map func_random(Map r9,List<Map> prms){
	Integer sz=prms!=null && (prms instanceof List) ? prms.size():iZ
	switch(sz){
		case iZ:
			return rtnMapD(Math.random())
		case i1:
			Double range=dblEvalExpr(r9,prms[iZ])
			return rtnMapI((Integer)Math.round(range*Math.random()))
		case i2:
			List<String> n=[sINT,sDEC]
			if((sMt(prms[iZ]) in n) && (sMt(prms[i1]) in n)){
				Double min,max,swap
				min=dblEvalExpr(r9,prms[iZ])
				max=dblEvalExpr(r9,prms[i1])
				if(min>max){
					swap=min
					min=max
					max=swap
				}
				return rtnMapI((Integer)Math.round(min+(max-min)*Math.random()))
			}
	}
	Integer choice
	choice=(Integer)Math.round((sz-i1)*Math.random())
	if(choice>=sz)choice=sz-i1
	return prms[choice]
}

/** distance returns a distance measurement							**/
/** Usage: distance((device | latitude,longitude),(device | latitude,longitude)[, unit])	**/
@SuppressWarnings('GroovyVariableNotAssigned')
private Map func_distance(Map r9,List<Map> prms){
	Integer sz=prms.size()
	if(badParams(r9,prms,i2) || sz>i5)return rtnErr('distance((device | latitude,longitude),(device | latitude,longitude)[, unit])')
	Double lat1,lng1,lat2,lng2
	String unit
	Integer idx,pidx
	idx=iZ
	pidx=iZ
	String errMsg; errMsg=sBLK
	while (pidx<sz){
		if(sMt(prms[pidx])!=sDEV || (sMt(prms[pidx])==sDEV && !!sMa(prms[pidx]))){
			//a decimal or device attribute is provided
			switch(idx){
				case iZ:
					lat1=dblEvalExpr(r9,prms[pidx])
					break
				case i1:
					lng1=dblEvalExpr(r9,prms[pidx])
					break
				case i2:
					lat2=dblEvalExpr(r9,prms[pidx])
					break
				case i3:
					lng2=dblEvalExpr(r9,prms[pidx])
					break
				case i4:
					unit=strEvalExpr(r9,prms[pidx])
			}
			idx+=i1
			pidx+=i1
			continue
		}else{
			switch(idx){
				case iZ:
				case i2:
					prms[pidx].a='latitude'
					Double lat=dblEvalExpr(r9,prms[pidx])
					prms[pidx].a='longitude'
					Double lng=dblEvalExpr(r9,prms[pidx])
					if(idx==iZ){
						lat1=lat
						lng1=lng
					}else{
						lat2=lat
						lng2=lng
					}
					idx+=i2
					pidx+=i1
					continue
				default:
					errMsg="Invalid parameter order. Expecting parameter #${idx+i1} to be a decimal, not a device.".toString()
					pidx=iN1
					break
			}
		}
		if(pidx==iN1)break
	}
	if(errMsg!=sBLK)return rtnMapE(errMsg)
	if(idx<i4 || idx>i5)return rtnMapE('Invalid parameter combination. Expecting either two devices, a device and two decimals, or four decimals, followed by an optional unit.')
	Double earthRadius=6371000.0D //meters
	Double dLat=Math.toRadians(lat2-lat1)
	Double dLng=Math.toRadians(lng2-lng1)
	Double a=Math.sin(dLat/d2)*Math.sin(dLat/d2)+
		Math.cos(Math.toRadians(lat1))*Math.cos(Math.toRadians(lat2))*
		Math.sin(dLng/d2)*Math.sin(dLng/d2)
	Double c=d2*Math.atan2(Math.sqrt(a),Math.sqrt(d1-a))
	Double dist=earthRadius*c
	switch(unit!=null ? unit:sM){
		case 'km':
		case 'kilometer':
		case 'kilometers':
			return rtnMapD(dist/d1000)
		case 'mi':
		case 'mile':
		case 'miles':
			return rtnMapD(dist/1609.3440D)
		case 'ft':
		case 'foot':
		case 'feet':
			return rtnMapD(dist/0.3048D)
		case 'yd':
		case 'yard':
		case 'yards':
			return rtnMapD(dist/0.9144D)
	}
	rtnMapD(dist)
}

/** json encodes data as a JSON string							**/
/** Usage: json(value[, pretty])							**/
private static Map func_json(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1) || prms.size()>i2)return rtnErr('json(value[, format])')
	JsonBuilder builder=new JsonBuilder([((Map)prms[iZ]).v])
	String op=prms[i1] ? 'toPrettyString':'toString'
	String json=builder."${op}"()
	rtnMapS(json[i1..-i2].trim())
}

/** urlencode encodes data for use in a URL						**/
/** Usage: urlencode(value)								**/
private Map func_urlencode(Map r9,List<Map> prms){
	if(badParams(r9,prms,i1))return rtnErr('urlencode(value])')
	String t0=strEvalExpr(r9,prms[iZ])
	String value=(t0!=sNL ? t0:sBLK)
	rtnMapS(encodeURIComponent(value))
}
private Map func_encodeuricomponent(Map r9,List prms){ return func_urlencode(r9,prms)}

/** COMMON PUBLISHED METHODS							**/

@CompileStatic
private String mem(Boolean showBytes=true){
	String mbytes=new JsonOutput().toJson(gtState())
	Integer bytes=mbytes.length()
	return Math.round(d100*(bytes/100000.0D))+"%${showBytes ? " ($bytes bytes)".toString():sBLK}"
}

private static String runTimeHis(Map r9){
	String myId=sMs(r9,snId)
	return 'Total run history: '+((List)theCacheVFLD[myId].runTimeHis).toString()+'<br>' +
			'Last run details: '+((Map)theCacheVFLD[myId].runStats).toString()
}

/** UTILITIES									**/

private static String encodeURIComponent(value){
	// URLEncoder converts spaces to + which is then indistinguishable from any
	// actual + characters in the value. Match encodeURIComponent in ECMAScript
	// which encodes "a+b c" as "a+b%20c" rather than URLEncoder's "a+b+c"
	String holder='__wc_plus__'
	return URLEncoder.encode(
		"${value}".toString().replaceAll('\\+',holder),
		'UTF-8'
	).replaceAll('\\+','%20').replaceAll(holder,'+')
}

@SuppressWarnings('GroovyAssignabilityCheck')
private static String getThreeAxisOrientation(Map m /*, Boolean getIndex=false */){
	if(m.x!=null && m.y!=null && m.z!=null){
		Integer x=Math.abs("${m.x}".toDouble()).toInteger()
		Integer y=Math.abs("${m.y}".toDouble()).toInteger()
		Integer z=Math.abs("${m.z}".toDouble()).toInteger()
		Integer side; side=(x>y ? (x>z ? iZ:i2):(y>z ? i1:i2))
		side+=(side==iZ && x<iZ) || (side==i1 && y<iZ) || (side==i2 && z<iZ) ? i3:iZ
//		if(getIndex)return side
		List<String> orientations=['rear','down','left','front','up','right']
		return orientations[side]+' side up'
	}
	return sNL
}

@CompileStatic
private Long gtWCTimeToday(Long time){
	Long t0=getMidnightTime()
	Long result=time+t0
	//we need to adjust for time overlapping during DST changes
	TimeZone mtz=mTZ()
	return Math.round(result+(mtz.getOffset(t0)-mtz.getOffset(result))*d1)
}

@Field static final List<String> trueStrings= [ '1','true', "on", "open",  "locked",  "active",  "wet",           "detected",    "present",    "occupied",    "muted",  "sleeping"]
@Field static final List<String> falseStrings=[ '0','false',"off","closed","unlocked","inactive","dry","clear",   "not detected","not present","not occupied","unmuted","not sleeping","null"]

@CompileStatic
private static Map dataT(ival,String isrcDT){
	def value; value=ival
	String srcDt; srcDt=isrcDT
	Boolean isfbd; isfbd=false
	value=(value instanceof GString)? "$value".toString():value //get rid of GStrings
	if(srcDt==sNL || srcDt.length()==iZ || srcDt in [sBOOLN,sDYN]){
		if(value instanceof List)srcDt=sDEV
		else if(value instanceof Boolean)srcDt=sBOOLN
		else if(value instanceof String)srcDt=sSTR
		else if(value instanceof Integer)srcDt=sINT
		else if(value instanceof Long || value instanceof BigInteger)srcDt=sLONG
		else if(value instanceof Double)srcDt=sDEC
		else if(value instanceof BigDecimal || value instanceof Float){srcDt=sDEC; isfbd=true}
		else if(value instanceof Map){
			Map m=(Map)value
			if(m.x!=null && m.y!=null && m.z!=null)srcDt='vector3'
		}else{ value="$value".toString(); srcDt=sSTR }
	}
	//overrides
	switch(srcDt){
		case sBOOL: srcDt=sBOOLN; break
		case sNUMBER: srcDt=sDEC; break
		case sENUM: srcDt=sSTR; break
	}
	return [s:srcDt,v:value,t:isfbd]
}

@CompileStatic
private static Long lcast(Map r9,ival){
	Map rr=dataT(ival,sNL)
	String srcDt=sMs(rr,sS)
	def value=rr.v
	return (Long)com_cast(r9,value,sLONG,srcDt)
}

@CompileStatic
private static Double dcast(Map r9,ival){
	Map rr=dataT(ival,sNL)
	String srcDt=sMs(rr,sS)
	def value=rr.v
	return (Double)com_cast(r9,value,sDEC,srcDt)
}

@CompileStatic
private static Integer icast(Map r9,ival){
	Map rr=dataT(ival,sNL)
	String srcDt=sMs(rr,sS)
	def value=rr.v
	return (Integer)com_cast(r9,value,sINT,srcDt)
}

private static Boolean bcast(Map r9,ival){
	Map rr=dataT(ival,sNL)
	String srcDt=sMs(rr,sS)
	def value=rr.v
	//noinspection GroovyAssignabilityCheck
	return (Boolean)com_cast(r9,value,sBOOLN,srcDt)
}

@CompileStatic
private String scast(Map r9,v){
	if(v==null) return sBLK
	if(matchCast(r9,v,sSTR))return (String)v
	Map rr=dataT(v,sNL)
	String srcDt=sMs(rr,sS)
	def vv=rr.v
	return matchCast(r9,vv,sSTR) ? (String)vv:(String)cast(r9,vv,sSTR,srcDt)
}

@CompileStatic
private static com_cast(Map r9,ival,String dataType,String srcDt){
	def value=ival
	switch(dataType){
		case sDEC:
			switch(srcDt){
				case sSTR:
					String s=((String)value).replaceAll(/[^-\d.-E]/,sBLK)
					if(s.isDouble())
						return s.toDouble()
					if(s.isFloat())
						return s.toDouble()
					if(s.isLong())
						return s.toLong().toDouble()
					if(s.isInteger())
						return s.toInteger().toDouble()
					if(s in trueStrings)
						return d1
					break
				case sBOOLN: return (Double)(value ? d1:dZ)
			}
			Double result
			try{
				result= value as Double
			}catch(ignored){
				result=dZ
			}
			return result
		case sINT:
			switch(srcDt){
				case sSTR:
					String s=((String)value).replaceAll(/[^-\d.-E]/,sBLK)
					if(s.isInteger())
						return s.toInteger()
					if(s.isFloat())
						return Math.floor(s.toDouble()).toInteger()
					if(s in trueStrings)
						return i1
					break
				case sBOOLN: return (Integer)(value ? i1:iZ)
			}
			Integer result
			try{
				result= value as Integer
			}catch(ignored){
				result=iZ
			}
			return result
		case sBOOLN:
			switch(srcDt){
				case sINT:
				case sDEC:
				case sBOOLN:
					return !!value
				case sDEV:
					return listWithSz(value)
			}
			if(value){
				String s= "$value".toLowerCase().trim()
				if(s in falseStrings)return false
				if(s in trueStrings)return true
			}
			return !!value
		case sLONG:
			switch(srcDt){
				case sSTR:
					String s=((String)value).replaceAll(/[^-\d.-E]/,sBLK)
					if(s.isLong())
						return s.toLong()
					if(s.isInteger())
						return s.toLong()
					if(s.isFloat())
						return Math.floor(s.toDouble()).toLong()
					if(s in trueStrings)
						return 1L
					break
				case sBOOLN: return (value ? 1L:lZ)
			}
			Long result
			try{
				result=value as Long
			}catch(ignored){
				result=lZ
			}
			return result
	}
	return value
}

private cast(Map r9,ival,String dataTT,String isrcDT=sNL){
	if(dataTT==sDYN)return ival

	String dataType,srcDt
	dataType=dataTT
	srcDt=isrcDT
	def value
	value=ival

	if(value==null){
		if(dataType==sSTR) return sBLK
		value=sBLK
		srcDt=sSTR
	}
	Map rr=dataT(value,srcDt)
	Boolean isfbd=bIs(rr,sT)
	srcDt=sMs(rr,sS)
	value=rr.v

	switch(dataType){
		case sBOOL: dataType=sBOOLN; break
		case sNUMBER: dataType=sDEC; break
		case sENUM: dataType=sSTR; break
	}
	if(isEric(r9))myDetail r9,"cast src: ${isrcDT} ($ival) as $dataTT --> ${srcDt}${isfbd ? ' bigDF':sBLK} ($value) as $dataType",iN2
	switch(dataType){
		case sSTR:
		case sTEXT:
			switch(srcDt){
				case sBOOLN: return value ? sTRUE:sFALSE
				case sDEC:
					// strip trailing zeroes (e.g. 5.00 to 5 and 5.030 to 5.03)
					return value.toString().replaceFirst(/(?:\.|(\.\d*?))0+$/,'$1')
				case sINT:
				case sLONG: break
				case sTIME: return formatLocalTime(value,'h:mm:ss a z')
				case sDATE: return formatLocalTime(value,'EEE, MMM d yyyy')
				case sDTIME: return formatLocalTime(value)
				case sDEV: return buildDeviceList(r9,value)
			}
			return "$value".toString()
		case sBOOLN:
			//noinspection GroovyAssignabilityCheck
			return (Boolean)com_cast(r9,value,dataType,srcDt)
		case sINT:
			return (Integer)com_cast(r9,value,dataType,srcDt)
		case sLONG:
			return (Long)com_cast(r9,value,dataType,srcDt)
		case sDEC:
			return (Double)com_cast(r9,value,dataType,srcDt)
		case sTIME:
			Long d
			d=srcDt==sSTR ? stringToTime(value):value.toLong()
			if(d<lMSDAY)return d
			Date t1=new Date(d)
			d=Math.round((t1.hours*dSECHR+t1.minutes*d60+t1.seconds)*d1000)
			return d
		case sDATE:
		case sDTIME:
			Long d
			if(srcDt in [sTIME,sLONG,sINT,sDEC]){
				d=value.toLong()
				if(d<lMSDAY) value=gtWCTimeToday(d)
				else value=d
			}
			d=srcDt==sSTR ? stringToTime(value):(Long)value
			if(dataType==sDATE){
				Date t1= new Date(d)
				// take ms off and first guess at midnight (could be earlier/later depending if DST change day
				d=Math.round((Math.floor(d/d1000)*d1000) - ((t1.hours*dSECHR+t1.minutes*d60+t1.seconds)*d1000) )
			}
			return d
		case 'vector3':
			return value instanceof Map && value.x!=null && value.y!=null && value.z!=null ? value:[(sX):iZ,y:iZ,(sZ):iZ]
		case sORIENT:
			return value instanceof Map ? getThreeAxisOrientation(value):value
		case sMS:
		case sS:
		case sM:
		case sH:
		case sD:
		case 'w':
		case sN: // months
		case 'y': // years
			Long t1
			switch(srcDt){
				case sINT:
				case sLONG:
					t1=value.toLong(); break
				default:
					t1=lcast(r9,value)
			}
			switch(dataType){
				case sMS: return t1
				case sS: return Math.round(t1*d1000)
				case sM: return Math.round(t1*dMSMINT)
				case sH: return Math.round(t1*dMSECHR)
				case sD: return Math.round(t1*dMSDAY)
				case 'w': return Math.round(t1*604800000.0D)
				case sN: return Math.round(t1*2592000000.0D)
				case 'y': return Math.round(t1*31536000000.0D)
			}
			break
		case sDEV:
			//device type is an array of device Ids
			if(value instanceof List){
				Boolean a=((List<String>)value).removeAll{ String it -> !it }
				return (List)value
			}
			String v=scast(r9,value)
			if(v!=sNL)return [v]
			return []
	}
	//anything else
	return value
}

@CompileStatic
private Long elapseT(Long t,Long n=wnow()){ return Math.round(d1*n-t) }

private Date utcToLocalDate(dateOrTimeOrString=null){ // cast to Date
	def mdate=dateOrTimeOrString
	Long ldate
	if(!(mdate instanceof Long)){
		if(mdate instanceof String){
			ldate=stringToTime((String)mdate)
		}else if(mdate instanceof Date){
			//get unix time
			ldate=((Date)mdate).getTime()
		}
	} else ldate=(Long)mdate
	if(ldate==null || ldate==lZ){
		ldate=wnow()
	}
	//HE defaults TZ to timezone of hub
	return new Date(ldate)
}

private static Date localDate(){ return new Date() }

@CompileStatic
private Long stringToTime(dateOrTimeOrString){ // convert to dtime
	Long lnull=(Long)null
	Long result
	result=lnull
	Integer cnt
	cnt=iZ
	def a=dateOrTimeOrString
	try{
		if("$a".isNumber()){
			Double aa= a as Double
			Long tt= aa.toLong()
			if(tt<lMSDAY){
				result=gtWCTimeToday(tt)
				cnt=i1
			}else{
// deal with a time in sec (vs. ms)
				Long span=63072000L // Math.round(730*(dMSDAY/d1000)) // 2 years in secs
				Long nowInsecs=Math.round((wnow()/lTHOUS).toDouble())
				if(tt<(nowInsecs+span) && tt>(nowInsecs-span)){
					result=tt*lTHOUS
					cnt=i2
				}
			}
			if(result==lnull){
				result=tt
				cnt=i3
			}
		}
	}catch(ignored){}

	if(result==lnull && dateOrTimeOrString instanceof String){
		String sdate=dateOrTimeOrString
		cnt=i4
		try{
			Date tt1=wtoDateTime(sdate)
			result=tt1.getTime()
		}catch(ignored){ result=lnull }


		// additional ISO 8601 that Hubitat does not parse
		if(result==lnull){
			cnt=i5
			try{
				String tt=sdate
				def regex1=/Z/
				String tt0=tt.replaceAll(regex1," -0000")
				result=(new Date()).parse("yyyy-MM-dd'T'HH:mm z",tt0).getTime()
			}catch(ignored){ result=lnull }
		}

		// next 3 format local time formatting done by cast
		if(result==lnull){
			cnt=14
			try{
				result=(new Date()).parse( 'EEE, MMM d yyyy @ h:mm:ss a z',sdate).getTime()
			}catch(ignored){ result=lnull }
		}

		if(result==lnull){
			cnt=15
			try{
				result=(new Date()).parse( 'EEE, MMM d yyyy',sdate).getTime()
			}catch(ignored){ result=lnull }
		}

		if(result==lnull){
			cnt=16
			try{
				result=(new Date()).parse( 'h:mm:ss a z',sdate).getTime()
			}catch(ignored){ result=lnull }
		}

		if(result==lnull){
			cnt=i6
			try{
				result=(new Date()).parse(sdate)
			}catch(ignored){ result=lnull }
		}

		if(result==lnull){
			cnt=i7
			try{
				//get unix time
				//if(!(sdate =~ /(\s[A-Z]{3}([+\-][0-9]{2}:[0-9]{2}|\s[0-9]{4})?$)/)){
				if(!(sdate =~ /(\s[A-Z]{3}([+\-]\d{2}:\d{2}|\s\d{4})?$)/)){
					Long newDate=(new Date()).parse(sdate+sSPC+formatLocalTime(wnow(),'Z'))
					result=newDate
				}
			}catch(ignored){ result=lnull }
		}

		if(result==lnull){
			cnt=i8
			try{
				TimeZone tz
				tz=mTZ()
				String nsdate,t0
				nsdate=sdate
				if(nsdate =~ /\s[A-Z]{3}$/){ // is not the timezone  strings like CET are not unique.
					try{
						tz=TimeZone.getTimeZone(nsdate[-i3..-i1])
						nsdate=nsdate[iZ..nsdate.size()-i3].trim()
					}catch(ignored){}
				}

				t0=nsdate?.trim() ?: sBLK
				t0=t0.toLowerCase()
				Boolean hasMeridian,hasAM,hasPM
				hasMeridian=false
				hasAM=false
				hasPM=false
				if(t0.endsWith('a.m.')){
					t0=t0.replaceAll('a\\.m\\.','am')
				}
				if(t0.endsWith('p.m.')){
					t0=t0.replaceAll('p\\.m\\.','pm')
				}
				if(t0.endsWith('am')){
					hasMeridian=true
					hasAM=true
				}
				if(t0.endsWith('pm')){
					hasMeridian=true
					hasPM=true
				}
				Long time
				time=lnull
				if(hasMeridian)t0=t0[iZ..-i3].trim()

				try{
					if(t0.length()==i8){
						cnt=i9
						String tt=t0
						time=(new Date()).parse('HH:mm:ss',tt).getTime()
						time=gtWCTimeToday(time)
					}else{
						cnt=i10
						time=wtimeToday(t0,tz).getTime()
					}
				}catch(ignored){}

				if(hasMeridian && time){
					cnt=11
					Date t1=new Date(time)
					Integer hr
					hr=t1.hours
					Integer min=t1.minutes
					Integer sec=t1.seconds
					Boolean twelve=hr>=i12
					if(twelve && hasAM)hr-=i12
					if(!twelve && hasPM)hr+=i12
					String str1,str2
					str1="${hr}".toString()
					str2="${min}".toString()
					if(hr<i10)str1=String.format('%02d',hr)
					if(min<i10)str2=String.format('%02d',min)
					String str=str1+sCLN+str2
					time=wtimeToday(str,tz).getTime()
					if(sec!=iZ)time+=sec*i1000
				}
				result=time ?: lZ
			}catch(ignored){ result=lnull }
		}
	}

	if(result==lnull){
		if(dateOrTimeOrString instanceof Date){
			cnt=i12
			result=((Date)dateOrTimeOrString).getTime()
		}
	}
	if(result==lnull){
		cnt=13
		result=lZ
	}
	return result
}

@CompileStatic
private String formatLocalTime(time,String format='EEE, MMM d yyyy @ h:mm:ss a z'){
	def nTime
	nTime=time
	Double aa
	Boolean fnd
	fnd=false
	try{
		aa= nTime as Double
		fnd=true
	}catch(ignored){}
	if(fnd || time instanceof Long || "${time}".isNumber()){
		Long lt
		lt=fnd ? aa.toLong():"${time}".toLong()
		if(lt<lMSDAY)lt=gtWCTimeToday(lt)
// deal with a time in sec (vs. ms)
		if(lt<Math.round((wnow()/d1000+86400.0D*365.0D).toDouble()) )lt=Math.round((lt*d1000).toDouble())
		nTime=new Date(lt)
	}else if(time instanceof String){
		nTime=new Date(stringToTime((String)time))
	}
	if(!(nTime instanceof Date)){
		return sNL
	}
	Date d=(Date)nTime
	SimpleDateFormat formatter=new SimpleDateFormat(format)
	formatter.setTimeZone(mTZ())
	return formatter.format(d)
}

@CompileStatic
private static Map hexToColor(String hex){
	String mhex
	mhex=hex!=sNL ? hex:sZ6
	if(mhex.startsWith('#'))mhex=mhex.substring(i1)
	if(mhex.size()!=i6)mhex=sZ6
	List<Integer> myHsl=hexToHsl(mhex)
	return [
		h: myHsl[iZ],
		s: myHsl[i1],
		l: myHsl[i2],
		rgb: '#'+mhex
	]
}

@CompileStatic
private static Double _hue2rgb(Double p,Double q,Double ti){
	Double d6=6.0D
	Double t
	t=ti
	if(t<dZ)t+=d1
	if(t>=d1)t-=d1
	if(t<d1/d6)return p+(q-p)*d6*t
	if(t<d1/d2)return q
	if(t<d2/3.0D)return p+(q-p)*(d2/3.0D-t)*d6
	return p
}

@CompileStatic
private static String hslToHex(Double hue,Double saturation,Double level){
	Double h,s,l
	h=hue/360.0D
	s=saturation/100.0D
	l=level/100.0D
// argument checking for user calls
	if(h<dZ)h=dZ
	if(h>d1)h=d1
	if(s<dZ)s=dZ
	if(s>d1)s=d1
	if(l<dZ)l=dZ
	if(l>d1)l=d1

	Double r,g,b
	if(s==dZ){
		r=g=b=l // achromatic
	}else{
		Double q=l<0.5D ? l*(d1+s):l+s-(l*s)
		Double p=d2*l-q
		r=_hue2rgb(p,q,h+d1/3.0D)
		g=_hue2rgb(p,q,h)
		b=_hue2rgb(p,q,h-d1/3.0D)
	}
	return sprintf('#%02X%02X%02X',Math.round(r*255.0D),Math.round(g*255.0D),Math.round(b*255.0D))
}
/*
private static Map<String,Integer> hexToRgb(String hex){
	hex=hex!=sNL ? hex:sZ6
	if(hex.startsWith('#'))hex=hex.substring(i1)
	if(hex.size()!=i6)hex=sZ6
	Integer r1=Integer.parseInt(hex.substring(0,2),16)
	Integer g1=Integer.parseInt(hex.substring(2,4),16)
	Integer b1=Integer.parseInt(hex.substring(4,6),16)
	return [r:r1,g:g1,b:b1]
}*/

@CompileStatic
private static List<Integer> hexToHsl(String hex){
	String mhex
	mhex=hex!=sNL ? hex:sZ6
	if(mhex.startsWith('#'))mhex=mhex.substring(i1)
	if(mhex.size()!=i6)mhex=sZ6
	Double r=Integer.parseInt(mhex.substring(iZ,i2),16)/255.0D
	Double g=Integer.parseInt(mhex.substring(i2,i4),16)/255.0D
	Double b=Integer.parseInt(mhex.substring(i4,i6),16)/255.0D

	Double max=Math.max(Math.max(r,g),b)
	Double min=Math.min(Math.min(r,g),b)
	Double h,s
	h=dZ
	s=dZ
	Double l=(max+min)/2.0D

	if(max==min){
		h=s=dZ // achromatic
	}else{
		Double d=max-min
		s= (l>0.5D ? d/(d2-max-min):d/(max+min)).toDouble()
		switch(max){
			case r: h= ((g-b)/d+(g<b ? 6.0D:dZ)).toDouble(); break
			case g: h= ((b-r)/d+d2).toDouble(); break
			case b: h= ((r-g)/d+4.0D).toDouble(); break
		}
		h /= 6.0D
	}
	return [Math.round(h*360.0D).toInteger(),Math.round(s*d100).toInteger(),Math.round(l*d100).toInteger()]
}

/**							**/
/** DEBUG FUNCTIONS					**/
/**							**/

private void myDetail(Map r9,String msg,Integer shift=iN1){ Map a=log(msg,r9,shift,null,sWARN,true,false) }

@CompileStatic
private Map log(message,Map r9,Integer shift=iN2,Exception err=null,String cmd=sNL,Boolean force=false,Boolean svLog=true){
	if(cmd==sTIMER){
		return [(sM):message.toString(),(sT):wnow(),(sS):shift,(sE):err]
	}
	String myMsg
	myMsg=sNL
	Exception merr
	merr=err
	Integer mshift
	mshift=shift
	if(message instanceof Map){
		mshift=iMs(message,sS)
		merr=(Exception)message.e
		myMsg=sMs(message,sM)+" (${elapseT(lMt(message))}ms)".toString()
	}else myMsg=message.toString()
	String mcmd=cmd!=sNL ? cmd:sDBG

	if(r9 && lMs(r9,sTMSTMP)){
		//shift is
		// 0 initialize level,level set to 1
		// 1 start of routine,level up
		// -1 end of routine,level down
		// anything else: nothing happens
//		Integer maxLevel=4
		Integer level
		level=r9[sDBGLVL]!=null ? iMs(r9,sDBGLVL):iZ
		String ss='╔'
		String sb='║'
		String se='╚'
		String prefix,prefix2
		prefix=sb
		prefix2=sb
//		String pad=sBLK //"░"
		switch(mshift){
			case iZ:
				level=iZ
			case i1:
				level+=i1
				prefix=se
				prefix2=ss
//				pad="═"
				break
			case iN1:
				level-=i1
//				pad='═'
				prefix=ss
				prefix2=se
				break
		}
		if(level>iZ){
			prefix=prefix.padLeft(level+(mshift==iN1 ? i1:iZ),sb)
			prefix2=prefix2.padLeft(level+(mshift==iN1 ? i1:iZ),sb)
		}
		r9[sDBGLVL]=level

		Boolean hasErr=(merr!=null && !!merr)
		if(svLog && r9[sLOGS] instanceof List){
			myMsg=myMsg.replaceAll(/(\r\n|\r|\n|\\r\\n|\\r|\\n)+/,"\r")
			if(myMsg.size()>1024){
				myMsg=myMsg[iZ..1023]+'...[TRUNCATED]'
			}
			List<String> msgs=!hasErr ? myMsg.tokenize("\r"):[myMsg]
			for(String msg in msgs){
				Boolean a=((List)r9[sLOGS]).push([(sO):elapseT(lMs(r9,sTMSTMP)),(sP):prefix2,(sM):msg+(hasErr ? " $merr".toString():sBLK),(sC):mcmd])
			}
		}
		String myPad=sSPC
		if(hasErr) myMsg+="$merr".toString()
		if((mcmd in [sERROR,sWARN]) || hasErr || force || !svLog || bIs(r9,sLOGHE) || isEric(r9)) doLog(mcmd, myPad+prefix+sSPC+myMsg)
	}else doLog(mcmd,myMsg)
	return [:]
}

void doLog(String mcmd, String msg){
	String clr
	switch(mcmd){
		case 'info':
			clr= '#0299b1'
			break
		case sTRC:
			clr= sCLRGRY
			break
		case sDBG:
			clr= 'purple'
			break
		case sWARN:
			clr= sCLRORG
			break
		case sERROR:
		default:
			clr= sCLRRED
	}
	log."$mcmd" span(msg,clr)
}

private void info(message,Map r9,Integer shift=iN2,Exception err=null){ Map a=log(message,r9,shift,err,'info')}
private void trace(message,Map r9,Integer shift=iN2,Exception err=null){ Map a=log(message,r9,shift,err,sTRC)}
private void debug(message,Map r9,Integer shift=iN2,Exception err=null){ Map a=log(message,r9,shift,err,sDBG)}
private void warn(message,Map r9,Integer shift=iN2,Exception err=null){ Map a=log(message,r9,shift,err,sWARN)}
private void error(message,Map r9,Integer shift=iN2,Exception err=null){
	String aa,bb
	aa=sNL
	bb=sNL
	try{
		if(err){
			aa=getExceptionMessageWithLine(err)
			bb=getStackTrace(err)
		}
		Map a=log(message,r9,shift,err,sERROR)
	}catch(ignored){}
	if(aa||bb)
		doLog(sERROR, "webCoRE exception: "+aa+" \n"+bb)
}

private Map timer(String message,Map r9,Integer shift=iN2,err=null){ log(message,r9,shift,err,sTIMER)}

@Field static final String sCLR4D9	= '#2784D9'
@Field static final String sCLRRED	= 'red'
@Field static final String sCLRRED2	= '#cc2d3b'
@Field static final String sCLRGRY	= 'gray'
@Field static final String sCLRGRN	= 'green'
@Field static final String sCLRGRN2	= '#43d843'
@Field static final String sCLRORG	= 'orange'
@Field static final String sLINEBR	= '<br>'
@CompileStatic
static String span(String str,String clr=sNL,String sz=sNL,Boolean bld=false,Boolean br=false){
	return str ? "<span ${(clr || sz || bld) ? "style='${clr ? "color: ${clr};":sBLK}${sz ? "font-size: ${sz};":sBLK}${bld ? "font-weight: bold;":sBLK}'":sBLK}>${str}</span>${br ? sLINEBR:sBLK}": sBLK
}

@CompileStatic
private void tracePoint(Map r9,String oId,Long duration,value){
	Map<String,Map> pt=(Map<String,Map>)((Map)r9[sTRC])?.points
	if(oId!=sNL && pt!=null){
		pt[oId]=[(sO):elapseT(lMt((Map)r9[sTRC]))-duration,(sD):duration,(sV):value]
	}else error "Invalid object ID $oId for trace point",r9
}

@Field static final List<String> weekDaysFLD=[
	'Sunday',
	'Monday',
	'Tuesday',
	'Wednesday',
	'Thursday',
	'Friday',
	'Saturday'
]

@Field static final List<String> yearMonthsFLD=[
	'',
	'January',
	'February',
	'March',
	'April',
	'May',
	'June',
	'July',
	'August',
	'September',
	'October',
	'November',
	'December'
]

@Field static Map svSunTFLD

/* wrappers */
private Map initSunriseAndSunset(Map r9){
	Map t0
	t0=svSunTFLD
	Long t
	t=wnow()
	if(t0!=null){
		if(t<lMs(t0,'nextM')){
			r9.sunTimes=[:]+t0
		}else{ t0=null; svSunTFLD=null; mb() }
	}
	if(t0==null){
		Map sunTimes=app.getSunriseAndSunset()
		if(sunTimes.sunrise==null){
			warn 'Actual sunrise and sunset times are unavailable; please reset the location for your hub',r9
			Long t1=getMidnightTime()
			sunTimes.sunrise=new Date(Math.round(t1+7.0D*dMSECHR))
			sunTimes.sunset=new Date(Math.round(t1+19.0D*dMSECHR))
			t=lZ
		}
		Long a=((Date)sunTimes.sunrise).getTime()
		Long b=((Date)sunTimes.sunset).getTime()
		Long nmnght=getNextMidnightTime()
		Long c,d,a1,b1
		d=lZ
		Boolean good
		good=true
		try{
			a1=((Date)todaysSunrise).getTime() // requires FW 2.2.3.132 or later
			b1=((Date)todaysSunset).getTime()
			c=((Date)tomorrowsSunrise).getTime()
			d=((Date)tomorrowsSunset).getTime()
		}catch(ignored){
			good=false
			Boolean agtr=a>nmnght
			Boolean bgtr=b>nmnght
			Long srSkew=getSkew(a,'Sunrise')
			Long ssSkew=getSkew(b,'Sunset')
			a1=agtr ? Math.round(a-dMSDAY-srSkew):a
			b1=bgtr ? Math.round(b-dMSDAY-ssSkew):b
			c=agtr ? a:Math.round(a+dMSDAY+srSkew)
			d=bgtr ? b:Math.round(b+dMSDAY+ssSkew)
		}
		Long c1=Math.round(c-dMSDAY)
		Long db1=Math.round(d-dMSDAY)
		t0=[
			sunrise: a,
			sunset:b,
			todayssunrise: a1,
			calcsunrise: (a>c1 ? a:c1),
			todayssunset:b1,
			calcsunset:(b>db1 ? b:db1),
			tomorrowssunrise: c,
			tomorrowssunset:d,
			updated: t,
			good: good,
			nextM: nmnght
		]
		if(!good) warn 'Please update HE firmware to improve time handling',r9
		r9.sunTimes=t0
		if(t!=lZ){
			svSunTFLD=t0
			mb()
			if(eric())debug "updating global sunrise ${t0}",null
		}
	}
	return (Map)r9.sunTimes
}

private Long getSunriseTime(Map r9){
	Map st=initSunriseAndSunset(r9)
	return lMs(st,'sunrise')
}

private Long getSunsetTime(Map r9){
	Map st=initSunriseAndSunset(r9)
	return lMs(st,'sunset')
}

private Long getNextSunriseTime(Map r9){
	Map st=initSunriseAndSunset(r9)
	return lMs(st,'tomorrowssunrise')
}

private Long getNextSunsetTime(Map r9){
	Map st=initSunriseAndSunset(r9)
	return lMs(st,'tomorrowssunset')
}

// This is trying to ensure to not fire sunsets or sunrises twice in same day by ensuring we fire a bit later than actual sunrise or sunset
Long getSkew(Long t4,String ttyp){
	Date t1=new Date(t4)
	Integer curMon
	curMon=t1.month
	Double lat=gtLlat().toDouble()
	curMon=lat>dZ ? curMon:((curMon+i6)%i12) // normalize for southern hemisphere
	Integer day=t1.date

	Integer addr
	Boolean shorteningDays=(curMon==i5 && day>i20) || (curMon>i5 && !(curMon==11 && day>i20))

	if( (shorteningDays && ttyp=='Sunset') || (!shorteningDays && ttyp=='Sunrise') ) addr=i1000 // minimize skew when sunrise or sunset moving earlier in day
	else{
		Integer t2=Math.abs(lat).toInteger()
		Integer t3=curMon%i6
		Integer t5=(Integer)Math.round(t3*(365.0D/12.0D)+day).toInteger() // days into period
		addr=Math.round((t5>37 && t5<(182-37) ? t2*2.8D:t2*1.9D)*d1000).toInteger()
	}
	return addr.toLong()
}

private Long getMidnightTime(){ return wtimeToday('00:00',mTZ()).getTime() }
private Long getNextMidnightTime(){ return wtimeTodayAfter('23:59','00:00',mTZ()).getTime() }
private Long getNoonTime(){ return wtimeToday('12:00',mTZ()).getTime() }
private Long getNextNoonTime(){ return wtimeTodayAfter('23:59','12:00',mTZ()).getTime() }

@CompileStatic
private void getLocalVariables(Map r9,Map aS){
	r9.localVars=[:]
	String t,n
	def v
	Map values=(Map)aS[sVARS]
	List<Map>l=(List<Map>)((Map)r9[sPIS]).v
	if(!l)return
	for(Map var in l){
		t=sMt(var)
		n=sMs(var,sN)
		v= values ? values[n]:null
		Map variable=[
			(sT):t,
			(sV):var.v!=null ? var.v: (t.endsWith(sRB) ? (v instanceof Map  || v instanceof List ? v:[:]) : (matchCast(r9,v,t) ? v:cast(r9,v,t))),
			(sF): !!var.v //f means fixed value; do not save to state
		]
		if(var.v!=null && sMa(var)==sS && !t.endsWith(sRB)){ // variable.a sS -> constant  sD-> dynamic
			variable.v=evaluateExpression(r9,(Map)evaluateOperand(r9,null,(Map)var.v),t).v
			variable.a=sS
		}
		((Map)r9.localVars)[n]=variable
	}
}

/** UI will not display anything that starts with $current or $previous; variables without d:true and non-null value will not display */
@CompileStatic
private Map<String,LinkedHashMap> getSystemVariablesAndValues(Map r9){
	LinkedHashMap<String,LinkedHashMap> result=getSystemVariables()
	LinkedHashMap<String,LinkedHashMap> c=(LinkedHashMap<String,LinkedHashMap>)r9[sCACHEP]
	def res
	for(variable in result){
		String k=(String)variable.key
		// special handle $fuel $file
		res=null
		if(/*variable.value.d!=null &&*/ (Boolean)variable.value.d) res=gtSysVarVal(r9,k)
		if(res==null && c[k]!=null)res=c[k].v
		variable.value.v=res
	}
	return result.sort{ (String)it.key }
}

/** define system variables, d:true also means get the variable value dynamically via gtSysVarVal */
@CompileStatic
private static LinkedHashMap<String,LinkedHashMap> getSystemVariables(){
	LinkedHashMap dynT=[(sT):sDYN,(sD):true]
	LinkedHashMap strT=[(sT):sSTR,(sD):true]
	LinkedHashMap strN=[(sT):sSTR,(sV):null]
	LinkedHashMap intT=[(sT):sINT,(sD):true]
	LinkedHashMap boolT=[(sT):sBOOLN,(sD):true]
	LinkedHashMap dtimeT=[(sT):sDTIME,(sD):true]
	LinkedHashMap devT=[(sT):sDEV,(sD):true]
	LinkedHashMap t=[:] as LinkedHashMap
	String shsm=sDLR+sHSMSTS
	return [
		'$locationMode':t+strT,
		(sDLLRDEVICE):rtnMap(sDEV,null),
		(sDLLRDEVS):rtnMap(sDEV,null),
		(sDLLRINDX):rtnMapD(null),
		'$location':rtnMap(sDEV,null),
		'$now':t+dtimeT,
		'$localNow':t+dtimeT,
		'$utc':t+dtimeT,

		(sDARGS):t+dynT,
		(sDJSON):t+dynT,
		'$places':t+dynT,
		'$file':t+dynT,
		(sDRESP):t+dynT,
		'$weather':t+dynT,
		'$incidents':t+dynT,
		'$hsmTripped':t+boolT,
		(shsm):t+strT,

		'$fuel':t+dynT,
		(sHTTPCNTN):t+strT,
		(sHTTPCODE):t+intT,
		(sHTTPOK):t+boolT,
		(sIFTTTCODE):t+intT,
		(sIFTTTOK):t+boolT,

		(sCURATTR):t+strT,
		(sCURDESC):t+strT,
		(sCURDATE):t+dtimeT,
		(sCURDELAY):t+intT,
		(sCURDEV):t+devT,
		(sCURDEVINDX):t+intT,
		(sCURPHYS):t+boolT,
		(sCURVALUE):t+dynT,
		(sCURUNIT):t+strT,
//		'$currentState':t+strN,
//		'$currentStateDuration':t+strN,
//		'$currentStateSince':rtnMap(sDTIME,null),
//		'$nextScheduledTime':rtnMap(sDTIME,null),
		'$name':t+strT,
		'$state':t+strT,
		'$hour':t+intT,
		'$hour24':t+intT,
		'$minute':t+intT,
		'$second':t+intT,
		'$zipCode':t+strT,
		'$latitude':t+strT,
		'$longitude':t+strT,
		'$meridian':t+strT,
		'$meridianWithDots':t+strT,
		'$day':t+intT,
		'$dayOfWeek':t+intT,
		'$dayOfWeekName':t+strT,
		'$month':t+intT,
		'$monthName':t+strT,
		'$year':t+intT,
		'$midnight':t+dtimeT,
		'$noon':t+dtimeT,
		'$sunrise':t+dtimeT,
		'$sunset':t+dtimeT,
		'$nextMidnight':t+dtimeT,
		'$nextNoon':t+dtimeT,
		'$nextSunrise':t+dtimeT,
		'$nextSunset':t+dtimeT,
		'$time':t+strT,
		'$time24':t+strT,
		'$mediaId':t+strT,
		'$mediaUrl':t+strT,
		'$mediaType':t+strT,
//		'$mediaSize':t+intT,
		(sPEVATTR):t+strT,
		(sPEVDESC):t+strT,
		(sPEVDATE):t+dtimeT,
		(sPEVDELAY):t+intT,
		(sPEVDEV):t+devT,
		(sPEVDEVINDX):t+intT,
		(sPEVPHYS):t+boolT,
		(sPEVVALUE):t+dynT,
		(sPEVUNIT):t+strT,
//		'$previousState':t+strN,
//		'$previousStateDuration':t+strN,
//		'$previousStateSince':rtnMap(sDTIME,null),
		'$random':[(sT):sDEC,(sD):true],
		'$randomColor':t+strT,
		'$randomColorName':t+strT,
		'$randomLevel':t+intT,
		'$randomSaturation':t+intT,
		'$randomHue':t+intT,
		'$temperatureScale':t+strT,
		'$tzName':t+strT,
		'$tzId':t+strT,
		'$tzOffset':t+intT,
		'$version':t+strT,
		'$versionH':t+strT,
		'$nfl':t+dynT
	] as LinkedHashMap<String,LinkedHashMap>
}

@CompileStatic
private static rtnStr(v){
	if(v!=null && v!=[:] && v!=[] && v!=sBLK)return "${v}".toString()
	return null
}

@CompileStatic
private gtSysVarVal(Map r9,String name){
	String shsm=sDLR+sHSMSTS
	Map<String,Map> sv=(Map<String,Map>)r9[sSYSVARS]
	Map ce=(Map)r9[sCUREVT]
	Map pe=(Map)r9.previousEvent
	switch(name){
		case '$locationMode':return gtLMode()
		case '$localNow':
		case '$now':
		case '$utc': return wnow()
		case sDARGS:
		case sDLLRDEVICE:
		case sDLLRDEVS:
		case sDLLRINDX:
		case sHTTPCNTN:
		case sHTTPCODE:
		case sHTTPOK:
		case sIFTTTCODE:
		case sIFTTTOK: return sv[name].v
		case '$file': String pNm=sMs(r9,snId); return readDataFLD[pNm]
		case '$fuel': String pNm=sMs(r9,snId); return fuelDataFLD[pNm]
		case sCURATTR: return rtnStr(ce?.name)
		case sCURDESC: return rtnStr(ce?.descriptionText)
		case sCURDATE: return ce?.t
		case sCURDELAY: return ce?.delay
		case sCURDEV: return ce?.device ? [sMs(ce,sDEV)]:[]
		case sCURDEVINDX: return ce?.index
		case sCURPHYS: return ce?.physical
		case sCURVALUE: return ce?.value
		case sCURUNIT: return ce?.unit
		case sDJSON: return rtnStr(r9.json)
		case '$places': return rtnStr(((Map)r9.settings)?.places)
		case sDRESP: return rtnStr(r9.response)
		case '$weather': return rtnStr(r9.weather)
		case '$nfl': return rtnStr(r9.nfl)
		case '$incidents': return rtnStr(r9.incidents)
		case '$hsmTripped': return listWithSz(r9.incidents)
		case (shsm): return gtLhsmStatus()
		case '$mediaId': return r9.mediaId
		case '$mediaUrl': return sMs(r9,'mediaUrl')
		case '$mediaType': return sMs(r9,'mediaType')
//		case '$mediaSize': return (r9.mediaData!=null ? (Integer)r9.mediaData.size():null)
		case sPEVATTR: return rtnStr(pe?.name)
		case sPEVDESC: return rtnStr(pe?.descriptionText)
		case sPEVDATE: return pe?.t
		case sPEVDELAY: return pe?.delay
		case sPEVDEV: return pe?.device ? [sMs(pe,sDEV)]:[]
		case sPEVDEVINDX: return pe?.index
		case sPEVPHYS: return pe?.physical
		case sPEVVALUE: return pe?.value
		case sPEVUNIT: return pe?.unit
		case '$name': return gtAppN()
		case '$state': return (String)((Map)r9[sST])?.new
		case '$tzName': return mTZ().displayName
		case '$tzId': return mTZ().getID()
		case '$tzOffset': return mTZ().rawOffset
		case '$version': return sVER
		case '$versionH': return sHVER
		case '$hour': Integer h=localDate().hours; return (h==iZ ? i12:(h>i12 ? h-i12:h))
		case '$hour24': return localDate().hours
		case '$minute': return localDate().minutes
		case '$second': return localDate().seconds
		case '$zipCode': return gtLzip()
		case '$latitude': return gtLlat()
		case '$longitude': return gtLlong()
		case '$meridian': Integer h=localDate().hours; return (h<i12 ? 'AM':'PM')
		case '$meridianWithDots': Integer h=localDate().hours; return (h<i12 ? 'A.M.':'P.M.')
		case '$day': return localDate().date
		case '$dayOfWeek': return localDate().day
		case '$dayOfWeekName': return weekDaysFLD[localDate().day]
		case '$month': return localDate().month+i1
		case '$monthName': return yearMonthsFLD[localDate().month+i1]
		case '$year': return localDate().year+1900
		case '$midnight': return getMidnightTime()
		case '$noon': return getNoonTime()
		case '$sunrise': return getSunriseTime(r9)
		case '$sunset': return getSunsetTime(r9)
		case '$nextMidnight': return getNextMidnightTime()
		case '$nextNoon': return getNextNoonTime()
		case '$nextSunrise': return getNextSunriseTime(r9)
		case '$nextSunset': return getNextSunsetTime(r9)
		case '$time': Date t=localDate(); Integer h=t.hours; Integer m=t.minutes; return ((h==iZ ? i12:(h>i12 ? h-i12:h))+sCLN+(m<i10 ? "0$m":"$m")+sSPC+(h<i12 ? 'A.M.':'P.M.')).toString()
		case '$time24': Date t=localDate(); Integer h=t.hours; Integer m=t.minutes; return (h+sCLN+(m<i10 ? "0$m":"$m")).toString()
		case '$random':
			def tresult=getRandomValue(r9,name)
			Double result
			if(tresult!=null)result=(Double)tresult
			else{
				result=Math.random()
				setRandomValue(r9,name,result)
			}
			return result
		case '$randomColor':
			def tresult=getRandomValue(r9,name)
			String result
			if(tresult!=null)result=(String)tresult
			else{
				result=sMs(getRandomColor(),'rgb')
				setRandomValue(r9,name,result)
			}
			return result
		case '$randomColorName':
			def tresult=getRandomValue(r9,name)
			String result
			if(tresult!=null)result=(String)tresult
			else{
				result=sMs(getRandomColor(),sNM)
				setRandomValue(r9,name,result)
			}
			return result
		case '$randomLevel':
			def tresult=getRandomValue(r9,name)
			Integer result
			if(tresult!=null)result=(Integer)tresult
			else{
				result=Math.round(d100*Math.random()).toInteger()
				setRandomValue(r9,name,result)
			}
			return result
		case '$randomSaturation':
			def tresult=getRandomValue(r9,name)
			Integer result
			if(tresult!=null)result=(Integer)tresult
			else{
				result=Math.round(i50+i50*Math.random()).toInteger()
				setRandomValue(r9,name,result)
			}
			return result
		case '$randomHue':
			def tresult=getRandomValue(r9,name)
			Integer result
			if(tresult!=null)result=(Integer)tresult
			else{
				result=Math.round(360.0D*Math.random()).toInteger()
				setRandomValue(r9,name,result)
			}
			return result
		case '$temperatureScale':return gtLtScale()
	}
	return null
}

@Field static List<String> ListCache=[]
private static List<String> fill_CACH(){ return [sDARGS,sHTTPCNTN,sHTTPCODE,sHTTPOK,sIFTTTCODE,sIFTTTOK] }

@CompileStatic
private static void stSysVarVal(Map r9,String nm,value,Boolean cachePersist=true){
	Map<String,Map>sysV=(Map<String,Map>)r9[sSYSVARS]
	Map var=sysV[nm]
	if(var==null)return
	if(!ListCache) ListCache=fill_CACH()
	if(cachePersist && nm in ListCache){
		LinkedHashMap<String,Map> c=(LinkedHashMap<String,Map>)r9[sCACHEP]
		if(value!=null) c[nm]= var+[(sV):value] as LinkedHashMap
		else c.remove(nm)
		r9[sCACHEP]=c
	}
	//if(var.d!=null)return
	((Map)((Map)r9[sSYSVARS])[nm]).v=value
}

private static getRandomValue(Map r9,String nm){ return ((Map<String,Map>)r9.temp).randoms[nm] }
private static void setRandomValue(Map r9,String nm,value){ ((Map<String,Map>)r9.temp).randoms[nm]=value }
private static void resetRandomValues(Map r9){ r9.temp=[randoms:[:]] }

private static Map getColorByName(String nm){
	return getColors().find{ Map it -> sMs(it,sNM)==nm }
}

private static Map getRandomColor(){
	Integer random=Math.round(Math.random()*(getColors().size()-i1)).toInteger()
	return getColors()[random]
}

private static String sectionTitleStr(String title)	{ return '<h3>'+title+'</h3>' }
private static String inputTitleStr(String title)	{ return '<u>'+title+'</u>' }
//private static String pageTitleStr(String title)	{ return '<h1>'+title+'</h1>' }
//private static String paraTitleStr(String title)	{ return '<b>'+title+'</b>' }

@Field static final String sGITP='https://raw.githubusercontent.com/ady624/webCoRE/master/resources/icons/'
private static String gimg(String imgSrc){ return sGITP+imgSrc }

@CompileStatic
private static String imgTitle(String imgSrc,String titleStr,String color=sNL,Integer imgWidth=30,Integer imgHeight=iZ){
	String imgStyle
	imgStyle=sBLK
	String myImgSrc=gimg(imgSrc)
	imgStyle+=imgWidth>iZ ? 'width: '+imgWidth.toString()+'px !important;':sBLK
	imgStyle+=imgHeight>iZ ? imgWidth!=iZ ? sSPC:sBLK+'height:'+imgHeight.toString()+'px !important;':sBLK
	if(color!=sNL) return """<div style="color: ${color}; font-weight:bold;"><img style="${imgStyle}" src="${myImgSrc}"> ${titleStr}</img></div>""".toString()
	else return """<img style="${imgStyle}" src="${myImgSrc}"> ${titleStr}</img>""".toString()
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

/** Returns true if string is encoded device hash  */
@CompileStatic
private static Boolean isWcDev(String dev){ return (dev && dev.size()==34 && dev.startsWith(sCLN) && dev.endsWith(sCLN)) }

/** Converts v to either webCoRE or hubitat hub variable types and values */
@SuppressWarnings('GroovyAssignabilityCheck')
@CompileStatic
Map fixHeGType(Boolean toHubV,String typ,v){
	Map ret
	ret=[:]
	def myv
	myv=v
	String T='T'
	String s9s='9999'
	if(toHubV){ // from webcore(9 types) -> global(5 types + 3 overloads + sDYN becomes sSTR)
		switch(typ){
			case sINT:
				ret=[(sINT):v]
				break
			case sBOOLN:
				ret=[(sBOOLN):v]
				break
			case sDEC:
				ret=['bigdecimal':v]
				break
			case sDEV:
				// HE is a List<String> -> String of words separated by a space (can split())
				List<String> dL= v instanceof List ? (List<String>)v: ((v ? [v]:[]) as List<String>)
				String res=sNL
				Boolean ok=true
				for(String d in dL){
					if(ok && isWcDev(d)){
						res=res ? res+sSPC+d:d
					}else ok=false
				}
				if(ok){
					ret=[(sSTR):res]
					break
				}
			case sDYN:
			case sSTR:
				ret=[(sSTR):v]
				break
			case sTIME:
				Double aa
				Boolean fnd
				fnd=false
				try{
					aa= v as Double
					fnd=true
				}catch(ignored){}
				Long aaa= fnd ? aa.toLong():("$v".isNumber() ? v as Long: null)
				if(aaa!=null){
					if(aaa<lMSDAY && aaa>=lZ){
						Long t0=getMidnightTime()
						Long a1=t0+aaa
						TimeZone tz=mTZ()
						myv=a1+(tz.getOffset(t0)-tz.getOffset(a1))
					}else{
						Date t1=new Date(aaa)
						Long t2=Math.round((t1.hours*dSECHR+t1.minutes*d60+t1.seconds)*d1000)
						myv=t2
					}
				}else if(eric()) warn "trying to convert nonnumber time",null
			case sDATE:
			case sDTIME: //@@
				Date nTime=new Date((Long)myv)
				String format="yyyy-MM-dd'T'HH:mm:ss.sssXX"
				SimpleDateFormat formatter=new SimpleDateFormat(format)
				formatter.setTimeZone(mTZ())
				String tt=formatter.format(nTime)
				String[] t1=tt.split(T)

				if(typ==sDATE){
					// comes in long format should be string -> 2021-10-13T99:99:99:999-9999
					String t2=t1[iZ]+'T99:99:99:999-9999'
					ret=[(sDTIME):t2]
					break
				}
				if(typ==sTIME){
					//comes in long format should be string -> 9999-99-99T14:25:09.009-0700
					String t2='9999-99-99T'+t1[i1]
					ret=[(sDTIME):t2]
					break
				}
				//	if(typ==sDTIME){
				// long needs to be string -> 2021-10-13T14:25:09.009-0700
				ret=[(sDTIME):tt]
				break
				//	}
		}
	}else{ // from global(5 types + 3 overloads ) -> to webcore(8 (cannot restore sDYN)
		switch(typ){
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
				Boolean ok=true
				String[] t1=((String)v).split(sSPC)
				for(String t in t1){
					// sDEV is a string in hub need to detect if it is really devices :xxxxx:
					if(ok && isWcDev(t))dvL.push(t)
					else ok=false
				}
				if(ok) ret=[(sDEV):dvL]
				else ret=[(sSTR):v]
				break
				// cannot really return a string to dynamic type here res=sDYN
			case sDTIME: // global times: everything is datetime -> these come in string and needs to be a long of the type
				String iD,mtyp,res
				iD=v
				mtyp=sDTIME
				res=v
				if(iD.endsWith(s9s) || iD.startsWith(s9s)){
					Date nTime=new Date()
					String format="yyyy-MM-dd'T'HH:mm:ss.sssXX"
					SimpleDateFormat formatter=new SimpleDateFormat(format)
					formatter.setTimeZone(mTZ())
					String tt=formatter.format(nTime)
					String[] mystart=tt.split(T)

					String[] t1=iD.split(T)

					if(iD.endsWith(s9s)){
						mtyp=sDATE
						res=t1[iZ]+T+mystart[i1]
					}else if(iD.startsWith(s9s)){
						mtyp=sTIME
						// we are ignoring the -0000 offset at end and using our current one
						String withOutEnd=t1[i1][iZ..-i6]
						String myend=tt[-i5..-i1]
						res=mystart[iZ]+T+withOutEnd+myend
					}
				}
				Date tt1=wtoDateTime(res)
				Long lres
				lres=tt1.getTime()
				if(mtyp==sTIME){
					Long m2=Math.round((tt1.hours*dSECHR+tt1.minutes*d60+tt1.seconds)*d1000)
					lres=m2
				}
				ret=[(mtyp):lres]
		}
	}
	return ret
}

@CompileStatic
private static String md5(String md5){
	MessageDigest md=MessageDigest.getInstance('MD5')
	byte[] array=md.digest(md5.getBytes())
	String result
	result=sBLK
	Integer l=array.size()
	for(Integer i=iZ; i<l;++i){
		result+=Integer.toHexString((array[i] & 0xFF)| 0x100).substring(i1,i3)
	}
	return result
}

@Field volatile static Map<String,Map<String,String>> theHashMapVFLD=[:]

static void clearHashMap(String wName){
	theHashMapVFLD[wName]=[:] as Map<String,String>
	theHashMapVFLD=theHashMapVFLD
}

@CompileStatic
private String hashPID(id){
	LinkedHashMap pC=getParentCache()
	if(bIs(pC,'newAcctSid'))return hashId3(sMs(pC,sLOCID)+id.toString())
	return hashId3(id)
}

@Field static final String sCR='core.'

@CompileStatic
private String hashId3(id){
	//String wName=parent ? sPAppId():sAppId()
	return hashId2(id,sPAppId())
}

@CompileStatic
private static String hashId(Map r9,id){ return hashId2(id,sMs(r9,spId)) }

@CompileStatic
private static String hashId2(id,String wName){
	String result
	String tId=id.toString()
	if(theHashMapVFLD[wName]==null) clearHashMap(wName)
	result=theHashMapVFLD[wName][tId]
	if(result==sNL){
		result=sCLN+md5(sCR+tId)+sCLN
		theHashMapVFLD[wName][tId]=result
		theHashMapVFLD=theHashMapVFLD
		mb()
	}
	return result
}

@Field static Semaphore theMBLockFLD=new Semaphore(0)

// Memory Barrier
@CompileStatic
static void mb(String meth=sNL){
	if(theMBLockFLD.tryAcquire()){
		theMBLockFLD.release()
	}
}


/* wrappers */
@Field static Map<String,Map> theAttributesFLD

//uses i,p,t,m
private static Map<String,Map> Attributes(){ return theAttributesFLD }

private Map<String,Map> AttributesF(){
	theAttributesFLD=(Map)parent.getChildAttributes()
	mb()
	return theAttributesFLD
}

@Field static Map<String,Map> theComparisonsFLD

//uses p,t
private static Map<String,Map<String,Map>> Comparisons(){ return theComparisonsFLD }

private Map<String,Map<String,Map>> ComparisonsF(){
	theComparisonsFLD=(Map)parent.getChildComparisons()
	mb()
	return theComparisonsFLD
}

@Field static Map<String,Map> theVirtCommandsFLD

//uses o (override phys command),a (aggregate commands)
private static Map<String,Map> VirtualCommands(){ return theVirtCommandsFLD }

private Map<String,Map> VirtualCommandsF(){
	Map result=theVirtCommandsFLD
	theVirtCommandsFLD=(Map)parent.getChildVirtCommands()
	mb()
	return theVirtCommandsFLD
}

//uses c and r
// the command r: is replaced with command c.
// If the VirtualCommand c exists and has o: true we will use that virtual command; otherwise it will be replaced with a physical command
@Field static final Map<String,Map> CommandsOverrides=[
		push:[c:"push",	s:null,r:"pushMomentary"],
		flash:[c:"flash",	s:null,r:"flashNative"] //flash native command conflicts with flash emulated command. Also needs "o" option on command described later
]

@Field static Map<String,Map> theVirtDevicesFLD

//uses ac,o
private Map<String,Map> VirtualDevices(){
	Map result=theVirtDevicesFLD
	if(result==null){
		theVirtDevicesFLD=(Map)parent.getChildVirtDevices()
		mb()
	}
	return theVirtDevicesFLD
}

@Field static Map<String,Map> thePhysCommandsFLD

//uses a,v
private static Map<String,Map> PhysicalCommands(){ return thePhysCommandsFLD }

private Map<String,Map> PhysicalCommandsF(){
	thePhysCommandsFLD=(Map)parent.getChildCommands()
	mb()
	return thePhysCommandsFLD
}

@Field static List<Map> theColorsFLD

private static List<Map> getColors(){ return theColorsFLD }

private List<Map> getColorsF(){
	theColorsFLD=(List)parent.getColors()
	mb()
	return theColorsFLD
}

private Map wgtPdata(){ return (Map)parent.gtPdata() }
private void relaypCall(Map rt){ parent.pCallupdateRunTimeData(rt) }
private List wgetPushDev() { return (List)parent.getPushDev() }
private Boolean wexecutePiston(String pistonId,Map data,String selfId){ return (Boolean)parent.executePiston(pistonId,data,selfId) }
private Boolean wpausePiston(String pistonId,String selfId){ return (Boolean)parent.pausePiston(pistonId,selfId) }
private Boolean wresumePiston(String pistonId,String selfId){ return (Boolean)parent.resumePiston(pistonId,selfId) }
private Map wgetGStore() { return (Map)parent.getGStore() }
private Map wlistAvailableDevices(Boolean t){ return parent.listAvailableDevices(t) }
private Map wgetWData(){ return (Map)parent.getWData() }
private Map wlistAvailableVariables() { return (Map)parent.listAvailableVariables() }
private String sPAppId(){ return ((Long)parent.id).toString() }

private String sAppId(){ return ((Long)app.id).toString() }

private Map wgetGlobalVar(String vn){
	def a= getGlobalVar(vn)
	if(a)return [(sTYPE):(String)a.type,(sVAL): a.value]
	return null
}

@Field volatile static Map<String,Map<String,List>> globalVarsUseFLD=[:]

private void waddInUseGlobalVar(Map r9,String vn, Boolean heglobal=true){
	String wName=sMs(r9,spId)
	Map<String,List> vars=globalVarsUseFLD[wName] ?: [:]
	String nvn= heglobal ? sAT2+vn : vn
	List<String> pstns
	pstns= vars[nvn] ?: []
	String sa= sAppId()
	if(!(sa in pstns)){
		pstns << sa
		vars[nvn]= pstns
		globalVarsUseFLD[wName]= vars
		globalVarsUseFLD= globalVarsUseFLD
		if(isEric(r9))myDetail r9,"added in use $nvn $wName $sa  $pstns  $vars",iN2
	}

	if(heglobal) Boolean a=addInUseGlobalVar(vn)
}

Map<String,List> gtGlobalVarsInUse(){
	String wName= sPAppId()
	Map<String,List> vars=globalVarsUseFLD[wName] ?: [:]
	return vars
}

private Boolean wsetGlobalVar(String vn,vl){ setGlobalVar(vn,vl) }

private void wremoveAllInUseGlobalVar(){
	String wName= sPAppId()
	String sa= sAppId()
	Map<String,List> vars=globalVarsUseFLD[wName] ?: [:]
	vars.each{
		String k= it.key
		List<String> pstns
		pstns= it.value //tvars[vn] ?: []
		if(k && sa in pstns){
			pstns = pstns - [sa]
			vars[k]= pstns
		}
	}
	globalVarsUseFLD[wName]= vars
	globalVarsUseFLD= globalVarsUseFLD

	Boolean a=removeAllInUseGlobalVar()
}

private void wunschedule(String m){ unschedule(m) }
private void wunsubscribe(){ unsubscribe() }

private static Class HubActionClass(){ return 'hubitat.device.HubAction' as Class }
private static Class HubProtocolClass(){ return 'hubitat.device.Protocol' as Class }
/*private Boolean isHubitat(){ return hubUID!=sNL }*/

private void wpauseExecution(Long t){ pauseExecution(t) }
private void wrunInMillis(Long t,String m,Map d) { runInMillis(t,m,d) }

private Date wtimeToday(String str,TimeZone tz){ return (Date)timeToday(str,tz) }
private Date wtimeTodayAfter(String astr,String tstr,TimeZone tz){ return (Date)timeTodayAfter(astr,tstr,tz) }
private Long wnow(){ return (Long)now() }
private Date wtoDateTime(String s){ return (Date)toDateTime(s) }

private String gtAppN(){ return (String)app.label }
private gtSetting(String nm){ return settings."${nm}" }
private gtSt(String nm){ return state."${nm}" }
private gtAS(String nm){ return atomicState."${nm}" }

/** assign to state  */
private void assignSt(String nm,v){ state."${nm}"=v }

/** assign to atomicState  */
private void assignAS(String nm,v){ atomicState."${nm}"=v }
private Map gtState(){ return state }

private gtLocation(){ return location }
private Map cvtLoc(){ cvtDev(location) }
private static TimeZone mTZ(){ return TimeZone.getDefault() } // (TimeZone)location.timeZone
private String gtLMode(){ return (String)location.getMode() }
private Map gtCurrentMode(){
	def a=location.getCurrentMode()
	if(a)return [(sID):(Long)a.getId(),(sNM): (String)a.getName()]
	return null
}
private String gtLtScale(){ return (String)location.getTemperatureScale() }
private String gtLname(){ return (String)location.getName() }
private String gtLzip(){ return (String)location.zipCode }
private String gtLlat(){ return ((BigDecimal)location.latitude).toString() }
private String gtLlong(){ return ((BigDecimal)location.longitude).toString() }
private String gtLhsmStatus(){ return (String)location.hsmStatus }
//error "object: ${describeObject(e)}",r9

private String gtLbl(d){ return "${d?.label ?: d?.name ?: gtLname()}".toString() }
//hubitat device ids can be the same as the location id
private Boolean isDeviceLocation(d){
	if(dString(d)==dString(gtLocation())){
		Integer tt0=d.hubs?.size()
		if((tt0!=null?tt0:iZ)>iZ)return true
	}
	return false
}
private static String dString(d){ return d.id.toString() }
private static String hashD(Map r9,d){ return hashId2(d.id,sMs(r9,spId)) }
private static Map cvtDev(d){
	Map myDev
	myDev=[:]
	if(d!=null){
		myDev=[(sID):d.id,(sNM):d.name,label:d.label]
		if(d.hubs!=null)myDev.hubs=[(sT):'tt']
	}
	return myDev
}
