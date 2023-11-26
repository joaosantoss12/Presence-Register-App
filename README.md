# Presence-Register-App
Java Application with the goal to register presences in events 📝
There are three main apps: 
Main Server:
- Launch a ServerSocket and accept connections
- Creates and updates a main database
- Connect to a Multicast to communicate with the backup servers (send alive signals)
- Launch a RMI service to communicate with the backup servers and clients

Client (ADMIN):
- Add, modify, delete, generate code for events
- Add, delete presences
- Check presences in an event or check client presences (all the results can be filtered)
- Launch a RMI service and using a callback with the Main Server RMI, update the UI
- Generate CSV files with information described on the 3. point

Client (STUDENT):
- Register an account
- Modify account data
- Submit event code
- Check own presences
- Generate CSV files with information described on the last point

Backup Server:
- Connect to the same Multicast as Main Server (check if it's alive)
- Launch a RMI service and using a callback with the Main Server RMI, update the database backup

<p align="left">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=java,sqlite" />
  </a>
</p>
