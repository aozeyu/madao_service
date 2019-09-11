package com.youyd.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.annotation.OptLog;
import com.youyd.constant.CommonConst;
import com.youyd.enums.StatusEnum;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.user.Role;
import com.youyd.pojo.user.User;
import com.youyd.user.service.RoleService;
import com.youyd.utils.JsonData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色管理
 * @author : LGG
 * @create : 2018-12-23
 **/

@Api(tags = "角色")
@RestController
@RequestMapping(value = "/su/role")
public class RoleController {

	private final RoleService roleService;

	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * 条件查询角色
	 * @param role 查询参数
	 * @return JsonData
	 */
	@GetMapping
	public JsonData findRuleByCondition(Role role, QueryVO queryVO ) {
		IPage<Role> ruleData = roleService.findRuleByCondition(role,queryVO);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), ruleData);
	}

	/**
	 * 查询当前用户关联的角色
	 * @param role 查询参数
	 * @return JsonData
	 */
	@GetMapping("/user")
	public JsonData fetchUsersList(Role role ) {
		List<User> ruleData = roleService.findUsersOfRole(role);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), ruleData);
	}

	/**
	 * 根据角色id查询匹配的资源
	 * @param roleId 角色id
	 * @return JsonData
	 */
	@GetMapping("/{roleId}")
	public JsonData findRoleById(@PathVariable String roleId) {
		Role ruleData = roleService.findRoleById(roleId);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), ruleData);
	}

	/**
	 * 添加一个角色
	 * @param role 角色实体
	 * @return JsonData
	 */
	@PostMapping
	@OptLog(operationType= CommonConst.MODIFY,operationName="添加一个角色")
	public JsonData insertSelective(@RequestBody @Valid Role role) {
		boolean state = roleService.insertSelective(role);
		return new JsonData(state, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 更新角色
	 * @param role 角色实体
	 * @return JsonData
	 */
	@PutMapping()
	@OptLog(operationType= CommonConst.MODIFY,operationName="更新角色")
	public JsonData updateByPrimaryKey(@RequestBody @Valid Role role) {
		boolean state = roleService.updateByPrimaryKey(role);
		return new JsonData(state, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 删除角色
	 * @param roleId 角色id数组
	 * @return JsonData
	 */
	@DeleteMapping
	@OptLog(operationType= CommonConst.DELETE,operationName="删除角色")
	public JsonData deleteByIds(@RequestBody List<String> roleId) {
		boolean state = roleService.deleteByIds(roleId);
		return new JsonData(state, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}
}