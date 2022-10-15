-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: alkemy_challengedisney
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Eliminar base de datos si existe
DROP DATABASE IF EXISTS `alkemy_challengedisney`;

-- Crear la base de datos
CREATE DATABASE `alkemy_challengedisney` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `alkemy_challengedisney`;
--
-- Table structure for table `characters`
--

DROP TABLE IF EXISTS `characters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `characters` (
  `character_id` int NOT NULL AUTO_INCREMENT,
  `age` int NOT NULL,
  `history` varchar(200) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `weight` int NOT NULL,
  PRIMARY KEY (`character_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `characters`
--

LOCK TABLES `characters` WRITE;
/*!40000 ALTER TABLE `characters` DISABLE KEYS */;
INSERT INTO `characters` VALUES (1,5,'Experimento 626. Es azul y muy esponjoso. Su función principal es destruir todo lo que toca.','https://static.wikia.nocookie.net/disney/images/5/5f/Stitch.png/revision/latest/top-crop/width/360/height/360?cb=20140703072656&path-prefix=es','Stitch',15),(2,10,'Es una niña hawaiana. Le encantan las películas de terror (en especial de monstruos raros) y Elvis Presley.','https://static.wikia.nocookie.net/stitchipediaalilostitch/images/a/a8/Lilo_Pelekai_official_flat_color_artwork.png/revision/latest?cb=20220515215039','Lilo Pelekai',25),(3,70,'Es un juguete leal que ha sido el juguete favorito de Andy desde la guardería. Es determinado, apasionado, y haría cualquier cosa por sus amigos más queridos y cercanos.','https://ss425.liverpool.com.mx/xl/1100180771.jpg','Woody',5);
/*!40000 ALTER TABLE `characters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `characters_movies`
--

DROP TABLE IF EXISTS `characters_movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `characters_movies` (
  `character_id` int NOT NULL,
  `movie_id` int NOT NULL,
  KEY `FK9c0exhh7ubghne8b45i243b9h` (`movie_id`),
  KEY `FK31tkodwhinl2nngwemfrnp8xq` (`character_id`),
  CONSTRAINT `FK31tkodwhinl2nngwemfrnp8xq` FOREIGN KEY (`character_id`) REFERENCES `characters` (`character_id`),
  CONSTRAINT `FK9c0exhh7ubghne8b45i243b9h` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`movie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `characters_movies`
--

LOCK TABLES `characters_movies` WRITE;
/*!40000 ALTER TABLE `characters_movies` DISABLE KEYS */;
INSERT INTO `characters_movies` VALUES (1,2),(2,2),(3,3);
/*!40000 ALTER TABLE `characters_movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genres`
--

DROP TABLE IF EXISTS `genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genres` (
  `genre_id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`genre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genres`
--

LOCK TABLES `genres` WRITE;
/*!40000 ALTER TABLE `genres` DISABLE KEYS */;
INSERT INTO `genres` VALUES (1,'https://pics.filmaffinity.com/indiana_jones_raiders_of_the_lost_ark-316486950-large.jpg','Aventura'),(2,'https://w7.pngwing.com/pngs/386/931/png-transparent-beyond-the-boundary-anime-drawing-animated-film-anime-mammal-face-manga.png','Anime');
/*!40000 ALTER TABLE `genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies` (
  `movie_id` int NOT NULL AUTO_INCREMENT,
  `created_at` date NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `rating` int NOT NULL,
  `title` varchar(200) NOT NULL,
  `genre_id` int DEFAULT NULL,
  PRIMARY KEY (`movie_id`),
  KEY `FKjp8fsy8a0kkmdi04i81v05c6a` (`genre_id`),
  CONSTRAINT `FKjp8fsy8a0kkmdi04i81v05c6a` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`genre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (1,'2003-09-19','https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcThZJtFnVVJSq7NsDSWW4PoRRG3jSj_HguiMKyYscOFVSR-CTiP',4,'Lilo y Stitch',2),(2,'2003-08-18','https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRSH8zD43pbmv2Dzks5R9N5lV7-qjAqZUertD3Ueiu2dQwngeea',5,'Leroy y Stitch',2),(3,'1995-02-18','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJzAvb8vFlI_NhQWAKMNDqDLXLBwG7uAtrXpbTiZGq5foib83-',4,'Toy Story',1);
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `pass_word` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kfsp0s1tflm1cwlj8idhqsad0` (`email`),
  UNIQUE KEY `UK_r9ksbmgwvokav9vgydstfdmws` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (3,'emaillocal@localhost.com','$2a$10$aKWc2oiMV9GYhmatbjWkFefRiy2BnNB2c.E.zbYhP.zc2RBDUtLY2','test');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-14 19:23:46
