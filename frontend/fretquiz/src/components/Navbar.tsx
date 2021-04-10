import React from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css';

export function Navbar() {
  return (
    <div className='Navbar'>
      <nav>
        <ul>
          <li>
            <Link to='/'>Home</Link>
          </li>
          <li>
            <Link to='/game'>Game</Link>
          </li>
          <li>
            <Link to='/login'>Login</Link>
          </li>
        </ul>
      </nav>
    </div>
  );
}
