**Del 1**

Jag använder mig av en Digital Ocean server som jag ansluter till via ssh.

Jag hade kunnat ansluta till databaserna remote och utföra uppgifterna.

Då jag i nuläget behöver få gjort uppgifterna så tar jag inte tiden och gör detta, kanske återkommer till det senare i rapporten


För att starta MariaDB så skriver jag in:
	
	mysql

(Eftersom jag är inloggad som root användare på servern behöver jag inte använda:

	sudo mysql

för att logga in som root användare i MariaDB.)

Därefter tar jag reda på vilka databaser jag har genom att skriva in:

	SHOW DATABASES;

Databasen som jag skapade i förra inlämningsuppgiften heter inlamning2, för att ställa mig i den skriver jag:

	USE inlamning2;

Därefter undersöker jag vilka tabeller som finns i databasen [inlamning2] med:

	SHOW TABLES;

Och jag får resultatet:

	+----------------------+
	| Tables_in_inlamning2 |
	+----------------------+
	| locations            |
	+----------------------+

För att se vad som finns inmatet i tabellen "locations" så skriver jag:

	SELECT * FROM locations;

Vilket ger mig:

	+----+---------+---------------------+
	| ID | country | address             |
	+----+---------+---------------------+
	|  1 | Sweden  | Plåtslagaregatan 2  |
	+----+---------+---------------------+

Detta vill jag tömma för att ha en tom tabell att mata in de nya värdena i, vilket jag gör med följande kommando:

	DELETE FROM locations;

Hade jag enbart velat ta bort den specifika raden som finns så kan jag specificera med

	DELETE FROM locations WHERE ID = 1;

Jag ser att jag har ett stavfel i kolumnen "address". Det är korrekt stavning på svenska men fel på engelska.

För att fixa till detta kan jag antingen göra om tabellen med:

	DROP TABLE locations;
	CREATE TABLE locations(
		id INT PRIMARY KEY AUTO_INCREMENT,
		country VARCHAR(256),
		adress VARCHAR(256)
	);

Googlade korrekt stavning på address och såg att det är två d:n på engelska och ett d på svenska. Så jag gör inte om tabellen.

Nu ska jag lägga till de nya radena:

	INSERT INTO locations (country, address) VALUES ("SE", "Vimmerbygatan 20");
	INSERT INTO locations (country, address) VALUES ("US", "Asteroid road 5");
	INSERT INTO locations (country, address) VALUES ("US", "Comet road 41");
	INSERT INTO locations (country, address) VALUES ("SE", "Brunnsgatan 7");

Eftersom id har AUTO_INCREMENT så behöver jag inte mata in värde för den kolumnen.

För att kontrollera att inmatningen lyckades så skriver jag återigen:

	SELECT * FROM locations;

Vilket ger resultatet:

	+----+---------+------------------+
	| ID | country | address          |
	+----+---------+------------------+
	|  3 | SE      | Vimmerbygatan 20 |
	|  4 | US      | Asteroid road 5  |
	|  5 | US      | Comet road 41    |
	|  6 | SE      | Brunnsgatan 7    |
	+----+---------+------------------+

Här kan vi se att värdena i "ID" kolumnen startar från 3, detta är för att jag har tagit bort två tidigare värden.

Nu är del 1 komplett för MySQL/MariaDB och jag går över till MongoDB.

För att stänga ner MariaDB trycker jag CTRL + C och hamnar i terminalen.

För att starta MongoDB från terminalen skriver jag:

	mongo

För att se vilka databases som finns tillgängliga skriver jag:

	show databases

Även här heter databasen "inlamning2". Jag ställer mig i databasen med:

	use inlamning2

Och för att lista vilka collections som finns skriver jag:

	show collections

Och jag kan se att det finns en collection som heter "locations".

För att se vad som finns i den skriver jag:

	db.locations.find()

Vilket ger mig:

	{ "_id" : ObjectId("6012ec38d19b1f35943ad2f5"), "country" : "Sweden", "address" : "Plåtslagaregatan 2" }
	{ "_id" : ObjectId("6012ec6e3fdaaf71324d29d7"), "country" : "Sweden", "address" : "Plåtslagaregatan 2" }

Jag kan snygga till resultatet genom att använda funktionen pretty():

	db.locations.find().pretty()

Vilket resulterar:

	{
		"_id" : ObjectId("6012ec38d19b1f35943ad2f5"),
		"country" : "Sweden",
		"address" : "Plåtslagaregatan 2"
	}
	{
		"_id" : ObjectId("6012ec6e3fdaaf71324d29d7"),
		"country" : "Sweden",
		"address" : "Plåtslagaregatan 2"
	}

För att ta bort de existerande värdena skriver jag:

	db.locations.remove({})

Det två måsvingarna representerar objekt, vilket innebär att jag med kommandot säger till MongoDB att ta bort allting som är object från kolletionen "locations" i databasen som jag står i.


För att lägga till datan kan jag göra på två sätt, antingen använda insert() och mata in ett dokument i taget. 

Jag kan även använda insertMany() och mata in allting med ett kommando. Båda sätt ger  samma resultat. 

Jag använder mig av det andra sättet då det innebär mindre kod.

	db.locations.insertMany([
		{country: "SE", address: "Vimmerbygatan 20"},
		{country: "US", address: "Asteroid road 5"},
		{country: "US", address: "Comet road 41"},
		{country: "SE", address: "Brunnsgatan 7"}
	])  

En viktig skillnad här är att jag behöver använda hakparenteser för att säga till MongoDB att det är en lista med dokument som jag matar in. 

Om jag använder insert() behövs det inga hakparenteser.

För att se att datan matades in ordentligt skriver jag återigen:

	db.locations.find().pretty()
	
Vilket ger mig:
	
	{
		"_id" : ObjectId("602ea6a8bb5e0b6a8e9ae159"),
		"country" : "SE",
		"address" : "Vimmerbygatan 20"
	}
	{
		"_id" : ObjectId("602ea6a8bb5e0b6a8e9ae15a"),
		"country" : "US",
		"address" : "Asteroid road 5"
	}
	{
		"_id" : ObjectId("602ea6a8bb5e0b6a8e9ae15b"),
		"country" : "US",
		"address" : "Comet road 41"
	}
	{
		"_id" : ObjectId("602ea6a8bb5e0b6a8e9ae15c"),
		"country" : "SE",
		"address" : "Brunnsgatan 7"
	}

Och del 1 är klar även för MongoDB.
Jag stänger ner MongoDB med CTRL + C.


Del 2


Del 3


Del 4


Frågor

1.
DBRef

2.
find()

3.


4.


5.


6.


