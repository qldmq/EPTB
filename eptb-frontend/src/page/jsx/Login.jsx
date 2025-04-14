import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../css/Auth.css';

const Login = () => {
  const [memberId, setMemberId] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/member/login', {
        memberId,
        password
      }, {
        withCredentials: true
      });

      // 로그인 성공 시 localStorage에 토큰 및 회원 아이디 저장
      localStorage.setItem('accessToken', response.data.accessToken);
      localStorage.setItem('memberId', response.data.memberId);

      alert('로그인 성공!');
      navigate('/');
      window.location.reload(); // 메인 상태 반영
    } catch (error) {
      const message = error?.response?.data?.message || '로그인에 실패했습니다.';
      alert(message);
    }
  };

  return (
    <div className="auth-container">
      <header className="auth-header">
        <div className="logo">
          <img src="/images/logo.png" alt="EPTB 로고" className="logo-img" />
        </div>
      </header>

      <main className="auth-form">
        <h2>로그인</h2>
        <form onSubmit={handleSubmit}>
          <div className="input-group">
            <label htmlFor="memberId">아이디</label>
            <input
              type="text"
              id="memberId"
              value={memberId}
              onChange={(e) => setMemberId(e.target.value)}
              placeholder="아이디를 입력하세요"
              required
            />
          </div>
          <div className="input-group">
            <label htmlFor="password">비밀번호</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="비밀번호를 입력하세요"
              required
            />
          </div>
          <button type="submit" className="auth-btn">로그인</button>
        </form>
        <p className="auth-link">
          계정이 없으신가요? <a href="/signup">회원가입</a>
        </p>

        <div className="social-login">
          <button className="kakao-login" onClick={() => window.location.href = '/auth/kakao'}>
            <img src="/images/kakao-icon.png" alt="카카오 로그인" />
            카카오 로그인
          </button>
          <button className="naver-login" onClick={() => window.location.href = '/auth/naver'}>
            <img src="/images/naver-icon.png" alt="네이버 로그인" />
            네이버 로그인
          </button>
        </div>
      </main>
    </div>
  );
};

export default Login;
