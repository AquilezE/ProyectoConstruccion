-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: coil
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `areaacademica`
--

DROP TABLE IF EXISTS `areaacademica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `areaacademica` (
  `area_academica_id` int NOT NULL AUTO_INCREMENT,
  `NombreAreaAcademica` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`area_academica_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areaacademica`
--

LOCK TABLES `areaacademica` WRITE;
/*!40000 ALTER TABLE `areaacademica` DISABLE KEYS */;
INSERT INTO `areaacademica` VALUES (1,'Área Académica de Artes'),(2,'Área Académica de Ciencias Biológicas y Agropecuarias'),(3,'Área Académica de Ciencias de la Salud'),(4,'Área Académica Económico-Administrativa'),(5,'Área Académica de Humanidades'),(6,'Área Académica Técnica');
/*!40000 ALTER TABLE `areaacademica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colaboracion`
--

DROP TABLE IF EXISTS `colaboracion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `colaboracion` (
  `colaboracion_id` int NOT NULL AUTO_INCREMENT,
  `Duracion` varchar(255) DEFAULT NULL,
  `Estado` varchar(255) DEFAULT NULL,
  `FechaCierre` date DEFAULT NULL,
  `FechaInicio` date DEFAULT NULL,
  `NumeroEstudiantes` int DEFAULT NULL,
  `Periodo` varchar(255) DEFAULT NULL,
  `TipoDeColab` varchar(255) DEFAULT NULL,
  `Titulo` varchar(255) DEFAULT NULL,
  `profesor_uv_id` int DEFAULT NULL,
  `profesor_externo_id` int DEFAULT NULL,
  `experiencia_educativa_id` int DEFAULT NULL,
  `evidencia_id` int DEFAULT NULL,
  PRIMARY KEY (`colaboracion_id`),
  KEY `fk_colaboracion_profesor_uv` (`profesor_uv_id`),
  KEY `fk_colaboracion_profesor_externo` (`profesor_externo_id`),
  KEY `fk_colaboracion_experiencia_educativa` (`experiencia_educativa_id`),
  KEY `fk_colaboracion_evidencia` (`evidencia_id`),
  CONSTRAINT `fk_colaboracion_evidencia` FOREIGN KEY (`evidencia_id`) REFERENCES `evidencia` (`evidencia_id`),
  CONSTRAINT `fk_colaboracion_experiencia_educativa` FOREIGN KEY (`experiencia_educativa_id`) REFERENCES `experienciaeducativa` (`experiencia_educativa_id`),
  CONSTRAINT `fk_colaboracion_profesor_externo` FOREIGN KEY (`profesor_externo_id`) REFERENCES `profesorexterno` (`profesor_id`),
  CONSTRAINT `fk_colaboracion_profesor_uv` FOREIGN KEY (`profesor_uv_id`) REFERENCES `profesoruv` (`profesor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colaboracion`
--

LOCK TABLES `colaboracion` WRITE;
/*!40000 ALTER TABLE `colaboracion` DISABLE KEYS */;
INSERT INTO `colaboracion` VALUES (86,'3 weeks','Concluida','2023-02-15','2023-02-01',30,'Febrero 2023-Julio 2023','Clase espejo','Estudio de Interculturalidad',11,16,1,1),(87,'4 weeks','En progreso','2023-01-31','2023-01-01',20,'Agosto 2023-Enero 2024','Taller COIL-VIC','Conocer Otras Culturas',12,20,2,2),(88,'5 weeks','Concluida','2024-07-05','2024-02-01',15,'Febrero 2024-Julio 2024','Clase espejo','Análisis de Conocimiento del Inglés',13,17,3,3);
/*!40000 ALTER TABLE `colaboracion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dependencia`
--

DROP TABLE IF EXISTS `dependencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dependencia` (
  `dependencia_id` int NOT NULL AUTO_INCREMENT,
  `Campus` varchar(255) DEFAULT NULL,
  `Municipio` varchar(255) DEFAULT NULL,
  `NombreDependencia` varchar(255) DEFAULT NULL,
  `area_academica_id` int DEFAULT NULL,
  PRIMARY KEY (`dependencia_id`),
  KEY `fk_dependencia_area_academica` (`area_academica_id`),
  CONSTRAINT `fk_dependencia_area_academica` FOREIGN KEY (`area_academica_id`) REFERENCES `areaacademica` (`area_academica_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dependencia`
--

LOCK TABLES `dependencia` WRITE;
/*!40000 ALTER TABLE `dependencia` DISABLE KEYS */;
INSERT INTO `dependencia` VALUES (1,'Xalapa','Xalapa','Centro de Est., Creación y Doc. De las Artes',1),(2,'Xalapa','Xalapa','Facultad de Artes Plásticas',1),(3,'Xalapa','Xalapa','Instituto de Artes Plásticas',1),(4,'Xalapa','Xalapa','Facultad de Ciencias Biológicas y Agropecuarias-Peñuela',2),(5,'Xalapa','Xalapa','Instituto de Ciencias de la Salud',3),(6,'Xalapa','Xalapa','Unidad Académica de Ciencias de la Salud',3),(7,'Xalapa','Xalapa','Administración y contabilidad',4),(8,'Xalapa','Xalapa','Unidad Académica de Humanidades',5),(9,'Xalapa','Xalapa','Unidad de ingeniería y arquitectura',6),(10,'Tuxpam','Alto Lucero','Facultad de Ciencias Biológicas y Agropecuarias',2),(11,'Amatlán de los Reyes','Córdoba','Facultad de Ciencias Biológicas y Agropecuarias',2),(12,'Coatzacoalcos-Minatitlán','Minatitlán','Facultad de Medicina',3);
/*!40000 ALTER TABLE `dependencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudiante`
--

DROP TABLE IF EXISTS `estudiante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estudiante` (
  `estudiante_id` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(255) DEFAULT NULL,
  `ApellidoMaterno` varchar(255) DEFAULT NULL,
  `ApellidoPaterno` varchar(255) DEFAULT NULL,
  `Matricula` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`estudiante_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiante`
--

LOCK TABLES `estudiante` WRITE;
/*!40000 ALTER TABLE `estudiante` DISABLE KEYS */;
INSERT INTO `estudiante` VALUES (1,'Juan','Pérez','García','zS22013628'),(2,'María','Rodríguez','López','zS22013629'),(3,'Carlos','Gómez','Martínez','zS22013630'),(4,'Ana','Sánchez','Fernández','zS22013631'),(5,'Javier','Ruiz','González','zS22013632'),(6,'Laura','Díaz','Pérez','zS22013633'),(7,'Sergio','Hernández','García','zS22013634'),(8,'Marta','López','Rodríguez','zS22013635'),(9,'Pablo','Martínez','Gómez','zS22013636'),(10,'Andrea','García','Sánchez','zS22013637');
/*!40000 ALTER TABLE `estudiante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudiantecolaboracion`
--

DROP TABLE IF EXISTS `estudiantecolaboracion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estudiantecolaboracion` (
  `colaboracion_id` int NOT NULL,
  `estudiante_id` int NOT NULL,
  `Calificacion` int DEFAULT NULL,
  `Faltas` int DEFAULT NULL,
  `Periodo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`colaboracion_id`,`estudiante_id`),
  KEY `fk_estudiante_colaboracion_estudiante` (`estudiante_id`),
  CONSTRAINT `fk_estudiante_colaboracion_colaboracion` FOREIGN KEY (`colaboracion_id`) REFERENCES `colaboracion` (`colaboracion_id`),
  CONSTRAINT `fk_estudiante_colaboracion_estudiante` FOREIGN KEY (`estudiante_id`) REFERENCES `estudiante` (`estudiante_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiantecolaboracion`
--

LOCK TABLES `estudiantecolaboracion` WRITE;
/*!40000 ALTER TABLE `estudiantecolaboracion` DISABLE KEYS */;
/*!40000 ALTER TABLE `estudiantecolaboracion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evidencia`
--

DROP TABLE IF EXISTS `evidencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evidencia` (
  `evidencia_id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `evidencia_pdf_id` int DEFAULT NULL,
  `evidencia_zip_id` int DEFAULT NULL,
  PRIMARY KEY (`evidencia_id`),
  KEY `fk_evidencia_pdf` (`evidencia_pdf_id`),
  KEY `fk_evidencia_zip` (`evidencia_zip_id`),
  CONSTRAINT `fk_evidencia_pdf` FOREIGN KEY (`evidencia_pdf_id`) REFERENCES `evidenciapdf` (`evidencia_pdf_id`),
  CONSTRAINT `fk_evidencia_zip` FOREIGN KEY (`evidencia_zip_id`) REFERENCES `evidenciazip` (`evidencia_zip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evidencia`
--

LOCK TABLES `evidencia` WRITE;
/*!40000 ALTER TABLE `evidencia` DISABLE KEYS */;
INSERT INTO `evidencia` VALUES (1,'Evidencia 1','Descripción de la Evidencia 1',1,1),(2,'Evidencia 2','Descripción de la Evidencia 2',2,2),(3,'Evidencia 3','Descripción de la Evidencia 3',3,3);
/*!40000 ALTER TABLE `evidencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evidenciapdf`
--

DROP TABLE IF EXISTS `evidenciapdf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evidenciapdf` (
  `evidencia_pdf_id` int NOT NULL AUTO_INCREMENT,
  `archivo_pdf` blob,
  PRIMARY KEY (`evidencia_pdf_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evidenciapdf`
--

LOCK TABLES `evidenciapdf` WRITE;
/*!40000 ALTER TABLE `evidenciapdf` DISABLE KEYS */;
INSERT INTO `evidenciapdf` VALUES (1,_binary ''),(2,_binary ''),(3,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0');
/*!40000 ALTER TABLE `evidenciapdf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evidenciazip`
--

DROP TABLE IF EXISTS `evidenciazip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evidenciazip` (
  `evidencia_zip_id` int NOT NULL AUTO_INCREMENT,
  `archivo_zip` blob,
  PRIMARY KEY (`evidencia_zip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evidenciazip`
--

LOCK TABLES `evidenciazip` WRITE;
/*!40000 ALTER TABLE `evidenciazip` DISABLE KEYS */;
INSERT INTO `evidenciazip` VALUES (1,_binary ''),(2,_binary ''),(3,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0');
/*!40000 ALTER TABLE `evidenciazip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experienciaeducativa`
--

DROP TABLE IF EXISTS `experienciaeducativa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `experienciaeducativa` (
  `experiencia_educativa_id` int NOT NULL AUTO_INCREMENT,
  `Creditos` int DEFAULT NULL,
  `Descripcion` varchar(255) DEFAULT NULL,
  `NombreExperienciaEducativa` varchar(255) DEFAULT NULL,
  `programa_educativo_id` int DEFAULT NULL,
  PRIMARY KEY (`experiencia_educativa_id`),
  KEY `programa_educativo_id` (`programa_educativo_id`),
  CONSTRAINT `experienciaeducativa_ibfk_1` FOREIGN KEY (`programa_educativo_id`) REFERENCES `programaeducativo` (`programa_educativo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experienciaeducativa`
--

LOCK TABLES `experienciaeducativa` WRITE;
/*!40000 ALTER TABLE `experienciaeducativa` DISABLE KEYS */;
INSERT INTO `experienciaeducativa` VALUES (1,120,'Curso técnico centrado en el dibujo y la pintura, abarcando técnicas tradicionales y digitales.','Dibujo y Pintura',1),(2,240,'Programa de estudios que profundiza en la teoría y práctica de las artes visuales, incluyendo historia del arte, técnicas de dibujo y pintura, escultura, entre otros.','Artes Visuales',2),(3,220,'Enfoque en el diseño gráfico, la comunicación visual y la creación de mensajes visuales efectivos.','Diseño de la Comunicación Visual',3),(4,200,'Exploración de la fotografía como medio artístico y documental, abarcando técnicas de captura, composición y edición.','Fotografía',4),(5,60,'Enfoque en el diseño de carteles publicitarios y promocionales, desarrollando habilidades creativas y técnicas.','Diseño de Cartel',5),(6,260,'Estudio de los procesos industriales relacionados con la producción agropecuaria, incluyendo manejo de recursos, procesamiento de alimentos y gestión de proyectos.','Agroindustrial',6),(7,280,'Aplicación de herramientas biotecnológicas en la industria agrícola, farmacéutica y alimentaria, con énfasis en la investigación y desarrollo de productos innovadores.','Biotecnología',7),(8,240,'Estudio de los sistemas de producción pecuaria, incluyendo manejo de ganado, nutrición animal, reproducción y sanidad.','Sistemas Pecuarios',8),(9,280,'Formación integral en agricultura y recursos naturales, abordando temas como manejo de cultivos, conservación de suelos y gestión ambiental.','Agronomía',9),(10,240,'Intersección entre la agricultura y la biotecnología, con énfasis en la mejora genética de cultivos, biofertilizantes y bioplaguicidas.','Agrobiotecnología',10),(11,320,'Formación en odontología clínica y quirúrgica, incluyendo diagnóstico, tratamiento y prevención de enfermedades bucodentales.','Cirujano Dentista',11),(12,280,'Programa de estudios que prepara a profesionales de enfermería para brindar cuidados integrales a individuos y comunidades.','Enfermería',12),(13,360,'Formación médica completa que incluye conocimientos teóricos, habilidades clínicas y prácticas profesionales en diferentes áreas de la medicina.','Médico Cirujano',13),(14,240,'Estudio de la nutrición humana y dietética, abordando aspectos relacionados con la alimentación saludable, la prevención y el tratamiento de enfermedades.','Nutrición',14),(15,300,'Formación en quiropráctica centrada en el diagnóstico y tratamiento de trastornos musculoesqueléticos, mediante técnicas manuales y terapias específicas.','Quiropráctica',15),(16,240,'Estudio de los principios y prácticas de gestión empresarial, incluyendo planificación estratégica, organización, dirección y control.','Administración',16),(17,220,'Formación en contabilidad financiera, costos, auditoría y fiscalidad, con énfasis en la aplicación de normativas contables.','Contaduría',17),(18,260,'Enfoque en el liderazgo, la gestión estratégica y la toma de decisiones empresariales en entornos competitivos y cambiantes.','Gestión y Dirección de Negocios',18),(19,280,'Estudio de la gestión de empresas en contextos internacionales, abordando aspectos como comercio exterior, finanzas internacionales y estrategias de expansión global.','Administración de Negocios Internacionales',19),(20,240,'Programa de estudios centrado en la creación y gestión de mensajes publicitarios, relaciones públicas y comunicación corporativa.','Publicidad y Relaciones Públicas',20),(21,220,'Estudio de las sociedades humanas desde una perspectiva histórica, analizando procesos culturales, políticos y sociales a lo largo del tiempo.','Antropología Histórica',21),(22,200,'Enfoque en el estudio de la diversidad lingüística y los procesos de comunicación humana, desde una perspectiva antropológica y sociolingüística.','Antropología Lingüística',22),(23,240,'Análisis de las estructuras sociales, las relaciones de poder y los procesos de cambio cultural en diferentes contextos socioculturales.','Antropología Social',23),(24,200,'Estudio de las sociedades pasadas a través del análisis de restos materiales y evidencias arqueológicas, incluyendo excavaciones y análisis de contextos culturales.','Arqueología',24),(25,240,'Enfoque en la teoría y práctica de la comunicación, abordando medios de comunicación, procesos comunicativos y análisis de mensajes en contextos sociales y culturales.','Ciencias de la Comunicación',25),(26,280,'Estudio del diseño y la construcción de espacios arquitectónicos, integrando aspectos estéticos, funcionales y tecnológicos.','Arquitectura',26),(27,300,'Formación en el diseño, construcción y mantenimiento de infraestructuras civiles, como carreteras, puentes y edificaciones.','Ingeniería Civil',27),(28,320,'Estudio de los principios y aplicaciones de la ingeniería mecánica en la industria, abarcando diseño de máquinas, sistemas de energía y procesos de fabricación.','Ingeniería Mecánica',28),(29,340,'Enfoque en la generación, transmisión y utilización de la energía eléctrica, incluyendo sistemas de potencia, electrónica de potencia y control de procesos.','Ingeniería Eléctrica',29),(30,360,'Intersección entre la ingeniería mecánica y eléctrica, abordando temas como automatización, robótica y sistemas mecatrónicos.','Ingeniería Mecánica Eléctrica',30);
/*!40000 ALTER TABLE `experienciaeducativa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `idioma`
--

DROP TABLE IF EXISTS `idioma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `idioma` (
  `idioma_id` int NOT NULL AUTO_INCREMENT,
  `Idioma` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idioma_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idioma`
--

LOCK TABLES `idioma` WRITE;
/*!40000 ALTER TABLE `idioma` DISABLE KEYS */;
INSERT INTO `idioma` VALUES (1,'Inglés'),(2,'Español'),(3,'Francés'),(4,'Alemán'),(5,'Italiano');
/*!40000 ALTER TABLE `idioma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ofertacolaboracion`
--

DROP TABLE IF EXISTS `ofertacolaboracion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ofertacolaboracion` (
  `oferta_colaboracion_id` int NOT NULL AUTO_INCREMENT,
  `Duracion` varchar(255) DEFAULT NULL,
  `Idioma` int DEFAULT NULL,
  `Periodo` varchar(255) DEFAULT NULL,
  `Titulo` varchar(255) DEFAULT NULL,
  `profesor_id` int DEFAULT NULL,
  `type` int DEFAULT NULL,
  PRIMARY KEY (`oferta_colaboracion_id`),
  KEY `fk_oferta_colaboracion_profesor` (`profesor_id`),
  CONSTRAINT `fk_oferta_colaboracion_profesor` FOREIGN KEY (`profesor_id`) REFERENCES `profesor` (`profesor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ofertacolaboracion`
--

LOCK TABLES `ofertacolaboracion` WRITE;
/*!40000 ALTER TABLE `ofertacolaboracion` DISABLE KEYS */;
INSERT INTO `ofertacolaboracion` VALUES (1,'2 months',1,'Diciembre-2023','Introduction to Spanish',11,0),(2,'3 months',2,'Julio-2023','Advanced French',12,0),(3,'1 month',3,'Diciembre-2023','Beginner German',13,0),(4,'6 months',4,'Julio-2024','Conversational Italian',14,0),(5,'4 months',5,'Diciembre-2024','Portuguese for Business',15,0),(6,'2 months',1,'Julio-2024','Intermediate Spanish',16,1),(7,'3 months',2,'Diciembre-2023','French Literature',17,1),(8,'1 month',3,'Julio-2023','German Grammar',18,1),(9,'6 months',4,'Diciembre-2024','Italian Culture',19,1),(10,'4 months',5,'Julio-2023','Brazilian Portuguese',20,1);
/*!40000 ALTER TABLE `ofertacolaboracion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ofertacolaboracionexterna`
--

DROP TABLE IF EXISTS `ofertacolaboracionexterna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ofertacolaboracionexterna` (
  `oferta_colaboracion_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`oferta_colaboracion_id`),
  CONSTRAINT `fk_oferta_colaboracion_externa` FOREIGN KEY (`oferta_colaboracion_id`) REFERENCES `ofertacolaboracion` (`oferta_colaboracion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ofertacolaboracionexterna`
--

LOCK TABLES `ofertacolaboracionexterna` WRITE;
/*!40000 ALTER TABLE `ofertacolaboracionexterna` DISABLE KEYS */;
/*!40000 ALTER TABLE `ofertacolaboracionexterna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ofertacolaboracionuv`
--

DROP TABLE IF EXISTS `ofertacolaboracionuv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ofertacolaboracionuv` (
  `oferta_colaboracion_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`oferta_colaboracion_id`),
  CONSTRAINT `fk_oferta_colaboracion_uv` FOREIGN KEY (`oferta_colaboracion_id`) REFERENCES `ofertacolaboracion` (`oferta_colaboracion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ofertacolaboracionuv`
--

LOCK TABLES `ofertacolaboracionuv` WRITE;
/*!40000 ALTER TABLE `ofertacolaboracionuv` DISABLE KEYS */;
/*!40000 ALTER TABLE `ofertacolaboracionuv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor`
--

DROP TABLE IF EXISTS `profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesor` (
  `profesor_id` int NOT NULL AUTO_INCREMENT,
  `CorreoElectronico` varchar(255) DEFAULT NULL,
  `Telefono` varchar(255) DEFAULT NULL,
  `idioma_id` int DEFAULT NULL,
  `Nombre` varchar(255) DEFAULT NULL,
  `ApellidoPaterno` varchar(255) DEFAULT NULL,
  `ApellidoMaterno` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`profesor_id`),
  KEY `fk_profesor_idioma` (`idioma_id`),
  CONSTRAINT `fk_profesor_idioma` FOREIGN KEY (`idioma_id`) REFERENCES `idioma` (`idioma_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor`
--

LOCK TABLES `profesor` WRITE;
/*!40000 ALTER TABLE `profesor` DISABLE KEYS */;
INSERT INTO `profesor` VALUES (11,'profesor1@example.com','+1234567890',1,'Juan','Pérez','García'),(12,'profesor2@example.com','+1987654321',2,'María','Rodríguez','López'),(13,'profesor3@example.com','+1122334455',3,'Carlos','Gómez','Martínez'),(14,'profesor4@example.com','+9876543210',4,'Ana','Sánchez','Fernández'),(15,'profesor5@example.com','+1357924680',5,'Javier','Ruiz','González'),(16,'profesor6@example.com','+1122334455',1,'Laura','Díaz','Pérez'),(17,'profesor7@example.com','+1234567890',2,'Sergio','Hernández','García'),(18,'profesor8@example.com','+9876543210',3,'Marta','López','Rodríguez'),(19,'profesor9@example.com','+1987654321',4,'Pablo','Martínez','Gómez'),(20,'profesor10@example.com','+1357924680',5,'Andrea','García','Sánchez');
/*!40000 ALTER TABLE `profesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesorexterno`
--

DROP TABLE IF EXISTS `profesorexterno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesorexterno` (
  `profesor_id` int NOT NULL AUTO_INCREMENT,
  `universidad_id` int DEFAULT NULL,
  PRIMARY KEY (`profesor_id`),
  KEY `fk_profesorexterno_universidad` (`universidad_id`),
  CONSTRAINT `fk_profesorexterno_profesor` FOREIGN KEY (`profesor_id`) REFERENCES `profesor` (`profesor_id`),
  CONSTRAINT `fk_profesorexterno_universidad` FOREIGN KEY (`universidad_id`) REFERENCES `universidad` (`universidad_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesorexterno`
--

LOCK TABLES `profesorexterno` WRITE;
/*!40000 ALTER TABLE `profesorexterno` DISABLE KEYS */;
INSERT INTO `profesorexterno` VALUES (16,1),(20,1),(17,2),(18,3),(19,4);
/*!40000 ALTER TABLE `profesorexterno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesoruv`
--

DROP TABLE IF EXISTS `profesoruv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesoruv` (
  `profesor_id` int NOT NULL AUTO_INCREMENT,
  `NumeroPersonal` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`profesor_id`),
  CONSTRAINT `fk_profesoruv_profesor` FOREIGN KEY (`profesor_id`) REFERENCES `profesor` (`profesor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesoruv`
--

LOCK TABLES `profesoruv` WRITE;
/*!40000 ALTER TABLE `profesoruv` DISABLE KEYS */;
INSERT INTO `profesoruv` VALUES (11,'UV2024001'),(12,'UV2024002'),(13,'UV2024003'),(14,'UV2024004'),(15,'UV2024005');
/*!40000 ALTER TABLE `profesoruv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programaeducativo`
--

DROP TABLE IF EXISTS `programaeducativo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `programaeducativo` (
  `programa_educativo_id` int NOT NULL AUTO_INCREMENT,
  `AnioInicio` int DEFAULT NULL,
  `NombreProgramaEducativo` varchar(255) DEFAULT NULL,
  `dependencia_id` int DEFAULT NULL,
  PRIMARY KEY (`programa_educativo_id`),
  KEY `fk_programa_educativo_dependencia` (`dependencia_id`),
  CONSTRAINT `fk_programa_educativo_dependencia` FOREIGN KEY (`dependencia_id`) REFERENCES `dependencia` (`dependencia_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programaeducativo`
--

LOCK TABLES `programaeducativo` WRITE;
/*!40000 ALTER TABLE `programaeducativo` DISABLE KEYS */;
INSERT INTO `programaeducativo` VALUES (1,2018,'Técnico en Dibujo y Pintura',1),(2,2015,'Licenciatura en Artes Visuales',2),(3,2016,'Licenciatura en Diseño de la Comunicación Visual',3),(4,2017,'Licenciatura en Fotografía',1),(5,2020,'Especialización en Diseño de Cartel',2),(6,2019,'Ingeniería Agroindustrial',4),(7,2016,'Ingeniería en Biotecnología',4),(8,2017,'Ingeniería en Sistemas Pecuarios',4),(9,2018,'Ingeniero Agrónomo',4),(10,2020,'Licenciatura en Agrobiotecnología',4),(11,2018,'Cirujano Dentista',5),(12,2017,'Enfermería',5),(13,2016,'Médico cirujano',5),(14,2019,'Nutrición',6),(15,2020,'Quiropráctica',6),(16,2016,'Administración',7),(17,2017,'Contaduría',7),(18,2018,'Gestión y dirección de negocios',7),(19,2019,'Administración de Negocios Internacionales',7),(20,2020,'Publicidad y Relaciones Públicas',7),(21,2017,'Antropología Histórica',8),(22,2018,'Antropología Lingüística',8),(23,2019,'Antropología Social',8),(24,2016,'Arqueología',8),(25,2020,'Ciencias de la Comunicación',8),(26,2016,'Arquitectura',9),(27,2017,'Ingeniería Civil',9),(28,2018,'Ingeniería Mecánica',9),(29,2019,'Ingeniería Eléctrica',9),(30,2020,'Ingeniería Mecánica Eléctrica',9);
/*!40000 ALTER TABLE `programaeducativo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `universidad`
--

DROP TABLE IF EXISTS `universidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `universidad` (
  `universidad_id` int NOT NULL AUTO_INCREMENT,
  `Pais` varchar(255) DEFAULT NULL,
  `Nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`universidad_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `universidad`
--

LOCK TABLES `universidad` WRITE;
/*!40000 ALTER TABLE `universidad` DISABLE KEYS */;
INSERT INTO `universidad` VALUES (1,'Estados Unidos','Universidad de Stanford'),(2,'Reino Unido','Universidad de Oxford'),(3,'Canadá','Universidad de Toronto'),(4,'México','Universidad Veracruzana'),(5,'Estados Unidos','Harvard University'),(6,'Reino Unido','University of Cambridge'),(7,'Estados Unidos','Stanford University'),(8,'Estados Unidos','Massachusetts Institute of Technology (MIT)'),(9,'Reino Unido','University of Oxford'),(10,'Estados Unidos','California Institute of Technology (Caltech)'),(11,'Suiza','ETH Zurich - Swiss Federal Institute of Technology'),(12,'Estados Unidos','University of Chicago'),(13,'Reino Unido','University College London (UCL)'),(14,'Estados Unidos','Yale University');
/*!40000 ALTER TABLE `universidad` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-25 20:12:48
