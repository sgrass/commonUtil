<%@ page contentType="text/html; charset=gb2312" errorPage="" %>
<%
	String username = request.getParameter("username");
	if("educhina".equals(username)) out.print("�û����Ѿ���ע�ᣬ�����һ���û�����");
	else out.print("�û�����δ��ʹ�ã������Լ�����");
%>