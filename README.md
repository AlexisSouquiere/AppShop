AppShop
=======

Java EE project proposed by [Daniel PETISME](https://github.com/danielpetisme).

The main idea is to provide a cross platform/device application market.


Architecture
------------

The AppShop persistence layer uses JPA. <br/>
The AppShop business layer uses EJB 3. <br/>
The AppShop presentation layer uses both Servlet/JSP and JSF. <br/>
Moreover, the AppShop exposes a credit card validator web service which fakes the Bank Company role.


Installation
------------
This project has been developped with Netbeans 7.2.1 and Glassfish 3.1.2.

1.	Create a new Derby database named AppShop with a user "root" and a password "root"
2.	Create the AppShop Database with the AppShopDB.sql file
3.	Open the GlassFish Admin Console
4.	Create a new JDBC connection pool :
	- Type : javax.sql.DataSource
	- Other properties :
		- User : root
		- ServerName : localhost
		- PortNuber : 1527
		- Password : root
		- URL : jdbc:derby://localhost:1527/AppShop
		- DatabaseName : AppShop
5. Create a new JDBC resource :
	- Name : jdbc/appshop
	- Pool name : AppShop
6. Eventually, import some data in the AppShop database with AppShopData.sql file
7. Always in Glassfish Admin console, go to Configurations -> server-config -> Security -> Domains
8. Create a new domain :
	- Name : jdbcRealmSecurity
	- Classname : com.sun.enterprise.security.auth.realm.jdbc.JDBCRealm
	- JAAS Context :jdbcRealm
	- JNDI : jdbc/appshop
	- Users table : APPSHOP.USERS
	- Username column : USER\_USERNAME
	- Password column : USER\_PASSWORD
	- Groups table : APPSHOP.USERS
	- Group name column : USER\_GROUP\_NAME
	- Databse user : root
	- Database password : root
	- Shortern algorithm : None 
	- Encrypt algorithm : None
9. Deploy and Run the AppShop project on Glassfish