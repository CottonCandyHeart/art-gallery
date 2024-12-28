DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE addArtist(IN artistName VARCHAR(30), IN artistSurname VARCHAR(40), IN artistBio TEXT, IN artistBirthDate DATE, IN artistDeathDate VARCHAR(20))
BEGIN

DECLARE artist_count INT;

SELECT COUNT(*) INTO artist_count FROM artists WHERE name = artistName AND surname = artistSurname;

IF artist_count = 0 THEN
	IF artistDeathDate = "NULL" THEN
    	INSERT INTO Artists VALUES (NULL, artistName, artistSurname, artistBio, artistBirthDate, NULL);
    ELSE
    	SELECT CONVERT(artistDeathDate, DATE) AS converted_date;
		INSERT INTO Artists VALUES (NULL, artistName, artistSurname, artistBio, artistBirthDate, converted_date);
    END IF;
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE addArtwork(IN artworkTitle VARCHAR(40), IN artworkArtistName VARCHAR(30), IN artworkArtistSurname VARCHAR(40), IN artworkCreationDate VARCHAR(20), IN artworkMethod VARCHAR(50), IN artworkDescription TEXT, IN artworkLocation VARCHAR(30), IN artworkStatus VARCHAR(30), IN artworkPrice VARCHAR(30), IN artworkPicturePath VARCHAR(50))
BEGIN

DECLARE artwork_count INT;
DECLARE artworkArtistId INT;
DECLARE converted_price FLOAT;

SELECT artist_id INTO artworkArtistId FROM Artists WHERE name = artworkArtistName AND surname = artworkArtistSurname;

IF artworkArtistId IS NOT NULL THEN
    SELECT COUNT(*) INTO artwork_count FROM Artworks WHERE title = artworkTitle AND artist_id = artworkArtistId;
    IF artwork_count = 0 THEN
        IF artworkPrice = "NULL" THEN
            INSERT INTO Artworks VALUES (NULL, artworkTitle, artworkArtistId, artworkCreationDate, artworkMethod, artworkDescription, artworkLocation, artworkStatus, NULL, artworkPicturePath);
        ELSE
            SET converted_price = CONVERT(artworkPrice, FLOAT);
            INSERT INTO Artworks VALUES (NULL, artworkTitle, artworkArtistId, artworkCreationDate, artworkMethod, artworkDescription, artworkLocation, artworkStatus, converted_price, artworkPicturePath);
        END IF;
    END IF;
END IF;

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE addEvent(IN eventName VARCHAR(30), IN eventDate DATE, IN eventExhibitionId INT, IN eventCapacity INT, IN eventType VARCHAR(20))
BEGIN

INSERT INTO Events VALUES (NULL, eventName, eventDate, eventExhibitionId, eventCapacity, eventType);

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE addExhibition(IN exhibitionName VARCHAR(50), IN exhibitionStartDate DATE, IN exhibitionEndDate VARCHAR(20), IN exhibitionLocation VARCHAR(60), IN exhibitionDescription TEXT)
BEGIN

DECLARE endDate DATE;

IF exhibitionEndDate = "NULL" THEN
	INSERT INTO Exhibitions VALUES (NULL, exhibitionName, exhibitionStartDate, NULL, exhibitionLocation, exhibitionDescription);
ELSE
	SET endDate = CONVERT(exhibitionEndDate, DATE);
    INSERT INTO Exhibitions VALUES (NULL, exhibitionName, exhibitionStartDate, NULL, exhibitionLocation, exhibitionDescription);
END IF;

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE addMarketing(IN marketingContent TEXT, IN marketingTarget VARCHAR(50), IN marketingStartDate DATE, IN marketingEndSate DATE, IN marketingUserId VARCHAR(50))
BEGIN

INSERT INTO Marketing VALUES (NULL, marketingContent, marketingTarget, marketingStartDate, marketingEndSate, marketingUserId);

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE addReport(IN reportType VARCHAR(40), IN reportDetails TEXT, IN reportUserId VARCHAR(50))
BEGIN

INSERT INTO Reports VALUES(NULL, reportType, CURDATE(), reportDetails, reportUserId);

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE addRole(IN roleRole VARCHAR(50), IN roleSalary FLOAT)
BEGIN

DECLARE role_count INT;

SELECT COUNT(*) INTO role_count FROM Roles WHERE role = roleRole;

IF role_count = 0 THEN
	INSERT INTO Roles VALUES (roleRole, roleSalary);
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE addUser(IN userType VARCHAR(3), IN userUsername VARCHAR(20), IN userPassword VARCHAR(30), IN userName VARCHAR(30), IN userSurname VARCHAR(40), IN userPhoneNo INT(9), IN userRole VARCHAR(50))
BEGIN

DECLARE user_count INT;
DECLARE userIDConcat VARCHAR(50);
DECLARE userNo INT;

SET userNo = (SELECT user_no FROM Users WHERE user_type = userType ORDER BY user_no DESC LIMIT 1);

IF userNo IS NULL THEN
	SET userNo = 0;
ELSE
	SET userNo = userNo+1;
END IF;

SET userIDConcat = CONCAT(userType, userNo);
SELECT COUNT(*) INTO user_count FROM Users WHERE user_id = userIDConcat;

IF user_count = 0 THEN
	IF userPhoneNo = 1 THEN
    	IF userRole IS NULL THEN
        	INSERT INTO Users VALUES (userIDConcat, userType, userNo, userUsername, userPassword, userName, userSurname, NULL, NULL);
        ELSE
    		INSERT INTO Users VALUES (userIDConcat, userType, userNo, userUsername, userPassword, userName, userSurname, NULL, userRole);
        END IF;
    ELSE
		INSERT INTO Users VALUES (userIDConcat, userType, userNo, userUsername, userPassword, userName, userSurname, userPhoneNo, userRole);
    END IF;
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE changeOwner(IN ownerID VARCHAR(50), IN artworkID INT)
BEGIN

UPDATE Artworks SET owner = ownerID WHERE artwork_id = artworkID;

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE makeTransaction(IN transUserId VARCHAR(50), IN transArtworkId INT)
BEGIN

DECLARE price FLOAT;
SET price = (SELECT price FROM Artworks WHERE artwork_id = transArtworkId);

INSERT INTO Transactions VALUES (NULL, transUserId, transArtworkId, CURDATE(), price);

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE modArtist(IN modArtistId INT, IN modName VARCHAR(30), IN modSurname VARCHAR(40), IN modBio TEXT, IN modDeathDate VARCHAR(20))
BEGIN

DECLARE modDate DATE;

IF modName != "NULL" THEN
	UPDATE Artist SET name = modName WHERE artist_id = modArtistId;
END IF;

IF modSurname != "NULL" THEN
	UPDATE Artist SET surname = modSurname WHERE artist_id = modArtistId;
END IF;

IF modBio != "NULL" THEN
	UPDATE Artist SET bio = modBio WHERE artist_id = modArtistId;
END IF;

IF modDeathDate != "NULL" THEN
	SET modDate = CONVERT(modDeathDate, DATE);
	UPDATE Artist SET death_date = modDate WHERE artist_id = modArtistId;
END IF;

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE modArtwork(IN modArtworkId INT, IN modTitle VARCHAR(40), IN modDescription TEXT, IN modLocation VARCHAR(20), IN modStatus VARCHAR(20), IN modPrice FLOAT, IN modPicturePath VARCHAR(50))
BEGIN

IF modTitle != "NULL" THEN
	UPDATE Artworks SET title = modTitle WHERE artwork_id = modArtworkId;
END IF;

IF modDescription != "NULL" THEN
	UPDATE Artworks SET description = modDescription WHERE artwork_id = modArtworkId;
END IF;

IF modLocation != "NULL" THEN
	UPDATE Artworks SET location = modLocation WHERE artwork_id = modArtworkId;
END IF;

IF modStatus != "NULL" THEN
	UPDATE Artworks SET status = modStatus WHERE artwork_id = modArtworkId;
END IF;

IF modPrice != "NULL" THEN
	UPDATE Artworks SET price = modPrice WHERE artwork_id = modArtworkId;
END IF;

IF modPicturePath != "NULL" THEN
	UPDATE Artworks SET picturePath = modPicturePath WHERE artwork_id = modArtworkId;
END IF;

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE modRole(IN oldRole VARCHAR(50), IN newRole VARCHAR(50))
BEGIN

UPDATE Roles SET role = newRole WHERE role = oldRole;

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE modSalary(IN modRole VARCHAR(50), IN modSalary FLOAT)
BEGIN

UPDATE Roles SET salary = modSalary WHERE role = modRole;

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE modUser(IN modUserId VARCHAR(50), IN modPassword VARCHAR(30), IN modName VARCHAR(30), IN modSurname VARCHAR(40), IN modPhoneNo INT(9), IN modRole VARCHAR(50))
BEGIN

IF modPassword != "NULL" THEN
	UPDATE Users SET password = modPassword WHERE user_id = modUserId;
END IF;

IF modName != "NULL" THEN
	UPDATE Users SET name = modName WHERE user_id = modUserId;
END IF;

IF modSurname != "NULL" THEN
	UPDATE Users SET surname = modSurname WHERE user_id = modUserId;
END IF;

IF modPhoneNo != 1 THEN
	UPDATE Users SET phoneNo = modPhoneNo WHERE user_id = modUserId;
END IF;

IF modRole != "NULL" THEN
	UPDATE Users SET role = modRole WHERE user_id = modUserId;
END IF;

END$$
DELIMITER ;
