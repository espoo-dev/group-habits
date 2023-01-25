class ItemPolicy < ApplicationPolicy
  def index?
    true
  end

  class Scope < ApplicationPolicy::Scope
    def resolve
      Item.where(user:)
    end
  end
end
