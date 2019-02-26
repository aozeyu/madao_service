package com.youyd.article.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.article.pojo.Answers;
import com.youyd.article.service.AnswersService;
import com.youyd.pojo.Result;
import com.youyd.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 问题答案
 * @author: LGG
 * @create: 2019-02-26
 **/

@RestController
@RequestMapping("/answers")
public class AnswersController {

	@Autowired
	private AnswersService answersService;


	/**
	 * 查询全部数据
	 *
	 * @return
	 */
	@GetMapping
	public Result findAnswersByCondition(Answers answers) {
		IPage<Answers> byCondition = answersService.findAnswersByCondition(answers);
		return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), byCondition);
	}

	/**
	 * 根据ID查询
	 *
	 * @param id ID
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public Result findAnswersByPrimaryKey(@PathVariable String id) {
		Answers result = answersService.findAnswersByPrimaryKey(id);
		return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg(), result);
	}


	/**
	 * 增加
	 *
	 * @param answers
	 */
	@PostMapping
	public Result insertAnswers(Answers answers) {
		answersService.insertAnswers(answers);
		return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}

	/**
	 * 修改
	 *
	 * @param answers
	 */
	@PutMapping()
	public Result updateByAnswersSelective(Answers answers) {
		boolean result = answersService.updateByAnswersSelective(answers);
		return new Result(result, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}

	/**
	 * 删除
	 *
	 * @param answersIds
	 */
	@DeleteMapping()
	public Result deleteByPrimaryKey(@RequestBody List answersIds) {
		answersService.deleteByPrimaryKey(answersIds);
		return new Result(true, StatusCode.OK.getCode(), StatusCode.OK.getMsg());
	}

}