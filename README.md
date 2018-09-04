# Anduin Design
Following is local development guide for engineers. For a user-friendly Welcome, please see at home page: [Anduin UI Guide](https://anduintransaction.github.io/design/).

## 1. Install dependencies
These are necessary packages for local development. You only need to install them once.

### 1.1. Yarn
Although this is a Scala project, we still use [Yarn](https://yarnpkg.com/en/) for local development stuffs, such as running local web server or executing bash scripts. Please follow [Yarn's installation guide](https://yarnpkg.com/en/docs/install).

### 1.2 Express JS
We use Node.js’s [Express](https://expressjs.com) for a simple local web server. It is recommended to install Express locally by running `yarn install` inside `scripts/dev-serve` folder:

```
➜  design: cd scripts/dev-serve
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

Then run `yarn run link` to link necessary stuffs from `stargazer`. In first run, it will ask for the path of your local `stargazer`, so it’s good to copy that path beforehand:
```
➜  design: yarn run link
yarn run v1.6.0
Please enter the absolute path to stargazer on your local:
/Users/thien/Code/anduin/stargazer
[info] Linking Config: .scalafmt.conf
[info] Linking CSS: Tachyons ...
...
[success] Successfully linked
```

### When to link
Thank to symlink, changes on linked files in `stargazer` folder will be reflected in `design` folder so you won’t need to re-link them when there are changes, including manual authoring or pulling latest `master`. You still need to re-compile though.

However, our current approach is to not link the whole package but only a set of revised files only. For example, the `anduin.component` package does not available fully in `design`, but only some upgraded components like `anduin.component.button` does.

This is done via a [manual list inside `link` script](https://github.com/anduintransaction/design/blob/master/scripts/link.sh#L55). Therefore, when there is new component added, it is necessary to  `yarn run link` again. In the near future, when most components are revised, we will link the whole packages and you won’t need to re-link anymore.

In the far future, when most components are stable, we will put their source and documentation together.
