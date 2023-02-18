class UpdaterService < BaseService
  def prepare_resource
    resource_class.find(resource_id)
  end

  def resource_policy_action
    :update?
  end

  def call_action
    resource.update!(params)
    resource
  end
end
