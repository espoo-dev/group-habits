class DestroyerService < BaseService
  def resource_policy_action
    :destroy?
  end

  def call_action
    resource.destroy!
    nil
  end
end
