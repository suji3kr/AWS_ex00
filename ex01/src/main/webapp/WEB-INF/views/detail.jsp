<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Detail</title>
</head>
<body>
    <h2>Member Details</h2>
    <table>
        <tr>
            <th>ID</th>
            <td>${member.id}</td>
        </tr>
        <tr>
            <th>Email</th>
            <td>${member.memberEmail}</td>
        </tr>
        <tr>
            <th>Password</th>
            <td>${member.memberPassword}</td>
        </tr>
        <tr>
            <th>Name</th>
            <td>${member.memberName}</td>
        </tr>
        <tr>
            <th>Age</th>
            <td>${member.memberAge}</td>
        </tr>
        <tr>
            <th>Mobile</th>
            <td>${member.memberMobile}</td>
        </tr>
    </table>
    <br/>
    <!-- 수정 버튼 -->
    <button onclick="goToUpdate()">수정</button>
    <!-- 삭제 버튼 -->
    <button onclick="deleteMember()">삭제</button>
    <!-- 목록으로 버튼 -->
    <button onclick="goToList()">목록</button>

    <script>
        // 수정 페이지로 이동하는 함수
        const goToUpdate = function() {
        	// 회원 식별자를 안보내도 됨. 컨트롤러 핸들러에서 세션 사용자 조회.
            location.href = `/ex01/member/update`;
        };

        // 회원 삭제를 처리하는 함수
        const deleteMember = function() {
            const confirmation = confirm("회원 정보를 삭제하시겠습니까?");
            if (confirmation) {
                location.href = '/ex01/member/delete?id=${member.id}';
            }
        };

        // 목록 페이지로 이동하는 함수
        const goToList = function() {
            location.href = '/ex01/member/';
        };
    </script>
</body>
</html>
