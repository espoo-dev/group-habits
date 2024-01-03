export const classNames = (...classes: Array<string | boolean>) => {
  return classes.filter(Boolean).join(' ')
}
