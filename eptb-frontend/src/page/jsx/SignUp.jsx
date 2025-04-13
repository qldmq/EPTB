import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../css/SignUp.css';

const SignUp = () => {
  const [username, setUsername] = useState('');
  const [nickname, setNickname] = useState('');
  const [email, setEmail] = useState('');
  const [authCode, setAuthCode] = useState('');
  const [authCodeSent, setAuthCodeSent] = useState(false);
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const navigate = useNavigate();

  const handleSendCode = async () => {
    if (!email.trim()) {
      alert('이메일을 입력해주세요.');
      return;
    }

    try {
      const response = await axios.post('http://localhost:8080/mail/sendEmail', {
        email: email,
      });

      const message = response.data.message;

      if (message === "이미 존재하는 이메일입니다.") {
        alert(message);
        return;
      }

      setAuthCodeSent(true);
      alert('인증번호가 전송되었습니다.');
    } catch (error) {
      const message = error?.response?.data?.message || '이메일 전송 중 오류가 발생했습니다.';
      alert(message);
    }
  };

  const handleSignUp = async () => {
    if (!username.trim()) {
      alert('아이디를 입력해주세요.');
      return;
    }
    if (!nickname.trim()) {
      alert('닉네임을 입력해주세요.');
      return;
    }
    if (!email.trim()) {
      alert('이메일을 입력해주세요.');
      return;
    }
    if (!password.trim()) {
      alert('비밀번호를 입력해주세요.');
      return;
    }
    if (!confirmPassword.trim()) {
      alert('비밀번호 확인을 입력해주세요.');
      return;
    }
    if (password !== confirmPassword) {
      alert('비밀번호가 일치하지 않습니다.');
      return;
    }

    const requestData = {
      username,
      nickname,
      email,
      authCode,
      password,
    };

    console.log('회원가입 요청 데이터:', requestData);

    try {
      const response = await axios.post('http://localhost:8080/member/signup', requestData, {
        withCredentials: true,
      });

      alert('회원가입이 완료되었습니다!');
      navigate('/');
    } catch (error) {
      
      console.error('회원가입 에러:', error);

      const message = error?.response?.data?.message || '회원가입에 실패했습니다.';
      alert(message);
    }
  };

  return (
    <div className="signup-container">
      <h2>회원가입</h2>
      <div className="signup-form">
        <label htmlFor="username">아이디</label>
        <input
          id="username"
          type="text"
          placeholder="아이디"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />

        <label htmlFor="nickname">닉네임</label>
        <input
          id="nickname"
          type="text"
          placeholder="닉네임"
          value={nickname}
          onChange={(e) => setNickname(e.target.value)}
        />

        <label htmlFor="email">이메일</label>
        <div className="email-verification">
          <input
            id="email"
            type="email"
            placeholder="이메일"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <button className="send-code-btn" onClick={handleSendCode}>
            인증번호 전송
          </button>
        </div>

        {authCodeSent && (
          <>
            <label htmlFor="authCode">인증번호</label>
            <input
              id="authCode"
              type="text"
              placeholder="인증번호 입력"
              value={authCode}
              onChange={(e) => setAuthCode(e.target.value)}
            />
          </>
        )}

        <label htmlFor="password">비밀번호</label>
        <input
          id="password"
          type="password"
          placeholder="비밀번호"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />

        <label htmlFor="confirmPassword">비밀번호 확인</label>
        <input
          id="confirmPassword"
          type="password"
          placeholder="비밀번호 확인"
          value={confirmPassword}
          onChange={(e) => setConfirmPassword(e.target.value)}
        />

        <button className="signup-button" onClick={handleSignUp}>
          회원가입
        </button>
      </div>
    </div>
  );
};

export default SignUp;
