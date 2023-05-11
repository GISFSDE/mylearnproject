@echo off
%1 mshta vbscript:CreateObject("WScript.Shell").Run("%~s0 ::",0,FALSE)(window.close)&&exit
set port=8081
for /f "tokens=1-5" %%i in ('netstat -ano^|findstr ":%port%"') do taskkill /f /pid %%m &
exit