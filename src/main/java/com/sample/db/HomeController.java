package com.sample.db;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * DDL
	 * CREATE TABLE `sample` (
	 *   `id` int(11) NOT NULL AUTO_INCREMENT,
	 *   `name` varchar(20) DEFAULT NULL,
	 *   PRIMARY KEY (`id`)
	 * ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8
	 * 
	 * DML
	 * insert into sample values(1,'test');
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		// データ取得テーブルは、当メソッドのコメント文のDDLとDMLを流して事前に用意して下さい。
		List<Map<String, Object>>  list = jdbcTemplate.queryForList("select * from sample");

		model.addAttribute("data", list.get(0).get("name") );
		return "home";
	}
	
}
