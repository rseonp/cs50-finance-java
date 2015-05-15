CREATE TABLE `users` (
  `hash` varchar(255) NOT NULL DEFAULT '',
  `username` varchar(50) NOT NULL DEFAULT '',
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;