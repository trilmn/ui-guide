# Anduin UI Guide
Following is local development guide for engineers. For a user-friendly Welcome, please see at home page: [Anduin UI Guide](https://anduintransaction.github.io/ui-guide/).

## 1. Install dependencies
These are necessary packages for local development. You only need to install them once.

### 1.1. Yarn
Although this is a Scala project, we still use [Yarn](https://yarnpkg.com/en/) for local development stuffs, such as running local web server or executing bash scripts. Please follow [Yarn's installation guide](https://yarnpkg.com/en/docs/install).

### 1.2 Express JS
We use Node.js’s [Express](https://expressjs.com) for a simple local web server. It is recommended to install Express locally by running `yarn install` inside `scripts/dev-serve` folder:

```
➜  ui-guide: cd scripts/dev-serve
➜  dev-serve: yarn install
yarn install v1.6.0
[1/4] 🔍  Resolving packages…
[2/4] 🚚  Fetching packages…
[3/4] 🔗  Linking dependencies…
[4/4] 📃  Building fresh packages…
✨  Done in 0.65s.
```

### 1.3. Sass
Like `stargazer`, we use [Sass](https://sass-lang.com) to process our stylesheets. Please follow [Sass’s installation guide](https://sass-lang.com/install).

## 2. Link with `stargazer`
This repo only contains the source of documentation. It uses `anduin.component`, `anduin.style` and some other stuffs by [symlink](https://en.wikipedia.org/wiki/Symbolic_link) them from your local copy of  `stargazer` repo.

So first, please ensure you have forked [anduintransaction/stargazer](https://github.com/anduintransaction/stargazer/) to your Github’s account and cloned it to your local:
```
➜  anduin: git clone https://github.com/<your-account>/stargazer
```

Then run `yarn run link` to link necessary stuffs from `stargazer`. In first run, it will ask for the path of your local `stargazer`, so it’s good to copy that beforehand:
```
➜  ui-guide: yarn run link
yarn run v1.6.0
Please enter the absolute path to stargazer on your local:
/Users/thien/Code/anduin/stargazer
[info] Linking Config: .scalafmt.conf
...
[success] Successfully linked
```

