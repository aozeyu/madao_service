package com.madao.article;

import com.madao.annotation.EnableSpringCloudComponent;
import com.madao.config.BasicApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * SpringBoot主配置类只会扫描自己所在的包及其子包下面,需要通过@ComponentScan处理
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@EnableSpringCloudComponent
@EnableJpaRepositories("com.madao.article.dao")
@EnableElasticsearchRepositories("com.madao.article.search")
public class ArticleApplication extends BasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleApplication.class, args);
	}

}
