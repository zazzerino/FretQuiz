import * as React from 'react';
import { Link } from 'react-router-dom';

export function Navbar() {
  return (
    <div className='Navbar'>
      <nav>
        <ul>
          <li>
            <Link to='/'>Home</Link>
          </li>
          <li>
            <Link to='/login'>Login</Link>
          </li>
          <li>
            <Link to='/game'>Game</Link>
          </li>
          <li>
            <Link to='/games'>Games</Link>
          </li>
        </ul>
      </nav>
    </div>
  );
}
