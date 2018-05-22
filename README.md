# Anduin UI Guide
How User Interface should be built at Anduin.

## Getting Started

### 1. Install dependencies
These are necessary packages for local development. You only need to install them once.

#### 1.1. Yarn
Although this is a Scala project, we still use [Yarn](https://yarnpkg.com/en/) for local development stuffs, such as running local web server or executing bash scripts. Please follow [Yarn's official guide](https://yarnpkg.com/en/docs/install) to install.

#### 1.2 Express JS
We use Node.js [Express](https://expressjs.com) for a simple local web server. It is recommended to install it locally by running `yarn install` inside `scripts/dev-serve` folder:

```
➜  ui-guide git:(master) cd scripts/dev-serve
➜  dev-serve git:(master) yarn install
yarn install v1.6.0
[1/4] 🔍  Resolving packages…
[2/4] 🚚  Fetching packages…
[3/4] 🔗  Linking dependencies…
[4/4] 📃  Building fresh packages…
✨  Done in 0.65s.
```

#### 1.3. Sass
Like `stargazer`, we use [Sass](https://sass-lang.com) to process our stylesheets. Please follow [Sass’s official guide](https://sass-lang.com/install) to install.

## Link with `stargazer`
When there are changes in these files, you don’t need to re-link them. However, when files (i.e. components) are added
