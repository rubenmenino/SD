machine=sd302@l040101-ws07.ua.pt

echo "Executing program at the Repository node."
sshpass -f password ssh $machine 'cd test/AirLift/ ; java -cp .:./genclass.jar Server.RepositoryMain'
