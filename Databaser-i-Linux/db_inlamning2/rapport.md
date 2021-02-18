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

En viktig skillnad här är att jag behöver använda hakparenteser för att säga till MongoDB att det är en lista med dokument som jag matar in. 

Om jag använder insert() behövs det inga hakparenteser.

	db.locations.insertMany([
		{country: "SE", address: "Vimmerbygatan 20"},
		{country: "US", address: "Asteroid road 5"},
		{country: "US", address: "Comet road 41"},
		{country: "SE", address: "Brunnsgatan 7"}
	])  

Jag använder inga sitationstecken för nycklarna (country och address) eftersom MongoDB och BSON tillåter det. 

Hade jag däremot gjort JSON-objekt och importerat hade jag behövt sätta nyckelvärdena inom situationstecken (alt. enkelfnuttar).

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


**Del 2**

Som jag tolkar uppgifter skall jag använda mig av bank_accounts tabellen som vi fick i föregående inlämningsuppgift.
Det är denna tabell som skall refereras till i relationstabellen.
Det innebär att jag behöver använda foreign key för både locations-ID samt bank-konto.

Jag vet att ID som jag ska referera till i locations är av typen INT, men jag behöver ta reda på hur bank_account tabellen ser ut innan jag kan skapa relationstabellen.

	mysql


	USE inlamning1;
	SELECT * FROM bank_accounts;

ID är av typen INT men kolumnnamnet har gemener och inte versaler som i ID locations tabellen.

Jag passar på att skapa en query där jag tar reda på id för de som jag ska referera i min relationstabell sedan.

	SELECT * FROM bank_accounts WHERE
		first_name = "Corbin" AND last_name = "Hauck"
		OR
		first_name = "Vanya" AND last_name = "Worsell"
                OR
		first_name = "Eldon" AND last_name = "McCartan"
                OR
		first_name = "Ingunna" AND last_name = "Castellucci";

Vilket ger mig:

	+-----+------------+-------------+---------+
	| id  | first_name | last_name   | holding |
	+-----+------------+-------------+---------+
	|  55 | Corbin     | Hauck       |  449092 |
	|  89 | Vanya      | Worsell     |  330641 |
	| 170 | Ingunna    | Castellucci |  471372 |
	| 174 | Eldon      | McCartan    |   75096 |
	+-----+------------+-------------+---------+


Nu har jag all information som jag behöver från bank_accounts för att kunna skapa relationstabellen enligt instruktionerna.

	USE inlamning2;
           
Eftersom tabellen bank_accounts ligger i databas inlamning1 behöver jag ange en längre sökväg i min foreign key än vad jag hade behövt om det hade legat i samma databas.

För att ta fram informationen från locations-tabellen skriver jag återigen:

	SELECT * FROM locations;

Vilket ger:

	+----+---------+------------------+
	| ID | country | address          |
	+----+---------+------------------+
	|  3 | SE      | Vimmerbygatan 20 |
	|  4 | US      | Asteroid road 5  |
	|  5 | US      | Comet road 41    |
	|  6 | SE      | Brunnsgatan 7    |
	+----+---------+------------------+

För att skapa tabellen skriver jag då följande:

	USE inlamning2;
	CREATE TABLE relations(
		bank_account_ID INT UNIQUE NOT NULL,
		locations_ID INT NOT NULL,
		FOREIGN KEY (bank_account_ID) REFERENCES inlamning1.bank_accounts(id),
		FOREIGN KEY (locations_ID) REFERENCES locations(ID)
	);
 
Jag väljer att göra bank_accounts__ID unik i relationstabellen då jag inte vill att ett bankkonto skall kunna vara kopplat till mer än en location.

Jag väljer också att skriva NOT NULL så att det krävs att det finns en ID referense till båda tabeller. 

För att skapa mina rader så skriver jag in följande:

	INSERT INTO relations (bank_account_ID, locations_ID) 
		VALUES (55, 6);
        INSERT INTO relations (bank_account_ID, locations_ID)
                VALUES (89, 4);
        INSERT INTO relations (bank_account_ID, locations_ID)
                VALUES (174, 3);
        INSERT INTO relations (bank_account_ID, locations_ID)
                VALUES (170, 5);

Värdena som jag matar in får jag från mina två tidigare querys.

För att se att tabellen blev skapad skriver jag:

	SELECT * FROM relations;
	
Vilket ger:

	+-----------------+--------------+
	| bank_account_ID | locations_ID |
	+-----------------+--------------+
	|             174 |            3 |
	|              89 |            4 |
	|             170 |            5 |
	|              55 |            6 |
	+-----------------+--------------+

Detta är vad som krävs för att del 2 skall vara klart. 

Jag kan göra det i MongoDB om jag vill vilket jag i så fall återkommer till om tiden finns.

**Del 3**


**Del 4**


**Frågor**

*1.*
DBRef

*2.*
find()

*3.*


*4.*


*5.*


*6.*













