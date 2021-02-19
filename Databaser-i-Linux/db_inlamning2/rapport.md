## Del 1
###

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


## Del 2

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

## Del 3

De två sätt som jag kan utföra detta på är antingen med s.k. subqueries samt med JOIN.

Jag tycker att båda är värda att göra och börjar med subqueries.

Eftersom jag vill ha datan från bankkonton så ställer jag min i databasen inlamning1

	USE inlamning1;

Min query ser ut såhär:

	SELECT * FROM bank_accounts WHERE id IN(
		SELECT bank_account_ID FROM inlamning2.relations
		);

Den säger att jag vill ha alla bankkonton som har id som finns i databas "inlamning2", tabell "relations".

Själva subqueryn är "SELECT bank_account_ID FROM inlamning2.relations" som ligger inom parenteserna.

Om jag enbart kör den får jag tillbaka:

	+-----------------+
	| bank_account_ID |
	+-----------------+
	|             174 |
	|              89 |
	|             170 |
	|              55 |
	+-----------------+

Dessa värden används sedan i "SELECT * FROM bank_accounts WHERE id IN" på samma sätt som om jag har skrivit:

	SELECT * FROM bank_accounts WHERE id = 174 
					OR id = 89 
					OR id = 170 
					OR id = 55;
 
Queryn ger mig resultatet:

	+-----+------------+-------------+---------+
	| id  | first_name | last_name   | holding |
	+-----+------------+-------------+---------+
	| 174 | Eldon      | McCartan    |   75096 |
	|  89 | Vanya      | Worsell     |  330641 |
	| 170 | Ingunna    | Castellucci |  471372 |
	|  55 | Corbin     | Hauck       |  449092 |
	+-----+------------+-------------+---------+

Jag kan även använda mig av JOIN för att lösa uppgiften.

	SELECT * FROM bank_accounts
	JOIN inlamning2.relations ON id = bank_account_ID;

JOIN tar in ett argument som i detta fallet säger att enbart joinar de rader där id i bank_accounts tabellen har ett motsvarande värde i bank_account_ID kolumnen i relations- tabellen.

JOIN queryn resulterar i:
	
	+-----+------------+-------------+---------+-----------------+--------------+
	| id  | first_name | last_name   | holding | bank_account_ID | locations_ID |
	+-----+------------+-------------+---------+-----------------+--------------+
	| 174 | Eldon      | McCartan    |   75096 |             174 |            3 |
	|  89 | Vanya      | Worsell     |  330641 |              89 |            4 |
	| 170 | Ingunna    | Castellucci |  471372 |             170 |            5 |
	|  55 | Corbin     | Hauck       |  449092 |              55 |            6 |
	+-----+------------+-------------+---------+-----------------+--------------+

Här får jag ut ett resultat med två extra kolumner jämfört med subqueryn.

Jag kan specificera vilka kolumner jag vill ha ut genom att ersätta mitt wildcard *

	SELECT id, first_name, last_name, holding FROM bank_accounts
		JOIN inlamning2.relations ON id = bank_account_ID;

alternativt:

	SELECT bank_accounts.* FROM bank_accounts
		JOIN inlamning2.relations ON id = bank_account_ID;
 
Vilka båda ger samma resultat som subqueryn:

	+-----+------------+-------------+---------+
	| id  | first_name | last_name   | holding |
	+-----+------------+-------------+---------+
	| 174 | Eldon      | McCartan    |   75096 |
	|  89 | Vanya      | Worsell     |  330641 |
	| 170 | Ingunna    | Castellucci |  471372 |
	|  55 | Corbin     | Hauck       |  449092 |
	+-----+------------+-------------+---------+

Det går även att välja färre kolumner i resultatet för subqueryn, t.ex. kanske jag bara vill ha ut holding för dessa 4 konton:

	SELECT holding FROM bank_accounts WHERE id IN(
                SELECT bank_account_ID FROM inlamning2.relations
                );
 
Då får jag tillbaka:

	+---------+
	| holding |
	+---------+
	|   75096 |
	|  330641 |
	|  471372 |
	|  449092 |
	+---------+

Men jag kan inte få mer kolumner är de som finns i tabellen som efterfrågas i den yttersta queryn, den som ligger utanför parenteserna.
"SELECT holding FROM bank_accounts" i detta fallet. 

Jag ser att jag har missat en nivå av frågan. Det är enbart de konton som är kopplade till country "SE" som ska visas i resultatet.

jag flyttar mig till databas inlamning2 för detta då det blir mindre kod.

	USE inlamning2;

Det är samma sökning men jag behöver lägga till ytterligare en subquery för att få fram detta resultat.

Subquery för denna sökning:

	SELECT * FROM inlamning1.bank_accounts WHERE id IN (
		SELECT bank_account_ID FROM relations WHERE locations_ID IN(
			SELECT ID FROM locations WHERE country="SE"
		)
	);

Vilket ger mig:

	+-----+------------+-----------+---------+
	| id  | first_name | last_name | holding |
	+-----+------------+-----------+---------+
	| 174 | Eldon      | McCartan  |   75096 |
	|  55 | Corbin     | Hauck     |  449092 |
	+-----+------------+-----------+---------+

JOIN frågan ser ut såhär:

	SELECT inlamning1.bank_accounts.*, locations.country FROM inlamning1.bank_accounts, locations
		JOIN relations ON locations.ID = relations.locations_ID 
		WHERE locations.country = "SE"
		AND inlamning1.bank_accounts.id = relations.bank_account_ID;

Här la jag till country kolumnen från locations tabellen i resultaten och får ut:

	+-----+------------+-----------+---------+---------+
	| id  | first_name | last_name | holding | country |
	+-----+------------+-----------+---------+---------+
	| 174 | Eldon      | McCartan  |   75096 | SE      |
	|  55 | Corbin     | Hauck     |  449092 | SE      |
	+-----+------------+-----------+---------+---------+

Jag har svårt att förklara vad jag har gjort här. 
En felaktig JOIN query kan ge väldigt konstiga resultat som jag inte förstår mig på.\
Om jag inte har med sista raden som specificerar att jag enbart vill att radernas id måste finnas både i bank_accounts- och relationstabellerna,
så får jag ut ett resultat där alla rader i bank_accounts dubbleras och alla konton har värde "SE" i den tillagda "country" kolumnen:

	+------+-----------------+----------------+---------+---------+
	| id   | first_name      | last_name      | holding | country |
	+------+-----------------+----------------+---------+---------+
	|    1 | Grete           | Gulliver       |  463797 | SE      |
	|    1 | Grete           | Gulliver       |  463797 | SE      |
	...

	|  999 | Ashbey          | Stovold        |  156360 | SE      |
	|  999 | Ashbey          | Stovold        |  156360 | SE      |
	| 1000 | Georgine        | Ocheltree      |  106479 | SE      |
	| 1000 | Georgine        | Ocheltree      |  106479 | SE      |
	+------+-----------------+----------------+---------+---------+

Det går säkert att förstå varför, men jag tänker att jag har demonstrerat att jag förstår hur man gör Subquerys ordentligt, 

samt att jag har tålamodet av att klura ut hur JOIN querys skulle specificeras i detta fallet. 

## Del 4
**CREATE**

*För att skapa en ny rad/nytt dokument i "bank_accounts":*

SQL

    INSERT INTO bank_accounts (first_name, last_name, holding) 
    VALUES ("Leo", "Möller", 666);

MongoDB:

    db.bank_accounts.insert(
        {
            first_name: "Leo", 
            last_name: "Möller", 
            holding: 666
        }
    )

I MySQL behöver jag skapa tabellen och definiera hur innehållet ska se ut innan ja gkan mata in data.
I detta fall hade det sett ut såhär:

    CREATE TABLE bank_accounts(
        id INT PRIMARY KEY AUTO_INCREMENT,
        first_name VARCHAR(256),
        last_name VARCHAR(256),
        holding INT
    );

I MongoDB skapas collectionen av frågan ifall den inte skulle existera på förhand.

**READ**

*För att hitta det jag precis matade in:*

SQL:
    
    SELECT * FROM bank_accounts
    WHERE first_name = "Leo"
    AND last_name = "Möller";

MongoDB:

    db.bank_accounts.find(
        {
            first_name:"Leo", 
            last_name:"Möller"
        }
    )

**UPDATE**

*För att uppdatera "holding" i det jag matade in i CREATE delen ovan:*

SQL:
    
    UPDATE bank_accounts
    SET holding = 999999
    WHERE first_name = "Leo"
    AND last_name = "Möller";

MongoDB:

    db.bank_accounts.update(
        {
            first_name:"Leo", 
            last_name:"Möller"
        },
        {
        $set:
            {
            holding: 999999
            }})

**DELETE**

*För att ta bort det jag har skapat och uppdaterat:*

SQL:

    DELETE FROM bank_accounts 
    WHERE first_name = "Leo"
    AND last_name = "Möller";

MongoDB:

    db.bank_accounts.remove(
        {
            first_name:"Leo", 
            last_name:"Möller"
        }
    )

*För att ta bort tabell/collection "bank_accounts":*

SQL:

    DROP TABLE bank_accounts;

MongoDB:

    db.bank_accounts.drop()

## Frågor

#### 1. Vad är motsvarigheten i MongoDB till en foreign key?

DBRef

#### 2. Vad är motsvarigheten till en SELECT i MongoDB?

find()

#### 3. Hur hade du löst del 2 och 3 i MongoDB? (du behöver inte göra en komplett lösning, men beskriv på ett ungefär hur du hade gjort)

**Del 2:**\
Jag hade fört in den data, som i SQL ligger i locations-tabellen, direkt i dokumentet för respektive person.\
Eftersom MongoDB är dynamiskt i hur collections och dokument kan utformas så innebär detta inte ett problem utan blir, i mitt tycke, ett enklare sätt att lösa problemet.\
Ett exempeldokument hade då sett ut såhär:

    {
        "_id" : ObjectId("6012b0b199db1eeb3859bcad"),
        "id" : 55,
        "first_name" : "Corbin",
        "last_name" : "Hauck",
	    "holding" : 449092, 
        "locations" : [ 
            {
            "country" : "SE",
            "address" : "Brunnsgatan 7"
            }
        ]
    }

Detta hade åstadkommits (om samma data fanns i bank_accounts collection i mongo) med frågan:

    db.bank_accounts.update(
        { id : 55 },
        {
        $push : {
                locations : {
            country : "SE",
            address : "Brunnsgatan 7"
                }
            }
        }
    )

Jag hade kunna skapa en collections som hette locations och använda mig av DBRef för att koppla samman dokument i de två\ 
collectionerna men eftersom det är så lite data som ska läggas in ser jag det som onödigt i detta fall.

**Del 3:**\
För att få fram de som har "SE" som värde för nyckeln "country" utformas query följande:

    db.bank_accounts.find( { "locations.country" : "SE" } ).pretty()

#### 4. Vad behöver du för information för att kunna logga in i någon annans databas?

Ip-adress, eventuellt portnummer, ett användarnamn samt lösenord att logga in med.
Jag behöver även veta vilken databas som jag ska logga in på MongoDB eller Mariadb/MySQL.


#### 5. Varför skulle man vilja använda sig utav en databas?

Först och främst behöver man ha data som man vill lagra.\
Väljer man då att lagra data i en databas så får du en struktur på din data som möjliggör överskådlighet.
Datan blir sökbar så att du inte behöver leta länge om du vet vad du letar efter.
Det ger dig också möjlighet att enkelt hålla din data uppdaterad.\
Varför du skulle vilja använda någon annans databas skulle kunna vara för att du är intresserad av datan i den.\
Det går också att säga att du indirekt använder en uppsjö av databaser omedvetet eftersom våra handlingar och metadata
kring våra handlingar på internet sparas i databaser och används för att vi ska t.ex. kunna logga in på facebook eller
köpa saker från Amazon. 


#### 6. Nämn några andra ställen / situationer utöver databaser som CRUD används
                                                                             
Jag vet ingenstans där uttrycket CRUD används utanför data som hanteras av databaser.\
Men där databaser är implementerade i bakgrunden är ju även CRUD funktionalitet möjligt.\
Till exempel för att skapa ett konto på en hemsida så används Create. Kan du även uppdatera din information så har du \
Update och kan du ta bort ditt konto har du Delete. Kan du söka på andra användare så finns det Read funktionalitet.\
Eftersom databasanvändningen är så utbredd så finns det väldigt många ställen där CRUD används.\
Hemsidor, butikslager, dataspel, mobilappar m.m.





