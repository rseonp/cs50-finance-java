ALTER TABLE `users`
  CHANGE COLUMN `id` `uid` int(11) unsigned NOT NULL AUTO_INCREMENT;

CREATE TABLE `stock_holdings` (
  `uid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL,
  `shares_owned` int(11) NOT NULL,
  `symbol` varchar(255) NOT NULL,
  `portfolio_key` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `transactions` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `price` float NOT NULL,
  `shares` int(11) NOT NULL,
  `symbol` varchar(255) NOT NULL,
  `transaction_time` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  `stock_holding` int(11) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;