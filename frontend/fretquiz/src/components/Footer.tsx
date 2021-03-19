import * as React from 'react';
import { useSelector } from 'react-redux';
import { selectUser } from '../features/user/userSlice';

export function Footer() {
  const user = useSelector(selectUser);
  const { id, name } = user;

  return (
    <div className="Footer">
        <p>{`id: ${id}, name: ${name}`}</p>
    </div>
  );
}
