--
-- Table structure for table `aportesproductores`
--

DROP TABLE IF EXISTS `aportesproductores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aportesproductores` (
  `idAportesProductores` int(11) NOT NULL AUTO_INCREMENT,
  `fechaEntrega` date NOT NULL,
  `cantidad` int(13) NOT NULL,
  `productosAsociadosUsuariosId` int(11) NOT NULL,
  `solicitudDistribuidorId` int(11) NOT NULL,
  `fechaActual` date NOT NULL,
  `estadoAporteProductorId` int(11) NOT NULL,
  `novedad` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`idAportesProductores`),
  KEY `productosAsociadosUsuariosId` (`productosAsociadosUsuariosId`),
  KEY `solicitudDistribuidorId` (`solicitudDistribuidorId`),
  KEY `estadoAporteProductorId` (`estadoAporteProductorId`),
  CONSTRAINT `aportesproductoresId` FOREIGN KEY (`estadoAporteProductorId`) REFERENCES `estadoaportesproductores` (`idEstadoAporteProductor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `productoAsociadoUsuarioId` FOREIGN KEY (`productosAsociadosUsuariosId`) REFERENCES `productosasociadosusuarios` (`idProductosAsociadosUsuarios`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `solicitudDistribuidorId` FOREIGN KEY (`solicitudDistribuidorId`) REFERENCES `solicituddistribuidor` (`idSolicitudDistribuidor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aportesproductores`
--

LOCK TABLES `aportesproductores` WRITE;
/*!40000 ALTER TABLE `aportesproductores` DISABLE KEYS */;
INSERT INTO `aportesproductores` VALUES (1,'2015-11-07',2000,10,2,'2015-11-07',1,'dcsvds'),(2,'2015-11-07',2000,11,2,'2015-11-07',1,NULL),(3,'2015-11-07',2500,11,1,'2015-11-07',1,NULL),(4,'2015-11-07',2500,11,1,'2015-11-07',1,NULL),(5,'2015-11-07',2500,11,1,'2015-11-07',1,NULL),(6,'2015-11-07',2500,11,1,'2015-11-07',1,NULL),(7,'2015-11-07',2500,11,1,'2015-11-07',1,NULL),(8,'2015-11-07',500,11,1,'2015-11-07',1,NULL),(9,'2015-11-07',200,11,1,'2015-11-07',1,NULL),(10,'2015-11-07',5000,11,1,'2015-11-09',1,NULL),(11,'2015-11-07',1000,11,2,'2015-11-09',1,NULL),(12,'2015-11-23',650,10,1,'2015-11-09',1,NULL),(13,'2015-11-23',650,10,1,'2015-11-09',2,'Prueba'),(14,'2015-11-23',650,10,1,'2015-11-09',1,NULL),(16,'2015-11-14',200,10,2,'2015-11-10',1,NULL),(19,'2015-11-14',200,10,2,'2015-11-10',1,NULL),(22,'2015-11-13',500,10,2,'2015-11-10',1,NULL),(23,'2015-11-14',400,10,3,'2015-11-10',1,NULL),(24,'2015-11-14',600,10,2,'2015-11-10',1,NULL),(25,'2015-11-13',499,10,2,'2015-11-10',1,NULL),(26,'2015-11-14',123,20,2,'2015-11-11',2,'prueba1'),(27,'2015-11-14',666,20,2,'2015-11-12',2,'prueba2'),(28,'2015-11-14',666,20,2,'2015-11-12',2,'prueba3'),(29,'2015-11-14',333,20,2,'2015-11-12',2,'prueba4'),(30,'2015-11-18',899,20,2,'2015-11-12',2,'pruebaderegistro'),(31,'2015-11-18',222,20,2,'2015-11-12',2,'pruebafinal'),(32,'2015-11-18',79,20,2,'2015-11-12',1,NULL),(33,'2015-11-18',700,20,2,'2015-11-12',1,NULL),(34,'2015-11-18',111,18,4,'2015-11-17',2,'Tengo problemas'),(35,'2015-11-18',150,18,4,'2015-11-17',2,'No puedo');
/*!40000 ALTER TABLE `aportesproductores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorias` (
  `idCategorias` int(11) NOT NULL AUTO_INCREMENT,
  `nombreCategoria` varchar(40) NOT NULL,
  PRIMARY KEY (`idCategorias`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Hortalizas'),(2,'Frutas'),(3,'Tuberculos');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `despachospedidos`
--

DROP TABLE IF EXISTS `despachospedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `despachospedidos` (
  `idDespachosPedidos` int(11) NOT NULL AUTO_INCREMENT,
  `direccionEntrega` varchar(45) NOT NULL,
  `fechaEntrega` date NOT NULL,
  `observaciones` varchar(100) NOT NULL,
  `usuariosId` int(11) NOT NULL,
  `solicitudDistribuidorId` int(11) NOT NULL,
  `estadoDespachoId` int(11) NOT NULL,
  PRIMARY KEY (`idDespachosPedidos`),
  KEY `usuariosId` (`usuariosId`),
  KEY `solicitudDistribuidorId` (`solicitudDistribuidorId`),
  KEY `estado` (`estadoDespachoId`),
  CONSTRAINT `despachoPedidoId` FOREIGN KEY (`estadoDespachoId`) REFERENCES `estadosdespachospedidos` (`idEstadoDespachoPedido`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `solicitudesDistribuidorId` FOREIGN KEY (`solicitudDistribuidorId`) REFERENCES `solicituddistribuidor` (`idSolicitudDistribuidor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usuarioId` FOREIGN KEY (`usuariosId`) REFERENCES `usuarios` (`idUsuarios`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `despachospedidos`
--

LOCK TABLES `despachospedidos` WRITE;
/*!40000 ALTER TABLE `despachospedidos` DISABLE KEYS */;
INSERT INTO `despachospedidos` VALUES (3,'Calle de sistema sigaa','2015-11-18','Cantidad incompleta',8,14,2);
/*!40000 ALTER TABLE `despachospedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadoaportesproductores`
--

DROP TABLE IF EXISTS `estadoaportesproductores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estadoaportesproductores` (
  `idEstadoAporteProductor` int(11) NOT NULL AUTO_INCREMENT,
  `nombreEstadoAporteProductor` varchar(20) NOT NULL,
  PRIMARY KEY (`idEstadoAporteProductor`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadoaportesproductores`
--

LOCK TABLES `estadoaportesproductores` WRITE;
/*!40000 ALTER TABLE `estadoaportesproductores` DISABLE KEYS */;
INSERT INTO `estadoaportesproductores` VALUES (1,'aportado'),(2,'cancelado');
/*!40000 ALTER TABLE `estadoaportesproductores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadosdespachospedidos`
--

DROP TABLE IF EXISTS `estadosdespachospedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estadosdespachospedidos` (
  `idEstadoDespachoPedido` int(11) NOT NULL AUTO_INCREMENT,
  `nombreEstadoDespachoPedido` varchar(25) NOT NULL,
  PRIMARY KEY (`idEstadoDespachoPedido`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadosdespachospedidos`
--

LOCK TABLES `estadosdespachospedidos` WRITE;
/*!40000 ALTER TABLE `estadosdespachospedidos` DISABLE KEYS */;
INSERT INTO `estadosdespachospedidos` VALUES (1,'Despachado completamente'),(2,'Despachado sin completar');
/*!40000 ALTER TABLE `estadosdespachospedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadospedidoosofertas`
--

DROP TABLE IF EXISTS `estadospedidoosofertas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estadospedidoosofertas` (
  `idEstadoPedidoOferta` int(11) NOT NULL AUTO_INCREMENT,
  `nombreestadopedidooferta` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`idEstadoPedidoOferta`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadospedidoosofertas`
--

LOCK TABLES `estadospedidoosofertas` WRITE;
/*!40000 ALTER TABLE `estadospedidoosofertas` DISABLE KEYS */;
INSERT INTO `estadospedidoosofertas` VALUES (1,'Solicitado'),(3,'Despachado');
/*!40000 ALTER TABLE `estadospedidoosofertas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadossolicitudesdistribuidores`
--

DROP TABLE IF EXISTS `estadossolicitudesdistribuidores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estadossolicitudesdistribuidores` (
  `idEstadoSolicitudDistribuidor` int(11) NOT NULL AUTO_INCREMENT,
  `nombreEstadoSolicitudDistribuidor` varchar(25) NOT NULL,
  PRIMARY KEY (`idEstadoSolicitudDistribuidor`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadossolicitudesdistribuidores`
--

LOCK TABLES `estadossolicitudesdistribuidores` WRITE;
/*!40000 ALTER TABLE `estadossolicitudesdistribuidores` DISABLE KEYS */;
INSERT INTO `estadossolicitudesdistribuidores` VALUES (1,'Solicitado'),(2,'Cancelado'),(3,'Para despachar'),(4,'Despachado'),(5,'En proceso');
/*!40000 ALTER TABLE `estadossolicitudesdistribuidores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadosusuarios`
--

DROP TABLE IF EXISTS `estadosusuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estadosusuarios` (
  `idEstadoUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombreEstadoUsuario` varchar(25) NOT NULL,
  PRIMARY KEY (`idEstadoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadosusuarios`
--

LOCK TABLES `estadosusuarios` WRITE;
/*!40000 ALTER TABLE `estadosusuarios` DISABLE KEYS */;
INSERT INTO `estadosusuarios` VALUES (1,'Registro sin permisos'),(2,'Distribuidor '),(3,'Asociacion'),(4,'Deshabilitado'),(5,'Productor');
/*!40000 ALTER TABLE `estadosusuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensajes`
--

DROP TABLE IF EXISTS `mensajes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mensajes` (
  `idMensaje` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(50) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idMensaje`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensajes`
--

LOCK TABLES `mensajes` WRITE;
/*!40000 ALTER TABLE `mensajes` DISABLE KEYS */;
/*!40000 ALTER TABLE `mensajes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ofertas`
--

DROP TABLE IF EXISTS `ofertas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ofertas` (
  `idOfertas` int(11) NOT NULL AUTO_INCREMENT,
  `productosAsociadosUsuariosId` int(11) NOT NULL,
  `precio` float(17,0) DEFAULT NULL,
  `cantidad` int(11) NOT NULL,
  `fechaPublic` date NOT NULL,
  `fechaFin` date NOT NULL,
  `estado` tinyint(3) NOT NULL DEFAULT '1',
  `cantidadOfertaFinal` int(11) NOT NULL,
  PRIMARY KEY (`idOfertas`),
  KEY `productosAsociadosUsuariosId` (`productosAsociadosUsuariosId`),
  CONSTRAINT `productosAsociadosUsuariosId` FOREIGN KEY (`productosAsociadosUsuariosId`) REFERENCES `productosasociadosusuarios` (`idProductosAsociadosUsuarios`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ofertas`
--

LOCK TABLES `ofertas` WRITE;
/*!40000 ALTER TABLE `ofertas` DISABLE KEYS */;
INSERT INTO `ofertas` VALUES (1,16,900,0,'2015-11-04','2015-11-26',2,0),(2,15,1000,7556,'2015-11-04','2015-11-19',1,0),(3,14,2200,5000,'2015-11-04','2015-11-19',1,0),(4,13,1500,7709,'2015-11-04','2015-11-19',1,0),(5,17,990,0,'2015-11-09','2015-11-24',2,5000),(6,20,850,6778,'2015-11-17','2015-12-02',2,7000);
/*!40000 ALTER TABLE `ofertas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidosofertas`
--

DROP TABLE IF EXISTS `pedidosofertas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedidosofertas` (
  `idPedidosOfertas` int(11) NOT NULL AUTO_INCREMENT,
  `cantidadSolicitada` int(11) NOT NULL,
  `fechaSolicitada` date NOT NULL,
  `fechaActual` date NOT NULL,
  `estadoPedidoOfertaId` int(11) NOT NULL,
  `ofertasId` int(11) NOT NULL,
  `usuarioSolicitadoId` int(11) NOT NULL,
  PRIMARY KEY (`idPedidosOfertas`),
  KEY `ofertasId` (`ofertasId`),
  KEY `estadoPedidoOfertaId` (`estadoPedidoOfertaId`),
  KEY `usuarioSolicitadoId` (`usuarioSolicitadoId`),
  CONSTRAINT `ofertasId` FOREIGN KEY (`ofertasId`) REFERENCES `ofertas` (`idOfertas`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pedidoOfertaId` FOREIGN KEY (`estadoPedidoOfertaId`) REFERENCES `estadospedidoosofertas` (`idEstadoPedidoOferta`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usuarioSolicitadoId` FOREIGN KEY (`usuarioSolicitadoId`) REFERENCES `usuarios` (`idUsuarios`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidosofertas`
--

LOCK TABLES `pedidosofertas` WRITE;
/*!40000 ALTER TABLE `pedidosofertas` DISABLE KEYS */;
INSERT INTO `pedidosofertas` VALUES (1,300,'2015-11-13','2015-11-09',3,2,9),(3,300,'2015-11-16','2015-11-09',3,2,9),(5,300,'2015-11-15','2015-11-09',1,2,9),(6,100,'2015-11-15','2015-11-09',1,2,9),(7,888,'2015-11-06','2015-11-09',3,3,9),(8,1200,'2015-11-23','2015-11-10',1,5,9),(9,1111,'2015-11-12','2015-11-14',3,5,9),(10,1111,'2015-11-12','2015-11-14',1,5,9),(11,1111,'2015-11-12','2015-11-14',3,5,9),(12,2111,'2015-11-18','2015-11-14',3,3,9),(13,67,'2015-11-19','2015-11-14',1,5,9),(14,50,'2015-11-19','2015-11-14',1,5,9),(15,444,'2015-11-18','2015-11-14',3,2,9),(16,50,'2015-11-18','2015-11-14',1,5,9),(17,889,'2015-11-16','2015-11-14',1,3,1),(18,300,'2015-11-23','2015-11-17',1,5,9),(19,200,'2015-11-22','2015-11-17',1,1,9),(20,222,'2015-11-22','2015-11-17',1,6,9);
/*!40000 ALTER TABLE `pedidosofertas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permisos`
--

DROP TABLE IF EXISTS `permisos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permisos` (
  `idPermisos` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(120) DEFAULT NULL,
  `url` varchar(90) NOT NULL,
  `padre` varchar(30) NOT NULL,
  PRIMARY KEY (`idPermisos`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permisos`
--

LOCK TABLES `permisos` WRITE;
/*!40000 ALTER TABLE `permisos` DISABLE KEYS */;
INSERT INTO `permisos` VALUES (1,'Listar Promociones','Listar Promociones','0'),(2,'Solicitar producto de una oferta','listarofertasactuales.jsp','1'),(3,'Gestión de Pedidos','Gestión de Pedidos','0'),(4,'Realizar Pedido Asociación','realizarpedidoasociacion.jsp','3'),(5,'Realizar aporte a un pedido de la asociación','listarsolicitudesproductores.jsp','3'),(6,'Productos','Productos','0'),(7,'Asociar Producto','asociarproducto.jsp','6'),(8,'Generar Pedido','listarsolicitudesasociaciones.jsp','3'),(9,'Gestión Usuarios','Gestión Usuarios','0'),(10,'Enviar Solicitudes','notificaciones.jsp','9'),(11,'Despachar Pedidos','listardespachos.jsp','3'),(12,'Vincular a Asociación','tablagestionarrol.jsp','9'),(13,'Generar Reportes','Generar Reportes','0'),(14,'Historial Ofertas','reporteofertas.jsp','13'),(15,'Mis productos','misproductos.jsp','6'),(16,'Mis ofertas','Mis ofertas','0'),(17,'Ofertas Realizadas','misofertas.jsp','16'),(18,'Participaciones a pedidos','misparticipaciones.jsp','3'),(19,'Mis Pedidos','Pedidos','0'),(20,'Listar pedidos sobre promociones','listarmispedidosdeofertas.jsp','19'),(21,'Listar mis pedidos a la asociación','listarmissolicitudesaunaasociacion.jsp','19'),(22,'Listar catálogo de productos','consultarproductos.jsp','19'),(23,'Consultar pedidos','consultarpedidos.jsp','3'),(24,'Pedidos sobre mis ofertas publicadas','consultarmispedidossobremisofertas.jsp','16'),(25,'Certificación','Certificación','0'),(26,'Descargar certificación por ser parte de una asociación','descargarcertificado.jsp','25');
/*!40000 ALTER TABLE `permisos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permisosroles`
--

DROP TABLE IF EXISTS `permisosroles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permisosroles` (
  `permisosId` int(11) NOT NULL,
  `rolesId` int(11) NOT NULL,
  `estado` tinyint(2) NOT NULL,
  PRIMARY KEY (`permisosId`,`rolesId`),
  KEY `permisosId` (`permisosId`),
  KEY `rolesId` (`rolesId`),
  CONSTRAINT `permisosId` FOREIGN KEY (`permisosId`) REFERENCES `permisos` (`idPermisos`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rolesId` FOREIGN KEY (`rolesId`) REFERENCES `roles` (`idRoles`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permisosroles`
--

LOCK TABLES `permisosroles` WRITE;
/*!40000 ALTER TABLE `permisosroles` DISABLE KEYS */;
INSERT INTO `permisosroles` VALUES (1,3,1),(2,3,1),(3,1,1),(3,2,1),(3,3,1),(4,3,1),(5,2,1),(6,1,1),(6,2,1),(7,1,1),(7,2,1),(8,1,1),(9,1,1),(10,1,1),(11,1,1),(12,1,1),(13,4,1),(14,4,1),(15,2,1),(16,2,1),(17,2,1),(18,2,1),(19,3,1),(20,3,1),(21,3,1),(22,3,1),(23,1,1),(24,2,1),(25,2,1),(26,2,1);
/*!40000 ALTER TABLE `permisosroles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `idProductos` int(11) NOT NULL AUTO_INCREMENT,
  `nombreProducto` varchar(40) NOT NULL,
  `unidad` varchar(15) NOT NULL DEFAULT 'Kilogramos',
  `categoriasId` int(11) NOT NULL,
  `estado` tinyint(2) NOT NULL DEFAULT '1',
  `precioProducto` float(10,0) DEFAULT NULL,
  PRIMARY KEY (`idProductos`),
  KEY `categoriasId` (`categoriasId`),
  CONSTRAINT `categoriasId` FOREIGN KEY (`categoriasId`) REFERENCES `categorias` (`idCategorias`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Papaya','kilogramos',2,1,12000),(2,'Platano','kilogramos',3,1,12000),(3,'Apio','kilogramos',1,1,12000),(4,'Tomate','kilogramos',3,1,12000),(5,'Cebolla blanca','kilogramos',3,1,12000),(6,'Cebolla roja','kilogramos',3,1,12000),(7,'Habichuela','kilogramos',1,1,12000),(8,'Papa','kilogramos',3,1,12000),(9,'Piña','kilogramos',2,1,12000),(10,'Yuva llanera','kilogramos',1,1,12000),(11,'Mango','kilogramos',2,1,12000),(12,'Sandia','kilogramos',2,1,12000),(13,'Banano','kilogramos',2,1,12000),(15,'Mora','kilogramos',2,1,12000),(16,'Granadilla','kilogramos',2,1,12000),(17,'Fresa','kilogramos',2,1,1111),(18,'Zanahoria','kilogramos',3,1,12000),(19,'Coliflor','kilogramos',1,1,800);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productosasociadosusuarios`
--

DROP TABLE IF EXISTS `productosasociadosusuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productosasociadosusuarios` (
  `idProductosAsociadosUsuarios` int(11) NOT NULL AUTO_INCREMENT,
  `usuariosId` int(11) NOT NULL,
  `productosId` int(11) NOT NULL,
  `estado` int(12) DEFAULT '1',
  PRIMARY KEY (`idProductosAsociadosUsuarios`,`usuariosId`,`productosId`),
  KEY `usuariosId` (`usuariosId`),
  KEY `productosId` (`productosId`),
  KEY `idProductosAsociadosUsuarios` (`idProductosAsociadosUsuarios`),
  CONSTRAINT `productoAsociadoId` FOREIGN KEY (`productosId`) REFERENCES `productos` (`idProductos`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usuarioAsociadoId` FOREIGN KEY (`usuariosId`) REFERENCES `usuarios` (`idUsuarios`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productosasociadosusuarios`
--

LOCK TABLES `productosasociadosusuarios` WRITE;
/*!40000 ALTER TABLE `productosasociadosusuarios` DISABLE KEYS */;
INSERT INTO `productosasociadosusuarios` VALUES (1,1,1,1),(2,2,2,1),(3,3,3,1),(4,3,2,1),(5,4,1,2),(7,5,7,1),(8,6,8,2),(10,7,10,1),(11,8,6,1),(12,9,7,1),(13,10,8,2),(14,10,12,2),(15,10,10,2),(16,10,11,2),(17,10,4,2),(18,10,19,1),(19,10,3,1),(20,10,6,2),(21,1,3,1),(22,7,19,1),(23,8,4,1);
/*!40000 ALTER TABLE `productosasociadosusuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `idRoles` int(10) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `estado` tinyint(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idRoles`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Asociacion',1),(2,'Productor',1),(3,'Distribuidor',1),(4,'Administrador',1);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rolesusuarios`
--

DROP TABLE IF EXISTS `rolesusuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rolesusuarios` (
  `rolesId` int(11) NOT NULL,
  `usuariosId` int(11) NOT NULL,
  `estado` tinyint(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`rolesId`,`usuariosId`),
  KEY `rolesId` (`rolesId`),
  KEY `usuariosId` (`usuariosId`),
  CONSTRAINT `rolId` FOREIGN KEY (`rolesId`) REFERENCES `roles` (`idRoles`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usuariosId` FOREIGN KEY (`usuariosId`) REFERENCES `usuarios` (`idUsuarios`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rolesusuarios`
--

LOCK TABLES `rolesusuarios` WRITE;
/*!40000 ALTER TABLE `rolesusuarios` DISABLE KEYS */;
INSERT INTO `rolesusuarios` VALUES (1,8,1),(2,3,1),(2,6,1),(2,10,1),(2,11,1),(3,9,1),(4,7,1);
/*!40000 ALTER TABLE `rolesusuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicituddistribuidor`
--

DROP TABLE IF EXISTS `solicituddistribuidor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `solicituddistribuidor` (
  `idSolicitudDistribuidor` int(11) NOT NULL AUTO_INCREMENT,
  `cantidadSolicitada` int(11) NOT NULL,
  `fechaActual` date NOT NULL,
  `fechaSolicitud` date NOT NULL,
  `fechaEntregaInterna` date DEFAULT NULL,
  `estadoSolicitudDistribuidorId` int(11) NOT NULL,
  `productosId` int(11) NOT NULL,
  `distribuidorId` int(11) NOT NULL,
  `cantidadSolicitudFinal` int(11) NOT NULL,
  `observacion` varchar(80) DEFAULT NULL,
  `precioSolicitud` float(15,0) NOT NULL,
  PRIMARY KEY (`idSolicitudDistribuidor`),
  KEY `productosId` (`productosId`),
  KEY `estadoSolicitudDistribuidorId` (`estadoSolicitudDistribuidorId`),
  CONSTRAINT `estadoSolicitudDistribuidorId` FOREIGN KEY (`estadoSolicitudDistribuidorId`) REFERENCES `estadossolicitudesdistribuidores` (`idEstadoSolicitudDistribuidor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `productosId` FOREIGN KEY (`productosId`) REFERENCES `productos` (`idProductos`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicituddistribuidor`
--

LOCK TABLES `solicituddistribuidor` WRITE;
/*!40000 ALTER TABLE `solicituddistribuidor` DISABLE KEYS */;
INSERT INTO `solicituddistribuidor` VALUES (1,11300,'2015-11-04','2015-11-14','2015-11-13',3,19,9,0,'prueba',0),(2,2000,'2015-11-07','2015-11-26','2015-11-24',3,3,9,0,NULL,0),(3,100,'2015-11-09','2015-11-17','2015-11-14',3,3,9,10000,NULL,0),(4,500,'2015-11-13','2015-11-30','2015-11-20',3,19,8,500,NULL,0),(5,8888,'2015-11-13','2015-11-19',NULL,2,15,9,8888,NULL,0),(6,7888,'2015-11-13','2015-11-19',NULL,1,7,9,7888,NULL,0),(7,222,'2015-11-13','2015-11-19',NULL,1,13,9,222,NULL,0),(8,566,'2015-11-13','2015-11-19',NULL,1,9,9,566,'Prueba',0),(9,454,'2015-11-13','2015-11-19',NULL,2,5,9,454,NULL,0),(10,4353,'2015-11-13','2015-11-19',NULL,1,9,9,4353,NULL,0),(11,333,'2015-11-13','2015-11-21',NULL,1,2,9,333,NULL,0),(12,222,'2015-11-13','2015-11-24',NULL,1,16,9,222,NULL,0),(13,999,'2015-11-14','2015-11-20','2015-11-18',3,3,9,999,NULL,0),(14,111,'2015-11-14','2015-11-20','2015-11-18',4,3,9,2222,'Fresco',0),(15,555,'2015-11-15','2015-11-27',NULL,1,18,9,555,'Grande',0),(16,111,'2015-11-16','2015-11-22',NULL,5,7,9,9999,'Prueba 9999',12000);
/*!40000 ALTER TABLE `solicituddistribuidor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `idUsuarios` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Este es el campo del id del usuario',
  `nombres` varchar(45) NOT NULL COMMENT 'Nombre del usuario',
  `apellidos` varchar(45) NOT NULL,
  `cedula` varchar(20) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `correo` varchar(60) NOT NULL,
  `clave` varchar(32) NOT NULL,
  `notificaciones` tinyint(2) NOT NULL,
  `ciudad` varchar(45) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `estadoUser` int(10) NOT NULL DEFAULT '0',
  `genero` int(10) NOT NULL,
  PRIMARY KEY (`idUsuarios`),
  KEY `estadoUser` (`estadoUser`),
  CONSTRAINT `estadoUserId` FOREIGN KEY (`estadoUser`) REFERENCES `estadosusuarios` (`idEstadoUsuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Stefhany ','Alfonso Rincón','1033765432','3534534','Calle 32 - 54 Norte','salfonso9@misena.edu.co','202cb962ac59075b964b07152d234b70',1,'Bogota','1992-11-25',1,1),(2,'Sebastian','Mondragón Rozo','1076576543','7609812','Av 68 N° 87-32','sebastianmondra@misena.edu.co','202cb962ac59075b964b07152d234b70',1,'Bogotá','1989-08-12',1,0),(3,'Nubia','Hernandez Muñoz','1087654870','2147483647','Calle 5 N° 32-17','rodrigoarandaf@hotmail.com','202cb962ac59075b964b07152d234b70',1,'Huila','1989-11-21',2,1),(4,'Juan','Perez','12344','17555','Cra x con y','juan@hotmail.com','202cb962ac59075b964b07152d234b70',1,'Bogota','1987-11-05',1,0),(5,'Juan','Perez','12344','17555','Cra x con y','juan@hotmail.com','202cb962ac59075b964b07152d234b70',1,'Bogota','1987-11-05',1,1),(6,'Nuevo','Nuevo','1223423','23223','lkmckl','mklmlk@gmail.com','d93151d28132fdc8b44229296cf555ad',1,'kmlk','1983-05-09',2,1),(7,'Stefhany','Alfonso','87632478','4567896','Calle de Stefhany Alfonso','salfonsorincon@gmail.com','1293d80c446408ac9d4847a78581cc7c',1,'Bogotá D.C.','1986-12-24',2,1),(8,'Contacto','Sigaa','198733','45678966','Calle de contacto sigaa','contactosigaa@gmail.com','0d87b5c76b4c480a107b621cc67f469d',1,'Bogotá D.C.','1989-09-10',3,0),(9,'Sistema','Sigaa','19865733','4561966','Calle de sistema sigaa','sistemasigaa@gmail.com','4c91d222b139eeccd6eb342f512ec180',1,'Bogotá D.C.','1972-03-07',2,0),(10,'Tifany','Alfonso','1234567','4567890','Calle de tifany 2 prueba','tifany_1996@hotmail.com','4b6b68ceee5b1f99baf69021dddda8fd',1,'Bogotá D.C.','1986-12-24',5,1),(11,'Juan','Perez','9999999','17555','Cra x con y','juan@hotmail.com','202cb962ac59075b964b07152d234b70',1,'Bogota','1987-11-05',5,0);
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

-- Dump completed on 2015-11-18  1:16:13
