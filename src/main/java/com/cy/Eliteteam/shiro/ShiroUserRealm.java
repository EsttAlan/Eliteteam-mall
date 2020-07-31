package com.cy.Eliteteam.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.mall.goodssimple.dao.EliteteamDao;
import com.cy.mall.goodssimple.pojo.Eliteteam;

@Service
public class ShiroUserRealm extends AuthorizingRealm {
	@Autowired
	private EliteteamDao eliteteamDao;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		String password = new String(upToken.getPassword());
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
			throw new AuthenticationException("用户名/密码不能为空");

		Eliteteam user = eliteteamDao.findUserByUserName(username);
		if (user == null)
			throw new UnknownAccountException("账户不存在");

		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt,
				getName());

		return info;

	}

	@Override
	public CredentialsMatcher getCredentialsMatcher() {
		HashedCredentialsMatcher hMatcher = new HashedCredentialsMatcher();
		hMatcher.setHashAlgorithmName("MD5");
		hMatcher.setHashIterations(1);
		return hMatcher;

	}
}
