class BaseService
  attr_reader :user, :params, :resource

  def initialize(user:, params:)
    @user = user
    @params = params.dup
  end

  def call
    @resource = prepare_resource

    authorize!(resource_policy_class, resource_policy_action, resource)

    call_action
  end

  protected

  def resource_id
    params[:id]
  end

  def authorize!(klass, method, entity)
    raise Pundit::NotAuthorizedError unless klass.new(user, entity).send(method)

    entity
  end

  def params_with_user
    params.merge(user:)
  end

  private

  def resource_policy_class
    Pundit::PolicyFinder.new(resource).policy
  end
end
