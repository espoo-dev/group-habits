class CustomerPolicy < ApplicationPolicy
  class Scope < ApplicationPolicy::Scope
    def resolve
      Customer.where(user:)
    end
  end
end
