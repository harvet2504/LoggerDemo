# Exercise:
Please review the following code snippet. Assume that all referenced assemblies have been properly included. 
The code is used to log different messages throughout an application. We want the ability to be able to log to a text file, the console and/or the database. Messages can be marked as message, warning or error. We also want the ability to selectively be able to choose what gets logged, such as to be able to log only errors or only errors and warnings. 
1.	If you were to review the following code, what feedback would you give? Please be specific and indicate any errors that would occur as well as other best practices and code refactoring that should be done. 
2.	Rewrite the code based on the feedback you provided in question 1. Please include unit tests on your code.


```html
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobLogger {
	private static boolean logToFile;
	private static boolean logToConsole;
	private static boolean logMessage;
	private static boolean logWarning;
	private static boolean logError;
	private static boolean logToDatabase;
	private boolean initialized;
	private static Map dbParams;
	private static Logger logger;

	public JobLogger(boolean logToFileParam, boolean logToConsoleParam, boolean logToDatabaseParam,
			boolean logMessageParam, boolean logWarningParam, boolean logErrorParam, Map dbParamsMap) {
		logger = Logger.getLogger("MyLog");  
		logError = logErrorParam;
		logMessage = logMessageParam;
		logWarning = logWarningParam;
		logToDatabase = logToDatabaseParam;
		logToFile = logToFileParam;
		logToConsole = logToConsoleParam;
		dbParams = dbParamsMap;
	}

	public static void LogMessage(String messageText, boolean message, boolean warning, boolean error) throws Exception {
		messageText.trim();
		if (messageText == null || messageText.length() == 0) {
			return;
		}
		if (!logToConsole && !logToFile && !logToDatabase) {
			throw new Exception("Invalid configuration");
		}
		if ((!logError && !logMessage && !logWarning) || (!message && !warning && !error)) {
			throw new Exception("Error or Warning or Message must be specified");
		}

		Connection connection = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", dbParams.get("userName"));
		connectionProps.put("password", dbParams.get("password"));

		connection = DriverManager.getConnection("jdbc:" + dbParams.get("dbms") + "://" + dbParams.get("serverName")
				+ ":" + dbParams.get("portNumber") + "/", connectionProps);

		int t = 0;
		if (message && logMessage) {
			t = 1;
		}

		if (error && logError) {
			t = 2;
		}

		if (warning && logWarning) {
			t = 3;
		}

		Statement stmt = connection.createStatement();

		String l = null;
		File logFile = new File(dbParams.get("logFileFolder") + "/logFile.txt");
		if (!logFile.exists()) {
			logFile.createNewFile();
		}
		
		FileHandler fh = new FileHandler(dbParams.get("logFileFolder") + "/logFile.txt");
		ConsoleHandler ch = new ConsoleHandler();
		
		if (error && logError) {
			l = l + "error " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
		}

		if (warning && logWarning) {
			l = l + "warning " +DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
		}

		if (message && logMessage) {
			l = l + "message " +DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
		}
		
		if(logToFile) {
			logger.addHandler(fh);
			logger.log(Level.INFO, messageText);
		}
		
		if(logToConsole) {
			logger.addHandler(ch);
			logger.log(Level.INFO, messageText);
		}
		
		if(logToDatabase) {
			stmt.executeUpdate("insert into Log_Values('" + message + "', " + String.valueOf(t) + ")");
		}
	}
}

```

## Respuesta
* Violación de principios S.O.L.I.D (Single responsibility principle), la clase Demo.java tiene varias responsabilidades al implementar la logica de tratamiento de archivos, consola y base de datos
* Mal uso de la separacion en capas
* No se utilizan interfaces.
* La variable "initialized" no se usa. (linea 23)
* No se lanzan excepciones específicas para los errores en cada escenario.
* El valor de las variables estáticas definidas en la clase no deben ser modificadas en el constructor, considerar inicializar en la declaracion de los campos o remover el atributo "static". (linea 27)  
* La conexion a BD no se cierra.
* El trim del mensaje no se asigna a una variable. (linea 40) 
* La condicion "messageText == null" siempre es falsa debido al trim del mensaje inicial, no tiene sentido (linea 41)
* Considerar usar el metodo .isEmpty en la validacion del size "messageText.length() == 0". (linea 41)
* La variable "l" no se utiliza fuera de las condiciones que sucesivamente le dan valor, basicamente no cumple funcion alguna en la logica, se debe remover. (linea 74)
* La barra del path debe ser procesada como "File.separator", garantizando compatibilidad multiplataforma(Windows, macOS, Linux). (linea 75 y 80)
* El metodo no sigue las Namming Convetions (LogMessage). (linea 39)
* Variables inentendibles, con nombres muy cortos, hay que seguir el codigo para entender que significan. (linea 59 y 74)
* En el escenario de log en BD la instruccion "String.valueOf(t)" es innecesaria, se puede sustituir por "t". (linea 106) 
* El uso de los logs debiese ser canalizado con los frameworks que existen para esto digase slf4j.

##Compilación y ejecución 

Para compilar y ejecutar la aplicación se necesita:

* JDK 11
* Maven 3

Ejecute el siguiente comando en la terminal

```shell
mvn spring-boot:run
```












