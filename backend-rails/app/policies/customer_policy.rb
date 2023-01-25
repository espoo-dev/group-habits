class CustomerPolicy < ApplicationPolicy
  def index?
    true
  end

  class Scope < ApplicationPolicy::Scope
    def resolve
      Customer.where(user:)
    end
  end
end
