class FinderService < BaseService
  def resource_policy_action
    :index?
  end

  def call_action
    resource
  end
end
