import React from 'react';
import { AppBar, Toolbar, Link, makeStyles } from '@material-ui/core';
import { Link as RouterLink } from 'react-router-dom';

interface NavbarLinkProps {
  path: string,
  text: string,
}

function NavbarLink(props: NavbarLinkProps) {
  return (
    <Link
      component={RouterLink}
      to={props.path}
      color="inherit"
    >
      {props.text}
    </Link>
  );
}

const navLinks = [
  ['/', 'Home'],
  ['/login', 'Login'],
  ['/game', 'Game'],
];

const useStyles = makeStyles({
  list: {
    display: 'flex',
  },
  listItem: {
    listStyle: 'none',
    marginRight: '2rem',
  }
});

export function Navbar() {
  const styles = useStyles();

  return (
    <AppBar position="static">
      <Toolbar>
        <ul className={styles.list}>
          {navLinks.map((link, index) => {
            const [path, name] = link;
            return (
              <li key={index} className={styles.listItem}>
                <NavbarLink path={path} text={name} />
              </li>
            );
          })}
        </ul>
      </Toolbar>
    </AppBar>
  );
}
