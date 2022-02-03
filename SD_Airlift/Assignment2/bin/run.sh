bold=$(tput bold)
normal=$(tput sgr0)

echo "----------------------------------------------------------"
echo "${bold}             AirLift Problem - Script              ${normal}"
echo "----------------------------------------------------------"

 javac  */*.java
 java  Server.RepositoryMain
 java  Server.DepartureAirportMain
 java  Server.PlaneMain
 java  Client.HostessMain
 java  Client.PilotMain
 java  Client.PassengerMain


echo -e "\n${normal}----------------------------------------------------------\n${normal}"
wait
echo -e "\n${normal}----------------------------------------------------------"

echo -e "\n${bold}>>>>>>>>>>${normal} A execução terminou"
