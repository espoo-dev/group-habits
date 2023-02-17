class BaseService
  attr_reader :user, :params

  def initialize(user:, params:)
    @user = user
    @params = params.dup
  end

  def authorize!(klass, method, entity)
    raise Pundit::NotAuthorizedError unless klass.new(user, entity).send(method)

    entity
  end

  def resource_id
    params[:id]
  end

  def params_with_user
    params.merge(user:)
  end
end
