class UpdaterService < BaseService
  def resource_policy_action
    :update?
  end

  def call_action
    resource.update!(params)
    resource
  end
end
