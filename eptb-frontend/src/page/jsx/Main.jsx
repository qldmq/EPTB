import React from 'react';
import '../css/Main.css';

const Main = () => {
  const products = [
    { id: 1, name: '셔츠', price: 29900, image: 'shirt.jpg' },
    { id: 2, name: '청바지', price: 49900, image: 'jeans.jpg' },
    { id: 3, name: '재킷', price: 79900, image: 'jacket.jpg' },
    { id: 4, name: '드레스', price: 89900, image: 'dress.jpg' },
  ];

  return (
    <div className="main-container">
      <header className="header">
        <div className="logo">
          <img src="/images/logo.png" alt="EPTB 로고" className="logo-img" />
        </div>
        <nav className="nav">
          <ul>
            <li><a href="/">홈</a></li>
            <li><a href="/about">소개</a></li>
            <li><a href="/contact">연락처</a></li>
            <li><a href="/login" className="auth-link">로그인</a></li>
            <li><a href="/signup" className="auth-link">회원가입</a></li>
          </ul>
        </nav>
      </header>

      <nav className="category-nav">
        <ul>
          <li><a href="/category/상의">상의</a></li>
          <li><a href="/category/하의">하의</a></li>
          <li><a href="/category/가방">가방</a></li>
          <li><a href="/category/액세서리">액세서리</a></li>
        </ul>
      </nav>

      <main className="product-list">
        <h2>상품 목록</h2>
        <div className="products">
          {products.map(product => (
            <div key={product.id} className="product-item">
              <img src={`/images/${product.image}`} alt={product.name} className="product-image" />
              <h3>{product.name}</h3>
              <p>₩{product.price.toLocaleString()}</p>
              <button className="add-to-cart">장바구니에 추가</button>
            </div>
          ))}
        </div>
      </main>

      <footer className="footer">
        <p>&copy; 2025 EPTB. All rights reserved.</p>
      </footer>
    </div>
  );
};

export default Main;
