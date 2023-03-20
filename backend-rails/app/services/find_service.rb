class FindService < BaseService
  def resource_policy_action
    :show?
  end

  def call_action
    resource
  end
end
