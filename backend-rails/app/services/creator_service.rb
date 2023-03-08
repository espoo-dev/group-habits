class CreatorService < BaseService
  def resource_policy_action
    :create?
  end

  def call_action
    resource.save!
    resource
  end
end
