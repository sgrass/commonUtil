<%@ page language="java" contentType="text/html; charset=GBK"%>
<html>
  <head>
    <title>aaa</title>
    
    <script type="text/javascript"  language="javascript">
			var http_request = false;
			function send_request(url) {//��ʼ����ָ������������������ĺ���
				http_request = false;
				//��ʼ��ʼ��XMLHttpRequest����
				if(window.XMLHttpRequest) { //Mozilla �����
					http_request = new XMLHttpRequest();
					if (http_request.overrideMimeType) {//����MiME���
						http_request.overrideMimeType("text/xml");
					}
				}
				else if (window.ActiveXObject) { // IE�����
					try {
						http_request = new ActiveXObject("Msxml2.XMLHTTP");
					} catch (e) {
						try {
							http_request = new ActiveXObject("Microsoft.XMLHTTP");
						} catch (e) {}
					}
				}
				if (!http_request) { // �쳣����������ʵ��ʧ��
					window.alert("���ܴ���XMLHttpRequest����ʵ��.");
					return false;
				}
				http_request.onreadystatechange = processRequest;
				// ȷ����������ķ�ʽ��URL�Լ��Ƿ�ͬ��ִ���¶δ���
				http_request.open("POST", url, true);
				http_request.send(null);
			}
			// ��������Ϣ�ĺ���
	    function processRequest() {
        if (http_request.readyState == 4) { // �ж϶���״̬
           if (http_request.status == 200) { // ��Ϣ�Ѿ��ɹ����أ���ʼ������Ϣ
               alert(http_request.responseText);
           } else { //ҳ�治����
               alert("���������ҳ�����쳣��");
           }
        }
	    }
	    
	    function userCheck() {
				var f = document.form1;
				var username = f.username.value;
				if(username=="") {
					window.alert("�û�������Ϊ�ա�");
					f.username.focus();
					return false;
				}
				else {
					send_request('check.jsp?username='+username);
				}
			}
	    
		</script>
  </head>
  
  <body>
  	<form name="form1" action="" method="post" onsubmit="return userCheck()">
		  �û�����<input type="text" name="username" value="">&nbsp;
		  <input type="button" name="check" value="Ψһ�Լ��" onClick="userCheck()">
		  <input type="submit" name="submit" value="�ύ">
		</form>
  </body>
</html>
