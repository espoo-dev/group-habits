import { classNames } from "./strings"

describe('when any two params is passed it should join the strings', () => {
  it('should join the strings', () => {
    expect(classNames('foo', 'bar')).toEqual('foo bar')
  })

  describe('when have a boolean param', () => {
    it('should join the strings if true', () => {
      expect(classNames('foo', true && 'bar')).toEqual('foo bar')
    })

    it('shouldn\'t join the strings if false', () => {
      expect(classNames('foo', false && 'bar')).toEqual('foo')
    })
  })
})
