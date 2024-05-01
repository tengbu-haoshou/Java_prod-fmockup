@echo off
echo fmockup - DML for test
echo FMOCKUP_USER password is "Asdf1234"
"C:\Program Files\MySQL\MySQL Server 8.1\bin\mysql.exe" -u FMOCKUP_USER -p --default-character-set=utf8 < ./fmockup-dml_test.sql
if not %ERRORLEVEL% equ 0 pause
