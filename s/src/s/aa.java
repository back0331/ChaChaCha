***ȸ������ �����......
1.table����

create table member1(
id varchar2(12) not null primary key,
passwd varchar2(12) not null,
name varchar2(10) not null,
jumin1 varchar2(6) not null,
jumin2 varchar2(7) not null,
email varchar2(30),
blog varchar2(50),
reg_date date not null,
zipcode varchar2(7),
address varchar2(1000)
)

2.JavaBean����

[LogonDataBean.java]
package logon;

import java.sql.Timestamp;

public class LogonDataBean {
	private String id;
	private String passwd;
	private String name;
	private String jumin1;
	private String jumin2;
	private String email; 
	private String blog;
	private Timestamp reg_date;
	private String zipcode;
	private String address;

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJumin1() {
		return jumin1;
	}
	public void setJumin1(String jumin1) {
		this.jumin1 = jumin1;
	}
	public String getJumin2() {
		return jumin2;
	}
	public void setJumin2(String jumin2) {
		this.jumin2 = jumin2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBlog() {
		return blog;
	}
	public void setBlog(String blog) {
		this.blog = blog;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	
	
	
}
----------------------------------------------------------
[ZipcodeBean.java]

package logon;

public class ZipcodeBean {
	private String zipcode;//�����ȣ
	private String area1;//
	private String area2;//
	private String area3;//
	private String area4;//
	
	public void setZipcode(String zipcode){
		this.zipcode=zipcode;
	}
	
	public void setArea1(String area1){
		this.area1=area1;
	}
	
	public void setArea2(String area2){
		this.area2=area2;
	}
	
	public void setArea3(String area3){
		this.area3=area3;
	}
	
	public void setArea4(String area4){
		this.area4=area4;
	}
	
	
	public String getZipcode(){
		return zipcode;
	}
	
	public String getArea1(){
		return area1;
	}
	public String getArea2(){
		return area2;
	}
	public String getArea3(){
		return area3;
	}
	public String getArea4(){
		return area4;
	}
}
------------------------------------------------------------------------------
[LogonDBBean.java]
package logon;

import java.sql.*;
import java.util.Vector;

public class LogonDBBean {//DB�� ���õ� ���� �ϴ� Ŭ����: DBBean, DAO
   
	private static LogonDBBean instance = new LogonDBBean();
   
	//LogonDBBean m = LogonDBBean.getInstance();
    public static LogonDBBean getInstance() {
        return instance;
    }
   
    private LogonDBBean() {}
   
    private Connection getConnection() throws Exception {
    	String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";        
    	return DriverManager.getConnection(jdbcDriver);
    }
     
    //inputPro.jsp
    public void insertMember(LogonDataBean member) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
       
        try {
            conn = getConnection();
 //DriverManager.getConnection(jdbc:apache:commons:dbcp:/pool);
            pstmt = conn.prepareStatement(
            "insert into MEMBERS values (?,?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPasswd());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getJumin1());
            pstmt.setString(5, member.getJumin2());
            pstmt.setString(6, member.getEmail());
            pstmt.setString(7, member.getBlog());
            pstmt.setTimestamp(8, member.getReg_date());
            pstmt.setString(9, member.getZipcode());
            pstmt.setString(10, member.getAddress());
            
            pstmt.executeUpdate();
        
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    }
    
    //loginPro.jsp
    public int userCheck(String id, String passwd) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;    
        ResultSet rs= null;
        String dbpasswd="";
        int x=-1;
        
        try {
            conn = getConnection();
           
            pstmt = conn.prepareStatement(
            "select passwd from MEMBERS where id = ?");
            pstmt.setString(1, id);
            rs= pstmt.executeQuery();

            if(rs.next()){
            	dbpasswd= rs.getString("passwd");
            	if(dbpasswd.equals(passwd))
            		x= 1; //���� ����
            	else
            		x= 0; //��й�ȣ Ʋ��
            }else
            	x= -1;//�ش� ���̵� ����

        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return x;
    }
    //confirmId.jsp
    public int confirmId(String id) throws Exception {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        int x=-1;//����� ��
       
        try {
            conn = getConnection();
           
            pstmt = conn.prepareStatement(
            "select id from MEMBERS where id = ?");
            pstmt.setString(1, id);
            rs= pstmt.executeQuery();

            if(rs.next())
            	x= 1; //�ش� ���̵� ����
            else
            	x= -1;//�ش� ���̵� ����
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return x;
    }

    //modifyForm.jsp
    public LogonDataBean getMember(String id) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LogonDataBean member=null;
        try {
            conn = getConnection();
           
            pstmt = conn.prepareStatement(
            "select * from MEMBERS where id = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                member = new LogonDataBean();
                member.setId(rs.getString("id"));
                member.setPasswd(rs.getString("passwd"));
                member.setName(rs.getString("name"));
                member.setJumin1(rs.getString("jumin1"));
                member.setJumin2(rs.getString("jumin2"));
                member.setEmail(rs.getString("email"));
                member.setBlog(rs.getString("blog"));
                member.setReg_date(rs.getTimestamp("reg_date"));    
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return member;
    }
    //modifyPro.jsp
    public void updateMember(LogonDataBean member) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
       
        try {
            conn = getConnection();
           
            pstmt = conn.prepareStatement(
"update MEMBERS set passwd=?,name=?,email=?,blog=?" +
" where id=?");
            pstmt.setString(1, member.getPasswd());
            pstmt.setString(2, member.getName());
            pstmt.setString(3, member.getEmail());
            pstmt.setString(4, member.getBlog());
            pstmt.setString(5, member.getId());
           
            pstmt.executeUpdate();
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    }
   
    public int deleteMember(String id, String passwd) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        String dbpasswd="";
        int x=-1;
        
        try {
        	conn = getConnection();

            pstmt = conn.prepareStatement(
            "select passwd from MEMBERS where id = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
           
            if(rs.next()){
            	dbpasswd= rs.getString("passwd");
            	if(dbpasswd.equals(passwd)){
            		pstmt = conn.prepareStatement(
                  "delete from MEMBERS where id=?");
                    pstmt.setString(1, id);
                    pstmt.executeUpdate();
                    x= 1; //ȸ��Ż�� ����
            	}else
            		x= 0; //��й�ȣ Ʋ��
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return x;
    }
    
    public Vector zipcodeRead(String area4)  {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Vector vecList = new Vector();
        
        try {
            con = getConnection();
            String strQuery = "select * from zipcode where area4 like '"+area4+"%'";
            pstmt = con.prepareStatement(strQuery);
            rs = pstmt.executeQuery();
            while(rs.next()){
                ZipcodeBean tempZipcode = new ZipcodeBean();
                tempZipcode.setZipcode(rs.getString("zipcode"));
                tempZipcode.setArea1(rs.getString("area1"));
                tempZipcode.setArea2(rs.getString("area2"));
                tempZipcode.setArea3(rs.getString("area3"));
                tempZipcode.setArea4(rs.getString("area4"));
                vecList.addElement(tempZipcode);
            }

        }catch(Exception ex) {
            System.out.println("Exception" + ex);
        }finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (con != null) try { con.close(); } catch(SQLException ex) {}
        }
        return vecList;
    }
}

----------------------------------------------------------------------------   //�����������.
[color.jsp]
<%
String bodyback_c="#e0ffff";
String back_c="#8fbc8f";
String title_c="#5f9ea0";
String value_c="#b0e0e6";
String bar="#778899";
%>
-----------------------------------------------------------------------------
[style.css]
BODY {
FONT-SIZE: 9pt; COLOR: black; LINE-HEIGHT: 160%; FONT-FAMILY: ����,verdana,tahoma
}
TD {
FONT-SIZE: 9pt; COLOR: black; LINE-HEIGHT: 160%; FONT-FAMILY: ����,verdana,tahoma
}
SELECT {
FONT-SIZE: 9pt; COLOR: black; LINE-HEIGHT: 160%; FONT-FAMILY: ����,verdana,tahoma
}
DIV {
FONT-SIZE: 9pt; COLOR: black; LINE-HEIGHT: 160%; FONT-FAMILY: ����,verdana,tahoma
}
FORM {
FONT-SIZE: 9pt; COLOR: black; LINE-HEIGHT: 160%; FONT-FAMILY: ����,verdana,tahoma
}
TEXTAREA {
BORDER-RIGHT: 1px solid #999999; BORDER-TOP: 1px solid #999999; FONT-SIZE: 9pt; BORDER-LEFT: 1px solid #999999 ; COLOR: BLACK; BORDER-BOTTOM: 1px solid #999999; FONT-FAMILY: ����,verdana; BACKGROUND-COLOR: white
}
INPUT {
BORDER-RIGHT: 1px solid #999999; BORDER-TOP: 1px solid #999999; FONT-SIZE: 9pt; BORDER-LEFT: 1px solid #999999; COLOR: BLACK; BORDER-BOTTOM: 1px solid #999999; FONT-FAMILY: ����,verdana; HEIGHT: 19px; BACKGROUND-COLOR: white
}

A:link {text-decoration:none;color:#696969}
A:hover{text-decoration:yes;color:#66CCFF}
A:visited {text-decoration:none;color:#330066}
----------------------------------------------------------------------
[main.jsp]
<%@ page  contentType="text/html; charset=utf-8"%>
<%@ include file="../view/color.jsp"%>
<html>
<head><title>�����Դϴ�..</title>
<link href="style.css" rel="stylesheet" type="text/css">

<%
try{
   if(session.getAttribute("memId")==null){%>//�α׿��� ���� �ʾ��� ���
<script>

function focusIt()
{     
   document.inform.id.focus();//���� �۾��ϰ� �ִ� ������ inform�̶�� ���� id�� Ŀ���� ������ ��
}

function checkIt()
{
   inputForm=eval("document.inform");
   if(!inputForm.id.value){
     alert("���̵� �Է��ϼ���..");
inputForm.id.focus();
return false;
   }
   if(!inputForm.passwd.value){
     alert("�н����带 �Է��ϼ���..");
inputForm.passwd.focus();
return false;
   }
}

</script>
</head>

<body onLoad="focusIt();" bgcolor="<%=bodyback_c%>">
  <table width=500 cellpadding="0" cellspacing="0"  align="center" border="1" >
      <tr>
       <td width="300" bgcolor="<%=bodyback_c%>" height="20">
       &nbsp;
       </td>
  
       <form name="inform" method="post" action="loginPro.jsp"  onSubmit="return checkIt();">

        <td bgcolor="<%=title_c%>"  width="100" align="right">���̵�</td>
        <td width="100" bgcolor="<%=value_c%>">
            <input type="text" name="id" size="15" maxlength="10"></td>
      </tr>
      <tr >
         <td rowspan="2" bgcolor="<%=bodyback_c%>" width="300" >�����Դϴ�.</td>
         <td bgcolor="<%=title_c%>"  width="100" align="right">�н�����</td>
         <td width="100" bgcolor="<%=value_c%>">
            <input type="password" name="passwd" size="15" maxlength="10"></td>
       </tr>
       <tr>
          <td colspan="3" bgcolor="<%=title_c%>"   align="center">
            <input type="submit" name="Submit" value="�α���">
            <input type="button"  value="ȸ������" onclick="javascript:window.location='inputForm.jsp'">
          </td></form></tr></table>
     <%}else{%>
       <table width=500 cellpadding="0" cellspacing="0"  align="center" border="1" >
         <tr>
           <td width="300" bgcolor="<%=bodyback_c%>" height="20">������</td>

           <td rowspan="3" bgcolor="<%=value_c%>" align="center">
             <%=session.getAttribute("memId")%>���� <br>
             �湮�ϼ̽��ϴ�
             <form  method="post" action="logout.jsp"> 
             <input type="submit"  value="�α׾ƿ�">
             <input type="button" value="ȸ����������" onclick="javascript:window.location='modify.jsp'">
             </form>
         </td>
        </tr>
       <tr >
         <td rowspan="2" bgcolor="<%=bodyback_c%>" width="300" >�����Դϴ�.</td>
      </tr>
     </table>
     <br>
<%}
}catch(NullPointerException e){}
%>
</body>
</html>
==========================================================
[inputForm.jsp]
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="color.jsp"%>
<html>
<head>
<title>ȸ������</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script>
   
    function checkIt() {
        var userinput = eval("document.userinput");
        
        if(!userinput.id.value) {
            alert("ID�� �Է��ϼ���");
            return false;
        }
       
        if(!userinput.passwd.value ) {
            alert("��й�ȣ�� �Է��ϼ���");
            return false;
        }
        if(userinput.passwd.value != userinput.passwd2.value)
        {
            alert("��й�ȣ�� �����ϰ� �Է��ϼ���");
            return false;
        }
      
        if(!userinput.name.value) {
            alert("����� �̸��� �Է��ϼ���");
            return false;
        }
        
        if(!userinput.jumin1.value  || !userinput.jumin2.value )
        {
            alert("�ֹε�Ϲ�ȣ�� �Է��ϼ���");
            return false;
        }
        return true;
    }

    // ���̵� �ߺ� ���θ� �Ǵ�
    function openConfirmid(userinput) {
        // ���̵� �Է��ߴ��� �˻�
        if (userinput.id.value == "") {
            alert("���̵� �Է��ϼ���");
            return;
        }
        // url�� ����� �Է� id�� �����մϴ�.
        url = "confirmId.jsp?id=" + userinput.id.value ;
       
        // ���ο� �����츦 ���ϴ�.
        open(url, "confirm", "toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300,height=200");
    }
    
    function zipCheck(){
    	
    	url="ZipCheck.jsp?check=y";
    	
    	window.open(url,"post","toolbar=no ,width=500 ,height=300,directories=no,status=yes,scrollbars=yes,menubar=no");
    }
</script>


<body bgcolor="<%=bodyback_c%>">

<form method="post" action="inputPro.jsp" name="userinput"  onSubmit="return checkIt()">
  <table width="600" border="1" cellspacing="0" cellpadding="3" align="center" >
    <tr>
    <td colspan="2" height="39" align="center" bgcolor="<%=value_c%>" >
       <font size="+1" ><b>ȸ������</b></font></td>
    </tr>
    <tr>
      <td width="200" bgcolor="<%=value_c%>"><b>���̵� �Է�</b></td>
      <td width="400" bgcolor="<%=value_c%>"> </td>
    </tr> 

    <tr>
      <td width="200"> ����� ID</td>
      <td width="400">
        <input type="text" name="id" size="10" maxlength="12">
        <input type="button" name="confirm_id" value="ID�ߺ�Ȯ��" OnClick="openConfirmid(this.form)">
      </td>
    </tr>
    <tr>
      <td width="200"> ��й�ȣ</td>
      <td width="400" >
        <input type="password" name="passwd" size="15" maxlength="12">
      </td>
    <tr> 
      <td width="200">��й�ȣ Ȯ��</td>
      <td width="400">
        <input type="password" name="passwd2" size="15" maxlength="12">
      </td>
    </tr>
   
    <tr>
      <td width="200" bgcolor="<%=value_c%>"><b>�������� �Է�</b></td>
      <td width="400" bgcolor="<%=value_c%>"> </td>
    <tr> 
    <tr>
      <td width="200">����� �̸�</td>
      <td width="400">
        <input type="text" name="name" size="15" maxlength="10">
      </td>
    </tr>
    <tr>
      <td width="200">�ֹε�Ϲ�ȣ</td>
      <td width="400">
        <input type="text" name="jumin1" size="7" maxlength="6">
        -<input type="text" name="jumin2" size="7" maxlength="7">
      </td>
    </tr>
    <tr>
      <td width="200">E-Mail</td>
      <td width="400">
        <input type="text" name="email" size="40" maxlength="30">
      </td>
    </tr>
    <tr>
      <td width="200"> Blog</td>
      <td width="400">
        <input type="text" name="blog" size="60" maxlength="50">
      </td>
    </tr>
    <tr> 
       <td width="200">�����ȣ</td>
       <td> <input type="text" name="zipcode" size="7" readonly>
               <input type="button" value="�����ȣã��" onClick="zipCheck()">
               �����ȣ�� �˻��ϼ���.</td>
          </tr>
    <tr>
    <tr> 
       <td>�ּ�</td>
       <td><input type="text" name="address" size="70">
       �ּҸ� ���� �ּ���.</td>
    </tr>
    <tr>
      <td colspan="2" align="center" bgcolor="<%=value_c%>">
          <input type="submit" name="confirm" value="��   ��" >
          <input type="reset" name="reset" value="�ٽ��Է�">
          <input type="button" value="���Ծ���" onclick="javascript:window.location='main.jsp'">
      </td>
    </tr>
  </table>
</form>
</body>
</html>
-----------------------------------------------------------------------------
[confirmId.jsp]
<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import = "logon.LogonDBBean" %>
<%@ include file="../view/color.jsp"%>
<html>
<head><title>ID �ߺ�Ȯ��</title>
<link href="style.css" rel="stylesheet" type="text/css">

<% request.setCharacterEncoding("utf-8");%>

<%
    String id = request.getParameter("id");
LogonDBBean manager = LogonDBBean.getInstance();
    int check= manager.confirmId(id);
 
%>



<body bgcolor="<%=bodyback_c%>">
<%
    if(check == 1) {
%>
<table width="270" border="0" cellspacing="0" cellpadding="5">
  <tr bgcolor="<%=title_c%>">
    <td height="39" ><%=id%>�̹� ������� ���̵��Դϴ�.</td>
  </tr>
</table>
<form name="checkForm" method="post" action="confirmId.jsp">
<table width="270" border="0" cellspacing="0" cellpadding="5">
  <tr>
    <td bgcolor="<%=value_c%>" align="center">
       �ٸ� ���̵� �����ϼ���.<p>
       <input type="text" size="10" maxlength="12" name="id">
       <input type="submit" value="ID�ߺ�Ȯ��">
    </td>
  </tr>
</table>
</form>
<%
    } else {
%>
<table width="270" border="0" cellspacing="0" cellpadding="5">
  <tr bgcolor="<%=title_c%>">
    <td align="center">
      <p>�Է��Ͻ� <%=id%> �� ����Ͻ� �� �ִ� ID�Դϴ�. </p>
      <input type="button" value="�ݱ�" onclick="setid()">
    </td>
  </tr>
</table>
<%
    }
%>
</body>
</html>
<script>

  function setid()
    {
    opener.document.userinput.id.value="<%=id%>";//opener:���ο� â�� ����.
self.close();
}

</script>
-----------------------------------------------------------------------------
[inputPro.jsp]
<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import = "logon.LogonDBBean" %>
<%@ page import = "java.sql.Timestamp" %>

<% request.setCharacterEncoding("utf-8");%>

<jsp:useBean id="member" class="logon.LogonDataBean">
    <jsp:setProperty name="member" property="*" />
</jsp:useBean>

<%
    member.setReg_date(new Timestamp(System.currentTimeMillis()) );
    LogonDBBean manager = LogonDBBean.getInstance();
    manager.insertMember(member);

    response.sendRedirect("loginForm.jsp");
%>
----------------------------------------------------------------------------
[loginForm.jsp]
<%@ page  contentType="text/html; charset=utf-8"%>
<%@ include file="../view/color.jsp"%>
<html>
<head><title>�α���</title>
<link href="style.css" rel="stylesheet" type="text/css">

   <script>

       function begin(){
         document.myform.id.focus();
       }
       function checkIt(){
         if(!document.myform.id.value){
           alert("�̸��� �Է����� �����̽��ϴ�.");
           document.myform.id.focus();
           return false;
         }
         if(!document.myform.passwd.value){
           alert("��й�ȣ�� �Է����� �����̽��ϴ�.");
           document.myform.passwd.focus();
           return false;
         }
         
       }
    
   </script>
</head>
<BODY onload="begin()" bgcolor="<%=bodyback_c%>">
<form name="myform" action="loginPro.jsp" method="post" onSubmit="return checkIt()">
<TABLE cellSpacing=1 cellPadding=1 width="260" border=1 align="center" >
 
  <TR height="30">
    <TD colspan="2" align="middle" bgcolor="<%=title_c%>"><STRONG>ȸ���α���</STRONG></TD></TR>
 
  <TR height="30">
    <TD width="110" bgcolor="<%=title_c%>" align=center>���̵�</TD>
    <TD width="150" bgcolor="<%=value_c%>" align=center>
       <INPUT type="text" name="id" size="15" maxlength="12"></TD></TR>
  <TR height="30">
    <TD width="110" bgcolor="<%=title_c%>" align=center>��й�ȣ</TD>
    <TD width="150" bgcolor="<%=value_c%>" align=center>
      <INPUT type=password name="passwd"  size="15" maxlength="12"></TD></TR>
  <TR height="30">
    <TD colspan="2" align="middle" bgcolor="<%=title_c%>" >
      <INPUT type=submit value="�α���">
      <INPUT type=reset value="�ٽ��Է�">
      <input type="button" value="ȸ������" onclick="javascript:window.location='inputForm.jsp'"></TD></TR>
</TABLE>
</form>

</BODY>
</HTML>
-----------------------------------------------------------------------------
[loginPro.jsp]
<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import = "logon.LogonDBBean" %>

<% request.setCharacterEncoding("utf-8");%>

<%
    String id = request.getParameter("id");
String passwd  = request.getParameter("passwd");

LogonDBBean manager = LogonDBBean.getInstance();
    int check= manager.userCheck(id,passwd);

if(check==1){
session.setAttribute("memId",id);
response.sendRedirect("main.jsp");
}else if(check==0){%>
<script>
  alert("��й�ȣ�� ���� �ʽ��ϴ�.");
      history.go(-1);
</script>
<% }else{ %>
<script>
  alert("���̵� ���� �ʽ��ϴ�..");
  history.go(-1);
</script>
<%} %>
-------------------------------------------------------------------------------
[logout.jsp]
<%
session.invalidate();
response.sendRedirect("main.jsp");
%>
--------------------------------------------------------------------------------
[modify.jsp]
<%@ page contentType="text/html; charset=utf-8"%>

  <body>
    <p>
    <a href="modifyForm.jsp">��������</a>
    <a href="deleteForm.jsp">Ż��</a>
    </p>
  </body>
</html>
-------------------------------------------------------------------------------
[modifyForm.jsp]
<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import = "logon.*" %>
<%@ include file="../view/color.jsp"%>

<html>
<head>
<title>ȸ����������</title>
<link href="style.css" rel="stylesheet" type="text/css">


<script>
   <!--
    function checkIt() {
        var userinput = eval("document.userinput");
              
        if(!userinput.passwd.value ) {
            alert("��й�ȣ�� �Է��ϼ���");
            return false;
        }
        if(userinput.passwd.value != userinput.passwd2.value)
        {
            alert("��й�ȣ�� �����ϰ� �Է��ϼ���");
            return false;
        }
      
        if(!userinput.username.value) {
            alert("����� �̸��� �Է��ϼ���");
            return false;
        }
        if(!userinput.jumin1.value  || !userinput.jumin2.value )
        {
            alert("�ֹε�Ϲ�ȣ�� �Է��ϼ���");
            return false;
        }
    }
-->
</script>

<%
    String id = (String)session.getAttribute("memId");
  
    LogonDBBean manager = LogonDBBean.getInstance();
    LogonDataBean c = manager.getMember(id);

try{
%>

<body bgcolor="<%=bodyback_c%>">
<form method="post" action="modifyPro.jsp" name="userinput" onsubmit="return checkIt()">

  <table width="600" border="1" cellspacing="0" cellpadding="3"  align="center">
    <tr >
      <td  colspan="2" height="39" bgcolor="<%=title_c%>" align="center">
     <font size="+1" ><b>ȸ�� ��������</b></font></td>
    </tr>
    <tr>
      <td colspan="2" class="normal" align="center">ȸ���� ������ �����մϴ�.</td>
    </tr>
     <tr>
      <td width="200" bgcolor="<%=value_c%>"><b>���̵� �Է�</b></td>
      <td width="400" bgcolor="<%=value_c%>"> </td>
    <tr> 

    <tr>
      <td  width="200"> ����� ID</td>
      <td  width="400"><%=c.getId()%></td>
    </tr>
   
     <tr>
      <td width="200"> ��й�ȣ</td>
      <td width="400">
        <input type="password" name="passwd" size="10" maxlength="10" value="<%=c.getPasswd()%>">
      </td>
    <tr> 
    <tr>
      <td  width="200" bgcolor="<%=value_c%>"><b>�������� �Է�</b></td>
      <td width="400" bgcolor="<%=value_c%>"> </td>
    <tr> 
    <tr>
      <td   width="200">����� �̸�</td>
      <td  width="400">
        <input type="text" name="name" size="15" maxlength="20" value="<%=c.getName()%>">
      </td>
    </tr>
    <tr>
      <td width="200">�ֹε�Ϲ�ȣ</td>
      <td width="400">
        <%=c.getJumin1()%>-<%=c.getJumin2()%>
      </td>
    </tr>
   <tr>
      <td width="200">E-Mail</td>
      <td width="400">
    <%if(c.getEmail()==null){%>
  <input type="text" name="email" size="40" maxlength="30" >
<%}else{%>
          <input type="text" name="email" size="40" maxlength="30" value="<%=c.getEmail()%>">
<%}%>
      </td>
    </tr>
    <tr>
      <td width="200">Blog</td>
      <td width="400">
    <%if(c.getBlog()==null){%>
  <input type="text" name="blog" size="60" maxlength="50" >
<%}else{%>
          <input type="text" name="blog" size="60" maxlength="50" value="<%=c.getBlog()%>">
<%}%>
      </td>
    </tr>     
    <tr>
      <td colspan="2" align="center" bgcolor="<%=value_c%>">
       <input type="submit" name="modify" value="��   ��" >
       <input type="button" value="��  ��" onclick="javascript:window.location='main.jsp'">     
      </td>
    </tr>
  </table>
</form>
</body>
<%}catch(Exception e){}%>
</html>
--------------------------------------------------------------------------------
[modifyPro.jsp]
<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import = "logon.LogonDBBean" %>
<%@ include file="../view/color.jsp"%>

<% request.setCharacterEncoding("utf-8");%>

<jsp:useBean id="member" class="logon.LogonDataBean">
    <jsp:setProperty name="member" property="*" />
</jsp:useBean>

<%
    String id = (String)session.getAttribute("memId");
member.setId(id);

LogonDBBean manager = LogonDBBean.getInstance();
    manager.updateMember(member);
%>
<link href="style.css" rel="stylesheet" type="text/css">

<table width="270" border="0" cellspacing="0" cellpadding="5" align="center">
  <tr bgcolor="<%=title_c%>">
    <td height="39"  align="center">
  <font size="+1" ><b>ȸ�������� �����Ǿ����ϴ�.</b></font></td>
  </tr>
  <tr>
    <td bgcolor="<%=value_c%>" align="center">
      <p>�Է��Ͻ� ������ ������ �Ϸ�Ǿ����ϴ�.</p>
    </td>
  </tr>
  <tr>
    <td bgcolor="<%=value_c%>" align="center">
      <form>
    <input type="button" value="��������" onclick="window.location='main.jsp'">
      </form>
      5���Ŀ� �������� �̵��մϴ�.<meta http-equiv="Refresh" content="5;url=main.jsp" >
    </td>
  </tr>
</table>
</body>
</html>
--------------------------------------------------------------------------------
[deleteForm.jsp]
<%@ page  contentType="text/html; charset=utf-8"%>
<%@ include file="../view/color.jsp"%>
<html>
<head><title>ȸ��Ż��</title>
<link href="style.css" rel="stylesheet" type="text/css">

   <script>
    
       function begin(){
         document.myform.passwd.focus();
       }

       function checkIt(){
  if(!document.myform.passwd.value){
           alert("��й�ȣ�� �Է����� �����̽��ϴ�.");
           document.myform.passwd.focus();
           return false;
         }
   }  
    
   </script>
</head>
<BODY onload="begin()" bgcolor="<%=bodyback_c%>">
<form name="myform" action="deletePro.jsp" method="post" onSubmit="return checkIt()">
<TABLE cellSpacing=1 cellPadding=1 width="260" border=1 align="center" >
 
  <TR height="30">
    <TD colspan="2" align="middle" bgcolor="<%=title_c%>">
  <font size="+1" ><b>ȸ�� Ż��</b></font></TD></TR>
 
  <TR height="30">
    <TD width="110" bgcolor="<%=value_c%>" align=center>��й�ȣ</TD>
    <TD width="150" align=center>
      <INPUT type=password name="passwd"  size="15" maxlength="12"></TD></TR>
  <TR height="30">
    <TD colspan="2" align="middle" bgcolor="<%=value_c%>" >
      <INPUT type=submit value="ȸ��Ż��">
      <input type="button" value="��  ��" onclick="javascript:window.location='main.jsp'"></TD></TR>
</TABLE>
</form>
</BODY>
</HTML>
--------------------------------------------------------------------------------
[deletePro.jsp]
<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import = "logon.LogonDBBean" %>
<%@ include file="../view/color.jsp"%>
<html>
<head>
<title>ȸ��Ż��</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<%
    String id = (String)session.getAttribute("memId");
String passwd  = request.getParameter("passwd");

LogonDBBean manager = LogonDBBean.getInstance();
    int check = manager.deleteMember(id,passwd);

if(check==1){
session.invalidate();
%>
<body bgcolor="<%=bodyback_c%>">
<form method="post" action="main.jsp" name="userinput" >
<table width="270" border="0" cellspacing="0" cellpadding="5" align="center">
  <tr bgcolor="<%=title_c%>">
    <td height="39" align="center">
  <font size="+1" ><b>ȸ�������� �����Ǿ����ϴ�.</b></font></td>
  </tr>
  <tr bgcolor="<%=value_c%>">
    <td align="center">
      <p>����.... �����մϴ�. �ȳ��� ������.</p>
      <meta http-equiv="Refresh" content="5;url=main.jsp" >
    </td>
  </tr>
  <tr bgcolor="<%=value_c%>">
    <td align="center">
      <input type="submit" value="Ȯ��">
    </td>
  </tr>
</table>
</form>
<%}else {%>
<script>
  alert("��й�ȣ�� ���� �ʽ��ϴ�.");
      history.go(-1);
</script>
<%}%>

</body>
</html>
===========================================================
[ZipCheck.jsp]

<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.*,logon.*" %>

<%
   request.setCharacterEncoding("utf-8");

   String check = request.getParameter("check");//n
   String area4 = request.getParameter("area4");//���̸�
   LogonDBBean manager = LogonDBBean.getInstance();  
   Vector zipcodeList = manager.zipcodeRead(area4);
   int totalList = zipcodeList.size();//
%>
<html>
<head>
<title>�����ȣ�˻�</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script>
	function dongCheck(){
		if (document.zipForm.area4.value == ""){
			alert("���̸��� �Է��ϼ���");
			document.zipForm.area4.focus();
			return;
		}
		document.zipForm.submit();
	}
	
function sendAddress(zipcode,area1,area2,area3,area4){
var address =area1+ " "+area2+ " " +area3+ " " +area4;
opener.document.userinput.zipcode.value=zipcode;
opener.document.userinput.address.value=address;
self.close();
	}
</script>
</head>
<body bgcolor="#FFFFCC">
<center>
<b>�����ȣ ã��</b>
<table>
<form name="zipForm" method="post" action="ZipCheck.jsp">
      <tr>
        <td><br>
          ���θ� �ּ� �Է� : <input name="area4" type="text">
          <input type="button" value="�˻�" onclick= "dongCheck();">
        </td>
      </tr>
     <input type="hidden" name="check" value="n">
    </form>
<%
if(check.equals("n")){
%>
<%
   if (zipcodeList.isEmpty()) {
%>
   <tr><td align="center"><br>�˻��� ����� �����ϴ�.</td></tr>
<% }
	else {
%>
<tr><td align="center"><br>
    �ذ˻� ��, �Ʒ� �����ȣ�� Ŭ���ϸ� �ڵ����� �Էµ˴ϴ�.</td></tr>
<%
	for (int i=0; i<totalList ;i++) {
		ZipcodeBean zipBean = 
				(ZipcodeBean)zipcodeList.elementAt(i);
		String tempZipcode =zipBean.getZipcode();
		String temptArea1 =zipBean.getArea1();
		String temptArea2 =zipBean.getArea2();
		String temptArea3 =zipBean.getArea3();
		String temptArea4 =zipBean.getArea4();
%>
<tr><td>
<a href="javascript:sendAddress(
'<%=tempZipcode%>','<%=temptArea1%>','<%=temptArea2%>',
'<%=temptArea3%>','<%=temptArea4%>')">
         <%=tempZipcode%>&nbsp;<%=temptArea1%>&nbsp;<%=temptArea2%>&nbsp;
<%=temptArea3%>&nbsp;<%=temptArea4%></a><br>
<%
}//for
}
%>
<%}%>
</td></tr>
<tr><td align="center"><br><a href="javascript:this.close();">�ݱ�</a><tr></td

>
</table>
</center>
</body>
</html>





����

 LogonDataBean
 LogonDBBean
 
 color.jsp
 style.css
 
 
 main.jsp =========> inputForm.jsp ===> confirmId.jsp(confirmId())
                                          inputPro.jsp(insertMember())
                                          
				 =========> loginForm.jsp
				 						  loginPro.jsp(userCheck())
				 						  logout.jsp
				 						  
                 =========> modify.jsp ===> modifyForm.jsp(getMember())
                 														modifyPro.jsp(updateMember())
                 														deleteForm.jsp
                 														deletePro.jsp(deleteMember())	                                            











