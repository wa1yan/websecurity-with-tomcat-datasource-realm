# websecurity-with-tomcat-datasource-realm
This project used jsp, servlet and  localdatasource with tomcat datasource realam

Tomcat Server's server.xml
       
       <Realm className="org.apache.catalina.realm.DataSourceRealm"
          dataSourceName="jdbc/postdb"
          localDataSource="true"
          roleNameCol="role"
          userCredCol="password"
          userNameCol="login"
          userRoleTable="member"
          userTable="member"/>
        
 
 Tomcat Server's context.xml
       
       <Resource
    	name="jdbc/postdb"
    	auth="Container"
   		type="javax.sql.DataSource"
    	driverClassName="com.mysql.cj.jdbc.Driver"
   		url="jdbc:mysql://localhost:3306/postdb"
    	username="****" //insert username
    	password="****" //insert password
    />
