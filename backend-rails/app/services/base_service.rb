class BaseService
  attr_reader :user

  def authorize!(klass, method, entity)
    raise Pundit::NotAuthorizedError unless klass.new(user, entity).send(method)

    entity
  end
end
