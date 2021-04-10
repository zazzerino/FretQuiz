import React from 'react';
import { AppBar, Toolbar, Typography, Link, IconButton, makeStyles, StylesProvider } from '@material-ui/core';
import { Link as RouterLink } from 'react-router-dom';

function NavbarLink(props: { path: string, text: string }) {
  const { path, text } = props;

  return (
    <div className="NavbarItem">
      <Link
        component={RouterLink}
        to={path}
        color="inherit"
      >
        {text}
      </Link>
    </div>
  );
}

const navLinks = [
  ['/', 'Home'],
  ['/login', 'Login'],
  ['/game', 'Game'],
];

const useStyles = makeStyles({
  root: {
    '& div': {
      display: 'flex',
    },
    '& a': {
      marginLeft: '2rem',
    },
  }
});

export function Navbar() {
  const styles = useStyles();

  return (
    <div className={styles.root}>
      <AppBar position="static">
        <Toolbar>
          <div className="Navbar-links">
            {navLinks.map((link, index) => {
              const [path, name] = link;
              return <NavbarLink key={index} path={path} text={name} />
            })}
          </div>
        </Toolbar>
      </AppBar>
    </div>
  );
}
