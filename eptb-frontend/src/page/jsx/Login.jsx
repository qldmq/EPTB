import React, { useState } from 'react';
import '../css/Auth.css';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    // 로그인 처리 로직 추가
    console.log('로그인 시도:', email, password);
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
            <label htmlFor="email">이메일</label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="이메일을 입력하세요"
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

        {/* 소셜 로그인 버튼들 */}
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
